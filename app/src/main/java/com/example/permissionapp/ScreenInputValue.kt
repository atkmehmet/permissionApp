package com.example.permissionapp


import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.coroutines.coroutineContext
import kotlin.time.times

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun MyDatePicker(view: MeetingView) {
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val state = view.uiState
    val meetings by view.meeting.collectAsState()

    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var selectedTime by remember { mutableStateOf<TimePickerState?>(null) }

    val formattedDate by remember(selectedDate) {
        derivedStateOf {
            selectedDate?.let {
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(it))
            } ?: ""
        }
    }

    val formattedTime by remember(selectedTime) {
        derivedStateOf {
            selectedTime?.let {
                val cal = Calendar.getInstance()
                cal.set(Calendar.HOUR_OF_DAY, it.hour)
                cal.set(Calendar.MINUTE, it.minute)
                SimpleDateFormat("HH:mm", Locale.getDefault()).format(cal.time)
            } ?: "No time selected"
        }
    }

    val isSaveEnabled = formattedDate.isNotEmpty() &&
            formattedTime != "No time selected" &&
            state.driverName.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(meetings) { meeting ->
                Text(text = meeting.name)
            }
        }

        Text(text = state.recordCount.toString())

        OutlinedTextField(
            value = formattedDate,
            onValueChange = {},
            label = { Text("Lesson Date") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(Icons.Default.DateRange, contentDescription = "Select date")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        )

        OutlinedTextField(
            value = formattedTime,
            onValueChange = {},
            label = { Text("Lesson Starting Time") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showTimePicker = true }) {
                    Icon(Icons.Default.DateRange, contentDescription = "Select time")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        )

        addEdt(
            value = state.driverName,
            onValueChange = { view.onNameChange(it) },
            label = "Customer Name",
            placeholder = "Write Customer Name"
        )

        addEdt(
            value = state.driverSurName,
            onValueChange = { view.onSurnameChange(it) },
            label = "Customer Surname",
            placeholder = "Write Customer Surname"
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            addEdt(
                value = state.driverHour,
                onValueChange = { view.onHourChange(it) },
                label = "Hour",
                placeholder = "Lesson Duration",
                numericOnly = true,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            addEdt(
                value = state.hourPrice,
                onValueChange = { view.onPriceChange(it) },
                label = "Price per Hour",
                placeholder = "Lesson Price",
                numericOnly = true,
                modifier = Modifier.weight(1f)
            )
        }

        addEdt(
            value = state.totalPrice,
            onValueChange = { },
            label = "Total Price",
            placeholder = "",
            numericOnly = true
        )

        Button(
            onClick = {
                view.meetingInsert(formattedDate, formattedTime)
            },
            modifier = Modifier.align(Alignment.End),
            enabled = isSaveEnabled
        ) {
            Text("Save Meeting")
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        selectedDate = rememberDatePickerState().selectedDateMillis
                        showDatePicker = false
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = Color.White)
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDatePicker = false },
                    colors = ButtonDefaults.textButtonColors(contentColor = Color.White)
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = rememberDatePickerState())
        }
    }

    if (showTimePicker) {
        InputUseState(
            onConfirm = {
                selectedTime = it
                showTimePicker = false
            },
            onDismiss = { showTimePicker = false }
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputUseState(
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit,
) {
    val currentTime = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimeInput(state = timePickerState)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { onConfirm(timePickerState) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
            ) {
                Icon(Icons.Default.Check, contentDescription = "Confirm")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Icon(Icons.Default.Close, contentDescription = "Close")
            }
        }
    }
}
