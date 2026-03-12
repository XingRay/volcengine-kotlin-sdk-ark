package io.github.xingray.volcengine_kotlin_sdk_ark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volcengine.ark.runtime.model.completion.chat.ChatCompletionRequest
import com.volcengine.ark.runtime.model.completion.chat.ChatMessage
import com.volcengine.ark.runtime.model.completion.chat.ChatMessageContent
import com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole
import com.volcengine.ark.runtime.service.ArkClient
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

data class ChatUiState(
    val messages: List<ChatMessage> = emptyList(),
    val inputText: String = "",
    val isLoading: Boolean = false,
    val apiKey: String = "",
    val model: String = "doubao-seed-2-0-lite-260215",
    val temperature: Double = 0.7,
    val topP: Double = 0.9,
    val maxTokens: Int = 2048,
    val streamEnabled: Boolean = false,
    val deepThinkingEnabled: Boolean = false
)

class ChatViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    fun updateInputText(text: String) {
        _uiState.value = _uiState.value.copy(inputText = text)
    }

    fun updateApiKey(apiKey: String) {
        _uiState.value = _uiState.value.copy(apiKey = apiKey)
    }

    fun updateModel(model: String) {
        _uiState.value = _uiState.value.copy(model = model)
    }

    fun updateTemperature(temperature: Double) {
        _uiState.value = _uiState.value.copy(temperature = temperature)
    }

    fun updateTopP(topP: Double) {
        _uiState.value = _uiState.value.copy(topP = topP)
    }

    fun updateMaxTokens(maxTokens: Int) {
        _uiState.value = _uiState.value.copy(maxTokens = maxTokens)
    }

    fun updateStreamEnabled(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(streamEnabled = enabled)
    }

    fun updateDeepThinkingEnabled(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(deepThinkingEnabled = enabled)
    }

    fun sendMessage() {
        val currentState = _uiState.value

        // 验证输入
        if (currentState.inputText.isBlank()) {
            println("[${getCurrentTimestamp()}] === Validation failed: Input text is blank ===")
            val errorMessage = ChatMessage(
                role = ChatMessageRole.ASSISTANT,
                content = ChatMessageContent.TextContent("请输入消息内容")
            )
            _uiState.value = _uiState.value.copy(
                messages = _uiState.value.messages + errorMessage
            )
            return
        }

        if (currentState.apiKey.isBlank()) {
            println("[${getCurrentTimestamp()}] === Validation failed: API Key is blank ===")
            val errorMessage = ChatMessage(
                role = ChatMessageRole.ASSISTANT,
                content = ChatMessageContent.TextContent("请先输入 API Key")
            )
            _uiState.value = _uiState.value.copy(
                messages = _uiState.value.messages + errorMessage
            )
            return
        }

        val userMessage = ChatMessage(
            role = ChatMessageRole.USER,
            content = ChatMessageContent.TextContent(currentState.inputText)
        )
        val updatedMessages = currentState.messages + userMessage

        _uiState.value = currentState.copy(
            messages = updatedMessages,
            inputText = "",
            isLoading = true
        )

        viewModelScope.launch {
            try {
                println("[${getCurrentTimestamp()}] === Starting chat request ===")
                println("[${getCurrentTimestamp()}] API Key: ${currentState.apiKey.take(10)}...")
                println("[${getCurrentTimestamp()}] Model: ${currentState.model}")
                println("[${getCurrentTimestamp()}] Stream enabled: ${currentState.streamEnabled}")

                val httpClient = createHttpClient()

                val client = ArkClient(
                    httpClient = httpClient,
                    defaultApiKey = currentState.apiKey
                )

                val request = ChatCompletionRequest(
                    model = currentState.model,
                    messages = updatedMessages,
                    temperature = currentState.temperature,
                    topP = currentState.topP,
                    maxTokens = currentState.maxTokens,
                    stream = currentState.streamEnabled,
                    thinking = com.volcengine.ark.runtime.model.completion.chat.ChatCompletionRequestThinking(
                        type = if (currentState.deepThinkingEnabled) {
                            com.volcengine.ark.runtime.model.completion.chat.ThinkingType.ENABLED
                        } else {
                            com.volcengine.ark.runtime.model.completion.chat.ThinkingType.DISABLED
                        }
                    )
                )

                println("[${getCurrentTimestamp()}] Request created, sending...")

                if (currentState.streamEnabled) {
                    // Stream mode
                    println("[${getCurrentTimestamp()}] Using stream mode")
                    val streamFlow = client.streamChatCompletion(request)
                    val assistantMessageBuilder = StringBuilder()

                    // Add an empty assistant message first
                    val emptyAssistantMessage = ChatMessage(
                        role = ChatMessageRole.ASSISTANT,
                        content = ChatMessageContent.TextContent("")
                    )
                    _uiState.value = _uiState.value.copy(
                        messages = _uiState.value.messages + emptyAssistantMessage
                    )
                    println("[${getCurrentTimestamp()}] Empty assistant message added to UI")

                    streamFlow.collect { chunk ->
                        println("[${getCurrentTimestamp()}] Received chunk in ViewModel")
                        val delta = chunk.choices?.firstOrNull()?.message
                        val deltaContent = when (val content = delta?.content) {
                            is ChatMessageContent.TextContent -> content.value
                            is ChatMessageContent.MultiContent -> content.items.joinToString("") { part ->
                                when (part) {
                                    is com.volcengine.ark.runtime.model.completion.chat.ContentPart.TextPart -> part.text
                                    is com.volcengine.ark.runtime.model.completion.chat.ContentPart.ImageUrlPart -> ""
                                    is com.volcengine.ark.runtime.model.completion.chat.ContentPart.VideoUrlPart -> ""
                                }
                            }
                            null -> ""
                        }
                        val deltaReasoningContent = delta?.reasoningContent ?: ""

                        if (deltaContent.isNotEmpty() || deltaReasoningContent.isNotEmpty()) {
                            if (deltaContent.isNotEmpty()) {
                                println("[${getCurrentTimestamp()}] Delta content: $deltaContent")
                            }
                            if (deltaReasoningContent.isNotEmpty()) {
                                println("[${getCurrentTimestamp()}] Delta reasoning content: $deltaReasoningContent")
                            }
                            assistantMessageBuilder.append(deltaContent)
                            val currentMessages = _uiState.value.messages.toMutableList()
                            val lastMessage = currentMessages.lastOrNull()
                            val updatedReasoningContent = (lastMessage?.reasoningContent ?: "") + deltaReasoningContent
                            currentMessages[currentMessages.lastIndex] = ChatMessage(
                                role = ChatMessageRole.ASSISTANT,
                                content = ChatMessageContent.TextContent(assistantMessageBuilder.toString()),
                                reasoningContent = updatedReasoningContent.ifEmpty { null }
                            )
                            _uiState.value = _uiState.value.copy(
                                messages = currentMessages
                            )
                            println("[${getCurrentTimestamp()}] UI updated with accumulated content")
                        }
                    }

                    println("[${getCurrentTimestamp()}] Stream completed")
                    _uiState.value = _uiState.value.copy(isLoading = false)
                } else {
                    // Non-stream mode
                    println("[${getCurrentTimestamp()}] Using non-stream mode")
                    val response = client.createChatCompletion(request)
                    println("[${getCurrentTimestamp()}] Response received: $response")
                    val assistantMessage = response.choices?.firstOrNull()?.message

                    if (assistantMessage != null) {
                        println("[${getCurrentTimestamp()}] Assistant message: $assistantMessage")
                        _uiState.value = _uiState.value.copy(
                            messages = updatedMessages + assistantMessage,
                            isLoading = false
                        )
                    } else {
                        println("[${getCurrentTimestamp()}] No assistant message in response")
                        val errorMessage = ChatMessage(
                            role = ChatMessageRole.ASSISTANT,
                            content = ChatMessageContent.TextContent("服务器返回了空响应，请检查模型名称是否正确")
                        )
                        _uiState.value = _uiState.value.copy(
                            messages = updatedMessages + errorMessage,
                            isLoading = false
                        )
                    }
                }

                httpClient.close()
                println("[${getCurrentTimestamp()}] === Chat request completed ===")
            } catch (e: Exception) {
                println("[${getCurrentTimestamp()}] === Error occurred ===")
                println("[${getCurrentTimestamp()}] Error type: ${e::class.simpleName}")
                println("[${getCurrentTimestamp()}] Error message: ${e.message}")

                val errorMessage = ChatMessage(
                    role = ChatMessageRole.ASSISTANT,
                    content = ChatMessageContent.TextContent("错误: ${e.message}")
                )
                _uiState.value = _uiState.value.copy(
                    messages = updatedMessages + errorMessage,
                    isLoading = false
                )
            }
        }
    }
}

expect fun createHttpClient(): HttpClient
