package io.github.xingray.volcengine_kotlin_sdk_ark.model

data class AiModel(
    val id: String,
    val name: String,
    val type: AiModelType
) {
    companion object {
        val AVAILABLE_MODELS = listOf(
            AiModel(
                id = "doubao-seed-2-0-lite-260215",
                name = "Doubao Seed 2.0 Lite",
                type = AiModelType.TEXT
            ),
            AiModel(
                id = "doubao-seedream-5-0-260128",
                name = "Doubao Seedream 5.0",
                type = AiModelType.IMAGE
            ),
            AiModel(
                id = "doubao-seedream-4-5-251128",
                name = "Doubao Seedream 4.5",
                type = AiModelType.IMAGE
            ),
            AiModel(
                id = "doubao-seedream-4-0-250828",
                name = "Doubao Seedream 4.0",
                type = AiModelType.IMAGE
            ),
            AiModel(
                id = "doubao-seedance-1-5-pro-251215",
                name = "Seedance 1.5 Pro",
                type = AiModelType.VIDEO
            ),
            AiModel(
                id = "doubao-seedance-1-0-pro-250528",
                name = "Seedance 1.0 Pro",
                type = AiModelType.VIDEO
            ),
            AiModel(
                id = "doubao-seedance-1-0-pro-fast-251015",
                name = "Seedance 1.0 Pro Fast",
                type = AiModelType.VIDEO
            ),
            AiModel(
                id = "doubao-seedance-1-0-lite-t2v-250428",
                name = "Seedance 1.0 Lite T2V",
                type = AiModelType.VIDEO
            ),
            AiModel(
                id = "doubao-seedance-1-0-lite-i2v-250428",
                name = "Seedance 1.0 Lite I2V",
                type = AiModelType.VIDEO
            )
        )

        fun findById(id: String): AiModel? {
            return AVAILABLE_MODELS.find { it.id == id }
        }

        fun getDefaultModel(): AiModel {
            return AVAILABLE_MODELS.first()
        }
    }
}
