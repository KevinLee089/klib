package org.kevin.compat

import android.os.Build

inline fun <reified T> MutableList<T>.removeIfCompat(noinline filter: (T) -> Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) removeIf(filter) else {
        for (i in size - 1 downTo 0) {
            if (filter(this[i])) {
                removeAt(i)
            }
        }
    }
}