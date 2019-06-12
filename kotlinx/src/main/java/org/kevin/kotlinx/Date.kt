package org.kevin.kotlinx

import org.kevin.compat.defaultLocale
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

@JvmOverloads
fun Date?.getFormatString(pattern: String = "yyyy-MM-dd HH:mm:ss"): String {
    if (this == null) return ""
    val sdf = SimpleDateFormat(pattern, defaultLocale)
    return sdf.format(this)
}

operator fun Date.minus(date: Date) : Long = time - date.time

fun days(start: Date, end: Date): Int {
    val c1 = Calendar.getInstance()
    val c2 = Calendar.getInstance()
    c1.time = start
    c2.time = end
    c1[Calendar.HOUR_OF_DAY] = 0
    c1[Calendar.MINUTE] = 0
    c1[Calendar.SECOND] = 0
    c2[Calendar.HOUR_OF_DAY] = 0
    c2[Calendar.MINUTE] = 0
    c2[Calendar.SECOND] = 0

    return abs((c1.timeInMillis / 1000 - c2.timeInMillis / 1000) / 60 / 60 / 24).toInt()
}