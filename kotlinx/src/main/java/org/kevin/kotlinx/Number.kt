package org.kevin.kotlinx

import android.content.Context
import kotlin.experimental.xor

inline val Int.byteArray: ByteArray
    get() = byteArrayOf(((this shr 24) and 0xFF).toByte(), ((this shr 16) and 0xFF).toByte(),
        ((this shr 0) and 0xFF).toByte(), this.toByte())

fun Int.distanceDisplay(context: Context): String {
    val value: String
    val unit: String
    if (this >= 1000) {
        value = (this / 1000f).toString()
        unit = context.getString(R.string.unit_km)
    } else {
        value = toString()
        unit = context.getString(R.string.unit_m)
    }
    return "$value$unit"
}

inline val Short.byteArray: ByteArray
    get() = byteArrayOf(((this.toInt() shr 8) and 0xFF).toByte(), this.toByte())

inline val Byte.sign: Int
    get() = when {
        this < 0 -> -1
        this > 0 -> 1
        else -> 0
    }

infix fun Byte.xorx(short: Short) = this xorx short.byteArray

infix fun Byte.xorx(byteArray: ByteArray?) = if (byteArray == null) this else (this xor byteArray.xorAll)

infix fun Byte.shlx(bitCount: Int) = (toInt() and 0xFF) shl bitCount

infix fun Byte.shrx(bitCount: Int) = (toInt() and 0xFF) shr bitCount

infix fun Byte.shr(bitCount: Int) = (this shrx bitCount).toByte()

infix fun Byte.shl(bitCount: Int) = (this shlx bitCount).toByte()

infix fun Byte.equals(value: Int) = this == value.toByte()

infix fun Byte.notEquals(value: Int) = this != value.toByte()

inline val Int.dp: Int
    get() {
        val density = globalContext.resources.displayMetrics.density
        return (this * density).toInt()
    }
