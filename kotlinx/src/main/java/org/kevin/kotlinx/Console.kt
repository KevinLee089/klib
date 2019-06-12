package org.kevin.kotlinx

import android.util.Log

fun logi(tag: String, msg: String) = Log.i(tag, msg)

fun Any.logi(msg: String) = logi(this.javaClass.simpleName, msg)

fun loge(tag: String, msg: String) = Log.i(tag, msg)

fun Any.loge(msg: String) = loge(this.javaClass.simpleName, msg)