package io.github.xingray.volcengine_kotlin_sdk_ark

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
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
                // Left panel - Basic Configuration
                BasicConfigPanel(
                    apiKey = uiState.apiKey,
                    accessKey = uiState.accessKey,
                    secretKey = uiState.secretKey,
                    ossBucket = uiState.ossBucket,
                    ossKeyPrefix = uiState.ossKeyPrefix,
                    selectedModel = uiState.selectedModel,
                    onApiKeyChange = viewModel::updateApiKey,
                    onAccessKeyChange = viewModel::updateAccessKey,
                    onSecretKeyChange = viewModel::updateSecretKey,
                    onOssBucketChange = viewModel::updateOssBucket,
                    onOssKeyPrefixChange = viewModel::updateOssKeyPrefix,
                    onModelChange = viewModel::updateModel,
                    modifier = Modifier.weight(0.25f).widthIn(min = 250.dp)
                )

                // Middle panel - Chat
                ChatPanel(
                    messages = uiState.messages,
                    inputText = uiState.inputText,
                    isLoading = uiState.isLoading,
                    isUploading = uiState.isUploading,
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
                    onImageClick = viewModel::showImageDialog,
                    modifier = Modifier.weight(0.5f)
                )

                // Right panel - Model Parameters
                ModelParametersPanel(
                    selectedModel = uiState.selectedModel,
                    temperature = uiState.temperature,
                    topP = uiState.topP,
                    maxTokens = uiState.maxTokens,
                    streamEnabled = uiState.streamEnabled,
                    deepThinkingEnabled = uiState.deepThinkingEnabled,
                    sequentialImageGenerationEnabled = uiState.sequentialImageGenerationEnabled,
                    maxImagesCount = uiState.maxImagesCount,
                    onTemperatureChange = viewModel::updateTemperature,
                    onTopPChange = viewModel::updateTopP,
                    onMaxTokensChange = viewModel::updateMaxTokens,
                    onStreamEnabledChange = viewModel::updateStreamEnabled,
                    onDeepThinkingEnabledChange = viewModel::updateDeepThinkingEnabled,
                    onSequentialImageGenerationEnabledChange = viewModel::updateSequentialImageGenerationEnabled,
                    onMaxImagesCountChange = viewModel::updateMaxImagesCount,
                    onAddSystemMessage = { viewModel.showAddMessageDialog(com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole.SYSTEM) },
                    onAddAssistantMessage = { viewModel.showAddMessageDialog(com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole.ASSISTANT) },
                    onAddUserMessage = { viewModel.showAddMessageDialog(com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole.USER) },
                    modifier = Modifier.weight(0.25f).widthIn(min = 250.dp)
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

            // Image dialog
            if (uiState.showImageDialog && uiState.dialogImageUrl != null) {
                ImageDialog(
                    imageUrl = uiState.dialogImageUrl!!,
                    onDismiss = viewModel::hideImageDialog
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicConfigPanel(
    apiKey: String,
    accessKey: String,
    secretKey: String,
    ossBucket: String,
    ossKeyPrefix: String,
    selectedModel: AiModel,
    onApiKeyChange: (String) -> Unit,
    onAccessKeyChange: (String) -> Unit,
    onSecretKeyChange: (String) -> Unit,
    onOssBucketChange: (String) -> Unit,
    onOssKeyPrefixChange: (String) -> Unit,
    onModelChange: (AiModel) -> Unit,
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Basic Configuration",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            OutlinedTextField(
                value = apiKey,
                onValueChange = onApiKeyChange,
                label = { Text("API Key") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = accessKey,
                onValueChange = onAccessKeyChange,
                label = { Text("Access Key (AK)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = secretKey,
                onValueChange = onSecretKeyChange,
                label = { Text("Secret Key (SK)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = ossBucket,
                onValueChange = onOssBucketChange,
                label = { Text("OSS Bucket") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = ossKeyPrefix,
                onValueChange = onOssKeyPrefixChange,
                label = { Text("OSS Key Prefix") },
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
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    ModelTypeChip(model.type)
                                    Text(model.name)
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
        }
    }
}

@Composable
fun ModelParametersPanel(
    selectedModel: AiModel,
    temperature: Double,
    topP: Double,
    maxTokens: Int,
    streamEnabled: Boolean,
    deepThinkingEnabled: Boolean,
    sequentialImageGenerationEnabled: Boolean,
    maxImagesCount: Int,
    onTemperatureChange: (Double) -> Unit,
    onTopPChange: (Double) -> Unit,
    onMaxTokensChange: (Int) -> Unit,
    onStreamEnabledChange: (Boolean) -> Unit,
    onDeepThinkingEnabledChange: (Boolean) -> Unit,
    onSequentialImageGenerationEnabledChange: (Boolean) -> Unit,
    onMaxImagesCountChange: (Int) -> Unit,
    onAddSystemMessage: () -> Unit,
    onAddAssistantMessage: () -> Unit,
    onAddUserMessage: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxHeight(),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Model Parameters",
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
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
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
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
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
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                ) {
                    Text(
                        text = "User",
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                }
            }

            // Display different parameters based on model type
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
                    ImageModelParamsPanel(
                        sequentialImageGenerationEnabled = sequentialImageGenerationEnabled,
                        maxImagesCount = maxImagesCount,
                        onSequentialImageGenerationEnabledChange = onSequentialImageGenerationEnabledChange,
                        onMaxImagesCountChange = onMaxImagesCountChange
                    )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParametersPanel(
    apiKey: String,
    accessKey: String,
    secretKey: String,
    ossBucket: String,
    ossKeyPrefix: String,
    selectedModel: AiModel,
    temperature: Double,
    topP: Double,
    maxTokens: Int,
    streamEnabled: Boolean,
    deepThinkingEnabled: Boolean,
    sequentialImageGenerationEnabled: Boolean,
    maxImagesCount: Int,
    onApiKeyChange: (String) -> Unit,
    onAccessKeyChange: (String) -> Unit,
    onSecretKeyChange: (String) -> Unit,
    onOssBucketChange: (String) -> Unit,
    onOssKeyPrefixChange: (String) -> Unit,
    onModelChange: (AiModel) -> Unit,
    onTemperatureChange: (Double) -> Unit,
    onTopPChange: (Double) -> Unit,
    onMaxTokensChange: (Int) -> Unit,
    onStreamEnabledChange: (Boolean) -> Unit,
    onDeepThinkingEnabledChange: (Boolean) -> Unit,
    onSequentialImageGenerationEnabledChange: (Boolean) -> Unit,
    onMaxImagesCountChange: (Int) -> Unit,
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

            OutlinedTextField(
                value = accessKey,
                onValueChange = onAccessKeyChange,
                label = { Text("Access Key (AK)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = secretKey,
                onValueChange = onSecretKeyChange,
                label = { Text("Secret Key (SK)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = ossBucket,
                onValueChange = onOssBucketChange,
                label = { Text("OSS Bucket") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = ossKeyPrefix,
                onValueChange = onOssKeyPrefixChange,
                label = { Text("OSS Key Prefix") },
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
                    ImageModelParamsPanel(
                        sequentialImageGenerationEnabled = sequentialImageGenerationEnabled,
                        maxImagesCount = maxImagesCount,
                        onSequentialImageGenerationEnabledChange = onSequentialImageGenerationEnabledChange,
                        onMaxImagesCountChange = onMaxImagesCountChange
                    )
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
    isUploading: Boolean,
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
    onImageClick: (String) -> Unit,
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
                        onDelete = { onDeleteMessage(index) },
                        onImageClick = onImageClick
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
                enabled = !isLoading && !isUploading
            )

            Button(
                onClick = onSendClick,
                enabled = !isLoading && !isUploading && inputText.isNotBlank(),
                modifier = Modifier.height(56.dp)
            ) {
                if (isUploading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "上传中...",
                        maxLines = 1,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                } else if (isLoading) {
                    Text(
                        text = "生成中...",
                        maxLines = 1,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                } else {
                    Text(
                        text = "Send",
                        maxLines = 1,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun MessageBubble(
    message: com.volcengine.ark.runtime.model.completion.chat.ChatMessage,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onImageClick: (String) -> Unit
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

    // Check if content contains image URLs (split by newline)
    val lines = contentText.split("\n").filter { it.isNotBlank() }
    val imageUrls = lines.filter { it.startsWith("http://") || it.startsWith("https://") }
    val hasImages = imageUrls.isNotEmpty() && !isUser

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
                    if (hasImages) {
                        // Display multiple images in a grid
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            imageUrls.forEach { imageUrl ->
                                AsyncImage(
                                    model = imageUrl,
                                    contentDescription = "Generated image",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .heightIn(max = 300.dp)
                                        .clickable { onImageClick(imageUrl) },
                                    contentScale = ContentScale.Fit
                                )
                            }
                        }
                    } else {
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
fun ImageModelParamsPanel(
    sequentialImageGenerationEnabled: Boolean,
    maxImagesCount: Int,
    onSequentialImageGenerationEnabledChange: (Boolean) -> Unit,
    onMaxImagesCountChange: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 生成组图开关
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "生成组图",
                style = MaterialTheme.typography.bodyMedium
            )
            Switch(
                checked = sequentialImageGenerationEnabled,
                onCheckedChange = onSequentialImageGenerationEnabledChange
            )
        }

        // 组图数量滑块
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "组图数量",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (sequentialImageGenerationEnabled) {
                        MaterialTheme.colorScheme.onSurface
                    } else {
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                    }
                )
                Text(
                    text = maxImagesCount.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (sequentialImageGenerationEnabled) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                    }
                )
            }
            Slider(
                value = maxImagesCount.toFloat(),
                onValueChange = { onMaxImagesCountChange(it.toInt()) },
                valueRange = 1f..15f,
                steps = 13,
                enabled = sequentialImageGenerationEnabled,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
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

@Composable
fun ImageDialog(
    imageUrl: String,
    onDismiss: () -> Unit
) {
    val clipboardManager = androidx.compose.ui.platform.LocalClipboardManager.current

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.fillMaxSize(0.9f)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Image display area
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "Full size image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )

                    // Close button at top right
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(16.dp)
                            .background(
                                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                                shape = RoundedCornerShape(50)
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                // URL display and copy area at bottom
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = imageUrl,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                // Copy to clipboard
                                clipboardManager.setText(
                                    androidx.compose.ui.text.AnnotatedString(imageUrl)
                                )
                            },
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = "复制",
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }
            }
        }
    }
}
