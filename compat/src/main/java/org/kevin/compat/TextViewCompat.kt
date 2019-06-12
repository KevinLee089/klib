package org.kevin.compat

import android.os.Build
import android.widget.TextView

fun TextView.setAppereance(resId: Int) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    setTextAppearance(resId)
} else {
    @Suppress("DEPRECATION") setTextAppearance(context, resId)
}