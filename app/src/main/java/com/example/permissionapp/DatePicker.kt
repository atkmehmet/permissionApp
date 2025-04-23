package com.example.permissionapp

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun myDatePicker(){
    var showModal by remember {

    mutableStateOf(false)

    }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToTime(it)
    }?:""

    Box (modifier = Modifier.fillMaxWidth()){
        OutlinedTextField(value = selectedDate,
            onValueChange = {}
        , label = { Text(text = "DOB")}
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

    }

    if (showModal){

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun datePickerShow(
    onSelectedDate:(Long?)->Unit,
    onDissmis:()->Unit
){
  val datePickerSelectd = rememberDatePickerState()
    
    DatePickerDialog(
        on
    )
}

fun convertMillisToTime(millis: Long): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}