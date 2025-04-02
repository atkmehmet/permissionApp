package com.example.permissionapp

import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun xScreen(){
    val context = LocalContext.current

    val permissions = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.INTERNET,
        android.Manifest.permission.ACCESS_WIFI_STATE
    )

val permissionGranted by remember {
    mutableStateOf(permissions.all { isPermissionGranted(context,it) })
}

}




fun isPermissionGranted(context: Context,permission:String):Boolean{
    return ContextCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_GRANTED
}
