package org.kevin.jetpackx

import android.content.Context
import androidx.core.content.getSystemService

@Throws(IllegalStateException::class)
inline fun <reified T : Any> Context.requireSystemService(): T = getSystemService()
    ?: throw IllegalStateException("Cannot find system service: ${T::class.simpleName}")