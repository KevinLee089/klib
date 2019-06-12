@file:Suppress("DEPRECATION")

package org.kevin.compat

import android.os.Build
import android.widget.TimePicker

inline var TimePicker.hourCompat: Int
    set(value) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) hour = value else currentHour = value
    get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) hour else currentHour

inline var TimePicker.minuteCompat: Int
    set(value) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) minute = value else currentMinute = value
    get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) minute else currentMinute