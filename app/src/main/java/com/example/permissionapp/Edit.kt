package com.example.permissionapp

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext



import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun addEdt(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Veri",
    placeholder: String = "Bir şeyler yazın...",
    helperText: String = "",
    maxChar: Int = 50,
    modifier: Modifier = Modifier
) {
    val isError = value.length > maxChar
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                if (it.length <= maxChar) {
                    onValueChange(it)
                }
            },
            label = { Text(label) },
            placeholder = { Text(placeholder) },
            leadingIcon = { Icon(Icons.Default.Edit, contentDescription = null) },
            trailingIcon = {
                if (isFocused && value.isNotEmpty()) {

                    IconButton(onClick = { onValueChange("") }) {
                        Icon(Icons.Default.Close, contentDescription = "Temizle")
                    }
                }
            },
            isError = isError,
            interactionSource = interactionSource,
            singleLine = true,

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = if (isError) "Maksimum $maxChar karaktere izin veriliyor" else helperText,
            color = if (isError) Color.Red else Color.Gray,
            fontSize = 12.sp
        )
    }
}
@Composable
fun DatePickerTextField(
    label: String = "Choose date",
    dateFormat: String = "yyyy-MM-dd"
) {
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }

    // State to hold the selected date as a string
    var selectedDate by remember { mutableStateOf("") }

    // Date Picker Dialog
    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
                selectedDate = formatter.format(calendar.time)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    // OutlinedTextField that shows date picker on click
    OutlinedTextField(
        value = selectedDate,
        onValueChange = {}, // Disabled manual input
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { datePickerDialog.show() },
        readOnly = true,
        trailingIcon = {
            Icon(Icons.Default.DateRange, contentDescription = "Select date")
        }
    )
}
