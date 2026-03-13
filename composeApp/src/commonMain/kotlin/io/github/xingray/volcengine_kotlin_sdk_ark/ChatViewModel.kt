package io.github.xingray.volcengine_kotlin_sdk_ark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volcengine.ark.runtime.model.completion.chat.ChatCompletionRequest
import com.volcengine.ark.runtime.model.completion.chat.ChatMessage
import com.volcengine.ark.runtime.model.completion.chat.ChatMessageContent
import com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole
import com.volcengine.ark.runtime.model.images.generation.GenerateImagesRequest
import com.volcengine.ark.runtime.service.ArkClient
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.name
import io.github.xingray.volcengine_kotlin_sdk_ark.model.AiModel
import io.github.xingray.volcengine_kotlin_sdk_ark.model.AiModelType
import io.github.xingray.volcengine_kotlin_sdk_ark.model.ImageGenerationTask
import io.github.xingray.volcengine_kotlin_sdk_ark.oss.ObjectStorageService
import io.github.xingray.volcengine_kotlin_sdk_ark.util.TimeUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ChatUiState(
    val messages: List<ChatMessage> = emptyList(),
    val inputText: String = "",
    val isLoading: Boolean = false,
    val isUploading: Boolean = false,
    val apiKey: String = "",
    val accessKey: String = "",
    val secretKey: String = "",
    val ossBucket: String = "ark-project",
    val ossKeyPrefix: String = "/image/",
    val selectedModel: AiModel = AiModel.getDefaultModel(),
    val temperature: Double = 0.7,
    val topP: Double = 0.9,
    val maxTokens: Int = 2048,
    val streamEnabled: Boolean = false,
    val deepThinkingEnabled: Boolean = false,
    val showAddMessageDialog: Boolean = false,
    val addMessageRole: ChatMessageRole? = null,
    val addMessageText: String = "",
    val editingMessageIndex: Int? = null,
    val editingMessageText: String = "",
    val errorMessage: String? = null,
    val selectedImageFiles: List<PlatformFile> = emptyList(),
    val selectedVideoFiles: List<PlatformFile> = emptyList(),
    val selectedDocumentFiles: List<PlatformFile> = emptyList(),
    val imageGenerationTask: ImageGenerationTask? = null,
    val showImageDialog: Boolean = false,
    val dialogImageUrl: String? = null
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

    fun updateAccessKey(accessKey: String) {
        _uiState.value = _uiState.value.copy(accessKey = accessKey)
    }

    fun updateSecretKey(secretKey: String) {
        _uiState.value = _uiState.value.copy(secretKey = secretKey)
    }

    fun updateOssBucket(bucket: String) {
        _uiState.value = _uiState.value.copy(ossBucket = bucket)
    }

    fun updateOssKeyPrefix(keyPrefix: String) {
        _uiState.value = _uiState.value.copy(ossKeyPrefix = keyPrefix)
    }

    fun updateModel(model: AiModel) {
        _uiState.value = _uiState.value.copy(selectedModel = model)
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

    fun showAddMessageDialog(role: ChatMessageRole) {
        _uiState.value = _uiState.value.copy(
            showAddMessageDialog = true,
            addMessageRole = role,
            addMessageText = ""
        )
    }

    fun hideAddMessageDialog() {
        _uiState.value = _uiState.value.copy(
            showAddMessageDialog = false,
            addMessageRole = null,
            addMessageText = ""
        )
    }

    fun updateAddMessageText(text: String) {
        _uiState.value = _uiState.value.copy(addMessageText = text)
    }

    fun confirmAddMessage() {
        val currentState = _uiState.value
        if (currentState.addMessageText.isNotBlank() && currentState.addMessageRole != null) {
            val newMessage = ChatMessage(
                role = currentState.addMessageRole,
                content = ChatMessageContent.TextContent(currentState.addMessageText)
            )
            _uiState.value = currentState.copy(
                messages = currentState.messages + newMessage,
                showAddMessageDialog = false,
                addMessageRole = null,
                addMessageText = ""
            )
        }
    }

    fun deleteMessage(index: Int) {
        val currentMessages = _uiState.value.messages.toMutableList()
        if (index in currentMessages.indices) {
            currentMessages.removeAt(index)
            _uiState.value = _uiState.value.copy(messages = currentMessages)
        }
    }

    fun startEditMessage(index: Int) {
        val message = _uiState.value.messages.getOrNull(index)
        if (message != null) {
            val text = when (val content = message.content) {
                is ChatMessageContent.TextContent -> content.value
                is ChatMessageContent.MultiContent -> content.items.joinToString("\n") { part ->
                    when (part) {
                        is com.volcengine.ark.runtime.model.completion.chat.ContentPart.TextPart -> part.text
                        else -> ""
                    }
                }

                null -> ""
            }
            _uiState.value = _uiState.value.copy(
                editingMessageIndex = index,
                editingMessageText = text
            )
        }
    }

    fun updateEditingMessageText(text: String) {
        _uiState.value = _uiState.value.copy(editingMessageText = text)
    }

    fun confirmEditMessage() {
        val currentState = _uiState.value
        val index = currentState.editingMessageIndex
        if (index != null && index in currentState.messages.indices && currentState.editingMessageText.isNotBlank()) {
            val currentMessages = currentState.messages.toMutableList()
            val oldMessage = currentMessages[index]
            currentMessages[index] = oldMessage.copy(
                content = ChatMessageContent.TextContent(currentState.editingMessageText)
            )
            _uiState.value = currentState.copy(
                messages = currentMessages,
                editingMessageIndex = null,
                editingMessageText = ""
            )
        }
    }

    fun cancelEditMessage() {
        _uiState.value = _uiState.value.copy(
            editingMessageIndex = null,
            editingMessageText = ""
        )
    }

    fun clearErrorMessage() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }

    fun addImageFiles(files: List<PlatformFile>) {
        _uiState.value = _uiState.value.copy(
            selectedImageFiles = _uiState.value.selectedImageFiles + files
        )
    }

    fun addVideoFiles(files: List<PlatformFile>) {
        _uiState.value = _uiState.value.copy(
            selectedVideoFiles = _uiState.value.selectedVideoFiles + files
        )
    }

    fun addDocumentFiles(files: List<PlatformFile>) {
        _uiState.value = _uiState.value.copy(
            selectedDocumentFiles = _uiState.value.selectedDocumentFiles + files
        )
    }

    fun removeImageFile(file: PlatformFile) {
        _uiState.value = _uiState.value.copy(
            selectedImageFiles = _uiState.value.selectedImageFiles.filter { it != file }
        )
    }

    fun removeVideoFile(file: PlatformFile) {
        _uiState.value = _uiState.value.copy(
            selectedVideoFiles = _uiState.value.selectedVideoFiles.filter { it != file }
        )
    }

    fun removeDocumentFile(file: PlatformFile) {
        _uiState.value = _uiState.value.copy(
            selectedDocumentFiles = _uiState.value.selectedDocumentFiles.filter { it != file }
        )
    }

    fun showImageDialog(imageUrl: String) {
        _uiState.value = _uiState.value.copy(
            showImageDialog = true,
            dialogImageUrl = imageUrl
        )
    }

    fun hideImageDialog() {
        _uiState.value = _uiState.value.copy(
            showImageDialog = false,
            dialogImageUrl = null
        )
    }

    fun sendMessage() {
        val currentState = _uiState.value

        // 验证输入
        if (currentState.inputText.isBlank()) {
            println("[${getCurrentTimestamp()}] === Validation failed: Input text is blank ===")
            _uiState.value = _uiState.value.copy(errorMessage = "请输入消息内容")
            return
        }

        if (currentState.apiKey.isBlank()) {
            println("[${getCurrentTimestamp()}] === Validation failed: API Key is blank ===")
            _uiState.value = _uiState.value.copy(errorMessage = "请先输入 API Key")
            return
        }

        // 根据模型类型分发到不同的处理逻辑
        when (currentState.selectedModel.type) {
            AiModelType.IMAGE -> sendImageGenerationMessage()
            else -> sendChatMessage()
        }
    }

    private fun sendChatMessage() {
        val currentState = _uiState.value
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
                println("[${getCurrentTimestamp()}] Model: ${currentState.selectedModel.id}")
                println("[${getCurrentTimestamp()}] Stream enabled: ${currentState.streamEnabled}")

                val httpClient = createHttpClient()

                val client = ArkClient(
                    httpClient = httpClient,
                    defaultApiKey = currentState.apiKey
                )

                val request = ChatCompletionRequest(
                    model = currentState.selectedModel.id,
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
                        _uiState.value = _uiState.value.copy(
                            errorMessage = "服务器返回了空响应，请检查模型名称是否正确",
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

                _uiState.value = _uiState.value.copy(
                    errorMessage = "错误: ${e.message}",
                    isLoading = false
                )
            }
        }
    }

    private fun sendImageGenerationMessage() {
        val currentState = _uiState.value
        val userMessage = ChatMessage(
            role = ChatMessageRole.USER,
            content = ChatMessageContent.TextContent(currentState.inputText)
        )
        val updatedMessages = currentState.messages + userMessage

        // 添加一个空的 assistant 消息用于显示 loading
        val loadingMessage = ChatMessage(
            role = ChatMessageRole.ASSISTANT,
            content = ChatMessageContent.TextContent("")
        )

        _uiState.value = currentState.copy(
            messages = updatedMessages + loadingMessage,
            inputText = "",
            isLoading = true
        )

        viewModelScope.launch {
            try {
                println("[${getCurrentTimestamp()}] === Starting image generation request ===")
                println("[${getCurrentTimestamp()}] API Key: ${currentState.apiKey.take(10)}...")
                println("[${getCurrentTimestamp()}] Model: ${currentState.selectedModel.id}")
                println("[${getCurrentTimestamp()}] Prompt: ${userMessage.content}")

                val httpClient = createHttpClient()
                val client = ArkClient(
                    httpClient = httpClient,
                    defaultApiKey = currentState.apiKey
                )

                // 如果选择了图片文件，先上传图片
                var imageUrl: String? = null
                if (currentState.selectedImageFiles.isNotEmpty()) {
                    // 检查AK/SK是否已配置
                    if (currentState.accessKey.isBlank() || currentState.secretKey.isBlank()) {
                        println("[${getCurrentTimestamp()}] AK/SK not configured")
                        _uiState.value = _uiState.value.copy(
                            errorMessage = "请先配置 Access Key 和 Secret Key",
                            isLoading = false
                        )
                        return@launch
                    }

                    println("[${getCurrentTimestamp()}] Uploading image file...")
                    _uiState.value = _uiState.value.copy(isUploading = true)

                    try {
                        val imageFile = currentState.selectedImageFiles.first()
                        val filePath = imageFile.saveToTempFile()
                        val fileName = imageFile.name

                        println("[${getCurrentTimestamp()}] File name: $fileName, path: $filePath")

                        val ossService = ObjectStorageService(
                            ak = currentState.accessKey,
                            sk = currentState.secretKey
                        )

                        val bucket = currentState.ossBucket
                        val key = "${currentState.ossKeyPrefix}${TimeUtil.nowMillis()}_$fileName"

                        val result = ossService.putObject(bucket, key, filePath)

                        when (result) {
                            is ApiResult.Success -> {
                                imageUrl = result.data
                                println("[${getCurrentTimestamp()}] Image uploaded successfully, URL: $imageUrl")

                                // 清除已选择的图片
                                _uiState.value = _uiState.value.copy(
                                    selectedImageFiles = emptyList(),
                                    isUploading = false
                                )
                            }

                            is ApiResult.Error -> {
                                println("[${getCurrentTimestamp()}] Image upload failed: ${result.message}")
                                _uiState.value = _uiState.value.copy(isUploading = false)
                                throw Exception("图片上传失败: ${result.message}")
                            }
                        }
                    } catch (e: Exception) {
                        println("[${getCurrentTimestamp()}] Image upload failed: ${e.message}")
                        _uiState.value = _uiState.value.copy(isUploading = false)
                        throw Exception("图片上传失败: ${e.message}", e)
                    }
                }

                val request = GenerateImagesRequest(
                    model = currentState.selectedModel.id,
                    prompt = when (val content = userMessage.content) {
                        is ChatMessageContent.TextContent -> content.value
                        else -> ""
                    },
                    image = imageUrl?.let { listOf(it) },
                    watermark = false,
                )

                println("[${getCurrentTimestamp()}] Submitting image generation request...")
                val response = client.generateImages(request)
                println("[${getCurrentTimestamp()}] Response received: $response")

                val generatedImageUrl = response.data?.firstOrNull()?.url
                val errorMsg = response.error?.message

                if (generatedImageUrl != null) {
                    println("[${getCurrentTimestamp()}] Image URL: $generatedImageUrl")
                    // 更新最后一条消息为图片 URL
                    val currentMessages = _uiState.value.messages.toMutableList()
                    currentMessages[currentMessages.lastIndex] = ChatMessage(
                        role = ChatMessageRole.ASSISTANT,
                        content = ChatMessageContent.TextContent(generatedImageUrl)
                    )
                    _uiState.value = _uiState.value.copy(
                        messages = currentMessages,
                        isLoading = false
                    )
                } else {
                    println("[${getCurrentTimestamp()}] Image generation failed: $errorMsg")
                    val currentMessages = _uiState.value.messages.toMutableList()
                    currentMessages[currentMessages.lastIndex] = ChatMessage(
                        role = ChatMessageRole.ASSISTANT,
                        content = ChatMessageContent.TextContent("图片生成失败: ${errorMsg ?: "未知错误"}")
                    )
                    _uiState.value = _uiState.value.copy(
                        messages = currentMessages,
                        isLoading = false,
                        errorMessage = "图片生成失败: ${errorMsg ?: "未知错误"}"
                    )
                }

                httpClient.close()
                println("[${getCurrentTimestamp()}] === Image generation request completed ===")
            } catch (e: Exception) {
                println("[${getCurrentTimestamp()}] === Error occurred ===")
                println("[${getCurrentTimestamp()}] Error type: ${e::class.simpleName}")
                println("[${getCurrentTimestamp()}] Error message: ${e.message}")

                val currentMessages = _uiState.value.messages.toMutableList()
                currentMessages[currentMessages.lastIndex] = ChatMessage(
                    role = ChatMessageRole.ASSISTANT,
                    content = ChatMessageContent.TextContent("错误: ${e.message}")
                )
                _uiState.value = _uiState.value.copy(
                    messages = currentMessages,
                    errorMessage = "错误: ${e.message}",
                    isLoading = false,
                    isUploading = false
                )
            }
        }
    }
}

expect suspend fun PlatformFile.saveToTempFile(): String



