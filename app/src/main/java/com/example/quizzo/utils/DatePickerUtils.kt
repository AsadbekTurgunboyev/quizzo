package com.example.quizzo.utils

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatDelegate
import java.util.*

object DatePickerUtils {


    fun showDatePickerDialog(
        activity: Activity,
        onDateSet: (birth: String) -> Unit
    ) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val themeResId = when (AppCompatDelegate.getDefaultNightMode()) {
            AppCompatDelegate.MODE_NIGHT_YES -> AlertDialog.THEME_HOLO_DARK
            else -> AlertDialog.THEME_HOLO_LIGHT
        }
        val datePickerDialog =
            DatePickerDialog(
                activity,
                themeResId,
                { _, _year, _month, _dayOfMonth ->

                    val day = if (_dayOfMonth < 10) "0${_dayOfMonth}" else {_dayOfMonth}
                    val birth = if (_month + 1 < 10) {
                        "$_year-0${_month + 1}-$day"
                    } else {
                        "$_year-${_month + 1}-$day"
                    }

                    onDateSet(birth)
                },
                year,
                month,
                dayOfMonth
            )

        datePickerDialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Bekor qilish") { _, _ -> }

        datePickerDialog.show()
    }
}
