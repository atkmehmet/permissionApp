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
fun myDatePicker(dao: MeetingDao,view: MeetingView){
    var showModal by remember { mutableStateOf(false) }
    var showTimeInput by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val context = LocalContext.current

     val state = view._uistate

    var selectedTime :TimePickerState? by remember { mutableStateOf(null) }
    val formatter = remember { SimpleDateFormat("hh:mm a", Locale.getDefault()) }
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var formattedDate =""
    var timeFormet = ""
    if (selectedDate != null) {
        val date = Date(selectedDate!!)
        formattedDate = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(date)
    }

    if (selectedTime != null) {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, selectedTime!!.hour)
        cal.set(Calendar.MINUTE, selectedTime!!.minute)
        cal.isLenient = false
        timeFormet=  formatter.format(cal.time)
    } else {
        timeFormet = "No time selected."
    }


    Column (modifier = Modifier
        .fillMaxSize()
        .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        OutlinedTextField(value = formattedDate ,
            onValueChange = {}
            , label = { Text(text = "Lesson Date")}
            , readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showModal = !showModal }) {
                    Icon(imageVector =Icons.Default.DateRange , contentDescription = "Select date")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)

        )

        OutlinedTextField(value = timeFormet ,
            onValueChange = {}
            , label = { Text(text = "Lesson Starting Time")}
            , readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showTimeInput = !showTimeInput }) {
                    Icon(imageVector =Icons.Default.DateRange , contentDescription = "Select Time")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        )
        addEdt(value = state.driverName, onValueChange ={ view.onNameChange(it)}, label = "Customer Name", placeholder = "Write Customer Name")
        addEdt(value = state.driverSurName, onValueChange = { view.onSurnameChange(it)}, label = "Customer SurName", placeholder = "Write Customer SurName")
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            addEdt(value = state.driverHour, onValueChange = {  view.onHourChange(it)},
                label = "Lesson of Hour", placeholder = "Write Hour", numericOnly = true, modifier = Modifier.weight(1f))
            addEdt(value = state.hourPrice, onValueChange = {  view.onPriceChange(it)},
                label = "Price of Hour", placeholder = "Lesson of Price", numericOnly = true, modifier = Modifier.weight(1f))

        }

        addEdt(value = state.totalPrice, onValueChange = { },
            label = "Total of Price", placeholder = "", numericOnly = true)

        Button(onClick = {

        }, modifier = Modifier.align(Alignment.End)) {
            Text(text = "Save Meeting")
        }

    }
    if (showModal){

        datePickerShow(
            onDateSelected = {
                selectedDate = it
                showModal = false
            },
            onDismiss = { showModal = false }
        )
    }
    if (showTimeInput){

        InputUseState(onConfirm = {time->
            selectedTime = time
            showTimeInput = false
        }) {
            showTimeInput = false
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
// [START android_compose_components_input_usestate]
@Composable
fun InputUseState(
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit,
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,) {
        TimeInput(
            state = timePickerState,
        )
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center){

            Button(
                onClick = { onConfirm(timePickerState) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green,       // Button background
                    contentColor = Color.White           // Text/icon color
                )
            ) {
               Icon(imageVector = Icons.Default.Check, contentDescription = "Confirm" )
            }
            Spacer(modifier = Modifier.width(10.dp))

            Button(onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                )) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
            }
        }
    }
}
// [END android_compose_components_input_usestate]




















@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun datePickerShow(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }, colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
                contentColor   = Color.White
            )) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss, colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor   = Color.White
            )) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

fun convertMillisToTime(millis: Long): String {
    val formatter = SimpleDateFormat("dd/mm/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun datePickerRange(){

    var selectedDateRange by remember { mutableStateOf<Pair<Long?, Long?>>(null to null) }

    var modalModalRange by remember {
        mutableStateOf(false)
    }
    var dateRange = ""

    if (selectedDateRange.first != null && selectedDateRange.second != null) {
        val startDate = Date(selectedDateRange.first!!)
        val endDate = Date(selectedDateRange.second!!)
        val formattedStartDate = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(startDate)
        val formattedEndDate = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(endDate)
        dateRange = "$formattedStartDate-$formattedEndDate"
    }

    Box (modifier = Modifier.fillMaxWidth()){

        OutlinedTextField(value = dateRange,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = "Please Choose Date Range")},
            trailingIcon = {
                IconButton(onClick = { modalModalRange = !modalModalRange }) {
                    Icon(imageVector =Icons.Default.DateRange , contentDescription = "Select date Range")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        )
    }

    if (modalModalRange) {
        DateRangePickerModal(
            onDateRangeSelected = {
                selectedDateRange = it
                modalModalRange = false
            },
            onDismiss = { modalModalRange = false }
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
// [START android_compose_components_datepicker_range]
@Composable
fun DateRangePicker(
    onDateRangeSelected: (Pair<Long?, Long?>) -> Unit,
    onDismiss: () -> Unit
) {
    val dateRangePickerState = rememberDateRangePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onDateRangeSelected(
                        Pair(
                            dateRangePickerState.selectedStartDateMillis,
                            dateRangePickerState.selectedEndDateMillis
                        )
                    )
                    onDismiss()
                }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DateRangePicker(
            state = dateRangePickerState,
            title = {
                Text(
                    text = "Select date range"
                )
            },
            showModeToggle = false,
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(16.dp)
        )
    }
}
// [END android_compose_components_datepicker_range]
