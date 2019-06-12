package org.kevin.kotlinx

fun<K,V> Map<K, V>.keyAt(value: V): K? {
    val entry = entries.find { it.value == value }
    return entry?.key
}