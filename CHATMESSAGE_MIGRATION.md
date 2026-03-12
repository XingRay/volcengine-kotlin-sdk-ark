# ChatMessage 类型安全优化 - 迁移指南

## 概述

ChatMessage 的 `content` 字段已从 `Any?` 类型优化为类型安全的 `ChatMessageContent` 密封类，使用 kotlinx.serialization 实现多态序列化。

## 核心改进

### 1. 类型安全
- **旧方式**: `content: Any?` - 需要运行时类型转换，容易出错
- **新方式**: `content: ChatMessageContent?` - 编译时类型检查，类型安全

### 2. 多态序列化
使用密封类 + 自定义序列化器，自动处理以下两种 JSON 格式：

```json
// 格式 1: 字符串类型
{
  "role": "user",
  "content": "Hello, how are you?"
}

// 格式 2: 多内容数组类型
{
  "role": "user",
  "content": [
    {"type": "text", "text": "What's in this image?"},
    {"type": "image_url", "image_url": {"url": "https://example.com/image.jpg"}},
    {"type": "video_url", "video_url": {"url": "https://example.com/video.mp4"}, "fps": 2.0}
  ]
}
```

### 3. 支持的内容类型

#### ChatMessageContent (密封类)
- `TextContent(value: String)` - 文本内容
- `MultiContent(items: List<ContentPart>)` - 多内容数组

#### ContentPart (密封类)
- `TextPart(text: String)` - 文本部分
- `ImageUrlPart(imageUrl: ImageUrl)` - 图片 URL 部分
- `VideoUrlPart(videoUrl: VideoUrl, fps: Double?)` - 视频 URL 部分

## 迁移示例

### 创建消息

#### 旧方式
```kotlin
// 文本消息
val message = ChatMessage.builder()
    .role(ChatMessageRole.USER)
    .content("Hello")
    .build()

// 多内容消息（使用旧的 ChatCompletionContentPart）
val parts = listOf(
    ChatCompletionContentPart.builder()
        .type("text")
        .text("What's in this image?")
        .build()
)
val message = ChatMessage.builder()
    .role(ChatMessageRole.USER)
    .multiContent(parts)
    .build()
```

#### 新方式
```kotlin
// 文本消息（API 保持兼容）
val message = ChatMessage.builder()
    .role(ChatMessageRole.USER)
    .content("Hello")
    .build()

// 多内容消息（使用新的 ContentPart）
val parts = listOf(
    ContentPart.TextPart(text = "What's in this image?"),
    ContentPart.ImageUrlPart(
        imageUrl = ImageUrl(url = "https://example.com/image.jpg", detail = "high")
    )
)
val message = ChatMessage.builder()
    .role(ChatMessageRole.USER)
    .multiContent(parts)
    .build()
```

### 读取消息内容

#### 旧方式
```kotlin
// 不安全的类型转换
val text = message.stringContent() // 可能抛出 ClassCastException
val parts = message.multiContent() // 需要 Converter.castList
```

#### 新方式
```kotlin
// 类型安全的模式匹配
when (val content = message.content) {
    is ChatMessageContent.TextContent -> {
        println("文本: ${content.value}")
    }
    is ChatMessageContent.MultiContent -> {
        content.items.forEach { part ->
            when (part) {
                is ContentPart.TextPart -> println("文本: ${part.text}")
                is ContentPart.ImageUrlPart -> println("图片: ${part.imageUrl.url}")
                is ContentPart.VideoUrlPart -> println("视频: ${part.videoUrl.url}, fps: ${part.fps}")
            }
        }
    }
    null -> println("空内容")
}

// 或使用便捷方法
val text = message.stringContent() // 返回 String? 而不是抛异常
val parts = message.multiContent() // 返回 List<ContentPart>?
```

### 序列化和反序列化

```kotlin
val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    prettyPrint = true
}

// 序列化
val jsonString = json.encodeToString(ChatMessage.serializer(), message)

// 反序列化（自动识别 content 是字符串还是数组）
val message = json.decodeFromString<ChatMessage>(jsonString)
```

## 兼容性

### 向后兼容
所有旧的 API 方法都保留，确保现有代码无需修改：

```kotlin
// 这些方法仍然可用
message.setContent("text")
message.stringContent()
message.multiContent()
```

### 从旧类型迁移
提供了扩展函数用于类型转换：

```kotlin
// ChatCompletionContentPart -> ContentPart
val newPart = oldPart.toContentPart()

// ContentPart -> ChatCompletionContentPart
val oldPart = newPart.toChatCompletionContentPart()

// 批量转换
val newParts = oldParts.toContentParts()
val oldParts = newParts.toChatCompletionContentParts()
```

## 优势总结

1. **类型安全**: 编译时检查，避免运行时类型转换错误
2. **可扩展**: 新增内容类型只需添加新的密封类子类
3. **自动序列化**: 无需手动处理 JSON 格式判断
4. **代码清晰**: 使用 when 表达式进行模式匹配，代码更易读
5. **向后兼容**: 保留所有旧 API，平滑迁移

## 示例代码

完整示例请参考：`ChatMessageExample.kt`

## 注意事项

1. 新代码建议使用 `ContentPart` 而不是 `ChatCompletionContentPart`
2. 使用 `when` 表达式处理 content，而不是类型转换
3. `stringContent()` 和 `multiContent()` 现在返回可空类型，需要处理 null 情况
4. 旧的 `ChatCompletionContentPart` 类仍然保留，用于兼容性
