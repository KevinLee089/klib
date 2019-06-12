package org.kevin.kotlinx

import kotlin.experimental.xor

inline val ByteArray?.xorAll: Byte
    get() {
        var result: Byte = 0
        this?.forEach {
            result = result xor it
        }
        return result
    }