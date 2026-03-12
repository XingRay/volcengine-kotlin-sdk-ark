package com.volcengine.ark.runtime.interceptor

import okhttp3.Interceptor

class RetryInterceptor(private val retryTimes: Int) : Interceptor {
    private val INITIAL_RETRY_DELAY = 0.5
    private val MAX_RETRY_DELAY = 8.0

    @Override
    @Throws(RuntimeException::class, InterruptedIOException::class)
    fun intercept(chain: Chain): Response? {
        val request: Request = chain.request()

        val requestRetryTimes = getRetryTimes(request)

        var response: Response? = null
        var tryCount = 0
        var shouldRetry: Boolean
        var exception: Exception?
        do {
            if (response != null) {
                response.close()
            }
            exception = null

            try {
                response = chain.proceed(request)
                shouldRetry = response.code() >= 500 || response.code() === 429
            } catch (e: Exception) {
                shouldRetry = true
                exception = e
            }

            tryCount++
            if (!(shouldRetry && tryCount <= requestRetryTimes)) {
                break
            }

            try {
                val interval = retryInterval(requestRetryTimes, requestRetryTimes - tryCount) * 1000
                Thread.sleep(Math.round(interval))
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
                throw InterruptedIOException()
            }
        } while (true)

        if (response != null) {
            return response
        }
        throw RuntimeException(exception)
    }

    fun retryInterval(max: Int, remain: Int): Double {
        val nbRetries: Double = Math.min(max - remain, MAX_RETRY_DELAY / INITIAL_RETRY_DELAY)
        val sleepSeconds: Double = Math.min(INITIAL_RETRY_DELAY * Math.pow(2.0, nbRetries), MAX_RETRY_DELAY)
        val jitter: Double = 1 - 0.25 * random()
        return sleepSeconds * jitter
    }

    fun getRetryTimes(request: Request): Int {
        val path: String = request.url().encodedPath()
        if (path.startsWith(BATCH_PATH_PREFIX)) {
            return MAX_RETRY_TIMES
        }
        return retryTimes
    }
}

