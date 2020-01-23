package org.kevin.kotlinx

import android.content.Context
import android.widget.Toast

@JvmOverloads
fun Context.toast(msg: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

@JvmOverloads
fun Context.toast(resId: Int, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()