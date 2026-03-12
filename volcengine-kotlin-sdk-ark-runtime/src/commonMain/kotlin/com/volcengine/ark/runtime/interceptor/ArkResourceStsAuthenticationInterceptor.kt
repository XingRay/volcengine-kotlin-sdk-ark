package com.volcengine.ark.runtime.interceptor

import com.volcengine.ApiClient

class ArkResourceStsAuthenticationInterceptor(ak: String?, sk: String?, region: String?) : Interceptor {
    private val ak: String?
    private val sk: String?

    // cacheKey = resourceType/resourceId
    private val resourceStsTokens: Map<String?, ArkResourceStsTokenInfo?>
    private val advisoryRefreshTimeout: Integer? = Const.DEFAULT_ADVISORY_REFRESH_TIMEOUT
    private val mandatoryRefreshTimeout: Integer? = Const.DEFAULT_MANDATORY_REFRESH_TIMEOUT
    private val lock: ReentrantReadWriteLock = ReentrantReadWriteLock()
    private val volcClient: ArkApi

    init {
        Objects.requireNonNull(ak, "Ak token required")
        Objects.requireNonNull(sk, "Sk token required")
        this.ak = ak
        this.sk = sk
        this.resourceStsTokens = ConcurrentHashMap()

        val apiClient: ApiClient? = ApiClient()
            .setCredentials(Credentials.getCredentials(ak, sk))
            .setRegion(region)
        val arkApi: ArkApi = ArkApi(apiClient)
        this.volcClient = arkApi
    }

    @Override
    @Throws(IOException::class)
    fun intercept(chain: Chain): Response {
        val request: Request = chain.request()
        val requestResourceId = getRequestResourceId(request)
        val requestResourceType = getRequestResourceType(request, requestResourceId)
        val projectName = getProjectName(request)

        if (requestResourceType.equalsIgnoreCase(Const.RESOURCE_TYPE_PRESETENDPOINT) && StringUtils.isBlank(projectName)) {
            throw ArkException("project name is required for preset endpoint")
        }

        if (request.url().url().getPath().contains("contents/generations") || request.url().url().getPath().contains("images/generations")) {
            throw ArkException("content generation currently does not support ak&sk authentication, use api_key instead.")
        }

        val newRequest: Request? = chain.request()
            .newBuilder()
            .header("Authorization", "Bearer " + getResourceStsToken(requestResourceType, requestResourceId, projectName))
            .build()
        return chain.proceed(newRequest)
    }

    private fun getRequestResourceType(request: Request, requestResourceId: String): String {
        if (StringUtils.isNotBlank(request.header(Const.REQUEST_BOT))) {
            return Const.RESOURCE_TYPE_BOT
        }

        if (StringUtils.isNotBlank(requestResourceId) && requestResourceId.startsWith("ep-m-")) {
            return Const.RESOURCE_TYPE_PRESETENDPOINT
        }

        if (StringUtils.isNotBlank(requestResourceId) && requestResourceId.startsWith("ep-")) {
            return Const.RESOURCE_TYPE_ENDPOINT
        }

        // for model id
        return Const.RESOURCE_TYPE_PRESETENDPOINT
    }

    private fun getRequestResourceId(request: Request): String {
        if (StringUtils.isNotBlank(request.header(Const.REQUEST_BOT))) {
            return request.header(Const.REQUEST_BOT)
        }
        return request.header(Const.REQUEST_MODEL)
    }

    private fun getProjectName(request: Request): String? {
        if (StringUtils.isNotBlank(request.header(Const.REQUEST_PROJECT_NAME))) {
            return request.header(Const.REQUEST_PROJECT_NAME)
        }
        return ""
    }

    private fun getResourceStsToken(resourceType: String?, resourceId: String?, projectName: String?): String? {
        refresh(resourceType, resourceId, projectName)

        val tokenInfo = this.resourceStsTokens.get(getResourceKey(resourceType, resourceId))
        if (tokenInfo == null) {
            return ""
        }
        return tokenInfo.token
    }

    private fun refresh(resourceType: String?, resourceId: String?, projectName: String?) {
        if (!need_refresh(resourceType, resourceId, this.advisoryRefreshTimeout)) {
            return
        }

        if (lock.writeLock().tryLock()) {
            if (!need_refresh(resourceType, resourceId, this.advisoryRefreshTimeout)) {
                return
            }

            try {
                val isMandatoryRefresh = need_refresh(resourceType, resourceId, this.mandatoryRefreshTimeout)
                protectedRefresh(resourceType, resourceId, isMandatoryRefresh, projectName)
            } finally {
                lock.writeLock().unlock()
            }
        } else if (need_refresh(resourceType, resourceId, this.mandatoryRefreshTimeout)) {
            try {
                lock.writeLock().lock()
                if (!need_refresh(resourceType, resourceId, this.mandatoryRefreshTimeout)) {
                    return
                }
                protectedRefresh(resourceType, resourceId, true, projectName)
            } finally {
                lock.writeLock().unlock()
            }
        }
    }

    private fun need_refresh(resourceType: String?, resourceId: String?, refresh_in: Integer?): Boolean {
        val tokenInfo = this.resourceStsTokens.get(getResourceKey(resourceType, resourceId))
        if (tokenInfo == null) {
            return true
        }

        return tokenInfo.getExpiredTime() - System.currentTimeMillis() / 1000 < refresh_in
    }

    private fun protectedRefresh(resourceType: String?, resourceId: String?, isMandatory: Boolean, projectName: String?) {
        this.resourceStsTokens.compute(getResourceKey(resourceType, resourceId), object : BiFunction<String?, ArkResourceStsTokenInfo?, ArkResourceStsTokenInfo?>() {
            @Override
            fun apply(s: String?, stringIntegerPair: ArkResourceStsTokenInfo?): ArkResourceStsTokenInfo? {
                try {
                    val tokenInfo = getToken(resourceType, resourceId, Const.DEFAULT_STS_TIMEOUT, projectName)
                    return tokenInfo
                } catch (e: ApiException) {
                    if (isMandatory) {
                        throw RuntimeException(e)
                    }
                    return null
                }
            }
        })
    }

    @Throws(ApiException::class)
    private fun getEndpointToken(endpointId: String?, ttl: Integer?): ArkResourceStsTokenInfo {
        return getToken("endpoint", endpointId, ttl, "")
    }

    @Throws(ApiException::class)
    private fun getToken(resourceType: String?, resourceId: String?, ttl: Integer?, projectName: String?): ArkResourceStsTokenInfo {
        if (ttl < this.advisoryRefreshTimeout * 2) {
            throw ArkException("ttl should not be under " + this.advisoryRefreshTimeout * 2 + " seconds.")
        }

        val r: GetApiKeyRequest = GetApiKeyRequest()
        r.durationSeconds(ttl)
        r.resourceType(resourceType)
        if (StringUtils.isNotBlank(projectName)) {
            r.projectName(projectName)
        }
        val list: List<String?> = ArrayList()
        list.add(resourceId)
        r.resourceIds(list)

        val response: GetApiKeyResponse = this.volcClient.getApiKey(r)
        return com.volcengine.ark.runtime.interceptor.ArkResourceStsAuthenticationInterceptor.ArkResourceStsTokenInfo(response.getApiKey(), response.getExpiredTime())
    }

    private fun getResourceKey(resourceType: String?, resourceId: String?): String {
        return resourceType.toString() + "/" + resourceId
    }

    class ArkResourceStsTokenInfo(var token: String?, expiredTime: Integer?) {
        private var expiredTime: Integer?

        init {
            this.expiredTime = expiredTime
        }

        fun getExpiredTime(): Integer? {
            return expiredTime
        }

        fun setExpiredTime(expiredTime: Integer?) {
            this.expiredTime = expiredTime
        }
    }
}
