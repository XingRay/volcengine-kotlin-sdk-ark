package io.github.xingray.volcengine_kotlin_sdk_ark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volcengine.ark.runtime.model.completion.chat.ChatCompletionRequest
import com.volcengine.ark.runtime.model.completion.chat.ChatMessage
import com.volcengine.ark.runtime.model.completion.chat.ChatMessageContent
import com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole
import com.volcengine.ark.runtime.model.content.generation.CreateContentGenerationTaskRequest
import com.volcengine.ark.runtime.model.content.generation.GetContentGenerationTaskRequest
import com.volcengine.ark.runtime.model.images.generation.GenerateImagesRequest
import com.volcengine.ark.runtime.model.images.generation.SequentialImageGeneration
import com.volcengine.ark.runtime.model.images.generation.SequentialImageGenerationOptions
import com.volcengine.ark.runtime.service.ArkClient
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.name
import io.github.xingray.volcengine_kotlin_sdk_ark.model.AiModel
import io.github.xingray.volcengine_kotlin_sdk_ark.model.AiModelType
import io.github.xingray.volcengine_kotlin_sdk_ark.model.ImageGenerationTask
import io.github.xingray.volcengine_kotlin_sdk_ark.model.VideoGenerationMode
import io.github.xingray.volcengine_kotlin_sdk_ark.oss.ObjectStorageService
import io.github.xingray.volcengine_kotlin_sdk_ark.util.TimeUtil
import io.github.xingray.volcengine_kotlin_sdk_ark.util.saveToTempFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
    val sequentialImageGenerationEnabled: Boolean = false,
    val maxImagesCount: Int = 4,
    val imageDetailLevel: com.volcengine.ark.runtime.model.completion.chat.ImageUrlDetail = com.volcengine.ark.runtime.model.completion.chat.ImageUrlDetail.HIGH,
    val videoFps: Float = 1.0f,
    // 视频生成参数
    val videoDuration: Int = 5,
    val videoResolution: io.github.xingray.volcengine_kotlin_sdk_ark.model.VideoResolution = io.github.xingray.volcengine_kotlin_sdk_ark.model.VideoResolution.P480,
    val videoRatio: io.github.xingray.volcengine_kotlin_sdk_ark.model.VideoRatio = io.github.xingray.volcengine_kotlin_sdk_ark.model.VideoRatio.RATIO_16_9,
    val videoGenerateAudio: Boolean = false,
    val videoSampleMode: Boolean = true,
    val videoGenerationMode: VideoGenerationMode = VideoGenerationMode.TEXT_TO_VIDEO,
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

    fun updateSequentialImageGenerationEnabled(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(sequentialImageGenerationEnabled = enabled)
    }

    fun updateMaxImagesCount(count: Int) {
        _uiState.value = _uiState.value.copy(maxImagesCount = count)
    }

    fun updateImageDetailLevel(detail: com.volcengine.ark.runtime.model.completion.chat.ImageUrlDetail) {
        _uiState.value = _uiState.value.copy(imageDetailLevel = detail)
    }

    fun updateVideoFps(fps: Float) {
        _uiState.value = _uiState.value.copy(videoFps = fps)
    }

    fun updateVideoDuration(duration: Int) {
        _uiState.value = _uiState.value.copy(videoDuration = duration)
    }

    fun updateVideoResolution(resolution: io.github.xingray.volcengine_kotlin_sdk_ark.model.VideoResolution) {
        _uiState.value = _uiState.value.copy(videoResolution = resolution)
    }

    fun updateVideoRatio(ratio: io.github.xingray.volcengine_kotlin_sdk_ark.model.VideoRatio) {
        _uiState.value = _uiState.value.copy(videoRatio = ratio)
    }

    fun updateVideoGenerateAudio(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(videoGenerateAudio = enabled)
    }

    fun updateVideoSampleMode(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(videoSampleMode = enabled)
    }

    fun updateVideoGenerationMode(mode: VideoGenerationMode) {
        _uiState.value = _uiState.value.copy(videoGenerationMode = mode)
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
            println("=== Validation failed: Input text is blank ===")
            _uiState.value = _uiState.value.copy(errorMessage = "请输入消息内容")
            return
        }

        if (currentState.apiKey.isBlank()) {
            println("=== Validation failed: API Key is blank ===")
            _uiState.value = _uiState.value.copy(errorMessage = "请先输入 API Key")
            return
        }

        // 根据模型类型分发到不同的处理逻辑
        when (currentState.selectedModel.type) {
            AiModelType.IMAGE -> sendImageGenerationMessage()
            AiModelType.VIDEO -> sendVideoGenerationMessage()
            else -> sendChatMessage()
        }
    }

    private fun sendChatMessage() {
        val currentState = _uiState.value

        // 保存当前的输入文本和选中的文件
        val inputText = currentState.inputText
        val selectedImageFiles = currentState.selectedImageFiles
        val selectedVideoFiles = currentState.selectedVideoFiles

        _uiState.value = currentState.copy(
            inputText = "",
            isLoading = true
        )

        viewModelScope.launch {
            try {
                println("=== Starting chat request ===")
                println("API Key: ${currentState.apiKey.take(10)}...")
                println("Model: ${currentState.selectedModel.id}")
                println("Stream enabled: ${currentState.streamEnabled}")
                println("Selected image files: ${selectedImageFiles.size}")
                println("Selected video files: ${selectedVideoFiles.size}")

                // 检查是否需要上传文件
                val needUpload = selectedImageFiles.isNotEmpty() || selectedVideoFiles.isNotEmpty()

                if (needUpload) {
                    // 检查AK/SK是否已配置
                    if (currentState.accessKey.isBlank() || currentState.secretKey.isBlank()) {
                        println("AK/SK not configured")
                        _uiState.value = _uiState.value.copy(
                            errorMessage = "请先配置 Access Key 和 Secret Key",
                            isLoading = false
                        )
                        return@launch
                    }

                    _uiState.value = _uiState.value.copy(isUploading = true)

                    ObjectStorageService(
                        ak = currentState.accessKey,
                        sk = currentState.secretKey
                    )
                }

                // 上传图片文件
                val imageUrls = mutableListOf<String>()
                if (selectedImageFiles.isNotEmpty()) {
                    println("Uploading ${selectedImageFiles.size} image file(s)...")

                    try {
                        val ossService = ObjectStorageService(
                            ak = currentState.accessKey,
                            sk = currentState.secretKey
                        )

                        // 上传所有选中的图片
                        for ((index, imageFile) in selectedImageFiles.withIndex()) {
                            println("Uploading image ${index + 1}/${selectedImageFiles.size}")

                            // 在后台线程中执行文件操作
                            val filePath = withContext(Dispatchers.Default) {
                                imageFile.saveToTempFile()
                            }
                            val fileName = imageFile.name

                            println("File name: $fileName, path: $filePath")

                            val bucket = currentState.ossBucket
                            val key = "${currentState.ossKeyPrefix}${TimeUtil.nowMillis()}_${index}_$fileName"

                            // 在后台线程中执行上传操作
                            val result = withContext(Dispatchers.Default) {
                                ossService.putObject(bucket, key, filePath)
                            }

                            when (result) {
                                is ApiResult.Success -> {
                                    imageUrls.add(result.data)
                                    println("Image ${index + 1} uploaded successfully, URL: ${result.data}")
                                }

                                is ApiResult.Error -> {
                                    println("Image ${index + 1} upload failed: ${result.message}")
                                    _uiState.value = _uiState.value.copy(isUploading = false)
                                    throw Exception("图片 ${index + 1} 上传失败: ${result.message}")
                                }
                            }
                        }

                        // 清除已选择的图片
                        _uiState.value = _uiState.value.copy(
                            selectedImageFiles = emptyList()
                        )

                        println("All images uploaded successfully. Total: ${imageUrls.size}")
                    } catch (e: Exception) {
                        println("Image upload failed: ${e.message}")
                        _uiState.value = _uiState.value.copy(isUploading = false)
                        throw Exception("图片上传失败: ${e.message}", e)
                    }
                }

                // 上传视频文件
                val videoUrls = mutableListOf<String>()
                if (selectedVideoFiles.isNotEmpty()) {
                    println("Uploading ${selectedVideoFiles.size} video file(s)...")

                    try {
                        val ossService = ObjectStorageService(
                            ak = currentState.accessKey,
                            sk = currentState.secretKey
                        )

                        // 上传所有选中的视频
                        for ((index, videoFile) in selectedVideoFiles.withIndex()) {
                            println("Uploading video ${index + 1}/${selectedVideoFiles.size}")

                            // 在后台线程中执行文件操作
                            val filePath = withContext(Dispatchers.Default) {
                                videoFile.saveToTempFile()
                            }
                            val fileName = videoFile.name

                            println("File name: $fileName, path: $filePath")

                            val bucket = currentState.ossBucket
                            val key = "${currentState.ossKeyPrefix}${TimeUtil.nowMillis()}_${index}_$fileName"

                            // 在后台线程中执行上传操作
                            val result = withContext(Dispatchers.Default) {
                                ossService.putObject(bucket, key, filePath)
                            }

                            when (result) {
                                is ApiResult.Success -> {
                                    videoUrls.add(result.data)
                                    println("Video ${index + 1} uploaded successfully, URL: ${result.data}")
                                }

                                is ApiResult.Error -> {
                                    println("Video ${index + 1} upload failed: ${result.message}")
                                    _uiState.value = _uiState.value.copy(isUploading = false)
                                    throw Exception("视频 ${index + 1} 上传失败: ${result.message}")
                                }
                            }
                        }

                        // 清除已选择的视频
                        _uiState.value = _uiState.value.copy(
                            selectedVideoFiles = emptyList()
                        )

                        println("All videos uploaded successfully. Total: ${videoUrls.size}")
                    } catch (e: Exception) {
                        println("Video upload failed: ${e.message}")
                        _uiState.value = _uiState.value.copy(isUploading = false)
                        throw Exception("视频上传失败: ${e.message}", e)
                    }
                }

                // 上传完成，关闭上传状态
                if (needUpload) {
                    _uiState.value = _uiState.value.copy(isUploading = false)
                }

                // 构建用户消息
                val userMessage = if (imageUrls.isNotEmpty() || videoUrls.isNotEmpty()) {
                    // 有图片或视频时使用 MultiContent 格式
                    val contentParts = mutableListOf<com.volcengine.ark.runtime.model.completion.chat.ContentPart>()

                    // 添加所有图片
                    imageUrls.forEach { imageUrl ->
                        contentParts.add(
                            com.volcengine.ark.runtime.model.completion.chat.ContentPart.ImageUrlPart(
                                imageUrl = com.volcengine.ark.runtime.model.completion.chat.ImageUrl(
                                    url = imageUrl,
                                    detail = currentState.imageDetailLevel
                                )
                            )
                        )
                    }

                    // 添加所有视频
                    videoUrls.forEach { videoUrl ->
                        contentParts.add(
                            com.volcengine.ark.runtime.model.completion.chat.ContentPart.VideoUrlPart(
                                videoUrl = com.volcengine.ark.runtime.model.completion.chat.VideoUrl(
                                    url = videoUrl,
                                    fps = currentState.videoFps
                                )
                            )
                        )
                    }

                    // 添加文本
                    if (inputText.isNotBlank()) {
                        contentParts.add(
                            com.volcengine.ark.runtime.model.completion.chat.ContentPart.TextPart(
                                text = inputText
                            )
                        )
                    }

                    println("Creating MultiContent message with ${imageUrls.size} images, ${videoUrls.size} videos and text: $inputText")

                    ChatMessage(
                        role = ChatMessageRole.USER,
                        content = ChatMessageContent.MultiContent(contentParts)
                    )
                } else {
                    // 没有图片或视频时使用 TextContent 格式
                    println("Creating TextContent message: $inputText")
                    ChatMessage(
                        role = ChatMessageRole.USER,
                        content = ChatMessageContent.TextContent(inputText)
                    )
                }

                val updatedMessages = currentState.messages + userMessage

                _uiState.value = _uiState.value.copy(
                    messages = updatedMessages
                )

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

                println("Request created, sending...")

                if (currentState.streamEnabled) {
                    // Stream mode
                    println("Using stream mode")
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
                    println("Empty assistant message added to UI")

                    streamFlow.collect { chunk ->
                        println("Received chunk in ViewModel")
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
                                println("Delta content: $deltaContent")
                            }
                            if (deltaReasoningContent.isNotEmpty()) {
                                println("Delta reasoning content: $deltaReasoningContent")
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
                            println("UI updated with accumulated content")
                        }
                    }

                    println("Stream completed")
                    _uiState.value = _uiState.value.copy(isLoading = false)
                } else {
                    // Non-stream mode
                    println("Using non-stream mode")
                    val response = client.createChatCompletion(request)
                    println("Response received: $response")
                    val assistantMessage = response.choices?.firstOrNull()?.message

                    if (assistantMessage != null) {
                        println("Assistant message: $assistantMessage")
                        _uiState.value = _uiState.value.copy(
                            messages = updatedMessages + assistantMessage,
                            isLoading = false
                        )
                    } else {
                        println("No assistant message in response")
                        _uiState.value = _uiState.value.copy(
                            errorMessage = "服务器返回了空响应，请检查模型名称是否正确",
                            isLoading = false
                        )
                    }
                }

                httpClient.close()
                println("=== Chat request completed ===")
            } catch (e: Exception) {
                println("=== Error occurred ===")
                println("Error type: ${e::class.simpleName}")
                println("Error message: ${e.message}")

                _uiState.value = _uiState.value.copy(
                    errorMessage = "错误: ${e.message}",
                    isLoading = false,
                    isUploading = false
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
                println("=== Starting image generation request ===")
                println("API Key: ${currentState.apiKey.take(10)}...")
                println("Model: ${currentState.selectedModel.id}")
                println("Prompt: ${userMessage.content}")

                val httpClient = createHttpClient()
                val client = ArkClient(
                    httpClient = httpClient,
                    defaultApiKey = currentState.apiKey
                )

                // 如果选择了图片文件，先上传图片
                val imageUrls = mutableListOf<String>()
                if (currentState.selectedImageFiles.isNotEmpty()) {
                    // 检查AK/SK是否已配置
                    if (currentState.accessKey.isBlank() || currentState.secretKey.isBlank()) {
                        println("AK/SK not configured")
                        _uiState.value = _uiState.value.copy(
                            errorMessage = "请先配置 Access Key 和 Secret Key",
                            isLoading = false
                        )
                        return@launch
                    }

                    println("Uploading ${currentState.selectedImageFiles.size} image file(s)...")
                    _uiState.value = _uiState.value.copy(isUploading = true)

                    try {
                        val ossService = ObjectStorageService(
                            ak = currentState.accessKey,
                            sk = currentState.secretKey
                        )

                        // 上传所有选中的图片
                        for ((index, imageFile) in currentState.selectedImageFiles.withIndex()) {
                            println("Uploading image ${index + 1}/${currentState.selectedImageFiles.size}")

                            // 在后台线程中执行文件操作
                            val filePath = withContext(Dispatchers.Default) {
                                imageFile.saveToTempFile()
                            }
                            val fileName = imageFile.name

                            println("File name: $fileName, path: $filePath")

                            val bucket = currentState.ossBucket
                            val key = "${currentState.ossKeyPrefix}${TimeUtil.nowMillis()}_${index}_$fileName"

                            // 在后台线程中执行上传操作
                            val result = withContext(Dispatchers.Default) {
                                ossService.putObject(bucket, key, filePath)
                            }

                            when (result) {
                                is ApiResult.Success -> {
                                    imageUrls.add(result.data)
                                    println("Image ${index + 1} uploaded successfully, URL: ${result.data}")
                                }

                                is ApiResult.Error -> {
                                    println("Image ${index + 1} upload failed: ${result.message}")
                                    _uiState.value = _uiState.value.copy(isUploading = false)
                                    throw Exception("图片 ${index + 1} 上传失败: ${result.message}")
                                }
                            }
                        }

                        // 清除已选择的图片
                        _uiState.value = _uiState.value.copy(
                            selectedImageFiles = emptyList(),
                            isUploading = false
                        )

                        println("All images uploaded successfully. Total: ${imageUrls.size}")
                    } catch (e: Exception) {
                        println("Image upload failed: ${e.message}")
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
                    image = imageUrls.takeIf { it.isNotEmpty() },
                    watermark = false,
                    sequentialImageGeneration = if (currentState.sequentialImageGenerationEnabled) {
                        SequentialImageGeneration.AUTO
                    } else {
                        SequentialImageGeneration.DISABLED
                    },
                    sequentialImageGenerationOptions = if (currentState.sequentialImageGenerationEnabled) {
                        SequentialImageGenerationOptions(
                            maxImages = currentState.maxImagesCount
                        )
                    } else {
                        null
                    }
                )

                println("Submitting image generation request...")
                val response = client.generateImages(request)
                println("Response received: $response")

                val generatedImages = response.data
                val errorMsg = response.error?.message

                if (!generatedImages.isNullOrEmpty()) {
                    println("Generated ${generatedImages.size} image(s)")
                    // 将所有生成的图片 URL 拼接成一条消息
                    val imageUrlsText = generatedImages.mapNotNull { it?.url }.joinToString("\n")

                    // 更新最后一条消息为图片 URLs
                    val currentMessages = _uiState.value.messages.toMutableList()
                    currentMessages[currentMessages.lastIndex] = ChatMessage(
                        role = ChatMessageRole.ASSISTANT,
                        content = ChatMessageContent.TextContent(imageUrlsText)
                    )
                    _uiState.value = _uiState.value.copy(
                        messages = currentMessages,
                        isLoading = false
                    )
                } else {
                    println("Image generation failed: $errorMsg")
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
                println("=== Image generation request completed ===")
            } catch (e: Exception) {
                println("=== Error occurred ===")
                println("Error type: ${e::class.simpleName}")
                println("Error message: ${e.message}")

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

    private fun sendVideoGenerationMessage() {
        val currentState = _uiState.value
        val userMessage = ChatMessage(
//            role = ChatMessageRole.USER,
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
                println("=== Starting video generation request ===")
                println("API Key: ${currentState.apiKey.take(10)}...")
                println("Model: ${currentState.selectedModel.id}")
                println("Prompt: ${userMessage.content}")

                val httpClient = createHttpClient()
                val client = ArkClient(
                    httpClient = httpClient,
                    defaultApiKey = currentState.apiKey
                )

                // 如果选择了图片文件，先上传图片
                val imageUrls = mutableListOf<String>()
                if (currentState.selectedImageFiles.isNotEmpty()) {
                    // 检查AK/SK是否已配置
                    if (currentState.accessKey.isBlank() || currentState.secretKey.isBlank()) {
                        println("AK/SK not configured")
                        _uiState.value = _uiState.value.copy(
                            errorMessage = "请先配置 Access Key 和 Secret Key",
                            isLoading = false
                        )
                        return@launch
                    }

                    println("Uploading ${currentState.selectedImageFiles.size} image file(s)...")
                    _uiState.value = _uiState.value.copy(isUploading = true)

                    try {
                        val ossService = ObjectStorageService(
                            ak = currentState.accessKey,
                            sk = currentState.secretKey
                        )

                        // 上传所有选中的图片
                        for ((index, imageFile) in currentState.selectedImageFiles.withIndex()) {
                            println("Uploading image ${index + 1}/${currentState.selectedImageFiles.size}")

                            val filePath = withContext(Dispatchers.Default) {
                                imageFile.saveToTempFile()
                            }
                            val fileName = imageFile.name

                            println("File name: $fileName, path: $filePath")

                            val bucket = currentState.ossBucket
                            val key = "${currentState.ossKeyPrefix}${TimeUtil.nowMillis()}_${index}_$fileName"

                            val result = withContext(Dispatchers.Default) {
                                ossService.putObject(bucket, key, filePath)
                            }

                            when (result) {
                                is ApiResult.Success -> {
                                    imageUrls.add(result.data)
                                    println("Image ${index + 1} uploaded successfully, URL: ${result.data}")
                                }

                                is ApiResult.Error -> {
                                    println("Image ${index + 1} upload failed: ${result.message}")
                                    _uiState.value = _uiState.value.copy(isUploading = false)
                                    throw Exception("图片 ${index + 1} 上传失败: ${result.message}")
                                }
                            }
                        }

                        // 清除已选择的图片
                        _uiState.value = _uiState.value.copy(
                            selectedImageFiles = emptyList(),
                            isUploading = false
                        )

                        println("All images uploaded successfully. Total: ${imageUrls.size}")
                    } catch (e: Exception) {
                        println("Image upload failed: ${e.message}")
                        _uiState.value = _uiState.value.copy(isUploading = false)
                        throw Exception("图片上传失败: ${e.message}", e)
                    }
                }

                // 构建视频生成请求的 content
                val contentList = mutableListOf<CreateContentGenerationTaskRequest.Content>()

                // 添加文本内容
                val promptText = when (val content = userMessage.content) {
                    is ChatMessageContent.TextContent -> content.value
                    else -> ""
                }
                if (promptText.isNotEmpty()) {
                    contentList.add(CreateContentGenerationTaskRequest.Content.text(promptText))
                }

                // 根据生成模式添加图片内容
                when (currentState.videoGenerationMode) {
                    VideoGenerationMode.FIRST_FRAME -> {
                        // 首帧模式：需要1张图片
                        if (imageUrls.isNotEmpty()) {
                            contentList.add(CreateContentGenerationTaskRequest.Content.firstFrame(imageUrls[0]))
                        }
                    }

                    VideoGenerationMode.FIRST_LAST_FRAME -> {
                        // 首尾帧模式：需要2张图片
                        if (imageUrls.size >= 2) {
                            contentList.add(CreateContentGenerationTaskRequest.Content.firstFrame(imageUrls[0]))
                            contentList.add(CreateContentGenerationTaskRequest.Content.lastFrame(imageUrls[1]))
                        }
                    }

                    VideoGenerationMode.REFERENCE_IMAGE -> {
                        // 参考图模式：1-4张图片
                        for (imageUrl in imageUrls.take(4)) {
                            contentList.add(CreateContentGenerationTaskRequest.Content.referenceImage(imageUrl))
                        }
                    }

                    VideoGenerationMode.TEXT_TO_VIDEO -> {
                        // 文字描述模式：不需要图片
                    }
                }

                val request = CreateContentGenerationTaskRequest(
                    model = currentState.selectedModel.id,
                    content = contentList,
                    resolution = currentState.videoResolution.value,
                    ratio = currentState.videoRatio.value,
                    duration = currentState.videoDuration.toLong(),
                    generateAudio = currentState.videoGenerateAudio,
                    draft = currentState.videoSampleMode
                )

                println("Submitting video generation task...")
                val createResponse = try {
                    client.createContentGenerationTask(request)
                } catch (e: Exception) {
                    println("Task creation failed: ${e.message}")
                    // 尝试从异常消息中提取错误信息
                    val errorMessage = e.message ?: "未知错误"
                    println("Error message: $errorMessage")
                    throw Exception("视频生成任务创建失败: $errorMessage")
                }

                println("Task created successfully: ${createResponse.id}")

                val taskId = createResponse.id
                if (taskId == null) {
                    throw Exception("任务创建失败: 未返回任务ID")
                }

                // 轮询任务状态
                println("Polling task status...")
                var taskResponse: com.volcengine.ark.runtime.model.content.generation.GetContentGenerationTaskResponse? = null
                var pollCount = 0
                val maxPollCount = 120 // 最多轮询2分钟（每秒一次）

                while (pollCount < maxPollCount) {
                    kotlinx.coroutines.delay(1000) // 每秒轮询一次
                    pollCount++

                    taskResponse = client.getContentGenerationTask(
                        GetContentGenerationTaskRequest(taskId = taskId)
                    )

                    println("Poll #$pollCount - Status: ${taskResponse.status}")

                    // 如果有错误信息，输出详细日志
                    if (taskResponse.error != null) {
                        println("Task error detected:")
                        println("  Code: ${taskResponse.error?.code}")
                        println("  Message: ${taskResponse.error?.message}")
                    }

                    when (taskResponse.status) {
                        "succeeded" -> {
                            println("Task succeeded!")
                            println("Video URL: ${taskResponse.content?.videoUrl}")
                            break
                        }

                        "failed" -> {
                            val error = taskResponse.error
                            val errorCode = error?.code ?: "Unknown"
                            val errorMsg = error?.message ?: "未知错误"
                            println("Task failed - Code: $errorCode, Message: $errorMsg")
                            throw Exception("视频生成失败 [$errorCode]: $errorMsg")
                        }

                        "pending", "processing" -> {
                            // 继续轮询
                            continue
                        }

                        else -> {
                            println("Unknown status: ${taskResponse.status}")
                        }
                    }
                }

                if (taskResponse?.status != "succeeded") {
                    throw Exception("视频生成超时: 任务在${maxPollCount}秒内未完成")
                }

                // 获取视频URL
                val videoUrl = taskResponse.content?.videoUrl
                if (videoUrl != null) {
                    println("Video generated successfully: $videoUrl")
                    println("Full response: $taskResponse")

                    // 更新最后一条消息为视频 URL
                    val currentMessages = _uiState.value.messages.toMutableList()
                    currentMessages[currentMessages.lastIndex] = ChatMessage(
                        role = ChatMessageRole.ASSISTANT,
                        content = ChatMessageContent.TextContent(videoUrl)
                    )
                    _uiState.value = _uiState.value.copy(
                        messages = currentMessages,
                        isLoading = false
                    )
                } else {
                    throw Exception("视频生成失败: 未返回视频URL")
                }

                httpClient.close()
                println("=== Video generation request completed ===")
            } catch (e: Exception) {
                println("=== Error occurred ===")
                println("Error type: ${e::class.simpleName}")
                println("Error message: ${e.message}")

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





