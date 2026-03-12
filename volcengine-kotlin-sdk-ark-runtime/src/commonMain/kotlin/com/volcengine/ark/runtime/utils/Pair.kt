package com.volcengine.ark.runtime.utils

class Pair<X, Y>(name: X?, value: Y?) {
    var name: X? = null
        private set
    var value: Y? = null
        private set

    init {
        this.name = name
        this.value = value
    }
}
