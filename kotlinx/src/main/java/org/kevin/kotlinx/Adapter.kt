package org.kevin.kotlinx

import android.widget.ArrayAdapter

operator fun <T> ArrayAdapter<T>.get(position: Int): T? = getItem(position)