package com.example.permissionapp
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun mainScreen() {
    val data = listOf(
        "Row 1 Col 1", "Row 1 Col 2", "Row 1 Col 3",
        "Row 2 Col 1", "Row 2 Col 2", "Row 2 Col 3"
    )
val openDialog = remember {
    mutableStateOf(false)
}
    LazyVerticalGrid(
        columns = GridCells.Fixed(3), // 3 columns
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center
    ) {
        items(data) { item ->
            Text(
                text = item,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { openDialog.value = !openDialog.value }

            )
        }

    }
    if (openDialog.value){

        ScreenDialog (onDismissRequest = {openDialog.value = false})
    }
}

// [START android_compose_components_fullscreendialog]
@Composable
fun ScreenDialog(onDismissRequest: () -> Unit) {
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

