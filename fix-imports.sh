#!/bin/bash

# Fix all missing imports

# Add imports to event files
files=(
  "volcengine-kotlin-sdk-ark-runtime/src/commonMain/kotlin/com/volcengine/ark/runtime/model/responses/event/doubaoapp/DoubaoAppCallCompletedEvent.kt"
  "volcengine-kotlin-sdk-ark-runtime/src/commonMain/kotlin/com/volcengine/ark/runtime/model/responses/event/outputtext/AnnotationAddedEvent.kt"
  "volcengine-kotlin-sdk-ark-runtime/src/commonMain/kotlin/com/volcengine/ark/runtime/model/responses/event/reasoningsummary/ReasoningSummaryPartDoneEvent.kt"
  "volcengine-kotlin-sdk-ark-runtime/src/commonMain/kotlin/com/volcengine/ark/runtime/model/responses/item/ItemReasoning.kt"
  "volcengine-kotlin-sdk-ark-runtime/src/commonMain/kotlin/com/volcengine/ark/runtime/model/responses/item/ItemWebSearchCall.kt"
  "volcengine-kotlin-sdk-ark-runtime/src/commonMain/kotlin/com/volcengine/ark/runtime/model/responses/item/MessageContent.kt"
  "volcengine-kotlin-sdk-ark-runtime/src/commonMain/kotlin/com/volcengine/ark/runtime/model/responses/item/doubaoapp/DoubaoAppCallBlockOutputText.kt"
  "volcengine-kotlin-sdk-ark-runtime/src/commonMain/kotlin/com/volcengine/ark/runtime/model/responses/item/doubaoapp/DoubaoAppCallBlockReasoningSearch.kt"
  "volcengine-kotlin-sdk-ark-runtime/src/commonMain/kotlin/com/volcengine/ark/runtime/model/responses/item/doubaoapp/DoubaoAppCallBlockReasoningText.kt"
  "volcengine-kotlin-sdk-ark-runtime/src/commonMain/kotlin/com/volcengine/ark/runtime/model/responses/item/doubaoapp/DoubaoAppCallBlockSearch.kt"
  "volcengine-kotlin-sdk-ark-runtime/src/commonMain/kotlin/com/volcengine/ark/runtime/model/responses/request/CreateResponsesRequest.kt"
  "volcengine-kotlin-sdk-ark-runtime/src/commonMain/kotlin/com/volcengine/ark/runtime/model/responses/request/ResponsesInput.kt"
  "volcengine-kotlin-sdk-ark-runtime/src/commonMain/kotlin/com/volcengine/ark/runtime/model/responses/response/ResponseObject.kt"
)

echo "Files to process: ${#files[@]}"
