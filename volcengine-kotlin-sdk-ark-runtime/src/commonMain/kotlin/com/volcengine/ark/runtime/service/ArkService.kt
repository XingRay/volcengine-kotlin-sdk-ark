package com.volcengine.ark.runtime.service


import com.fasterxml.jackson.annotation.JsonInclude

class ArkService : ArkBaseService, com.volcengine.ark.runtime.service.ArkBaseServiceImpl {
    private val api: com.volcengine.ark.runtime.service.ArkApi
    private val executorService: ExecutorService?

    constructor(apiKey: String?) : this(apiKey, DEFAULT_TIMEOUT)

    constructor(apiKey: String?, timeout: Duration) {
        val mapper: ObjectMapper = com.volcengine.ark.runtime.service.ArkService.Companion.defaultObjectMapper()
        val client: OkHttpClient = com.volcengine.ark.runtime.service.ArkService.Companion.defaultApiKeyClient(apiKey, timeout)
        val retrofit: Retrofit = com.volcengine.ark.runtime.service.ArkService.Companion.defaultRetrofit(client, mapper, BASE_URL, null)

        this.api = retrofit.create(com.volcengine.ark.runtime.service.ArkApi::class.java)
        this.executorService = client.dispatcher().executorService()
    }

    constructor(ak: String?, sk: String?) : this(ak, sk, DEFAULT_TIMEOUT)

    constructor(ak: String?, sk: String?, timeout: Duration) {
        val mapper: ObjectMapper = com.volcengine.ark.runtime.service.ArkService.Companion.defaultObjectMapper()
        val client: OkHttpClient = com.volcengine.ark.runtime.service.ArkService.Companion.defaultResourceStsClient(ak, sk, timeout, BASE_REGION)
        val retrofit: Retrofit = com.volcengine.ark.runtime.service.ArkService.Companion.defaultRetrofit(client, mapper, BASE_URL, null)

        this.api = retrofit.create(com.volcengine.ark.runtime.service.ArkApi::class.java)
        this.executorService = client.dispatcher().executorService()
    }

    constructor(api: com.volcengine.ark.runtime.service.ArkApi) {
        this.api = api
        this.executorService = null
    }

    constructor(api: com.volcengine.ark.runtime.service.ArkApi, executorService: ExecutorService?) {
        this.api = api
        this.executorService = executorService
    }

    override fun createChatCompletion(request: ChatCompletionRequest): ChatCompletionResult? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<ChatCompletionResult?>(api.createChatCompletion(request, request.getModel(), HashMap()))
    }

    override fun createBatchChatCompletion(request: ChatCompletionRequest): ChatCompletionResult? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<ChatCompletionResult?>(api.createBatchChatCompletion(request, request.getModel(), HashMap()))
    }

    fun createChatCompletion(request: ChatCompletionRequest, customHeaders: Map<String?, String?>?): ChatCompletionResult? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<ChatCompletionResult?>(api.createChatCompletion(request, request.getModel(), customHeaders))
    }

    fun createBatchChatCompletion(request: ChatCompletionRequest, customHeaders: Map<String?, String?>?): ChatCompletionResult? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<ChatCompletionResult?>(api.createBatchChatCompletion(request, request.getModel(), customHeaders))
    }

    override fun streamChatCompletion(request: ChatCompletionRequest): Flowable<ChatCompletionChunk?>? {
        request.setStream(true)

        return stream(api.createChatCompletionStream(request, request.getModel(), HashMap()), ChatCompletionChunk::class.java)
    }

    fun streamChatCompletion(request: ChatCompletionRequest, customHeaders: Map<String?, String?>?): Flowable<ChatCompletionChunk?>? {
        request.setStream(true)

        return stream(api.createChatCompletionStream(request, request.getModel(), customHeaders), ChatCompletionChunk::class.java)
    }

    override fun createEmbeddings(request: EmbeddingRequest): EmbeddingResult? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<EmbeddingResult?>(api.createEmbeddings(request, request.getModel(), HashMap()))
    }

    fun createEmbeddings(request: EmbeddingRequest, customHeaders: Map<String?, String?>?): EmbeddingResult? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<EmbeddingResult?>(api.createEmbeddings(request, request.getModel(), customHeaders))
    }

    override fun createBatchEmbeddings(request: EmbeddingRequest): EmbeddingResult? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<EmbeddingResult?>(api.createBatchEmbeddings(request, request.getModel(), HashMap()))
    }

    fun createBatchEmbeddings(request: EmbeddingRequest, customHeaders: Map<String?, String?>?): EmbeddingResult? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<EmbeddingResult?>(api.createBatchEmbeddings(request, request.getModel(), customHeaders))
    }

    override fun createMultiModalEmbeddings(request: MultimodalEmbeddingRequest): MultimodalEmbeddingResult? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<MultimodalEmbeddingResult?>(api.createMultiModalEmbeddings(request, request.getModel(), HashMap()))
    }

    fun createMultiModalEmbeddings(request: MultimodalEmbeddingRequest, customHeaders: Map<String?, String?>?): MultimodalEmbeddingResult? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<MultimodalEmbeddingResult?>(api.createMultiModalEmbeddings(request, request.getModel(), customHeaders))
    }

    override fun createBatchMultiModalEmbeddings(request: MultimodalEmbeddingRequest): MultimodalEmbeddingResult? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<MultimodalEmbeddingResult?>(api.createBatchMultiModalEmbeddings(request, request.getModel(), HashMap()))
    }

    fun createBatchMultiModalEmbeddings(request: MultimodalEmbeddingRequest, customHeaders: Map<String?, String?>?): MultimodalEmbeddingResult? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<MultimodalEmbeddingResult?>(api.createBatchMultiModalEmbeddings(request, request.getModel(), customHeaders))
    }

    @Override
    override fun createBotChatCompletion(request: BotChatCompletionRequest): BotChatCompletionResult? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<BotChatCompletionResult?>(api.createBotChatCompletion(request, request.getModel(), HashMap()))
    }

    fun createBotChatCompletion(request: BotChatCompletionRequest, customHeaders: Map<String?, String?>?): BotChatCompletionResult? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<BotChatCompletionResult?>(api.createBotChatCompletion(request, request.getModel(), customHeaders))
    }


    @Override
    override fun streamBotChatCompletion(request: BotChatCompletionRequest): Flowable<BotChatCompletionChunk?>? {
        request.setStream(true)
        return stream(api.createBotChatCompletionStream(request, request.getModel(), HashMap()), BotChatCompletionChunk::class.java)
    }

    fun streamBotChatCompletion(request: BotChatCompletionRequest, customHeaders: Map<String?, String?>?): Flowable<BotChatCompletionChunk?>? {
        request.setStream(true)
        return stream(api.createBotChatCompletionStream(request, request.getModel(), customHeaders), BotChatCompletionChunk::class.java)
    }

    @Override
    override fun createContext(request: CreateContextRequest): CreateContextResult? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<CreateContextResult?>(api.createContext(request, request.getModel(), HashMap()))
    }

    fun createContext(request: CreateContextRequest, customHeaders: Map<String?, String?>?): CreateContextResult? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<CreateContextResult?>(api.createContext(request, request.getModel(), customHeaders))
    }

    @Override
    override fun createContextChatCompletion(request: ContextChatCompletionRequest): ChatCompletionResult? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<ChatCompletionResult?>(api.createContextChatCompletion(request, request.getModel(), HashMap()))
    }

    fun createContextChatCompletion(request: ContextChatCompletionRequest, customHeaders: Map<String?, String?>?): ChatCompletionResult? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<ChatCompletionResult?>(api.createContextChatCompletion(request, request.getModel(), customHeaders))
    }

    @Override
    override fun streamContextChatCompletion(request: ContextChatCompletionRequest): Flowable<ChatCompletionChunk?>? {
        request.setStream(true)
        return stream(api.createContextChatCompletionStream(request, request.getModel(), HashMap()), ChatCompletionChunk::class.java)
    }

    fun streamContextChatCompletion(request: ContextChatCompletionRequest, customHeaders: Map<String?, String?>?): Flowable<ChatCompletionChunk?>? {
        request.setStream(true)
        return stream(api.createContextChatCompletionStream(request, request.getModel(), customHeaders), ChatCompletionChunk::class.java)
    }

    override fun createTokenization(request: TokenizationRequest): TokenizationResult? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<TokenizationResult?>(api.createTokenization(request, request.getModel(), HashMap()))
    }

    fun createTokenization(request: TokenizationRequest, customHeaders: Map<String?, String?>?): TokenizationResult? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<TokenizationResult?>(api.createTokenization(request, request.getModel(), customHeaders))
    }


    override fun generateImages(request: GenerateImagesRequest): ImagesResponse? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<ImagesResponse?>(api.generateImages(request, request.getModel(), HashMap()))
    }

    fun generateImages(request: GenerateImagesRequest, customHeaders: Map<String?, String?>?): ImagesResponse? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<ImagesResponse?>(api.generateImages(request, request.getModel(), customHeaders))
    }

    fun streamGenerateImages(request: GenerateImagesRequest): Flowable<ImageGenStreamEvent?>? {
        request.setStream(true)
        return stream(api.streamGenerateImages(request, request.getModel(), HashMap()), ImageGenStreamEvent::class.java)
    }

    fun streamGenerateImages(request: GenerateImagesRequest, customHeaders: Map<String?, String?>?): Flowable<ImageGenStreamEvent?>? {
        request.setStream(true)
        return stream(api.streamGenerateImages(request, request.getModel(), customHeaders), ImageGenStreamEvent::class.java)
    }

    override fun createContentGenerationTask(request: CreateContentGenerationTaskRequest): CreateContentGenerationTaskResult? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<CreateContentGenerationTaskResult?>(api.createContentGenerationTask(request, request.getModel(), HashMap()))
    }

    fun createContentGenerationTask(request: CreateContentGenerationTaskRequest, customHeaders: Map<String?, String?>?): CreateContentGenerationTaskResult? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<CreateContentGenerationTaskResult?>(api.createContentGenerationTask(request, request.getModel(), customHeaders))
    }


    override fun getContentGenerationTask(request: GetContentGenerationTaskRequest): GetContentGenerationTaskResponse? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<GetContentGenerationTaskResponse?>(api.getContentGenerationTask(request.getTaskId(), HashMap()))
    }

    fun getContentGenerationTask(request: GetContentGenerationTaskRequest, customHeaders: Map<String?, String?>?): GetContentGenerationTaskResponse? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<GetContentGenerationTaskResponse?>(api.getContentGenerationTask(request.getTaskId(), customHeaders))
    }

    @Override
    override fun listContentGenerationTasks(request: ListContentGenerationTasksRequest): ListContentGenerationTasksResponse? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<ListContentGenerationTasksResponse?>(
            api.listContentGenerationTasks(
                request.getPageNum(),
                request.getPageSize(),
                request.getStatus(),
                request.getModel(),
                request.getServiceTier(),
                request.getTaskIds(),
                HashMap()
            )
        )
    }

    fun listContentGenerationTasks(
        request: ListContentGenerationTasksRequest,
        customHeaders: Map<String?, String?>?
    ): ListContentGenerationTasksResponse? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<ListContentGenerationTasksResponse?>(
            api.listContentGenerationTasks(
                request.getPageNum(),
                request.getPageSize(),
                request.getStatus(),
                request.getModel(),
                request.getServiceTier(),
                request.getTaskIds(),
                customHeaders
            )
        )
    }


    override fun deleteContentGenerationTask(request: DeleteContentGenerationTaskRequest): DeleteContentGenerationTaskResponse? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<DeleteContentGenerationTaskResponse?>(api.deleteContentGenerationTask(request.getTaskId(), HashMap()))
    }

    fun deleteContentGenerationTask(request: DeleteContentGenerationTaskRequest, customHeaders: Map<String?, String?>?): DeleteContentGenerationTaskResponse? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<DeleteContentGenerationTaskResponse?>(api.deleteContentGenerationTask(request.getTaskId(), customHeaders))
    }


    @Override
    override fun createResponse(request: CreateResponsesRequest): ResponseObject? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<ResponseObject?>(api.createResponse(request, request.getModel(), HashMap()))
    }

    fun createResponse(request: CreateResponsesRequest, customHeaders: Map<String?, String?>?): ResponseObject? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<ResponseObject?>(api.createResponse(request, request.getModel(), customHeaders))
    }

    @Override
    override fun streamResponse(request: CreateResponsesRequest): Flowable<StreamEvent?>? {
        return stream(api.streamResponse(request, request.getModel(), HashMap()), StreamEvent::class.java)
    }

    fun streamResponse(request: CreateResponsesRequest, customHeaders: Map<String?, String?>?): Flowable<StreamEvent?>? {
        return stream(api.streamResponse(request, request.getModel(), customHeaders), StreamEvent::class.java)
    }

    @Override
    override fun getResponse(request: GetResponseRequest): ResponseObject? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<ResponseObject?>(api.getResponse(request.getResponseId(), HashMap()))
    }

    @Override
    override fun deleteResponse(request: DeleteResponseRequest): DeleteResponseResponse? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<DeleteResponseResponse?>(api.deleteResponse(request.getResponseId(), HashMap()))
    }

    @Override
    override fun listResponseInputItems(request: ListInputItemsRequest): ListInputItemsResponse? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<ListInputItemsResponse?>(
            api.listResponseInputItems(
                request.getResponseId(),
                request.getAfter(),
                request.getBefore(),
                request.getLimit(),
                request.getInclude(),
                HashMap()
            )
        )
    }

    @Override
    override fun uploadFile(request: UploadFileRequest): FileMeta? {
        val fileBody: MultipartBody.Part? = MultipartBodyUtils.getPart(request.getFile(), "file")
        val purpose: RequestBody? = RequestBody.create(MultipartBodyUtils.TYPE, request.getPurpose())
        var expireAt: RequestBody? = null
        if (request.getExpireAt() != null) {
            expireAt = RequestBody.create(MultipartBodyUtils.TYPE, String.valueOf(request.getExpireAt()))
        }
        var fps: RequestBody? = null
        if (request.getPreprocessConfigs() != null && request.getPreprocessConfigs().getVideo() != null && request.getPreprocessConfigs().getVideo().getFps() != null) {
            fps = RequestBody.create(MultipartBodyUtils.TYPE, String.valueOf(request.getPreprocessConfigs().getVideo().getFps()))
        }
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<FileMeta?>(api.uploadFile(fileBody, purpose, expireAt, fps, HashMap()))
    }

    @Override
    override fun retrieveFile(fileId: String?): FileMeta? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<FileMeta?>(api.retrieveFile(fileId, HashMap()))
    }

    @Override
    override fun deleteFile(fileId: String?): DeleteFileResponse? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<DeleteFileResponse?>(api.deleteFile(fileId, HashMap()))
    }

    @Override
    override fun listFiles(request: ListFilesRequest): ListFilesResponse? {
        return com.volcengine.ark.runtime.service.ArkService.Companion.execute<ListFilesResponse?>(
            api.listFiles(
                request.getLimit(),
                request.getAfter(),
                request.getPurpose(),
                request.getOrder(),
                HashMap()
            )
        )
    }

    fun shutdownExecutor() {
        Objects.requireNonNull(this.executorService, "executorService must be set in order to shut down")
        this.executorService.shutdown()
    }

    class Builder {
        private var ak: String? = null
        private var sk: String? = null
        private var apiKey: String? = null
        private var region: String? = BASE_REGION
        private var baseUrl: String? = BASE_URL
        private var timeout: Duration = DEFAULT_TIMEOUT
        private var callTimeout: Duration? = null
        private var connectTimeout: Duration? = DEFAULT_CONNECT_TIMEOUT
        private var retryTimes: Int = DEFAULT_RETRY_TIMES
        private var proxy: Proxy? = null
        private var connectionPool: ConnectionPool? = null
        private var dispatcher: Dispatcher? = null
        private var callbackExecutor: Executor? = null

        fun ak(ak: String?): Builder {
            this.ak = ak
            return this
        }

        fun sk(sk: String?): Builder {
            this.sk = sk
            return this
        }

        fun apiKey(apiKey: String?): Builder {
            this.apiKey = apiKey
            return this
        }

        fun region(region: String?): Builder {
            this.region = region
            return this
        }

        fun baseUrl(baseUrl: String): Builder {
            this.baseUrl = baseUrl
            if (!baseUrl.endsWith("/")) {
                this.baseUrl = baseUrl.toString() + "/"
            }
            return this
        }

        fun timeout(timeout: Duration): Builder {
            this.timeout = timeout
            return this
        }

        fun callTimeout(callTimeout: Duration?): Builder {
            this.callTimeout = callTimeout
            return this
        }

        fun connectTimeout(connectTimeout: Duration?): Builder {
            this.connectTimeout = connectTimeout
            return this
        }

        fun retryTimes(retryTimes: Int): Builder {
            this.retryTimes = retryTimes
            return this
        }

        fun proxy(proxy: Proxy?): Builder {
            this.proxy = proxy
            return this
        }

        fun connectionPool(connectionPool: ConnectionPool?): Builder {
            this.connectionPool = connectionPool
            return this
        }

        fun dispatcher(dispatcher: Dispatcher?): Builder {
            this.dispatcher = dispatcher
            return this
        }

        fun callbackExecutor(callbackExecutor: Executor?): Builder {
            this.callbackExecutor = callbackExecutor
            return this
        }

        fun build(): ArkService {
            val mapper: ObjectMapper = com.volcengine.ark.runtime.service.ArkService.Companion.defaultObjectMapper()
            val clientBuilder: OkHttpClient.Builder = Builder()
            if (apiKey != null && apiKey.length() > 0) {
                clientBuilder.addInterceptor(AuthenticationInterceptor(apiKey))
                clientBuilder.addInterceptor(EncryptionInterceptor(apiKey, baseUrl))
            } else if (ak != null && sk != null && ak.length() > 0 && sk.length() > 0) {
                clientBuilder.addInterceptor(ArkResourceStsAuthenticationInterceptor(ak, sk, region))
            } else {
                throw ArkException("missing api_key or ak&sk.")
            }

            if (proxy != null) {
                clientBuilder.proxy(proxy)
            }

            if (connectionPool != null) {
                clientBuilder.connectionPool(connectionPool)
            } else {
                clientBuilder.connectionPool(ConnectionPool(5, 1, TimeUnit.SECONDS))
            }

            if (dispatcher != null) {
                clientBuilder.dispatcher(dispatcher)
            }

            val client: OkHttpClient = clientBuilder
                .addInterceptor(RequestIdInterceptor())
                .addInterceptor(RetryInterceptor(retryTimes))
                .addInterceptor(BatchInterceptor())
                .readTimeout(timeout.toMillis(), TimeUnit.MILLISECONDS)
                .callTimeout(if (callTimeout == null) timeout.toMillis() else callTimeout.toMillis(), TimeUnit.MILLISECONDS)
                .connectTimeout(connectTimeout)
                .build()
            val retrofit: Retrofit = com.volcengine.ark.runtime.service.ArkService.Companion.defaultRetrofit(client, mapper, baseUrl, callbackExecutor)
            return com.volcengine.ark.runtime.service.ArkService(
                retrofit.create(com.volcengine.ark.runtime.service.ArkApi::class.java),
                client.dispatcher().executorService()
            )
        }
    }

    companion object {
        private val mapper: ObjectMapper = com.volcengine.ark.runtime.service.ArkService.Companion.defaultObjectMapper()
        fun defaultObjectMapper(): ObjectMapper {
            val mapper: ObjectMapper = ObjectMapper()
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
            return mapper
        }

        fun defaultApiKeyClient(apiKey: String?, timeout: Duration): OkHttpClient {
            return Builder()
                .addInterceptor(AuthenticationInterceptor(apiKey))
                .addInterceptor(RequestIdInterceptor())
                .addInterceptor(RetryInterceptor(DEFAULT_RETRY_TIMES))
                .connectionPool(ConnectionPool(5, 1, TimeUnit.SECONDS))
                .readTimeout(timeout.toMillis(), TimeUnit.MILLISECONDS)
                .build()
        }

        fun defaultResourceStsClient(ak: String?, sk: String?, timeout: Duration, region: String?): OkHttpClient {
            return Builder()
                .addInterceptor(ArkResourceStsAuthenticationInterceptor(ak, sk, region))
                .addInterceptor(RequestIdInterceptor())
                .addInterceptor(RetryInterceptor(DEFAULT_RETRY_TIMES))
                .connectionPool(ConnectionPool(5, 1, TimeUnit.SECONDS))
                .readTimeout(timeout.toMillis(), TimeUnit.MILLISECONDS)
                .build()
        }

        fun defaultRetrofit(client: OkHttpClient?, mapper: ObjectMapper?, baseUrl: String?, callbackExecutor: Executor?): Retrofit {
            val builder: Retrofit.Builder = Builder()
                .baseUrl(baseUrl!!)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

            if (callbackExecutor != null) {
                // to avoid NPE
                builder.callbackExecutor(callbackExecutor)
            }

            return builder.build()
        }

        fun <T> execute(apiCall: Single<T?>): T? {
            try {
                val resp: T? = apiCall.blockingGet()
                return resp
            } catch (e: HttpException) {
                var requestId: String? = ""
                try {
                    val headers: Headers = e.response().raw().request().headers()
                    requestId = headers.get(Const.CLIENT_REQUEST_HEADER)
                } catch (ignored: Exception) {
                }

                try {
                    if (e.response() == null || e.response().errorBody() == null) {
                        throw e
                    }
                    val errorBody: String? = e.response().errorBody().string()

                    val error: ArkAPIError = com.volcengine.ark.runtime.service.ArkService.Companion.mapper.readValue(errorBody, ArkAPIError::class.java)
                    throw ArkHttpException(error, e, e.code(), requestId)
                } catch (ex: IOException) {
                    throw e
                }
            }
        }

        fun stream(apiCall: Call<ResponseBody?>): Flowable<SSE?> {
            return com.volcengine.ark.runtime.service.ArkService.Companion.stream(apiCall, false)
        }

        fun stream(apiCall: Call<ResponseBody?>, emitDone: Boolean): Flowable<SSE?> {
            return Flowable.create({ emitter -> apiCall.enqueue(ResponseBodyCallback(emitter, emitDone)) }, BackpressureStrategy.BUFFER)
        }

        fun <T> stream(apiCall: Call<ResponseBody?>, cl: Class<T?>?): Flowable<T?> {
            return com.volcengine.ark.runtime.service.ArkService.Companion.stream(apiCall).map({ sse -> com.volcengine.ark.runtime.service.ArkService.Companion.mapper.readValue(sse.getData(), cl) })
        }

        fun builder(): Builder {
            return com.volcengine.ark.runtime.service.ArkService.Builder()
        }
    }
}
