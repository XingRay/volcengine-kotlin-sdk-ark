package com.volcengine.ark.runtime.service

import com.volcengine.ark.runtime.Const

interface ArkApi {
    @POST("/api/v3/chat/completions")
    fun createChatCompletion(@Body request: ChatCompletionRequest?, @Header(Const.REQUEST_MODEL) model: String?, @HeaderMap customHeaders: Map<String?, String?>?): Single<ChatCompletionResult?>?

    @POST("/api/v3/batch/chat/completions")
    fun createBatchChatCompletion(@Body request: ChatCompletionRequest?, @Header(Const.REQUEST_MODEL) model: String?, @HeaderMap customHeaders: Map<String?, String?>?): Single<ChatCompletionResult?>?

    @Streaming
    @POST("/api/v3/chat/completions")
    fun createChatCompletionStream(@Body request: ChatCompletionRequest?, @Header(Const.REQUEST_MODEL) model: String?, @HeaderMap customHeaders: Map<String?, String?>?): Call<ResponseBody?>?

    @POST("/api/v3/bots/chat/completions")
    fun createBotChatCompletion(
        @Body request: BotChatCompletionRequest?,
        @Header(Const.REQUEST_BOT) botId: String?,
        @HeaderMap customHeaders: Map<String?, String?>?
    ): Single<BotChatCompletionResult?>?

    @Streaming
    @POST("/api/v3/bots/chat/completions")
    fun createBotChatCompletionStream(@Body request: BotChatCompletionRequest?, @Header(Const.REQUEST_BOT) botId: String?, @HeaderMap customHeaders: Map<String?, String?>?): Call<ResponseBody?>?

    @POST("/api/v3/context/create")
    fun createContext(@Body request: CreateContextRequest?, @Header(Const.REQUEST_MODEL) model: String?, @HeaderMap customHeaders: Map<String?, String?>?): Single<CreateContextResult?>?

    @POST("/api/v3/context/chat/completions")
    fun createContextChatCompletion(
        @Body request: ContextChatCompletionRequest?,
        @Header(Const.REQUEST_MODEL) model: String?,
        @HeaderMap customHeaders: Map<String?, String?>?
    ): Single<ChatCompletionResult?>?

    @Streaming
    @POST("/api/v3/context/chat/completions")
    fun createContextChatCompletionStream(
        @Body request: ContextChatCompletionRequest?,
        @Header(Const.REQUEST_MODEL) model: String?,
        @HeaderMap customHeaders: Map<String?, String?>?
    ): Call<ResponseBody?>?

    @POST("/api/v3/embeddings")
    fun createEmbeddings(@Body request: EmbeddingRequest?, @Header(Const.REQUEST_MODEL) model: String?, @HeaderMap customHeaders: Map<String?, String?>?): Single<EmbeddingResult?>?

    @POST("/api/v3/batch/embeddings")
    fun createBatchEmbeddings(@Body request: EmbeddingRequest?, @Header(Const.REQUEST_MODEL) model: String?, @HeaderMap customHeaders: Map<String?, String?>?): Single<EmbeddingResult?>?

    @POST("/api/v3/embeddings/multimodal")
    fun createMultiModalEmbeddings(
        @Body request: MultimodalEmbeddingRequest?,
        @Header(Const.REQUEST_MODEL) model: String?,
        @HeaderMap customHeaders: Map<String?, String?>?
    ): Single<MultimodalEmbeddingResult?>?

    @POST("/api/v3/batch/embeddings/multimodal")
    fun createBatchMultiModalEmbeddings(
        @Body request: MultimodalEmbeddingRequest?,
        @Header(Const.REQUEST_MODEL) model: String?,
        @HeaderMap customHeaders: Map<String?, String?>?
    ): Single<MultimodalEmbeddingResult?>?

    @POST("/api/v3/tokenization")
    fun createTokenization(@Body request: TokenizationRequest?, @Header(Const.REQUEST_MODEL) model: String?, @HeaderMap customHeaders: Map<String?, String?>?): Single<TokenizationResult?>?

    @POST("/api/v3/images/generations")
    fun generateImages(@Body request: GenerateImagesRequest?, @Header(Const.REQUEST_MODEL) model: String?, @HeaderMap customHeaders: Map<String?, String?>?): Single<ImagesResponse?>?

    @Streaming
    @POST("/api/v3/images/generations")
    fun streamGenerateImages(@Body request: GenerateImagesRequest?, @Header(Const.REQUEST_MODEL) model: String?, @HeaderMap customHeaders: Map<String?, String?>?): Call<ResponseBody?>?

    @POST("/api/v3/contents/generations/tasks")
    fun createContentGenerationTask(
        @Body request: com.volcengine.ark.runtime.model.content.generation.CreateContentGenerationTaskRequest?,
        @Header(Const.REQUEST_MODEL) model: String?,
        @HeaderMap customHeaders: Map<String?, String?>?
    ): Single<com.volcengine.ark.runtime.model.content.generation.CreateContentGenerationTaskResult?>?

    @GET("/api/v3/contents/generations/tasks/{taskId}")
    fun getContentGenerationTask(
        @Path("taskId") taskId: String?,
        @HeaderMap customHeaders: Map<String?, String?>?
    ): Single<com.volcengine.ark.runtime.model.content.generation.GetContentGenerationTaskResponse?>?

    @GET("/api/v3/contents/generations/tasks")
    fun listContentGenerationTasks(
        @Query("page_num") pageNum: Int,
        @Query("page_size") pageSize: Int,
        @Query("filter.status") status: String?,
        @Query("filter.model") model: String?,
        @Query("filter.service_tier") serviceTier: String?,
        @Query("filter.task_ids") taskIds: List<String?>?,
        @HeaderMap customHeaders: Map<String?, String?>?
    ): Single<com.volcengine.ark.runtime.model.content.generation.ListContentGenerationTasksResponse?>?

    @DELETE("/api/v3/contents/generations/tasks/{taskId}")
    fun deleteContentGenerationTask(
        @Path("taskId") taskId: String?,
        @HeaderMap customHeaders: Map<String?, String?>?
    ): Single<com.volcengine.ark.runtime.model.content.generation.DeleteContentGenerationTaskResponse?>?

    @POST("/api/v3/responses")
    fun createResponse(@Body request: CreateResponsesRequest?, @Header(Const.REQUEST_MODEL) model: String?, @HeaderMap customHeaders: Map<String?, String?>?): Single<ResponseObject?>?

    @Streaming
    @POST("/api/v3/responses")
    fun streamResponse(@Body request: CreateResponsesRequest?, @Header(Const.REQUEST_MODEL) model: String?, @HeaderMap customHeaders: Map<String?, String?>?): Call<ResponseBody?>?

    @GET("/api/v3/responses/{responseId}")
    fun getResponse(@Path("responseId") responsesId: String?, @HeaderMap customHeaders: Map<String?, String?>?): Single<ResponseObject?>?

    @DELETE("/api/v3/responses/{responseId}")
    fun deleteResponse(@Path("responseId") responsesId: String?, @HeaderMap customHeaders: Map<String?, String?>?): Single<DeleteResponseResponse?>?

    @GET("/api/v3/responses/{responseId}/input_items")
    fun listResponseInputItems(
        @Path("responseId") responsesId: String?,
        @Query("after") after: String?,
        @Query("before") before: String?,
        @Query("limit") limit: Integer?,
        @Query("include[]") include: List<String?>?,
        @HeaderMap customHeaders: Map<String?, String?>?
    ): Single<ListInputItemsResponse?>?

    @Multipart
    @POST("/api/v3/files")
    fun uploadFile(
        @Part file: MultipartBody.Part?,
        @Part("purpose") purpose: RequestBody?,
        @Part("expire_at") expireAt: RequestBody?,
        @Part("preprocess_configs[video][fps]") fps: RequestBody?,
        @HeaderMap customHeaders: Map<String?, String?>?
    ): Single<FileMeta?>?

    @DELETE("/api/v3/files/{fileId}")
    fun deleteFile(@Path("fileId") fileId: String?, @HeaderMap customHeaders: Map<String?, String?>?): Single<DeleteFileResponse?>?

    @GET("/api/v3/files/{fileId}")
    fun retrieveFile(@Path("fileId") fileId: String?, @HeaderMap customHeaders: Map<String?, String?>?): Single<FileMeta?>?

    @GET("/api/v3/files")
    fun listFiles(
        @Query("limit") limit: Integer?,
        @Query("after") after: String?,
        @Query("purpose") purpose: String?,
        @Query("order") order: String?,
        @HeaderMap customHeaders: Map<String?, String?>?
    ): Single<ListFilesResponse?>?
}
