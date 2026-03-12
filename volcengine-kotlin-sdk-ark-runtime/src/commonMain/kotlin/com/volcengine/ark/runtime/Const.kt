package com.volcengine.ark.runtime

object Const {
    val ACCESS_KEY: String = "VOLC_ACCESSKEY"
    val SECRET_KEY: String = "VOLC_SECRETKEY"
    val CLIENT_REQUEST_HEADER: String = "X-Client-Request-Id"
    val SERVER_REQUEST_HEADER: String = "X-Request-Id"
    val REQUEST_MODEL: String = "X-Request-Model"
    val REQUEST_BOT: String = "X-Request-Bot"
    val REQUEST_PROJECT_NAME: String = "X-Project-Name"
    val RETRY_AFTER: String = "Retry-After"
    val REQUEST_BOT_ID: String = "botId"
    val DEFAULT_MANDATORY_REFRESH_TIMEOUT: Integer = 10 * 60 // 10 min
    val DEFAULT_ADVISORY_REFRESH_TIMEOUT: Integer = 30 * 60 // 30 min
    val DEFAULT_STS_TIMEOUT: Integer = 7 * 24 * 60 * 60 // 7 days

    val RESOURCE_TYPE_BOT: String = "bot"
    val RESOURCE_TYPE_ENDPOINT: String = "endpoint"
    val RESOURCE_TYPE_PRESETENDPOINT: String = "presetendpoint"

    val CONTEXT_MODE_SESSION: String = "session"
    val CONTEXT_MODE_COMMON_PREFIX: String = "common_prefix"
    val TRUNCATION_STRATEGY_TYPE_LAST_HISTORY_TOKENS: String = "last_history_tokens"
    val TRUNCATION_STRATEGY_TYPE_ROLLING_TOKENS: String = "rolling_tokens"

    val BATCH_PATH_PREFIX: String = "/api/v3/batch"
    const val MAX_RETRY_TIMES: Int = 259200
}
