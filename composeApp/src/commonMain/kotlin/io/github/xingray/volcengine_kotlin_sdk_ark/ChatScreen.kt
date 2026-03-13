package io.github.xingray.volcengine_kotlin_sdk_ark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.vinceglb.filekit.dialogs.compose.rememberFilePickerLauncher
import io.github.vinceglb.filekit.dialogs.FileKitMode
import io.github.vinceglb.filekit.dialogs.FileKitType
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.name
import io.github.xingray.volcengine_kotlin_sdk_ark.model.AiModel
import io.github.xingray.volcengine_kotlin_sdk_ark.model.AiModelType

@Composable
fun ChatScreen(viewModel: ChatViewModel = viewModel { ChatViewModel() }) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let { message ->
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
            viewModel.clearErrorMessage()
        }
    }

    MaterialTheme {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { paddingValues ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                // Left panel - Parameters
                ParametersPanel(
                    apiKey = uiState.apiKey,
                    selectedModel = uiState.selectedModel,
                    temperature = uiState.temperature,
                    topP = uiState.topP,
                    maxTokens = uiState.maxTokens,
                    streamEnabled = uiState.streamEnabled,
                    deepThinkingEnabled = uiState.deepThinkingEnabled,
                    onApiKeyChange = viewModel::updateApiKey,
                    onModelChange = viewModel::updateModel,
                    onTemperatureChange = viewModel::updateTemperature,
                    onTopPChange = viewModel::updateTopP,
                    onMaxTokensChange = viewModel::updateMaxTokens,
                    onStreamEnabledChange = viewModel::updateStreamEnabled,
                    onDeepThinkingEnabledChange = viewModel::updateDeepThinkingEnabled,
                    onAddSystemMessage = { viewModel.showAddMessageDialog(com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole.SYSTEM) },
                    onAddAssistantMessage = { viewModel.showAddMessageDialog(com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole.ASSISTANT) },
                    onAddUserMessage = { viewModel.showAddMessageDialog(com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole.USER) },
                    modifier = Modifier.weight(0.3f).widthIn(min = 280.dp)
                )

                // Right panel - Chat
                ChatPanel(
                    messages = uiState.messages,
                    inputText = uiState.inputText,
                    isLoading = uiState.isLoading,
                    editingMessageIndex = uiState.editingMessageIndex,
                    editingMessageText = uiState.editingMessageText,
                    selectedImageFiles = uiState.selectedImageFiles,
                    selectedVideoFiles = uiState.selectedVideoFiles,
                    selectedDocumentFiles = uiState.selectedDocumentFiles,
                    onInputChange = viewModel::updateInputText,
                    onSendClick = viewModel::sendMessage,
                    onDeleteMessage = viewModel::deleteMessage,
                    onEditMessage = viewModel::startEditMessage,
                    onEditingTextChange = viewModel::updateEditingMessageText,
                    onConfirmEdit = viewModel::confirmEditMessage,
                    onCancelEdit = viewModel::cancelEditMessage,
                    onAddImageFiles = viewModel::addImageFiles,
                    onAddVideoFiles = viewModel::addVideoFiles,
                    onAddDocumentFiles = viewModel::addDocumentFiles,
                    onRemoveImageFile = viewModel::removeImageFile,
                    onRemoveVideoFile = viewModel::removeVideoFile,
                    onRemoveDocumentFile = viewModel::removeDocumentFile,
                    modifier = Modifier.weight(0.7f)
                )
            }

            // Add message dialog
            if (uiState.showAddMessageDialog) {
                AddMessageDialog(
                    role = uiState.addMessageRole,
                    text = uiState.addMessageText,
                    onTextChange = viewModel::updateAddMessageText,
                    onConfirm = viewModel::confirmAddMessage,
                    onDismiss = viewModel::hideAddMessageDialog
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParametersPanel(
    apiKey: String,
    selectedModel: AiModel,
    temperature: Double,
    topP: Double,
    maxTokens: Int,
    streamEnabled: Boolean,
    deepThinkingEnabled: Boolean,
    onApiKeyChange: (String) -> Unit,
    onModelChange: (AiModel) -> Unit,
    onTemperatureChange: (Double) -> Unit,
    onTopPChange: (Double) -> Unit,
    onMaxTokensChange: (Int) -> Unit,
    onStreamEnabledChange: (Boolean) -> Unit,
    onDeepThinkingEnabledChange: (Boolean) -> Unit,
    onAddSystemMessage: () -> Unit,
    onAddAssistantMessage: () -> Unit,
    onAddUserMessage: () -> Unit,
    modifier: Modifier = Modifier
) {
    val modelOptions = AiModel.AVAILABLE_MODELS
    var expanded by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier.fillMaxHeight(),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Parameters",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Add message buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onAddSystemMessage,
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues.Zero,
                ) {
                    Text(
                        text = "System",
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                }
                Button(
                    onClick = onAddAssistantMessage,
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues.Zero,
                ) {
                    Text(
                        text = "Assistant",
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                }
                Button(
                    onClick = onAddUserMessage,
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues.Zero,
                ) {
                    Text(
                        text = "User",
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                }
            }

            OutlinedTextField(
                value = apiKey,
                onValueChange = onApiKeyChange,
                label = { Text("API Key") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Model dropdown
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = selectedModel.name,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Model") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    leadingIcon = { ModelTypeChip(selectedModel.type) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable),
                    singleLine = true
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    modelOptions.forEach { model ->
                        DropdownMenuItem(
                            text = {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    ModelTypeChip(model.type)
                                    Text(
                                        text = model.name,
                                        maxLines = 1,
                                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                                    )
                                }
                            },
                            onClick = {
                                onModelChange(model)
                                expanded = false
                            }
                        )
                    }
                }
            }

            // Type-specific parameter panels
            when (selectedModel.type) {
                AiModelType.TEXT -> {
                    TextModelParamsPanel(
                        temperature = temperature,
                        topP = topP,
                        maxTokens = maxTokens,
                        streamEnabled = streamEnabled,
                        deepThinkingEnabled = deepThinkingEnabled,
                        onTemperatureChange = onTemperatureChange,
                        onTopPChange = onTopPChange,
                        onMaxTokensChange = onMaxTokensChange,
                        onStreamEnabledChange = onStreamEnabledChange,
                        onDeepThinkingEnabledChange = onDeepThinkingEnabledChange
                    )
                }
                AiModelType.IMAGE -> {
                    ImageModelParamsPanel()
                }
                AiModelType.VIDEO -> {
                    VideoModelParamsPanel()
                }
                AiModelType.AUDIO -> {
                    AudioModelParamsPanel()
                }
            }
        }
    }
}

@Composable
fun ChatPanel(
    messages: List<com.volcengine.ark.runtime.model.completion.chat.ChatMessage>,
    inputText: String,
    isLoading: Boolean,
    editingMessageIndex: Int?,
    editingMessageText: String,
    selectedImageFiles: List<PlatformFile>,
    selectedVideoFiles: List<PlatformFile>,
    selectedDocumentFiles: List<PlatformFile>,
    onInputChange: (String) -> Unit,
    onSendClick: () -> Unit,
    onDeleteMessage: (Int) -> Unit,
    onEditMessage: (Int) -> Unit,
    onEditingTextChange: (String) -> Unit,
    onConfirmEdit: () -> Unit,
    onCancelEdit: () -> Unit,
    onAddImageFiles: (List<PlatformFile>) -> Unit,
    onAddVideoFiles: (List<PlatformFile>) -> Unit,
    onAddDocumentFiles: (List<PlatformFile>) -> Unit,
    onRemoveImageFile: (PlatformFile) -> Unit,
    onRemoveVideoFile: (PlatformFile) -> Unit,
    onRemoveDocumentFile: (PlatformFile) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        Text(
            text = "Chat",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Messages list
        val listState = rememberLazyListState()
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(messages.size) { index ->
                val message = messages[index]
                if (editingMessageIndex == index) {
                    EditMessageCard(
                        text = editingMessageText,
                        onTextChange = onEditingTextChange,
                        onConfirm = onConfirmEdit,
                        onCancel = onCancelEdit
                    )
                } else {
                    MessageBubble(
                        message = message,
                        onEdit = { onEditMessage(index) },
                        onDelete = { onDeleteMessage(index) }
                    )
                }
            }

            if (isLoading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }

        LaunchedEffect(messages.size) {
            if (messages.isNotEmpty()) {
                listState.animateScrollToItem(messages.size - 1)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // File picker launchers
        val imagePickerLauncher = rememberFilePickerLauncher(
            type = FileKitType.Image,
            mode = FileKitMode.Multiple(),
        ) { files ->
            files?.let { onAddImageFiles(it) }
        }

        val videoPickerLauncher = rememberFilePickerLauncher(
            type = FileKitType.Video,
            mode = FileKitMode.Multiple(),
        ) { files ->
            files?.let { onAddVideoFiles(it) }
        }

        val documentPickerLauncher = rememberFilePickerLauncher(
            type = FileKitType.File(extensions = listOf("pdf", "doc", "docx", "txt", "md")),
            mode = FileKitMode.Multiple(),
        ) { files ->
            files?.let { onAddDocumentFiles(it) }
        }

        // File selection buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IconButton(
                onClick = { imagePickerLauncher.launch() },
                enabled = !isLoading
            ) {
                Icon(Icons.Default.Image, contentDescription = "选择图片")
            }
            IconButton(
                onClick = { videoPickerLauncher.launch() },
                enabled = !isLoading
            ) {
                Icon(Icons.Default.VideoLibrary, contentDescription = "选择视频")
            }
            IconButton(
                onClick = { documentPickerLauncher.launch() },
                enabled = !isLoading
            ) {
                Icon(Icons.Default.AttachFile, contentDescription = "选择文档")
            }
        }

        // Selected files display
        if (selectedImageFiles.isNotEmpty() || selectedVideoFiles.isNotEmpty() || selectedDocumentFiles.isNotEmpty()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                selectedImageFiles.forEach { file ->
                    FileChip(
                        fileName = file.name,
                        onRemove = { onRemoveImageFile(file) }
                    )
                }
                selectedVideoFiles.forEach { file ->
                    FileChip(
                        fileName = file.name,
                        onRemove = { onRemoveVideoFile(file) }
                    )
                }
                selectedDocumentFiles.forEach { file ->
                    FileChip(
                        fileName = file.name,
                        onRemove = { onRemoveDocumentFile(file) }
                    )
                }
            }
        }

        // Input area
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedTextField(
                value = inputText,
                onValueChange = onInputChange,
                modifier = Modifier.weight(1f),
                placeholder = { Text("Type a message...") },
                maxLines = 4,
                enabled = !isLoading
            )

            Button(
                onClick = onSendClick,
                enabled = !isLoading && inputText.isNotBlank(),
                modifier = Modifier.height(56.dp)
            ) {
                Text(
                    text = "Send",
                    maxLines = 1,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun MessageBubble(
    message: com.volcengine.ark.runtime.model.completion.chat.ChatMessage,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    val isUser = message.role == com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole.USER
    val alignment = if (isUser) Alignment.CenterEnd else Alignment.CenterStart
    val backgroundColor = if (isUser) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.secondaryContainer
    }
    val textColor = if (isUser) {
        MaterialTheme.colorScheme.onPrimaryContainer
    } else {
        MaterialTheme.colorScheme.onSecondaryContainer
    }

    val contentText = when (val content = message.content) {
        is com.volcengine.ark.runtime.model.completion.chat.ChatMessageContent.TextContent -> content.value
        is com.volcengine.ark.runtime.model.completion.chat.ChatMessageContent.MultiContent -> {
            content.items.joinToString("\n") { part ->
                when (part) {
                    is com.volcengine.ark.runtime.model.completion.chat.ContentPart.TextPart -> part.text
                    is com.volcengine.ark.runtime.model.completion.chat.ContentPart.ImageUrlPart -> "[Image: ${part.imageUrl.url}]"
                    is com.volcengine.ark.runtime.model.completion.chat.ContentPart.VideoUrlPart -> "[Video: ${part.videoUrl.url}]"
                }
            }
        }

        null -> ""
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = alignment
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = backgroundColor,
            modifier = Modifier.widthIn(max = 400.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = message.role?.value ?: "unknown",
                        style = MaterialTheme.typography.labelSmall,
                        color = textColor.copy(alpha = 0.7f)
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        TextButton(onClick = onEdit) {
                            Text(
                                text = "编辑",
                                style = MaterialTheme.typography.labelSmall,
                                maxLines = 1,
                                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                            )
                        }
                        TextButton(onClick = onDelete) {
                            Text(
                                text = "删除",
                                style = MaterialTheme.typography.labelSmall,
                                maxLines = 1,
                                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))

                // Display reasoning content if available
                if (!message.reasoningContent.isNullOrEmpty()) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = textColor.copy(alpha = 0.1f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(
                                text = "💭 思考过程",
                                style = MaterialTheme.typography.labelSmall,
                                color = textColor.copy(alpha = 0.6f)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = message.reasoningContent ?: "",
                                style = MaterialTheme.typography.bodySmall,
                                color = textColor.copy(alpha = 0.8f)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Display main content
                if (contentText.isNotEmpty()) {
                    Text(
                        text = contentText,
                        style = MaterialTheme.typography.bodyMedium,
                        color = textColor
                    )
                }
            }
        }
    }
}

@Composable
fun AddMessageDialog(
    role: com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole?,
    text: String,
    onTextChange: (String) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    androidx.compose.ui.window.Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.widthIn(min = 400.dp, max = 600.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "添加 ${role?.value ?: ""} 消息",
                    style = MaterialTheme.typography.headlineSmall
                )

                OutlinedTextField(
                    value = text,
                    onValueChange = onTextChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    placeholder = { Text("输入消息内容...") },
                    maxLines = 10
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = onDismiss) {
                        Text(
                            text = "取消",
                            maxLines = 1,
                            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = onConfirm,
                        enabled = text.isNotBlank()
                    ) {
                        Text(
                            text = "确认",
                            maxLines = 1,
                            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EditMessageCard(
    text: String,
    onTextChange: (String) -> Unit,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "编辑消息",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            OutlinedTextField(
                value = text,
                onValueChange = onTextChange,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 10
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onCancel) {
                    Text(
                        text = "取消",
                        maxLines = 1,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = onConfirm,
                    enabled = text.isNotBlank()
                ) {
                    Text(
                        text = "确认",
                        maxLines = 1,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun ModelTypeChip(type: AiModelType) {
    val backgroundColor = when (type) {
        AiModelType.TEXT -> Color(0xFF4CAF50)      // 绿色
        AiModelType.IMAGE -> Color(0xFF2196F3)     // 蓝色
        AiModelType.VIDEO -> Color(0xFFFF9800)     // 橙色
        AiModelType.AUDIO -> Color(0xFF9C27B0)     // 紫色
    }

    Surface(
        shape = RoundedCornerShape(4.dp),
        color = backgroundColor,
        modifier = Modifier.padding(0.dp)
    ) {
        Text(
            text = type.label,
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
            maxLines = 1
        )
    }
}

@Composable
fun TextModelParamsPanel(
    temperature: Double,
    topP: Double,
    maxTokens: Int,
    streamEnabled: Boolean,
    deepThinkingEnabled: Boolean,
    onTemperatureChange: (Double) -> Unit,
    onTopPChange: (Double) -> Unit,
    onMaxTokensChange: (Int) -> Unit,
    onStreamEnabledChange: (Boolean) -> Unit,
    onDeepThinkingEnabledChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column {
            Text("Temperature: $temperature")
            Slider(
                value = temperature.toFloat(),
                onValueChange = { onTemperatureChange(it.toDouble()) },
                valueRange = 0f..2f,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Column {
            Text("Top P: $topP")
            Slider(
                value = topP.toFloat(),
                onValueChange = { onTopPChange(it.toDouble()) },
                valueRange = 0f..1f,
                modifier = Modifier.fillMaxWidth()
            )
        }

        OutlinedTextField(
            value = maxTokens.toString(),
            onValueChange = { onMaxTokensChange(it.toIntOrNull() ?: maxTokens) },
            label = { Text("Max Tokens") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Stream Mode")
            Switch(
                checked = streamEnabled,
                onCheckedChange = onStreamEnabledChange
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("深度思考")
            Switch(
                checked = deepThinkingEnabled,
                onCheckedChange = onDeepThinkingEnabledChange
            )
        }
    }
}

@Composable
fun ImageModelParamsPanel() {
    Text(
        text = "图片生成模型参数（待实现）",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
fun VideoModelParamsPanel() {
    Text(
        text = "视频生成模型参数（待实现）",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
fun AudioModelParamsPanel() {
    Text(
        text = "音频生成模型参数（待实现）",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
fun FileChip(
    fileName: String,
    onRemove: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.secondaryContainer,
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = fileName,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                maxLines = 1,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f, fill = false)
            )
            IconButton(
                onClick = onRemove,
                modifier = Modifier.size(20.dp)
            ) {
                Text(
                    text = "×",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}
