package org.kevin.kotlinx

import android.content.SharedPreferences

@JvmOverloads
@Suppress("IMPLICIT_CAST_TO_ANY")
inline fun <reified T> SharedPreferences.get(key: String, defValue: T? = null): T? {
    val cls = T::class
    return if (defValue == null) {
        when (cls) {
            Boolean::class -> getBoolean(key, false)
            Int::class -> getInt(key, 0)
            Long::class -> getLong(key, 0)
            Float::class -> getFloat(key, 0f)
            String::class -> getString(key, defValue)
            Set::class -> getStringSet(key, defValue)
            else -> throw IllegalArgumentException("Unsupported type: ${cls.simpleName}")
        } as? T
    } else when (defValue) {
        is Boolean -> getBoolean(key, defValue)
        is Int -> getInt(key, defValue)
        is Long -> getLong(key, defValue)
        is Float -> getFloat(key, defValue)
        is String -> getString(key, defValue)
        is Set<*> -> getStringSet(key, defValue as Set<String>)
        else -> throw IllegalArgumentException("Unsupported type: ${cls.simpleName}")
    } as? T
}

inline fun <reified T : Number> SharedPreferences.require(key: String, defValue: T) =
    get(key, defValue) ?: defValue

inline fun <reified T> SharedPreferences.Editor.put(key: String,
    value: T): SharedPreferences.Editor {
    @Suppress("UNCHECKED_CAST") when (value) {
        is Boolean -> putBoolean(key, value)
        is Int -> putInt(key, value)
        is Long -> putLong(key, value)
        is Float -> putFloat(key, value)
        is String -> putString(key, value)
        is Set<*> -> putStringSet(key, value as Set<String>)
        else -> throw IllegalArgumentException("Unsupported type: ${T::class.simpleName}")
    }
    return this
}