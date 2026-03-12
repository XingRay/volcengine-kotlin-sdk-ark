package com.volcengine.ark.runtime.interceptor

import com.fasterxml.jackson.core.type.TypeReference

/**
 * 加密拦截器 - 处理请求加密和响应解密
 */
class EncryptionInterceptor(private val apiKey: String?, private val baseUrl: String?) : Interceptor {
    private val mapper: ObjectMapper = ObjectMapper()

    /**
     * 拦截器主入口 - 处理请求加密和响应解密
     */
    @Throws(IOException::class)
    fun intercept(chain: Chain): Response {
        val request: Request = chain.request()

        val is_encrypt: String? = request.headers().get("x-is-encrypted")

        if (!"true".equals(is_encrypt)) {
            return chain.proceed(request)
        }

        val originalBody: RequestBody? = request.body()
        if (originalBody == null) {
            return chain.proceed(request)
        }

        val requestBodyJson: Map<String?, Object?> = parseRequestBody(originalBody)
        val model: String? = requestBodyJson.get("model").toString()


        return proceedWithEncryption(chain, request, requestBodyJson, model)
    }

    @Throws(IOException::class)
    private fun proceedWithEncryption(chain: Chain, request: Request, requestBodyJson: Map<String?, Object?>, model: String?): Response {
        val certInfo: CertificateManager.ServerCertificateInfo = getServerCertificate(this.apiKey, this.baseUrl, model)
        if (certInfo == null) {
            throw IOException("Failed to get server certificate for encryption")
        }
        val sessionData: SessionData
        try {
            sessionData = KeyAgreementUtil.generateEciesKeyPair(certInfo.getPublicKey())
        } catch (e: GeneralSecurityException) {
            throw RuntimeException(e)
        }
        val e2eKey: ByteArray? = sessionData.getCryptoKey()
        val e2eNonce: ByteArray? = sessionData.getCryptoNonce()
        val sessionToken: String? = sessionData.getSessionToken()
        val encryptedBody: RequestBody = encryptRequestBody(requestBodyJson, e2eKey, e2eNonce)

        val requestBuilder: Request.Builder = request.newBuilder()
            .method(request.method(), encryptedBody)

        addAiccEncryptionHeader(requestBuilder, certInfo)

        requestBuilder.addHeader("X-Session-Token", sessionToken)
        val encryptedRequest: Request? = requestBuilder.build()
        val originalResponse: Response = chain.proceed(encryptedRequest)

        if (!originalResponse.isSuccessful()) {
            return handleErrorResponse(originalResponse)
        }

        return decryptResponse(e2eKey, e2eNonce, originalResponse)
    }

    /**
     * 解析请求体为Map对象
     */
    @Throws(IOException::class)
    private fun parseRequestBody(body: RequestBody): Map<String?, Object?> {
        val buffer: Buffer = Buffer()
        body.writeTo(buffer)
        val requestBodyStr: String? = buffer.readString(StandardCharsets.UTF_8)
        return mapper.readValue(requestBodyStr, object : TypeReference<Map<String?, Object?>?>() {
        })
    }

    /**
     * 加密请求体内容
     */
    @Throws(IOException::class)
    private fun encryptRequestBody(requestBodyJson: Map<String?, Object?>, e2eKey: ByteArray?, e2eNonce: ByteArray?): RequestBody {
        try {
            val messagesObj: Object? = requestBodyJson.get("messages")
            if (messagesObj is List) {
                val messagesList = messagesObj as List<*>
                val processedMessages: List<Map<String?, Object?>?> = ArrayList()

                for (message in messagesList) {
                    if (message is Map) {
                        @SuppressWarnings("unchecked") val messageMap: Map<String?, Object?> = message as Map<String?, Object?>
                        processedMessages.add(processMessage(messageMap, e2eKey, e2eNonce))
                    }
                }
                requestBodyJson.put("messages", processedMessages)
            }

            val modifiedRequestBodyStr: String? = mapper.writeValueAsString(requestBodyJson)
            return RequestBody.create(MediaType.get("application/json"), modifiedRequestBodyStr)
        } catch (e: Exception) {
            throw IOException("Failed to process request body", e)
        }
    }

    /**
     * 处理单条消息
     */
    @Throws(IOException::class)
    private fun processMessage(message: Map<String?, Object?>, e2eKey: ByteArray?, e2eNonce: ByteArray?): Map<String?, Object?> {
        val content: Object? = message.get("content")
        if (content != null) {
            message.put("content", processMessageContent(content, e2eKey, e2eNonce))
        }
        return message
    }

    /**
     * 处理消息内容
     */
    @Throws(IOException::class)
    private fun processMessageContent(content: Object, e2eKey: ByteArray?, e2eNonce: ByteArray?): Object {
        if (content is String) {
            return encryptStringWithKey(e2eKey, e2eNonce, content as String?)
        } else if (content is Iterable) {
            val processedParts: List<Object?> = ArrayList()
            for (part in (content as Iterable<*>?)!!) {
                if (part is Map) {
                    @SuppressWarnings("unchecked") val partMap: Map<String?, Object?> = part as Map<String?, Object?>
                    processedParts.add(processContentPart(partMap, e2eKey, e2eNonce))
                } else {
                    throw IOException("encryption is not supported for content type " + part.getClass().getSimpleName())
                }
            }
            return processedParts
        } else {
            throw IOException("encryption is not supported for content type " + content.getClass().getSimpleName())
        }
    }

    /**
     * 处理内容部分
     */
    @Throws(IOException::class)
    private fun processContentPart(part: Map<String?, Object?>, e2eKey: ByteArray?, e2eNonce: ByteArray?): Map<String?, Object?> {
        val type = part.get("type").toString()

        when (type) {
            "text" -> part.put("text", encryptStringWithKey(e2eKey, e2eNonce, part.get("text").toString()))
            "image_url" -> {
                @SuppressWarnings("unchecked") val imageUrl: Map<String?, Object?> = part.get("image_url") as Map<String?, Object?>
                processImageUrl(imageUrl, e2eKey, e2eNonce)
            }

            else -> throw IOException("encryption is not supported for content type " + type)
        }

        return part
    }

    /**
     * 处理图片URL
     */
    @Throws(IOException::class)
    private fun processImageUrl(imageUrl: Map<String?, Object?>, e2eKey: ByteArray?, e2eNonce: ByteArray?) {
        val url = imageUrl.get("url").toString()
        try {
            val uri: URI = URI(url)
            val scheme: String? = uri.getScheme()
            if ("data".equals(scheme)) {
                imageUrl.put("url", encryptStringWithKey(e2eKey, e2eNonce, url))
            } else if ("http".equals(scheme) || "https".equals(scheme)) {
                System.err.println("WARNING: encryption is not supported for image url, please use base64 image if you want encryption")
            } else {
                throw IOException("encryption is not supported for image url scheme " + scheme)
            }
        } catch (e: URISyntaxException) {
            if (url.startsWith("data:")) {
                imageUrl.put("url", encryptStringWithKey(e2eKey, e2eNonce, url))
            } else {
                throw IOException("Invalid image URL format: " + url, e)
            }
        }
    }

    /**
     * 添加AICC加密信息头
     */
    @Throws(IOException::class)
    private fun addAiccEncryptionHeader(requestBuilder: Request.Builder, certInfo: CertificateManager.ServerCertificateInfo) {
        val volcArkEncryption: String? = System.getenv("VOLC_ARK_ENCRYPTION")
        val aiccEnabled = "AICC".equals(volcArkEncryption)
        if (aiccEnabled) {
            val info: Map<String?, String?> = HashMap()
            info.put("Version", "AICCv0.1")
            info.put("RingID", certInfo.getRingId())
            info.put("KeyID", certInfo.getKeyId())
            val infoJson: String? = mapper.writeValueAsString(info)
            requestBuilder.addHeader("X-Encrypt-Info", infoJson)
        }
    }

    /**
     * 处理错误响应
     */
    @Throws(IOException::class)
    private fun handleErrorResponse(response: Response): Response {
        val errorBody: ResponseBody? = response.body()
        if (errorBody != null) {
            val errorResponseStr: String? = errorBody.string()
            var contentType: MediaType? = errorBody.contentType()
            if (contentType == null) {
                contentType = MediaType.get("application/json; charset=utf-8")
            }
            val newErrorBody: ResponseBody? = ResponseBody.create(contentType, errorResponseStr)
            return response.newBuilder().body(newErrorBody).build()
        }
        return response
    }

    /**
     * 解密响应内容
     */
    private fun decryptResponse(key: ByteArray?, nonce: ByteArray?, response: Response): Response {
        try {
            val responseBody: ResponseBody? = response.body()
            if (responseBody == null) {
                return response
            }
            val contentType: MediaType? = responseBody.contentType()
            var isStreaming = false
            if (contentType != null) {
                val contentTypeStr = contentType.toString()
                isStreaming = contentTypeStr.contains("text/event-stream")
            }
            if (isStreaming) {
                return response.newBuilder()
                    .addHeader("X-Decryption-Key", Base64.getEncoder().encodeToString(key))
                    .addHeader("X-Decryption-Nonce", Base64.getEncoder().encodeToString(nonce))
                    .addHeader("X-Is-Encrypted", "true")
                    .build()
            } else {
                try {
                    val responseBodyStr: String? = responseBody.string()
                    val responseJson: Map<String?, Object?> = mapper.readValue(
                        responseBodyStr,
                        object : TypeReference<Map<String?, Object?>?>() {
                        }
                    )
                    return handleNormalResponse(key, nonce, response, responseJson)
                } catch (e: Exception) {
                    return response.newBuilder().build()
                }
            }
        } catch (e: Exception) {
            return response
        }
    }


    /**
     * 处理普通响应解密
     */
    @Throws(IOException::class)
    private fun handleNormalResponse(key: ByteArray?, nonce: ByteArray?, response: Response, responseJson: Map<String?, Object?>): Response {
        if (responseJson.containsKey("choices") && responseJson.get("choices") is List) {
            @SuppressWarnings("unchecked") val choices: List<Map<String?, Object?>?> = responseJson.get("choices") as List<Map<String?, Object?>?>

            for (choice in choices) {
                if (ResponseDecryptUtil.shouldDecryptChoice(choice)) {
                    ResponseDecryptUtil.decryptChoiceContent(key, nonce, choice)
                }
            }
        }

        val decryptedContent: String = mapper.writeValueAsString(responseJson)
        val originalResponseBody: ResponseBody? = response.body()
        var contentType: MediaType? = null
        if (originalResponseBody != null) {
            contentType = originalResponseBody.contentType()
        }
        if (contentType == null) {
            contentType = MediaType.get("application/json; charset=utf-8")
        }
        val decryptedBody: ResponseBody? = ResponseBody.create(
            contentType,
            decryptedContent.getBytes(StandardCharsets.UTF_8)
        )

        return response.newBuilder()
            .body(decryptedBody)
            .build()
    }
}