package org.kevin.kotlinx

import java.io.DataInputStream

fun DataInputStream.read(count: Int): ByteArray {
    val result = ByteArray(count)
    readFully(result)
    return result
}
