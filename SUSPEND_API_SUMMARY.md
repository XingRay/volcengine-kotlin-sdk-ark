# Service 层 API 设计 - 总结

## 修改概述

API 方法分为两类：
1. **同步返回的 API**：使用 `suspend` 函数
2. **流式返回的 API**：普通函数，返回 `Flow<T>`

## API 签名

### 同步 API（suspend 函数）
```kotlin
suspend fun createChatCompletion(
    request: ChatCompletionRequest,
    apiKey: String? = defaultApiKey,
    baseUrl: String = defaultBaseUrl
): ChatCompletionResult
```

### 流式 API（普通函数，返回 Flow）
```kotlin
fun streamChatCompletion(
    request: ChatCompletionRequest,
    apiKey: String? = defaultApiKey,
    baseUrl: String = defaultBaseUrl
): Flow<ChatCompletionChunk>
```

## 关键设计

### 1. 同步 API = suspend 函数
- ✅ 必须在协程作用域中调用
- ✅ 直接返回结果
- ✅ 使用 try-catch 处理错误

### 2. 流式 API = 普通函数 + Flow
- ✅ 可以在任何地方调用（获取 Flow）
- ✅ 返回 `Flow<T>`
- ✅ `Flow.collect` 是 suspend 函数，需要在协程中调用

## 使用示例

### 示例 1：同步 API 调用
```kotlin
suspend fun chatExample() {
    val client = ArkClient.create("your-api-key")

    val request = ChatCompletionRequest.builder()
        .model("doubao-pro-32k")
        .messages(listOf(
            ChatMessage.builder()
                .role(ChatMessageRole.USER)
                .content("Hello")
                .build()
        ))
        .build()

    // createChatCompletion 是 suspend 函数
    val result = client.createChatCompletion(request)
    println(result)

    client.close()
}
```

### 示例 2：流式 API 调用
```kotlin
fun streamExample() {
    val client = ArkClient.create("your-api-key")

    val request = ChatCompletionRequest.builder()
        .model("doubao-pro-32k")
        .messages(listOf(
            ChatMessage.builder()
                .role(ChatMessageRole.USER)
                .content("Tell me a story")
                .build()
        ))
        .build()

    // streamChatCompletion 不是 suspend 函数，可以在任何地方调用
    val flow = client.streamChatCompletion(request)

    // 但 collect 是 suspend 函数，需要在协程中调用
    runBlocking {
        flow.collect { chunk ->
            println(chunk)
        }
    }

    client.close()
}

// 或者直接在协程中链式调用
suspend fun streamExample2() {
    val client = ArkClient.create("your-api-key")

    val request = ChatCompletionRequest.builder()
        .model("doubao-pro-32k")
        .messages(listOf(
            ChatMessage.builder()
                .role(ChatMessageRole.USER)
                .content("Tell me a story")
                .build()
        ))
        .build()

    // 直接链式调用
    client.streamChatCompletion(request).collect { chunk ->
        println(chunk)
    }

    client.close()
}
```

### 示例 3：在 Android ViewModel 中使用
```kotlin
class ChatViewModel : ViewModel() {
    private val client = ArkClient.create("your-api-key")

    fun sendMessage(message: String) {
        viewModelScope.launch {
            try {
                val request = ChatCompletionRequest.builder()
                    .model("doubao-pro-32k")
                    .messages(listOf(
                        ChatMessage.builder()
                            .role(ChatMessageRole.USER)
                            .content(message)
                            .build()
                    ))
                    .build()

                val result = client.createChatCompletion(request)
                // 处理结果
            } catch (e: Exception) {
                // 处理错误
            }
        }
    }

    fun streamMessage(message: String) {
        viewModelScope.launch {
            try {
                val request = ChatCompletionRequest.builder()
                    .model("doubao-pro-32k")
                    .messages(listOf(
                        ChatMessage.builder()
                            .role(ChatMessageRole.USER)
                            .content(message)
                            .build()
                    ))
                    .build()

                client.streamChatCompletion(request).collect { chunk ->
                    // 处理流式响应
                }
            } catch (e: Exception) {
                // 处理错误
            }
        }
    }
}
```

### 示例 4：在 Ktor Server 中使用
```kotlin
fun Application.configureRouting() {
    routing {
        post("/chat") {
            val client = ArkClient.create("your-api-key")

            val request = call.receive<ChatCompletionRequest>()

            // 直接在 suspend 函数中调用
            val result = client.createChatCompletion(request)

            call.respond(result)
            client.close()
        }
    }
}
```

## 已修改的方法列表

### Chat Completion
- ✅ `suspend fun createChatCompletion(...)` - suspend 函数
- ✅ `suspend fun createBatchChatCompletion(...)` - suspend 函数
- ✅ `fun streamChatCompletion(...): Flow<ChatCompletionChunk>` - 返回 Flow

### Bot Chat Completion
- ✅ `suspend fun createBotChatCompletion(...)` - suspend 函数
- ✅ `fun streamBotChatCompletion(...): Flow<BotChatCompletionChunk>` - 返回 Flow

### Context
- ✅ `suspend fun createContext(...)` - suspend 函数
- ✅ `suspend fun createContextChatCompletion(...)` - suspend 函数
- ✅ `fun streamContextChatCompletion(...): Flow<ChatCompletionChunk>` - 返回 Flow

### Embeddings
- ✅ `suspend fun createEmbeddings(...)` - suspend 函数
- ✅ `suspend fun createBatchEmbeddings(...)` - suspend 函数

### Tokenization
- ✅ `suspend fun createTokenization(...)` - suspend 函数

### Images
- ✅ `suspend fun generateImages(...)` - suspend 函数
- ✅ `fun streamGenerateImages(...): Flow<ImageGenStreamEvent>` - 返回 Flow

### Content Generation
- ✅ `suspend fun createContentGenerationTask(...)` - suspend 函数
- ✅ `suspend fun getContentGenerationTask(...)` - suspend 函数
- ✅ `suspend fun listContentGenerationTasks(...)` - suspend 函数
- ✅ `suspend fun deleteContentGenerationTask(...)` - suspend 函数

### Files
- ✅ `suspend fun uploadFile(...)` - suspend 函数
- ✅ `suspend fun retrieveFile(...)` - suspend 函数
- ✅ `suspend fun deleteFile(...)` - suspend 函数
- ✅ `suspend fun listFiles(...)` - suspend 函数

## 优势

1. **清晰的 API 设计**: 同步用 suspend，流式用 Flow
2. **灵活性**: 流式 API 可以在任何地方获取 Flow，在需要时才 collect
3. **协程友好**: 完全拥抱 Kotlin 协程生态
4. **易于组合**: suspend 函数和 Flow 都可以轻松组合
5. **结构化并发**: 利用协程的结构化并发特性
6. **取消支持**: 自动支持协程取消

## 注意事项

1. **同步 API 必须在协程中调用**: 因为是 suspend 函数
2. **流式 API 可以在任何地方调用**: 但 collect 需要在协程中
3. **错误处理**: 使用 try-catch 处理异常
4. **资源管理**: 记得调用 `client.close()` 关闭客户端

## 设计理由

### 为什么同步 API 是 suspend 函数？
- 需要等待网络响应
- 可能阻塞线程
- 使用 suspend 可以非阻塞地等待

### 为什么流式 API 不是 suspend 函数？
- 返回 Flow 本身不需要等待
- Flow 是冷流，只有在 collect 时才开始执行
- 这样可以先获取 Flow，稍后再决定何时 collect
- 更灵活的使用方式

### 示例：流式 API 的灵活性
```kotlin
// 可以先获取 Flow（不需要协程）
val flow1 = client.streamChatCompletion(request1)
val flow2 = client.streamChatCompletion(request2)

// 稍后在协程中并发 collect
runBlocking {
    launch { flow1.collect { println("Flow1: $it") } }
    launch { flow2.collect { println("Flow2: $it") } }
}
```

---

**修改完成时间**: 2026-03-12
**状态**: ✅ 所有 API 方法都已改为 suspend 函数
