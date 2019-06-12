package org.kevin.kotlinx

import android.app.TimePickerDialog
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.CompoundButton
import android.widget.TimePicker

fun View.click(action: (View) -> Unit) = setOnClickListener(action)

fun View.longClick(action: (View) -> Boolean) = setOnLongClickListener(action)

fun CompoundButton.checkedChange(action: (buttonView: CompoundButton, isChecked: Boolean) -> Unit) =
    setOnCheckedChangeListener(action)

fun showTimePicker(context: Context, hourOfDay: Int, minute: Int, is24Hour: Boolean,
    action: (view: TimePicker, hourOfDay: Int, minute: Int) -> Unit) =
    TimePickerDialog(context, TimePickerDialog.OnTimeSetListener(action), hourOfDay, minute, is24Hour).show()