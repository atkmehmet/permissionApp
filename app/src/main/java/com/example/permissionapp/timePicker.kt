package com.example.permissionapp

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import java.text.SimpleDateFormat
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


    Box(propagateMinConstraints = false) {
     
        Button(onClick = { showTimerPicker = true }) {
            Text(text = "Set Timer")
        }
        SnackbarHost(snackbarState)
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

  }
    
}