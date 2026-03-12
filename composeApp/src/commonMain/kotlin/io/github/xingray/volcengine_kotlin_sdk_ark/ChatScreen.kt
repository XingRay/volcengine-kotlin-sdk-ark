package io.github.xingray.volcengine_kotlin_sdk_ark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ChatScreen(viewModel: ChatViewModel = viewModel { ChatViewModel() }) {
    val uiState by viewModel.uiState.collectAsState()

    MaterialTheme {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Left panel - Parameters
            ParametersPanel(
                apiKey = uiState.apiKey,
                model = uiState.model,
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
                modifier = Modifier.weight(0.3f)
            )

            // Right panel - Chat
            ChatPanel(
                messages = uiState.messages,
                inputText = uiState.inputText,
                isLoading = uiState.isLoading,
                onInputChange = viewModel::updateInputText,
                onSendClick = viewModel::sendMessage,
                modifier = Modifier.weight(0.7f)
            )
        }
    }
}

@Composable
fun ParametersPanel(
    apiKey: String,
    model: String,
    temperature: Double,
    topP: Double,
    maxTokens: Int,
    streamEnabled: Boolean,
    deepThinkingEnabled: Boolean,
    onApiKeyChange: (String) -> Unit,
    onModelChange: (String) -> Unit,
    onTemperatureChange: (Double) -> Unit,
    onTopPChange: (Double) -> Unit,
    onMaxTokensChange: (Int) -> Unit,
    onStreamEnabledChange: (Boolean) -> Unit,
    onDeepThinkingEnabledChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxHeight(),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Parameters",
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
                value = model,
                onValueChange = onModelChange,
                label = { Text("Model") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

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
}

@Composable
fun ChatPanel(
    messages: List<com.volcengine.ark.runtime.model.completion.chat.ChatMessage>,
    inputText: String,
    isLoading: Boolean,
    onInputChange: (String) -> Unit,
    onSendClick: () -> Unit,
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
            items(messages) { message ->
                MessageBubble(message)
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
                Text("Send")
            }
        }
    }
}

@Composable
fun MessageBubble(message: com.volcengine.ark.runtime.model.completion.chat.ChatMessage) {
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
                Text(
                    text = message.role?.value ?: "unknown",
                    style = MaterialTheme.typography.labelSmall,
                    color = textColor.copy(alpha = 0.7f)
                )
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
                                text = message.reasoningContent?:"",
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
