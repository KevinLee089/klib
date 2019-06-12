package org.kevin.kotlinx.util

import android.content.Context
import org.kevin.kotlinx.loge
import java.lang.ref.WeakReference

object Global {
    private var sContext: WeakReference<Context>? = null
    val globalContext: Context
        @Throws(IllegalStateException::class) get() = sContext?.get() ?: throw IllegalStateException(
            "Must call Global.init(Context) before.")

    fun init(context: Context) {
        val ctx = sContext?.get()
        if (ctx != null) {
            loge("init should be called once.")
            return
        }
        sContext = WeakReference(context)
    }
}