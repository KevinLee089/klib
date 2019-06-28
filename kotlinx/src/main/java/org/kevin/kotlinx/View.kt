package org.kevin.kotlinx

import android.app.TimePickerDialog
import android.content.Context
import android.view.View
import android.widget.CompoundButton
import android.widget.TimePicker

inline fun <reified T : View> T.onClick(noinline action: T.() -> Unit) = setOnClickListener { action(it as T) }

inline fun <reified T : View> T.onLongClick(noinline action: T.() -> Boolean) =
    setOnLongClickListener { action(it as T) }

fun CompoundButton.checkedChange(action: (buttonView: CompoundButton, isChecked: Boolean) -> Unit) =
    setOnCheckedChangeListener(action)

fun showTimePicker(
    context: Context, hourOfDay: Int, minute: Int, is24Hour: Boolean,
    action: (view: TimePicker, hourOfDay: Int, minute: Int) -> Unit
) =
    TimePickerDialog(context, TimePickerDialog.OnTimeSetListener(action), hourOfDay, minute, is24Hour).show()