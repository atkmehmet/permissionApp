package com.example.permissionapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavGraph(viewModel: MeetingView,dao: MeetingDao) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "meeting_list") {
        composable("meeting_list") {
            MeetingListScreen(
                meetings = viewModel.meeting.collectAsState().value,
                onAddMeetingClick = {
                    navController.navigate("add_meeting")
                }
            )
        }

        composable("add_meeting") {
            MyDatePicker(viewModel,
                onMeetingAd = {
                    navController.popBackStack()
                })
        }
    }
}
