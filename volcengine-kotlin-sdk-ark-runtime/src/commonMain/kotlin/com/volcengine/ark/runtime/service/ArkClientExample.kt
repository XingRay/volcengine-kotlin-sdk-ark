package com.volcengine.ark.runtime.service

import com.volcengine.ark.runtime.model.completion.chat.*
import io.ktor.client.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.collect

/**
 * ArkClient 使用示例
 */
object ArkClientExample {

    /**
     * 示例 1：使用默认配置创建客户端
     */
    fun example1_basicUsage() = runBlocking {
        // 创建客户端（使用默认 HttpClient）
        val client = ArkClient.create(
            apiKey = "your-api-key",
            baseUrl = "https://ark.cn-beijing.volces.com"
        )

        // 创建聊天补全请求
        val request = ChatCompletionRequest.builder()
            .model("doubao-pro-32k")
            .messages(listOf(
                ChatMessage.builder()
                    .role(ChatMessageRole.USER)
                    .content("Hello, how are you?")
                    .build()
            ))
            .build()

        // 发送请求
        val result = client.createChatCompletion(request)
        println("Response: ${result}")

        // 关闭客户端
        client.close()
    }

    /**
     * 示例 2：使用自定义 HttpClient
     */
    fun example2_customHttpClient() = runBlocking {
        // 创建自定义 HttpClient
        val httpClient = KtorClientConfig.createDefaultClient(
            apiKey = "your-api-key",
            timeout = kotlin.time.Duration.parse("5m"),
            enableLogging = true
        )

        // 创建客户端
        val client = ArkClient(
            httpClient = httpClient,
            defaultApiKey = "your-api-key",
            defaultBaseUrl = "https://ark.cn-beijing.volces.com"
        )

        // 使用客户端...
        client.close()
    }

    /**
     * 示例 3：流式聊天补全
     */
    fun example3_streamChatCompletion() = runBlocking {
        val client = ArkClient.create("your-api-key")

        val request = ChatCompletionRequest.builder()
            .model("doubao-pro-32k")
            .messages(listOf(
                ChatMessage.builder()
                    .role(ChatMessageRole.USER)
                    .content("Tell me a story")
                    .build()
            ))
            .stream(true)
            .build()

        // 流式接收响应（streamChatCompletion 返回 Flow，不是 suspend 函数）
        client.streamChatCompletion(request).collect { chunk ->
            println("Chunk: ${chunk}")
        }

        client.close()
    }

    /**
     * 示例 4：每个请求使用不同的 API Key 和 Base URL
     */
    fun example4_perRequestConfig() = runBlocking {
        val client = ArkClient(
            httpClient = KtorClientConfig.createDefaultClient(),
            defaultApiKey = "default-api-key",
            defaultBaseUrl = "https://ark.cn-beijing.volces.com"
        )

        val request = ChatCompletionRequest.builder()
            .model("doubao-pro-32k")
            .messages(listOf(
                ChatMessage.builder()
                    .role(ChatMessageRole.USER)
                    .content("Hello")
                    .build()
            ))
            .build()

        // 使用不同的 API Key 和 Base URL
        val result = client.createChatCompletion(
            request = request,
            apiKey = "another-api-key",
            baseUrl = "https://custom-endpoint.com"
        )

        println("Response: ${result}")
        client.close()
    }

    /**
     * 示例 5：创建嵌入
     */
    fun example5_createEmbeddings() = runBlocking {
        val client = ArkClient.create("your-api-key")

        val request = com.volcengine.ark.runtime.model.embeddings.EmbeddingRequest.builder()
            .model("doubao-embedding")
            .input(listOf("Hello, world!"))
            .build()

        val result = client.createEmbeddings(request)
        println("Embeddings: ${result}")

        client.close()
    }

    /**
     * 示例 6：Bot 聊天补全
     */
    fun example6_botChatCompletion() = runBlocking {
        val client = ArkClient.create("your-api-key")

        val request = com.volcengine.ark.runtime.model.bot.completion.chat.BotChatCompletionRequest.builder()
            .model("bot-id")
            .messages(listOf(
                ChatMessage.builder()
                    .role(ChatMessageRole.USER)
                    .content("Hello")
                    .build()
            ))
            .build()

        val result = client.createBotChatCompletion(request)
        println("Bot Response: ${result}")

        client.close()
    }

    /**
     * 示例 7：使用多内容消息（图片 + 文本）
     */
    fun example7_multiContentMessage() = runBlocking {
        val client = ArkClient.create("your-api-key")

        val contentParts = listOf(
            ContentPart.TextPart(text = "What's in this image?"),
            ContentPart.ImageUrlPart(
                imageUrl = ImageUrl(
                    url = "https://example.com/image.jpg",
                    detail = "high"
                )
            )
        )

        val request = ChatCompletionRequest.builder()
            .model("doubao-vision")
            .messages(listOf(
                ChatMessage.builder()
                    .role(ChatMessageRole.USER)
                    .multiContent(contentParts)
                    .build()
            ))
            .build()

        val result = client.createChatCompletion(request)
        println("Response: ${result}")

        client.close()
    }

    /**
     * 示例 8：使用视频内容
     */
    fun example8_videoContent() = runBlocking {
        val client = ArkClient.create("your-api-key")

        val contentParts = listOf(
            ContentPart.VideoUrlPart(
                videoUrl = VideoUrl(url = "https://example.com/video.mp4"),
                fps = 2.0
            ),
            ContentPart.TextPart(text = "What is in the video?")
        )

        val request = ChatCompletionRequest.builder()
            .model("doubao-vision")
            .messages(listOf(
                ChatMessage.builder()
                    .role(ChatMessageRole.USER)
                    .multiContent(contentParts)
                    .build()
            ))
            .build()

        val result = client.createChatCompletion(request)
        println("Response: ${result}")

        client.close()
    }
}
