package io.github.xingray.volcengine_kotlin_sdk_ark.model

enum class VideoGenerationMode(val displayName: String) {
    TEXT_TO_VIDEO("文字描述"),
    FIRST_FRAME("首帧图片"),
    FIRST_LAST_FRAME("首尾帧"),
    REFERENCE_IMAGE("参考图")
}

enum class VideoResolution(val value: String, val displayName: String) {
    P480("480p", "480P"),
    P720("720p", "720P"),
    P1080("1080p", "1080P")
}

enum class VideoRatio(val value: String, val displayName: String) {
    RATIO_21_9("21:9", "21:9"),
    RATIO_16_9("16:9", "16:9"),
    RATIO_4_3("4:3", "4:3"),
    RATIO_1_1("1:1", "1:1"),
    RATIO_3_4("3:4", "3:4"),
    RATIO_9_16("9:16", "9:16")
}
