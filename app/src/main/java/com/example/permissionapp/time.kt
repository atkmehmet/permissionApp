package com.example.permissionapp
import android.app.TimePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.util.Locale

@Composable
fun TimePickerTextField(
    label: String = "Choose time",
    timeFormat: String = "HH:mm"
) {
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }

    var selectedTime by remember { mutableStateOf("") }

    val timePickerDialog = remember {
        TimePickerDialog(
            context,
            { _, hour, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                val formatter = SimpleDateFormat(timeFormat, Locale.getDefault())
                selectedTime = formatter.format(calendar.time)
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true // is24HourView
        )
    }

    OutlinedTextField(
        value = selectedTime,
        onValueChange = {}, // read-only
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { timePickerDialog.show() },
        readOnly = true,
        trailingIcon = {
            Icon(Icons.Default.DateRange, contentDescription = "Select time")
        }
    )
}
