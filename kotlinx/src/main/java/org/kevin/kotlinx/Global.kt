package org.kevin.kotlinx

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import org.kevin.kotlinx.util.Global
import java.util.*

val globalContext: Context
    get() = Global.globalContext

@JvmOverloads
fun arrayCopy(src: ByteArray, srcPos: Int = 0, des: ByteArray, desPos: Int = 0, length: Int = src.size) {
    System.arraycopy(src, srcPos, des, desPos, length)
}

@Suppress("DEPRECATION")
private fun Context.getResourcesByLocale(locale: Locale): Resources {
    val resources = packageManager.getResourcesForApplication(packageName)
    val config = resources.configuration
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        config.locales = LocaleList(locale)
    } else {
        config.locale = locale
    }
    resources.updateConfiguration(config, null)
    return resources
}

fun Context.getStringArrayByLocale(resId: Int, locale: Locale): Array<String> {
    val resources = getResourcesByLocale(locale)
    return resources.getStringArray(resId)
}