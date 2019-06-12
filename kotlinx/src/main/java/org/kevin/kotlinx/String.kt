package org.kevin.kotlinx

import android.text.TextUtils
import org.kevin.compat.defaultLocale
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.ArrayList
import kotlin.reflect.KProperty1
import kotlin.reflect.jvm.isAccessible

inline val String?.doubleValue: Double
    get() = if (TextUtils.isEmpty(this)) 0.0 else try {
        this!!.toDouble()
    } catch (e: Exception) {
        0.0
    }

inline val String?.intValue: Int
    get() = if (TextUtils.isEmpty(this)) 0 else try {
        this!!.toInt()
    } catch (e: Exception) {
        0
    }

inline val String?.floatValue: Float
    get() = if (TextUtils.isEmpty(this)) 0f else try {
        this!!.toFloat()
    } catch (e: Exception) {
        0f
    }

inline val CharSequence?.intValue: Int
    get() = toString().intValue

inline val CharSequence?.floatValue: Float
    get() = toString().floatValue

inline val String.isIp: Boolean
    get() {
        val p = Pattern.compile(
            "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}")
        val m = p.matcher(this)
        return m.find()
    }

fun stringPairOf(vararg pair: Pair<String, Any?>): String {
    val strList = ArrayList<String>(pair.size)
    for ((key, value) in pair) {
        strList.add("$key: $value")
    }
    return strList.joinToString()
}

inline fun <reified T> T.logString(): String {
    val cls = T::class
    val sb = StringBuilder()
    sb.append(cls.simpleName)
    sb.append("[")
    sb.append(cls.members.filter { it is KProperty1<*, *> }.joinToString {
        it.isAccessible = true
        @Suppress("UNCHECKED_CAST") "${it.name}: ${(it as KProperty1<T, *>).get(this)}"
    })
    sb.append("]")
    return sb.toString()
}

@JvmOverloads
fun String.getDate(pattern: String = "yyyy-MM-dd HH:mm:ss"): Date {
    val sdf = SimpleDateFormat(pattern, defaultLocale)
    return sdf.parse(this)
}