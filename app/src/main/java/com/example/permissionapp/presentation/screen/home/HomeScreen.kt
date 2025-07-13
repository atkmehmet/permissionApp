package com.example.permissionapp.presentation.screen.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val state by viewModel.state.collectAsState()

    if (state.isLoading) {
        CircularProgressIndicator()
    } else {
        LazyColumn {
            items(state.meetings) { meeting ->
                Text("${meeting.dateMeeting} - ${meeting.meetingDuration}")
            }
        }
    }
}