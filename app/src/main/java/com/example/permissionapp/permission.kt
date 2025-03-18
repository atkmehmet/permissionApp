package com.example.permissionapp

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

@Composable
fun PermissionScreen() {
    val context = LocalContext.current

    // Define the required permissions
    val permissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_WIFI_STATE
    )

    // Track permission status
    var permissionsGranted by remember {
        mutableStateOf(permissions.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        })
    }

    // Track if the permission request was denied
    var showRationaleDialog by remember { mutableStateOf(false) }

    // Request multiple permissions at once
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        permissionsGranted = result.all { it.value }
        if (!permissionsGranted) {
            showRationaleDialog = true // Show dialog if denied
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        if (permissionsGranted) {
            Text("✅ All permissions granted!")
        } else {
            Button(onClick = { launcher.launch(permissions) }) {
                Text("Request Permissions")
            }
        }
    }

    // Show a dialog if permissions are denied
    if (showRationaleDialog) {
        PermissionRationaleDialog(
            onDismiss = { showRationaleDialog = false },
            onGoToSettings = { openAppSettings(context) }
        )
    }
}

// Dialog explaining why permissions are needed
@Composable
fun PermissionRationaleDialog(onDismiss: () -> Unit, onGoToSettings: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Permissions Required") },
        text = { Text("This app needs camera, storage, and location permissions to function correctly. Please grant them in settings.") },
        confirmButton = {
            TextButton(onClick = onGoToSettings) {
                Text("Go to Settings")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

// Function to open App Settings for manual permission enablement
fun openAppSettings(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", context.packageName, null)
    }
    context.startActivity(intent)
}
