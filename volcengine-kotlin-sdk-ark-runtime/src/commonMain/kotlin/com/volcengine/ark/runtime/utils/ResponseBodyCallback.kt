package com.volcengine.ark.runtime.utils

import com.fasterxml.jackson.databind.ObjectMapper

class ResponseBodyCallback(emitter: FlowableEmitter<SSE?>, emitDone: Boolean, key: ByteArray?, nonce: ByteArray?, isEncrypted: Boolean) : Callback<ResponseBody?> {
    private val emitter: FlowableEmitter<SSE?>
    private val emitDone: Boolean
    private var key: ByteArray?
    private var nonce: ByteArray?
    private var isEncrypted: Boolean

    constructor(emitter: FlowableEmitter<SSE?>, emitDone: Boolean) : this(emitter, emitDone, null, null, false)

    init {
        this.emitter = emitter
        this.emitDone = emitDone
        this.key = if (key != null) key.clone() else null
        this.nonce = if (nonce != null) nonce.clone() else null
        this.isEncrypted = isEncrypted
    }

    @Override
    fun onResponse(call: Call<ResponseBody?>?, response: Response<ResponseBody?>) {
        var reader: BufferedReader? = null

        var requestId: String? = ""
        try {
            val headers: Headers = response.raw().request().headers()
            requestId = headers.get(Const.CLIENT_REQUEST_HEADER)
        } catch (ignored: Exception) {
        }

        try {
            val responseHeaders: Headers = response.raw().headers()
            val encryptedHeader: String? = responseHeaders.get("X-Is-Encrypted")
            if ("true".equals(encryptedHeader)) {
                this.isEncrypted = true

                val keyHeader: String? = responseHeaders.get("X-Decryption-Key")
                if (keyHeader != null) {
                    this.key = Base64.getDecoder().decode(keyHeader)
                }

                val nonceHeader: String? = responseHeaders.get("X-Decryption-Nonce")
                if (nonceHeader != null) {
                    this.nonce = Base64.getDecoder().decode(nonceHeader)
                }
            }
        } catch (ignored: Exception) {
            // 忽略解密参数读取错误，保持原有配置
        }

        try {
            if (!response.isSuccessful()) {
                val e: HttpException = HttpException(response)
                val errorBody: ResponseBody = response.errorBody()

                if (errorBody == null) {
                    throw e
                } else {
                    try {
                        val error: ArkAPIError = com.volcengine.ark.runtime.utils.ResponseBodyCallback.Companion.mapper.readValue(
                            errorBody.string(),
                            ArkAPIError::class.java
                        )
                        throw ArkHttpException(error, e, e.code(), requestId)
                    } catch (httpException: ArkHttpException) {
                        throw httpException
                    } catch (ignore: Exception) {
                        throw ArkHttpException(ArkAPIError(ArkErrorDetails(e.getMessage(), "", "", "InternalServiceError")), e, e.code(), requestId)
                    }
                }
            }

            val `in`: InputStream? = response.body().byteStream()
            reader = BufferedReader(InputStreamReader(`in`, StandardCharsets.UTF_8))
            var line: String?
            var sse: SSE? = null

            while (!emitter.isCancelled() && (reader.readLine().also { line = it }) != null) {
                if (line!!.startsWith("event:")) {
                    // do nothing
                    continue
                } else if (line.startsWith("data:")) {
                    val data = line.substring(5).trim()
                    try {
                        val err: ArkAPIError = com.volcengine.ark.runtime.utils.ResponseBodyCallback.Companion.mapper.readValue(data, ArkAPIError::class.java)
                        if (err.getError() != null) {
                            throw ArkHttpException(err, null, -1, requestId)
                        }
                    } catch (e: ArkHttpException) {
                        throw e
                    } catch (ignored: Exception) {
                    }

                    if (data.startsWith("[DONE]")) {
                        break
                    }

                    // 解密数据（如果需要）
                    var processedData: String? = data
                    if (isEncrypted && key != null && nonce != null) {
                        try {
                            processedData = ResponseDecryptUtil.decryptStreamChunk(data, key, nonce)
                        } catch (ignored: Exception) {
                            // 如果解密失败，使用原始数据
                        }
                    }
                    sse = SSE(processedData)
                } else if (line.equals("") && sse != null) {
                    if (sse.isDone()) {
                        if (emitDone) {
                            emitter.onNext(sse)
                        }
                        break
                    }

                    emitter.onNext(sse)
                    sse = null
                } else {
                    throw SSEFormatException("Invalid sse format! " + line)
                }
            }

            emitter.onComplete()
        } catch (t: Throwable) {
            onFailure(call, t)
        } finally {
            if (reader != null) {
                try {
                    reader.close()
                } catch (e: IOException) {
                    // do nothing
                }
            }
        }
    }

    @Override
    fun onFailure(call: Call<ResponseBody?>?, t: Throwable?) {
        emitter.onError(t)
    }

    companion object {
        private val mapper: ObjectMapper = ArkService.defaultObjectMapper()
    }
}
