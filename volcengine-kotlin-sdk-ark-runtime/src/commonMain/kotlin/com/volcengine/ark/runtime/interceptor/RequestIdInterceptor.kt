package com.volcengine.ark.runtime.interceptor

import com.volcengine.ark.runtime.Const

class RequestIdInterceptor : Interceptor {
    @Override
    @Throws(IOException::class)
    fun intercept(chain: Chain): Response {
        var requestBuilder: Request.Builder = chain.request().newBuilder()

        if (chain.request().header(Const.CLIENT_REQUEST_HEADER) == null || chain.request().header(Const.CLIENT_REQUEST_HEADER).length() === 0) {
            requestBuilder = requestBuilder.header(Const.CLIENT_REQUEST_HEADER, genRequestId())
        }
        requestBuilder.header("User-Agent", com.volcengine.ark.runtime.interceptor.RequestIdInterceptor.Companion.getUserAgent())

        val request: Request = requestBuilder.build()

        try {
            return chain.proceed(request)
        } catch (e: Exception) {
            val requestId: String? = request.header(Const.CLIENT_REQUEST_HEADER)
            val arkAPIError: ArkAPIError = ArkAPIError(ArkErrorDetails(e.getMessage(), "", "", ""))
            throw ArkHttpException(arkAPIError, e, ArkHttpException.INTERNAL_SERVICE_CODE, requestId)
        }
    }

    private fun genRequestId(): String {
        val date: Date = Date()
        val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyyMMddhhmmss")
        return dateFormat.format(date) + RandomStringUtils.randomAlphanumeric(20)
    }

    companion object {
        private val userAgent: String
            get() {
                val format = "%s/%s/(%s;%s;%s)"

                val osInfo: String = System.getProperty("os.name") + "-" + System.getProperty("os.version")
                val jdkInfo = "java-" + System.getProperty("java.version")
                val arch: String? = System.getProperty("os.arch")

                return String.format(format, Version.SDK_NAME, Version.SDK_VERSION, jdkInfo, osInfo, arch)
            }
    }
}
