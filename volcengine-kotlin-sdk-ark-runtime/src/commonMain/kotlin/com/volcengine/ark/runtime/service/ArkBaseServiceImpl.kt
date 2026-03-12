package com.volcengine.ark.runtime.service

import com.volcengine.ark.runtime.model.bot.completion.chat.BotChatCompletionChunk

interface ArkBaseServiceImpl {
    fun createChatCompletion(request: ChatCompletionRequest?): ChatCompletionResult?

    fun createBatchChatCompletion(request: ChatCompletionRequest?): ChatCompletionResult?

    fun streamChatCompletion(request: ChatCompletionRequest?): Flowable<ChatCompletionChunk?>?

    fun createBotChatCompletion(request: BotChatCompletionRequest?): BotChatCompletionResult?

    fun streamBotChatCompletion(request: BotChatCompletionRequest?): Flowable<BotChatCompletionChunk?>?

    fun createContext(request: CreateContextRequest?): CreateContextResult?

    fun createContextChatCompletion(request: ContextChatCompletionRequest?): ChatCompletionResult?

    fun createResponse(request: CreateResponsesRequest?): ResponseObject?

    fun streamResponse(request: CreateResponsesRequest?): Flowable<StreamEvent?>?

    fun streamContextChatCompletion(request: ContextChatCompletionRequest?): Flowable<ChatCompletionChunk?>?

    fun createTokenization(request: TokenizationRequest?): TokenizationResult?

    fun createEmbeddings(request: EmbeddingRequest?): EmbeddingResult?

    fun createBatchEmbeddings(request: EmbeddingRequest?): EmbeddingResult?

    fun createMultiModalEmbeddings(request: MultimodalEmbeddingRequest?): MultimodalEmbeddingResult?

    fun createBatchMultiModalEmbeddings(request: MultimodalEmbeddingRequest?): MultimodalEmbeddingResult?

    fun generateImages(request: GenerateImagesRequest?): ImagesResponse?

    fun createContentGenerationTask(request: CreateContentGenerationTaskRequest?): CreateContentGenerationTaskResult?

    fun getContentGenerationTask(request: GetContentGenerationTaskRequest?): GetContentGenerationTaskResponse?

    fun listContentGenerationTasks(request: ListContentGenerationTasksRequest?): ListContentGenerationTasksResponse?

    fun deleteContentGenerationTask(request: DeleteContentGenerationTaskRequest?): DeleteContentGenerationTaskResponse?

    fun getResponse(request: GetResponseRequest?): ResponseObject?

    fun deleteResponse(request: DeleteResponseRequest?): DeleteResponseResponse?

    fun listResponseInputItems(request: ListInputItemsRequest?): ListInputItemsResponse?

    fun uploadFile(file: UploadFileRequest?): FileMeta?

    fun retrieveFile(fileId: String?): FileMeta?

    fun deleteFile(fileId: String?): DeleteFileResponse?

    fun listFiles(request: ListFilesRequest?): ListFilesResponse?
}
