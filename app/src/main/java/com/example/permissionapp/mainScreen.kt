package com.example.permissionapp
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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.permissionapp.Data.Meeting
import com.example.permissionapp.Data.MeetingDao

@Composable
fun mainScreen(view: MeetingView,meetingDao: MeetingDao){

    PersonGridWithDialog(view.meeting.collectAsState().value,meetingDao)
}

data class Person(val id: Int, val name: String)
@Composable
fun PersonGridWithDialog(meetings:List<Meeting>, meetingDao: MeetingDao) {
    var selectedMeeting by remember { mutableStateOf<Meeting?>(null) }


    Column {
        
    Button(onClick = { }) {
        Text(text = "Add Meeting")
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {

            items(meetings) { meeting ->
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable { selectedMeeting = meeting }
                ) {
                    Text(text = "Name: ${meeting.name}")
                    Text(text = "ID: ${meeting.id}")
                }
            }
        }
    }

        // Show dialog if a person is selected
        selectedMeeting?.let { meeting ->
            ScreenDialog(
                meeting = meeting,
                onDismissRequest = { selectedMeeting = null }
            )
        }

    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenDialog(onDismissRequest: () -> Unit, meeting: Meeting) {
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
                Text(text = meeting.name, textAlign = TextAlign.Center)
                TextButton(onClick = onDismissRequest) {
                    Text("Dismiss")
                }
                addEdt(value = meeting.id.toString(), onValueChange = {})
                addEdt(value = "EXX2", onValueChange = {})
            }
        }
    }
}
