package com.example.permissionapp
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun mainScreen(dao: MeetingDao){

    PersonGridWithDialog(dao)
}

data class Person(val id: Int, val name: String)
@Composable
fun PersonGridWithDialog(dao: MeetingDao) {
    var selectedPerson by remember { mutableStateOf<List<Meeting?>>(null) }

    CoroutineScope(Dispatchers.IO).launch {
        selectedPerson= dao.MeetingAll()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize().padding(8.dp)
        ) {
            items(people) { person ->
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable { selectedPerson = person }
                ) {
                    Text(text = "Name: ${person.name}")
                    Text(text = "ID: ${person.id}")
                }
            }
        }

        // Show dialog if a person is selected
        selectedPerson?.let { person ->
            ScreenDialog(
                person = person,
                onDismissRequest = { selectedPerson = null }
            )
        }

    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenDialog(onDismissRequest: () -> Unit, person: Person) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false, dismissOnBackPress = true)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = person.name, textAlign = TextAlign.Center)
                TextButton(onClick = onDismissRequest) {
                    Text("Dismiss")
                }
                addEdt(value = person.id.toString(), onValueChange = {})
                addEdt(value = "EXX2", onValueChange = {})
            }
        }
    }
}
