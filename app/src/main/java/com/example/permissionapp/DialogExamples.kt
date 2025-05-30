package com.example.permissionapp



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties


@Composable
fun DialogExamples() {
    // [START_EXCLUDE]
    val openMinimalDialog = remember { mutableStateOf(false) }
    val openDialogWithImage = remember { mutableStateOf(false) }
    val openFullScreenDialog = remember { mutableStateOf(false) }
    // [END_EXCLUDE]
    val openAlertDialog = remember { mutableStateOf(false) }
    val openDialogComposable = remember { mutableStateOf(false) }
    // [START_EXCLUDE]
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Click the following button to toggle the given dialog example.")
        Button(
            onClick = { openMinimalDialog.value = !openMinimalDialog.value }
        ) {
            Text("Minimal dialog component")
        }
        Button(
            onClick = { openDialogWithImage.value = !openDialogWithImage.value }
        ) {
            Text("Dialog component with an image")
        }
        Button(
            onClick = { openAlertDialog.value = !openAlertDialog.value }
        ) {
            Text("Alert dialog component with buttons")
        }
        Button(
            onClick = { openFullScreenDialog.value = !openFullScreenDialog.value }
        ) {
            Text("Full screen dialog")
        }
        Button(onClick = { openDialogComposable.value = !openDialogComposable.value }) {
            Text("Dialog Composable")
        }

        // [END_EXCLUDE]
        when {
            // [START_EXCLUDE]
            openMinimalDialog.value -> {
                MinimalDialog(
                    onDismissRequest = { openMinimalDialog.value = false },
                )
            }
            openDialogWithImage.value -> {
                DialogWithImage(
                    onDismissRequest = { openDialogWithImage.value = false },
                    onConfirmation = {
                        openDialogWithImage.value = false
                        println("Confirmation registered") // Add logic here to handle confirmation.
                    },
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    imageDescription = stringResource(id = R.string.app_name),
                )
            }
            openFullScreenDialog.value -> {
                FullScreenDialog(
                    onDismissRequest = { openFullScreenDialog.value = false },
                )
            }
            // [END_EXCLUDE]
            openAlertDialog.value -> {
                AlertDialogExample(
                    onDismissRequest = { openAlertDialog.value = false },
                    onConfirmation = {
                        openAlertDialog.value = false
                        println("Confirmation registered") // Add logic here to handle confirmation.
                    },
                    dialogTitle = "Alert dialog example",
                    dialogText = "This is an example of an alert dialog with buttons.",
                    icon = Icons.Default.Info
                )
            }
            openDialogComposable.value ->{
                DialogComposable(
                    onDismissRequest = { openDialogComposable.value = false },
                    onConfirmation = { openDialogComposable.value = false },
                    dialogTitle = "My Dialog Function",
                    dialogText = "Pleas add composable"
                ) {
                    addEdt(value = "", onValueChange = {})
                }
            }
        }
    }
}
// [END android_compose_components_dialogparent]

// [START android_compose_components_minimaldialog]
@Composable
fun MinimalDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = "This is a minimal dialog",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
            )
        }
    }
}
@Composable
fun DialogWithImage(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    painter: Painter,
    imageDescription: String,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painter,
                    contentDescription = imageDescription,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(160.dp)
                )
                Text(
                    text = "This is a dialog with buttons and an image.",
                    modifier = Modifier.padding(16.dp),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Dismiss")
                    }
                    TextButton(
                        onClick = { onConfirmation() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}

@Composable
fun DialogComposable(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    design:@Composable () ->Unit
    ){
    Dialog(onDismissRequest = { onDismissRequest() },) {
   Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally) {
       Text(
           text = dialogTitle,
           textAlign = TextAlign.Center,
       )

       Text(
           text = dialogText,

       )
       design()

       Button(onClick = { onConfirmation() }, modifier = Modifier.align(alignment = Alignment.End)) {
           Text(
               text = "Confirm",
               textAlign = TextAlign.Center,
           )
       }
   }
    }

}
// [END android_compose_components_dialogwithimage]

// [START android_compose_components_alertdialog]
@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}
// [END android_compose_components_alertdialog]

// [START android_compose_components_fullscreendialog]
@Composable
fun FullScreenDialog(onDismissRequest: () -> Unit) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = true,
        ),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "This is a full screen dialog",
                    textAlign = TextAlign.Center,
                )
                TextButton(onClick = { onDismissRequest() }) {
                    Text("Dismiss")
                }
                addEdt(value = "EXX", onValueChange ={} )
                addEdt(value = "EXX2", onValueChange = {})
            }
        }
    }
}
// [END android_compose_components_fullscreendialog]