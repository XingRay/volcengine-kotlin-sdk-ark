package com.volcengine.ark.runtime.service

import com.volcengine.ark.runtime.model.bot.completion.chat.BotChatCompletionChunk
import com.volcengine.ark.runtime.model.bot.completion.chat.BotChatCompletionRequest
import com.volcengine.ark.runtime.model.bot.completion.chat.BotChatCompletionResult
import com.volcengine.ark.runtime.model.completion.chat.ChatCompletionChunk
import com.volcengine.ark.runtime.model.completion.chat.ChatCompletionRequest
import com.volcengine.ark.runtime.model.completion.chat.ChatCompletionResult
import com.volcengine.ark.runtime.model.content.generation.*
import com.volcengine.ark.runtime.model.context.CreateContextRequest
import com.volcengine.ark.runtime.model.context.CreateContextResult
import com.volcengine.ark.runtime.model.context.chat.ContextChatCompletionRequest
import com.volcengine.ark.runtime.model.embeddings.EmbeddingRequest
import com.volcengine.ark.runtime.model.embeddings.EmbeddingResult
import com.volcengine.ark.runtime.model.files.DeleteFileResponse
import com.volcengine.ark.runtime.model.files.FileMeta
import com.volcengine.ark.runtime.model.files.ListFilesRequest
import com.volcengine.ark.runtime.model.files.ListFilesResponse
import com.volcengine.ark.runtime.model.images.generation.GenerateImagesRequest
import com.volcengine.ark.runtime.model.images.generation.ImageGenStreamEvent
import com.volcengine.ark.runtime.model.images.generation.ImagesResponse
import com.volcengine.ark.runtime.model.tokenization.TokenizationRequest
import com.volcengine.ark.runtime.model.tokenization.TokenizationResult
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.sse.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

/**
 * Ark API 客户端
 *
 * @param httpClient Ktor HttpClient 实例
 * @param defaultApiKey 默认 API Key
 * @param defaultBaseUrl 默认 Base URL
 */
class ArkClient(

    private val httpClient: HttpClient,
    val defaultApiKey: String? = null,
    val defaultBaseUrl: String = ArkConstants.BASE_URL,
    private val json: Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    },
) {

    // ==================== Chat Completion ====================

    /**
     * 创建聊天补全
     */
    suspend fun createChatCompletion(
        request: ChatCompletionRequest,
        apiKey: String? = defaultApiKey,
        baseUrl: String = defaultBaseUrl
    ): ChatCompletionResult {
        return httpClient.post("$baseUrl/api/v3/chat/completions") {
            apiKey?.let { bearerAuth(it) }
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    /**
     * 创建批量聊天补全
     */
    suspend fun createBatchChatCompletion(
        request: ChatCompletionRequest,
        apiKey: String? = defaultApiKey,
        baseUrl: String = defaultBaseUrl
    ): ChatCompletionResult {
        return httpClient.post("$baseUrl/api/v3/batch/chat/completions") {
            apiKey?.let { bearerAuth(it) }
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    /**
     * 流式聊天补全
     */
    fun streamChatCompletion(
        request: ChatCompletionRequest,
        apiKey: String? = defaultApiKey,
        baseUrl: String = defaultBaseUrl
    ): Flow<ChatCompletionChunk> = flow {
        println("Starting stream request")
        try {
            httpClient.sse(
                request = {
                    url("$baseUrl/api/v3/chat/completions")
                    apiKey?.let { bearerAuth(it) }
                    contentType(ContentType.Application.Json)
                    setBody(request.copy(stream = true))
                    method = HttpMethod.Post
                }
            ) {
                println("SSE connection established")
                incoming.collect { event ->
                    println("Received SSE event: ${event.event}, data: ${event.data}")
                    event.data?.let { data ->
                        if (data != "[DONE]") {
                            try {
                                val chunk = json.decodeFromString<ChatCompletionChunk>(data)
                                println("Parsed chunk successfully, emitting")
                                emit(chunk)
                                println("Chunk emitted")
                            } catch (e: Exception) {
                                println("Failed to parse SSE chunk: ${e.message}")
                                println("Raw data: $data")
                            }
                        }
                    }
                }
                println("SSE stream completed")
            }
        } catch (e: Exception) {
            println("SSE stream error: ${e.message}")
            throw e
        }
    }

    // ==================== Bot Chat Completion ====================

    /**
     * 创建 Bot 聊天补全
     */
    suspend fun createBotChatCompletion(
        request: BotChatCompletionRequest,
        apiKey: String? = defaultApiKey,
        baseUrl: String = defaultBaseUrl
    ): BotChatCompletionResult {
        return httpClient.post("$baseUrl/api/v3/bots/chat/completions") {
            apiKey?.let { bearerAuth(it) }
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    /**
     * 流式 Bot 聊天补全
     */
    fun streamBotChatCompletion(
        request: BotChatCompletionRequest,
        apiKey: String? = defaultApiKey,
        baseUrl: String = defaultBaseUrl
    ): Flow<BotChatCompletionChunk> = flow {
        try {
            httpClient.sse(
                request = {
                    url("$baseUrl/api/v3/bots/chat/completions")
                    apiKey?.let { bearerAuth(it) }
                    contentType(ContentType.Application.Json)
                    setBody(request.copy(stream = true))
                    method = HttpMethod.Post
                }
            ) {
                incoming.collect { event ->
                    event.data?.let { data ->
                        if (data != "[DONE]") {
                            try {
                                val chunk = json.decodeFromString<BotChatCompletionChunk>(data)
                                emit(chunk)
                            } catch (e: Exception) {
                                println("Failed to parse SSE chunk: ${e.message}")
                                println("Raw data: $data")
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            println("SSE stream error: ${e.message}")
            throw e
        }
    }

    // ==================== Context ====================

    /**
     * 创建上下文
     */
    suspend fun createContext(
        request: CreateContextRequest,
        apiKey: String? = defaultApiKey,
        baseUrl: String = defaultBaseUrl
    ): CreateContextResult {
        return httpClient.post("$baseUrl/api/v3/context/create") {
            apiKey?.let { bearerAuth(it) }
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    /**
     * 创建上下文聊天补全
     */
    suspend fun createContextChatCompletion(
        request: ContextChatCompletionRequest,
        apiKey: String? = defaultApiKey,
        baseUrl: String = defaultBaseUrl
    ): ChatCompletionResult {
        return httpClient.post("$baseUrl/api/v3/context/chat/completions") {
            apiKey?.let { bearerAuth(it) }
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    /**
     * 流式上下文聊天补全
     */
    fun streamContextChatCompletion(
        request: ContextChatCompletionRequest,
        apiKey: String? = defaultApiKey,
        baseUrl: String = defaultBaseUrl
    ): Flow<ChatCompletionChunk> = flow {
        try {
            httpClient.sse(
                request = {
                    url("$baseUrl/api/v3/context/chat/completions")
                    apiKey?.let { bearerAuth(it) }
                    contentType(ContentType.Application.Json)
                    setBody(request.copy(stream = true))
                    method = HttpMethod.Post
                }
            ) {
                incoming.collect { event ->
                    event.data?.let { data ->
                        if (data != "[DONE]") {
                            try {
                                val chunk = json.decodeFromString<ChatCompletionChunk>(data)
                                emit(chunk)
                            } catch (e: Exception) {
                                println("Failed to parse SSE chunk: ${e.message}")
                                println("Raw data: $data")
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            println("SSE stream error: ${e.message}")
            throw e
        }
    }

    // ==================== Embeddings ====================

    /**
     * 创建嵌入
     */
    suspend fun createEmbeddings(
        request: EmbeddingRequest,
        apiKey: String? = defaultApiKey,
        baseUrl: String = defaultBaseUrl
    ): EmbeddingResult {
        return httpClient.post("$baseUrl/api/v3/embeddings") {
            apiKey?.let { bearerAuth(it) }
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    /**
     * 创建批量嵌入
     */
    suspend fun createBatchEmbeddings(
        request: EmbeddingRequest,
        apiKey: String? = defaultApiKey,
        baseUrl: String = defaultBaseUrl
    ): EmbeddingResult {
        return httpClient.post("$baseUrl/api/v3/batch/embeddings") {
            apiKey?.let { bearerAuth(it) }
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    // ==================== Tokenization ====================

    /**
     * 创建分词
     */
    suspend fun createTokenization(
        request: TokenizationRequest,
        apiKey: String? = defaultApiKey,
        baseUrl: String = defaultBaseUrl
    ): TokenizationResult {
        return httpClient.post("$baseUrl/api/v3/tokenization") {
            apiKey?.let { bearerAuth(it) }
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    // ==================== Images ====================

    /**
     * 生成图片
     */
    suspend fun generateImages(
        request: GenerateImagesRequest,
        apiKey: String? = defaultApiKey,
        baseUrl: String = defaultBaseUrl
    ): ImagesResponse {
        return httpClient.post("$baseUrl/api/v3/images/generations") {
            apiKey?.let { bearerAuth(it) }
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    /**
     * 流式生成图片
     */
    fun streamGenerateImages(
        request: GenerateImagesRequest,
        apiKey: String? = defaultApiKey,
        baseUrl: String = defaultBaseUrl
    ): Flow<ImageGenStreamEvent> = flow {
        try {
            httpClient.sse(
                request = {
                    url("$baseUrl/api/v3/images/generations")
                    apiKey?.let { bearerAuth(it) }
                    contentType(ContentType.Application.Json)
                    setBody(request.copy(stream = true))
                    method = HttpMethod.Post
                }
            ) {
                incoming.collect { event ->
                    event.data?.let { data ->
                        if (data != "[DONE]") {
                            try {
                                val streamEvent = json.decodeFromString<ImageGenStreamEvent>(data)
                                emit(streamEvent)
                            } catch (e: Exception) {
                                println("Failed to parse SSE chunk: ${e.message}")
                                println("Raw data: $data")
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            println("SSE stream error: ${e.message}")
            throw e
        }
    }

    // ==================== Content Generation ====================

    /**
     * 创建内容生成任务
     */
    suspend fun createContentGenerationTask(
        request: CreateContentGenerationTaskRequest,
        apiKey: String? = defaultApiKey,
        baseUrl: String = defaultBaseUrl
    ): CreateContentGenerationTaskResult {
        return httpClient.post("$baseUrl/api/v3/contents/generations/tasks") {
            apiKey?.let { bearerAuth(it) }
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    /**
     * 获取内容生成任务
     */
    suspend fun getContentGenerationTask(
        request: GetContentGenerationTaskRequest,
        apiKey: String? = defaultApiKey,
        baseUrl: String = defaultBaseUrl
    ): GetContentGenerationTaskResponse {
        return httpClient.get("$baseUrl/api/v3/contents/generations/tasks/${request.taskId}") {
            apiKey?.let { bearerAuth(it) }
        }.body()
    }

    /**
     * 列出内容生成任务
     */
    suspend fun listContentGenerationTasks(
        request: ListContentGenerationTasksRequest,
        apiKey: String? = defaultApiKey,
        baseUrl: String = defaultBaseUrl
    ): ListContentGenerationTasksResponse {
        return httpClient.get("$baseUrl/api/v3/contents/generations/tasks") {
            apiKey?.let { bearerAuth(it) }
            parameter("page_num", request.pageNum)
            parameter("page_size", request.pageSize)
            request.status?.let { parameter("filter.status", it) }
            request.model?.let { parameter("filter.model", it) }
            request.serviceTier?.let { parameter("filter.service_tier", it) }
            request.taskIds?.let { parameter("filter.task_ids", it) }
        }.body()
    }

    /**
     * 删除内容生成任务
     */
    suspend fun deleteContentGenerationTask(
        request: DeleteContentGenerationTaskRequest,
        apiKey: String? = defaultApiKey,
        baseUrl: String = defaultBaseUrl
    ): DeleteContentGenerationTaskResponse {
        return httpClient.delete("$baseUrl/api/v3/contents/generations/tasks/${request.taskId}") {
            apiKey?.let { bearerAuth(it) }
        }.body()
    }

    // ==================== Files ====================

    /**
     * 上传文件
     */
    suspend fun uploadFile(
        fileBytes: ByteArray,
        filename: String,
        purpose: String = "user_data",
        apiKey: String? = defaultApiKey,
        baseUrl: String = defaultBaseUrl
    ): FileMeta {
        return httpClient.submitFormWithBinaryData(
            url = "$baseUrl/api/v3/files",
            formData = formData {
                append("file", fileBytes, Headers.build {
                    append(HttpHeaders.ContentType, "application/octet-stream")
                    append(HttpHeaders.ContentDisposition, "filename=\"$filename\"")
                })
                append("purpose", purpose)
            }
        ) {
            apiKey?.let { bearerAuth(it) }
        }.body()
    }

    /**
     * 获取文件信息
     */
    suspend fun retrieveFile(
        fileId: String,
        apiKey: String? = defaultApiKey,
        baseUrl: String = defaultBaseUrl
    ): FileMeta {
        return httpClient.get("$baseUrl/api/v3/files/$fileId") {
            apiKey?.let { bearerAuth(it) }
        }.body()
    }

    /**
     * 删除文件
     */
    suspend fun deleteFile(
        fileId: String,
        apiKey: String? = defaultApiKey,
        baseUrl: String = defaultBaseUrl
    ): DeleteFileResponse {
        return httpClient.delete("$baseUrl/api/v3/files/$fileId") {
            apiKey?.let { bearerAuth(it) }
        }.body()
    }

    /**
     * 列出文件
     */
    suspend fun listFiles(
        request: ListFilesRequest,
        apiKey: String? = defaultApiKey,
        baseUrl: String = defaultBaseUrl
    ): ListFilesResponse {
        return httpClient.get("$baseUrl/api/v3/files") {
            apiKey?.let { bearerAuth(it) }
            request.limit?.let { parameter("limit", it) }
            request.after?.let { parameter("after", it) }
            request.purpose?.let { parameter("purpose", it) }
            request.order?.let { parameter("order", it) }
        }.body()
    }

    /**
     * 关闭客户端
     */
    fun close() {
        httpClient.close()
    }

}
