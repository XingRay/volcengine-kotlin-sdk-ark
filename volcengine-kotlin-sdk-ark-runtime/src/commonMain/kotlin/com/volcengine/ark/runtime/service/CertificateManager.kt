package com.volcengine.ark.runtime.service

import com.fasterxml.jackson.core.type.TypeReference

object CertificateManager {
    private val certificateCache: ConcurrentHashMap<String?, ServerCertificateInfo?> = ConcurrentHashMap()

    private fun getDnsNamesFromExtension(sanExtension: Extension): List<String?> {
        val dnsNames: List<String?> = ArrayList()

        try {
            val generalNames: GeneralNames = GeneralNames.getInstance(sanExtension.getParsedValue())

            for (generalName in generalNames.getNames()) {
                if (generalName.getTagNo() === GeneralName.dNSName) {
                    val dnsName: String? = generalName.getName().toString()
                    dnsNames.add(dnsName)
                }
            }
        } catch (e: Exception) {
            throw RuntimeException("Error getting DNS names from extension:", e)
        }

        return dnsNames
    }

    /**
     * 检查内存缓存中是否存在证书
     */
    fun hasCertificateInCache(ep: String?): Boolean {
        return com.volcengine.ark.runtime.service.CertificateManager.certificateCache.containsKey(ep)
    }

    /**
     * 从内存缓存获取证书
     */
    fun getServerCertificateFromCache(ep: String?): ServerCertificateInfo {
        return com.volcengine.ark.runtime.service.CertificateManager.certificateCache.get(ep)
    }

    @Throws(IOException::class)
    fun getServerCertificate(apiKey: String?, baseUrl: String, ep: String?): ServerCertificateInfo {
        if (com.volcengine.ark.runtime.service.CertificateManager.hasCertificateInCache(ep)) {
            return com.volcengine.ark.runtime.service.CertificateManager.getServerCertificateFromCache(ep)
        }

        try {
            val volcArkEncryption: String? = System.getenv("VOLC_ARK_ENCRYPTION")
            val aiccEnabled = "AICC".equals(volcArkEncryption)

            var certificate: String?

            certificate = com.volcengine.ark.runtime.service.CertificateManager.loadCertificateLocally(ep)
            if (certificate != null) {
                return com.volcengine.ark.runtime.service.CertificateManager.createCertificateInfo(certificate, ep)
            }

            certificate = com.volcengine.ark.runtime.service.CertificateManager.loadCertificateByApiKey(baseUrl, apiKey, ep, aiccEnabled)

            com.volcengine.ark.runtime.service.CertificateManager.saveCertificateLocally(ep, certificate)

            return com.volcengine.ark.runtime.service.CertificateManager.createCertificateInfo(certificate, ep)
        } catch (e: Exception) {
            throw IOException("Failed to fetch server certificate", e)
        }
    }

    fun getCertInfo(certPem: String?): Array<String> {
        try {
            PEMParser(StringReader(certPem)).use { pemParser ->
                val `object`: Object? = pemParser.readObject()
                if (`object` is X509CertificateHolder) {
                    val certHolder: X509CertificateHolder = `object` as X509CertificateHolder

                    val sanExtension: Extension? = certHolder.getExtension(Extension.subjectAlternativeName)
                    if (sanExtension != null) {
                        val dnsNames: List<String?> = com.volcengine.ark.runtime.service.CertificateManager.getDnsNamesFromExtension(sanExtension)

                        if (dnsNames.size() > 1) {
                            val firstDns: String = dnsNames.get(0)!!
                            val secondDns: String = dnsNames.get(1)!!

                            val ringPattern: Pattern = Pattern.compile("^ring\\..*$")
                            val keyPattern: Pattern = Pattern.compile("^key\\..*$")

                            if (ringPattern.matcher(firstDns).matches() &&
                                keyPattern.matcher(secondDns).matches()
                            ) {
                                val ringId: String? = firstDns.substring(5)
                                val keyId: String? = secondDns.substring(4)
                                return arrayOf<String>(ringId!!, keyId!!)
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            throw RuntimeException("Failed to parse certificate to get ring_id and key_id", e)
        }
        return arrayOf<String>("", "")
    }

    /**
     * 从本地缓存加载证书
     */
    @Throws(IOException::class)
    fun loadCertificateLocally(ep: String?): String? {
        try {
            val certStoragePath: String = com.volcengine.ark.runtime.service.CertificateManager.getCertStoragePath()
            val certFilePath = (certStoragePath + File.separator + ep).toString() + ".pem"

            val certFile: File = File(certFilePath)

            if (certFile.exists()) {
                val lastModifiedSeconds: Long = certFile.lastModified() / 1000
                val currentTimeSeconds: Long = System.currentTimeMillis() / 1000
                val timeDifferenceSeconds = currentTimeSeconds - lastModifiedSeconds
                val certExpirationSeconds = 14L * 24 * 60 * 60
                if (timeDifferenceSeconds <= certExpirationSeconds) {
                    val certPem: String = String(java.nio.file.Files.readAllBytes(certFile.toPath()), StandardCharsets.UTF_8)

                    val certInfo: Array<String> = com.volcengine.ark.runtime.service.CertificateManager.getCertInfo(certPem)
                    val ringId = certInfo[0]
                    val keyId = certInfo[1]

                    val aiccEnabled = "AICC".equals(System.getenv("VOLC_ARK_ENCRYPTION"))

                    if ((ringId.isEmpty() || keyId.isEmpty()) && !aiccEnabled) {
                        return certPem
                    }
                    if (!ringId.isEmpty() && !keyId.isEmpty() && aiccEnabled) {
                        return certPem
                    }
                }

                certFile.delete()
            }
        } catch (e: Exception) {
            val errMsg = "Failed to load local certificate: " + e.getMessage()
            throw IOException(errMsg, e)
        }
        return null
    }

    /**
     * 使用API Key方式获取证书 - 重构后降低复杂度
     */
    @Throws(IOException::class)
    fun loadCertificateByApiKey(baseUrl: String, apiKey: String?, ep: String?, aiccEnabled: Boolean): String? {
        var connection: HttpURLConnection? = null
        try {
            connection = com.volcengine.ark.runtime.service.CertificateManager.createHttpConnection(baseUrl, apiKey)
            com.volcengine.ark.runtime.service.CertificateManager.sendCertificateRequest(connection, ep, aiccEnabled)
            return com.volcengine.ark.runtime.service.CertificateManager.processCertificateResponse(connection)
        } catch (e: IOException) {
            throw e
        } catch (e: Exception) {
            val errMsg = "通过API Key获取证书失败: " + e.getMessage()
            throw IOException(errMsg, e)
        } finally {
            if (connection != null) {
                connection.disconnect()
            }
        }
    }

    /**
     * 创建HTTP连接
     */
    @Throws(IOException::class)
    private fun createHttpConnection(baseUrl: String, apiKey: String?): HttpURLConnection {
        val certificateUrl: String = com.volcengine.ark.runtime.service.CertificateManager.buildCertificateUrl(baseUrl)
        val url: URL = URI.create(certificateUrl).toURL()
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection

        connection.setRequestMethod("POST")
        connection.setRequestProperty("Content-Type", "application/json")
        connection.setRequestProperty("Authorization", "Bearer " + apiKey)
        connection.setRequestProperty("X-Session-Token", "/e2e/get/certificate")

        return connection
    }

    /**
     * 构建证书请求URL
     */
    private fun buildCertificateUrl(baseUrl: String): String {
        val requestedUrl: String = baseUrl.replaceAll("/+$", "")

        if (requestedUrl.endsWith("/api/v3")) {
            return requestedUrl.toString() + "/e2e/get/certificate"
        } else {
            return requestedUrl.toString() + "/api/v3/e2e/get/certificate"
        }
    }

    /**
     * 发送证书请求
     */
    @Throws(IOException::class)
    private fun sendCertificateRequest(connection: HttpURLConnection, ep: String?, aiccEnabled: Boolean) {
        val jsonBody: String = com.volcengine.ark.runtime.service.CertificateManager.buildRequestBody(ep, aiccEnabled)

        connection.setDoOutput(true)
        connection.getOutputStream().use { os ->
            val input: ByteArray = jsonBody.getBytes(StandardCharsets.UTF_8)
            os.write(input, 0, input.size)
        }
    }

    /**
     * 构建请求体
     */
    @Throws(IOException::class)
    private fun buildRequestBody(ep: String?, aiccEnabled: Boolean): String {
        val requestBody: Map<String?, Object?> = HashMap()
        requestBody.put("model", ep)
        if (aiccEnabled) {
            requestBody.put("type", "AICCv0.1")
        }
        val mapper: ObjectMapper = ObjectMapper()
        return mapper.writeValueAsString(requestBody)
    }

    /**
     * 处理证书响应
     */
    @Throws(IOException::class)
    private fun processCertificateResponse(connection: HttpURLConnection): String? {
        val responseCode: Int = connection.getResponseCode()

        if (!com.volcengine.ark.runtime.service.CertificateManager.isSuccessfulResponse(responseCode)) {
            com.volcengine.ark.runtime.service.CertificateManager.handleErrorResponse(connection, responseCode)
        }

        return com.volcengine.ark.runtime.service.CertificateManager.extractCertificateFromResponse(connection)
    }

    /**
     * 检查响应是否成功
     */
    private fun isSuccessfulResponse(responseCode: Int): Boolean {
        return responseCode >= 200 && responseCode < 300
    }

    /**
     * 处理错误响应
     */
    @Throws(IOException::class)
    private fun handleErrorResponse(connection: HttpURLConnection, responseCode: Int) {
        val errorResponse: String = com.volcengine.ark.runtime.service.CertificateManager.readErrorResponse(connection)
        val errorMsg = "证书请求失败，状态码: " + responseCode + ", 响应: " + errorResponse
        throw IOException(errorMsg)
    }

    /**
     * 从响应中提取证书
     */
    @Throws(IOException::class)
    private fun extractCertificateFromResponse(connection: HttpURLConnection): String? {
        val responseBody: String = com.volcengine.ark.runtime.service.CertificateManager.readResponseBody(connection)
        val mapper: ObjectMapper = ObjectMapper()

        val responseJson: Map<String?, Object?> = mapper.readValue(
            responseBody,
            object : TypeReference<HashMap<String?, Object?>?>() {
            }
        )

        com.volcengine.ark.runtime.service.CertificateManager.validateResponse(responseJson)

        if (responseJson.containsKey("Certificate")) {
            return responseJson.get("Certificate") as String?
        } else {
            throw IOException("响应中未找到Certificate字段")
        }
    }

    /**
     * 验证响应数据
     */
    @Throws(IOException::class)
    private fun validateResponse(responseJson: Map<String?, Object?>) {
        if (responseJson.containsKey("error")) {
            val error: Object? = responseJson.get("error")
            val errorMsg = "获取证书失败: " + error
            throw IOException(errorMsg)
        }
    }

    /**
     * 保存证书到本地缓存
     */
    @Throws(IOException::class)
    fun saveCertificateLocally(ep: String?, certificate: String) {
        try {
            val certStoragePath: String = com.volcengine.ark.runtime.service.CertificateManager.getCertStoragePath()
            val certFilePath = (certStoragePath + File.separator + ep).toString() + ".pem"

            val storageDir: File = File(certStoragePath)
            if (!storageDir.exists()) {
                if (!storageDir.mkdirs()) {
                    val errMsg = "创建证书存储目录失败: " + certStoragePath
                    throw IOException(errMsg)
                }
            }

            java.nio.file.Files.write(
                Paths.get(certFilePath),
                certificate.getBytes(StandardCharsets.UTF_8)
            )
        } catch (e: Exception) {
            val errMsg = "保存证书到本地失败: " + e.getMessage()
            throw IOException(errMsg, e)
        }
    }

    val certStoragePath: String
        /**
         * 获取证书存储路径
         */
        get() {
            val userHome: String? = System.getProperty("user.home")
            return (userHome + File.separator).toString() + ".ark" + File.separator + "certificates"
        }

    /**
     * 缓存服务端证书信息
     */
    fun cacheServerCertificate(cacheKey: String?, publicKey: PublicKey?, ringId: String?, keyId: String?) {
        com.volcengine.ark.runtime.service.CertificateManager.certificateCache.put(cacheKey, com.volcengine.ark.runtime.service.CertificateManager.ServerCertificateInfo(publicKey, ringId, keyId))
    }

    /**
     * 从PEM格式的X.509证书中提取公钥
     */
    @Throws(GeneralSecurityException::class)
    fun extractPublicKeyFromCertificate(certificate: String): PublicKey {
        try {
            val certContent: String? = certificate.replace("-----BEGIN CERTIFICATE-----", "")
                .replace("-----END CERTIFICATE-----", "")
                .replaceAll("\\s", "")

            val certBytes: ByteArray? = Base64.getDecoder().decode(certContent)

            val certFactory: CertificateFactory = CertificateFactory.getInstance("X.509")
            val x509Cert: X509Certificate = certFactory.generateCertificate(
                ByteArrayInputStream(certBytes)
            ) as X509Certificate

            return x509Cert.getPublicKey()
        } catch (e: Exception) {
            throw GeneralSecurityException("Failed to extract public key from certificate", e)
        }
    }

    /**
     * 创建证书信息对象并缓存
     */
    @Throws(IOException::class)
    fun createCertificateInfo(certificate: String, ep: String?): ServerCertificateInfo {
        try {
            val result: Array<String> = com.volcengine.ark.runtime.service.CertificateManager.getCertInfo(certificate)
            val ringId: String? = result[0]
            val keyId: String? = result[1]

            val publicKey: java.security.PublicKey = com.volcengine.ark.runtime.service.CertificateManager.extractPublicKeyFromCertificate(certificate)

            val certInfo: ServerCertificateInfo =
                com.volcengine.ark.runtime.service.CertificateManager.ServerCertificateInfo(publicKey, ringId, keyId)

            com.volcengine.ark.runtime.service.CertificateManager.cacheServerCertificate(ep, publicKey, ringId, keyId)

            return certInfo
        } catch (e: GeneralSecurityException) {
            val errMsg = "Failed to extract public key from certificate: " + e.getMessage()
            throw IOException(errMsg, e)
        } catch (e: Exception) {
            val errMsg = "Failed to create certificate info: " + e.getMessage()
            throw IOException(errMsg, e)
        }
    }

    /**
     * 读取HTTP响应体
     */
    @Throws(IOException::class)
    fun readResponseBody(connection: HttpURLConnection): String {
        BufferedReader(
            InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8)
        ).use { br ->
            val response = StringBuilder()
            var line: String?
            while ((br.readLine().also { line = it }) != null) {
                response.append(line)
            }
            return response.toString()
        }
    }

    /**
     * 读取HTTP错误响应体
     */
    @Throws(IOException::class)
    fun readErrorResponse(connection: HttpURLConnection): String {
        BufferedReader(
            InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8)
        ).use { br ->
            val errorResponse = StringBuilder()
            var line: String?
            while ((br.readLine().also { line = it }) != null) {
                errorResponse.append(line)
            }
            return errorResponse.toString()
        }
    }

    /**
     * 服务端证书信息类
     */
    class ServerCertificateInfo(publicKey: PublicKey?, ringId: String?, keyId: String?) {
        private val publicKey: PublicKey?
        val ringId: String?
        val keyId: String?

        init {
            this.publicKey = publicKey
            this.ringId = ringId
            this.keyId = keyId
        }

        fun getPublicKey(): PublicKey? {
            return publicKey
        }
    }
}