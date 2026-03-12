package com.volcengine.ark.runtime.interceptor

import com.volcengine.ark.runtime.utils.ModelBreaker

class BatchInterceptor : Interceptor {
    private val modelBreakerMap: ConcurrentHashMap<String?, ModelBreaker?>

    init {
        this.modelBreakerMap = ConcurrentHashMap()
    }

    @Override
    @Throws(IOException::class)
    fun intercept(chain: Chain): Response {
        val request: Request = chain.request()
        val url: HttpUrl = request.url()
        if (!url.encodedPath().startsWith(BATCH_PATH_PREFIX)) {
            return chain.proceed(request)
        }
        val endpoint: String? = request.header(REQUEST_MODEL)
        try {
            waitModelForRetryAfter(endpoint)
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
        val response: Response = chain.proceed(chain.request())
        val retryAfter: String? = response.header(RETRY_AFTER)
        if (retryAfter != null && !retryAfter.isEmpty()) {
            try {
                val delay = Integer.parseInt(retryAfter)
                setModelForRetryAfter(endpoint, Duration.ofSeconds(delay))
            } catch (e: NumberFormatException) {
                // 无效的RetryAfter Header. 跳过
            }
        }
        return response
    }

    @Throws(InterruptedException::class)
    private fun waitModelForRetryAfter(model: String?) {
        val breaker: ModelBreaker = this.getModelBreaker(model)
        while (!breaker.Allow()) {
            val duration: Duration = breaker.GetAllowedDuration()
            if (duration.getSeconds() > 0) {
                Thread.sleep(duration.toMillis())
            }
        }
    }

    private fun setModelForRetryAfter(model: String?, duration: Duration?) {
        val breaker: ModelBreaker = this.getModelBreaker(model)
        breaker.Reset(duration)
    }

    private fun getModelBreaker(model: String?): ModelBreaker {
        val breaker: ModelBreaker
        if (this.modelBreakerMap.containsKey(model)) {
            breaker = this.modelBreakerMap.get(model)
        } else {
            breaker = ModelBreaker()
            this.modelBreakerMap.put(model, breaker)
        }
        return breaker
    }
}
