package com.example.permissionapp

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup

import java.util.*


import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
@Composable
fun addEdt(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Veri",
    placeholder: String = "Bir şeyler yazın...",
    helperText: String = "",
    maxChar: Int = 50,
    numericOnly: Boolean = false,
    fieldWidth: Dp = Dp.Unspecified, // 👈 New parameter
    modifier: Modifier = Modifier
) {
    val isError = value.length > maxChar
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val fieldModifier = if (fieldWidth != Dp.Unspecified) {
        Modifier.width(fieldWidth)
    } else {
        Modifier.fillMaxWidth()
    }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                var newValue = it
                if (numericOnly) {
                    newValue = newValue.filter { char -> char.isDigit() }
                }
                if (newValue.length <= maxChar) {
                    onValueChange(newValue)
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
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = if (numericOnly) KeyboardType.Number else KeyboardType.Text
            ),
            modifier = fieldModifier
        )
        if (value.isEmpty()){
            Text(
                text = "Field cannot be empty",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = if (isError) "Maksimum $maxChar karakter izin veriliyor" else helperText,
            color = if (isError) Color.Red else Color.Gray,
            fontSize = 12.sp
        )

    }
}

@Composable
fun dateTimePicker(){
    var showModal by remember {
        mutableStateOf(false)
    }

}