package com.example.permissionapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun timePickerSample() {
    var showTimerPicker by remember {
        mutableStateOf(false)
    }
    var state = rememberTimePickerState()
    val formatter = remember {
        SimpleDateFormat("hh:mm a", Locale.getDefault())
    }
    val snackbarState = remember {
        SnackbarHostState()
    }
    val snackScope = rememberCoroutineScope()
    val cal :  Calender()

    Box(propagateMinConstraints = false) {
     
        Button(onClick = { showTimerPicker = true }) {
            Text(text = "Set Timer")
        }
        SnackbarHost(snackbarState)
    }
    if (showTimerPicker){
        TimePickerDialog(onCancel = { showTimerPicker = false },
            onConfirm = {
                cal.set(Calendar.HOUR_OF_DAY,state.hour)
            },
            toggle = { /*TODO*/ }) {
            
        }
    }
}


@Composable
fun TimePickerDialog(
    title:String = "Select Time",
    onCancel:()-> Unit,
    onConfirm:()-> Unit,
    toggle: @Composable () -> Unit,
    content:@Composable () -> Unit
){
  Dialog(onDismissRequest = onCancel,
       properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
          Surface(
              MaterialTheme.shapes.extraLarge,
              tonalElevation = 6.dp,
              modifier = Modifier
                  .width(IntrinsicSize.Min)
                  .height(IntrinsicSize.Min)
                  .background(
                      shape = MaterialTheme.shapes.extraLarge,
                      color = MaterialTheme.colorScheme.surface
                  ),


          ) {

          }
  }
    
}