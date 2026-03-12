package com.volcengine.ark.runtime.utils

import java.util.ArrayList

object Converter {
    fun <T> castList(obj: Object?, clazz: Class<T?>): List<T?>? {
        val result: List<T?> = ArrayList<T?>()
        if (obj is List<*>) {
            for (o in (obj as List<*>?)!!) {
                result.add(clazz.cast(o))
            }
            return result
        }
        return null
    }
}
