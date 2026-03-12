# Service 层重构 - 迁移指南

## 概述

Service 层已从 Retrofit + OkHttp + RxJava 重构为 Ktor + Kotlin Coroutines + Flow，提供更现代化、更符合 Kotlin 多平台的 API。

## 核心改进

### 1. 技术栈升级
- **旧**: Retrofit + OkHttp + RxJava
- **新**: Ktor + Kotlin Coroutines + Flow

### 2. API 设计改进
- **旧**: 每个 API 只接受 request 参数
- **新**: 每个 API 接受 `request + apiKey + baseUrl` 参数，支持每个请求自定义配置

### 3. 异步模型
- **旧**: RxJava `Single<T>` 和 `Flowable<T>`
- **新**: Kotlin Coroutines `suspend fun` 和 `Flow<T>`

## 新的 API 结构

### ArkClient 类

```kotlin
class ArkClient(
    private val httpClient: HttpClient,
    val defaultApiKey: String? = null,
    val defaultBaseUrl: String = "https://ark.cn-beijing.volces.com"
)
```

### API 方法签名

API 方法分为两类：

**1. 同步返回的 API（suspend 函数）**
```kotlin
suspend fun createChatCompletion(
    request: ChatCompletionRequest,
    apiKey: String? = defaultApiKey,      // 可选，默认使用 client 的 defaultApiKey
    baseUrl: String = defaultBaseUrl      // 可选，默认使用 client 的 defaultBaseUrl
): ChatCompletionResult
```

**2. 流式返回的 API（普通函数，返回 Flow）**
```kotlin
fun streamChatCompletion(
    request: ChatCompletionRequest,
    apiKey: String? = defaultApiKey,
    baseUrl: String = defaultBaseUrl
): Flow<ChatCompletionChunk>
```

**重要**:
- 同步 API 是 `suspend` 函数，必须在协程作用域中调用
- 流式 API 是普通函数，返回 `Flow<T>`，Flow 的 `collect` 是 suspend 函数

## 迁移示例

### 创建客户端

#### 旧方式
```kotlin
// 使用 API Key
val service = ArkService("your-api-key")

// 使用 Builder
val service = ArkService.builder()
    .apiKey("your-api-key")
    .baseUrl("https://custom-endpoint.com")
    .timeout(Duration.ofMinutes(5))
    .build()
```

#### 新方式
```kotlin
// 使用默认配置
val client = ArkClient.create(
    apiKey = "your-api-key",
    baseUrl = "https://ark.cn-beijing.volces.com"
)

// 使用自定义 HttpClient
val httpClient = KtorClientConfig.createDefaultClient(
    apiKey = "your-api-key",
    timeout = 5.minutes,
    enableLogging = true
)
val client = ArkClient(
    httpClient = httpClient,
    defaultApiKey = "your-api-key",
    defaultBaseUrl = "https://ark.cn-beijing.volces.com"
)
```

### 同步调用

#### 旧方式
```kotlin
val request = ChatCompletionRequest.builder()
    .model("doubao-pro-32k")
    .messages(messages)
    .build()

// 阻塞调用
val result = service.createChatCompletion(request)
```

#### 新方式
```kotlin
val request = ChatCompletionRequest.builder()
    .model("doubao-pro-32k")
    .messages(messages)
    .build()

// 协程调用（同步 API 是 suspend 函数）
runBlocking {
    val result = client.createChatCompletion(request)
}

// 或在协程作用域中
suspend fun example() {
    val result = client.createChatCompletion(request)
}
```

### 流式调用

#### 旧方式
```kotlin
service.streamChatCompletion(request)
    .subscribe(
        { chunk -> println(chunk) },
        { error -> println("Error: $error") },
        { println("Completed") }
    )
```

#### 新方式
```kotlin
// streamChatCompletion 返回 Flow（不是 suspend 函数）
// 但 Flow.collect 是 suspend 函数，需要在协程作用域中调用
runBlocking {
    client.streamChatCompletion(request).collect { chunk ->
        println(chunk)
    }
}

// 或使用 try-catch 处理错误
runBlocking {
    try {
        client.streamChatCompletion(request).collect { chunk ->
            println(chunk)
        }
    } catch (e: Exception) {
        println("Error: $e")
    }
}

// 在协程作用域外可以获取 Flow，但需要在协程中 collect
val flow = client.streamChatCompletion(request)  // 不需要协程
runBlocking {
    flow.collect { chunk -> println(chunk) }  // collect 需要协程
}
```

**注意**:
- `streamChatCompletion` 本身不是 suspend 函数，可以在任何地方调用
- 但返回的 `Flow` 需要在协程作用域中 `collect`

### 每个请求自定义配置

#### 旧方式
```kotlin
// 不支持，需要创建新的 service 实例
val service1 = ArkService("api-key-1")
val service2 = ArkService("api-key-2")

val result1 = service1.createChatCompletion(request)
val result2 = service2.createChatCompletion(request)
```

#### 新方式
```kotlin
// 使用同一个 client，每个请求指定不同的配置
val client = ArkClient.create("default-api-key")

val result1 = client.createChatCompletion(
    request = request,
    apiKey = "api-key-1",
    baseUrl = "https://endpoint1.com"
)

val result2 = client.createChatCompletion(
    request = request,
    apiKey = "api-key-2",
    baseUrl = "https://endpoint2.com"
)
```

## API 对照表

| 旧 API (ArkService) | 新 API (ArkClient) | 返回类型变化 |
|---------------------|-------------------|-------------|
| `createChatCompletion(request)` | `createChatCompletion(request, apiKey?, baseUrl)` | `T?` → `T` |
| `streamChatCompletion(request)` | `streamChatCompletion(request, apiKey?, baseUrl)` | `Flowable<T>` → `Flow<T>` |
| `createEmbeddings(request)` | `createEmbeddings(request, apiKey?, baseUrl)` | `T?` → `T` |
| `createBotChatCompletion(request)` | `createBotChatCompletion(request, apiKey?, baseUrl)` | `T?` → `T` |
| `generateImages(request)` | `generateImages(request, apiKey?, baseUrl)` | `T?` → `T` |
| `uploadFile(request)` | `uploadFile(request, apiKey?, baseUrl)` | `T?` → `T` |

## 优势总结

### 1. Kotlin 多平台支持
- Ktor 支持 JVM、JS、Native 等多个平台
- 可以在 Android、iOS、Web 等平台复用代码

### 2. 协程支持
- 使用 Kotlin Coroutines，更符合 Kotlin 习惯
- 更好的异步编程体验
- 更容易的错误处理

### 3. 灵活的配置
- 每个请求可以指定不同的 apiKey 和 baseUrl
- 支持自定义 HttpClient
- 更细粒度的控制

### 4. 类型安全
- 返回类型不再是可空的（`T` 而不是 `T?`）
- 错误通过异常抛出，更清晰

### 5. 更简洁的 API
- 移除了 RxJava 依赖
- 使用 Flow 替代 Flowable
- 代码更简洁易读

## 注意事项

### 1. 协程作用域
同步 API 是 suspend 函数，需要在协程作用域中调用：

```kotlin
// ❌ 错误：不能在非协程作用域中调用 suspend 函数
val result = client.createChatCompletion(request)

// ✅ 正确：在协程作用域中调用
runBlocking {
    val result = client.createChatCompletion(request)
}

// ✅ 正确：在 suspend 函数中调用
suspend fun example() {
    val result = client.createChatCompletion(request)
}
```

### 2. 流式 API 的使用
流式 API 返回 Flow，本身不是 suspend 函数：

```kotlin
// ✅ 可以在任何地方获取 Flow
val flow = client.streamChatCompletion(request)

// ❌ 但 collect 是 suspend 函数，需要在协程中调用
flow.collect { chunk -> println(chunk) }  // 编译错误

// ✅ 正确：在协程中 collect
runBlocking {
    flow.collect { chunk -> println(chunk) }
}
```

### 2. 错误处理
错误通过异常抛出，需要使用 try-catch：

```kotlin
runBlocking {
    try {
        val result = client.createChatCompletion(request)
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}
```

### 3. 资源管理
记得关闭客户端：

```kotlin
val client = ArkClient.create("api-key")
try {
    // 使用 client
} finally {
    client.close()
}
```

### 4. 流式处理
流式 API 返回 Flow，不是 suspend 函数，但 collect 需要在协程中：

```kotlin
// ❌ 旧方式
service.streamChatCompletion(request).subscribe { chunk -> ... }

// ✅ 新方式（streamChatCompletion 不是 suspend，但 collect 是）
val flow = client.streamChatCompletion(request)  // 可以在任何地方调用
runBlocking {
    flow.collect { chunk -> ... }  // collect 需要在协程中
}

// 或直接链式调用
runBlocking {
    client.streamChatCompletion(request).collect { chunk -> ... }
}
```

## 完整示例

```kotlin
import com.volcengine.ark.runtime.service.ArkClient
import com.volcengine.ark.runtime.model.completion.chat.*
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    // 创建客户端
    val client = ArkClient.create(
        apiKey = "your-api-key",
        baseUrl = "https://ark.cn-beijing.volces.com"
    )

    try {
        // 创建请求
        val request = ChatCompletionRequest.builder()
            .model("doubao-pro-32k")
            .messages(listOf(
                ChatMessage.builder()
                    .role(ChatMessageRole.USER)
                    .content("Hello, how are you?")
                    .build()
            ))
            .build()

        // 同步调用
        val result = client.createChatCompletion(request)
        println("Response: ${result}")

        // 流式调用
        client.streamChatCompletion(request).collect { chunk ->
            println("Chunk: ${chunk}")
        }

    } catch (e: Exception) {
        println("Error: ${e.message}")
    } finally {
        // 关闭客户端
        client.close()
    }
}
```

## 相关文档

- `ArkClient.kt` - 新的客户端实现
- `KtorClientConfig.kt` - Ktor 客户端配置
- `ArkClientExample.kt` - 完整使用示例

---

**迁移完成时间**: 2026-03-12
**状态**: ✅ 新 API 已实现，旧 API 保留用于兼容
