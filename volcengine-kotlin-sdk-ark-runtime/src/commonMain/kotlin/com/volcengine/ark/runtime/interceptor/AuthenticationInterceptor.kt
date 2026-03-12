package com.volcengine.ark.runtime.interceptor

import okhttp3.Interceptor

class AuthenticationInterceptor(apiKey: String?) : Interceptor {
    private val apiKey: String?

    init {
        Objects.requireNonNull(apiKey, "Api key required")
        this.apiKey = apiKey
    }

    @Override
    @Throws(IOException::class)
    fun intercept(chain: Chain): Response {
        val request: Request? = chain.request()
            .newBuilder()
            .header("Authorization", "Bearer " + apiKey)
            .build()
        return chain.proceed(request)
    }
}
