package com.adielgarcia.gympro.ui.components

import android.app.DatePickerDialog
import androidx.compose.runtime.Composable
import java.util.Calendar
import java.util.Locale

@Composable
fun ShowDatePickerDialog(
    context: android.content.Context,
    onDateSelected: (String) -> Unit
) {
    val calendar = Calendar.getInstance()
    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH]
    val day = calendar[Calendar.DAY_OF_MONTH]

    DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            val formattedDate = String.format(
                Locale.getDefault(),
                "%04d-%02d-%02d",
                selectedYear,
                selectedMonth + 1,
                selectedDay
            )
            onDateSelected(formattedDate)

        },
        year,
        month,
        day
    ).show()
}
