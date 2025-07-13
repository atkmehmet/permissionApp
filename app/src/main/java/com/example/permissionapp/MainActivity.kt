package com.example.permissionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.permissionapp.Data.local.MeetingDatabase
import com.example.permissionapp.Data.repository.MeetingRepositoryImpl
import com.example.permissionapp.domain.use_case.GetMeetingsUseCase
import com.example.permissionapp.presentation.screen.home.HomeScreen
import com.example.permissionapp.presentation.screen.home.HomeViewModel
import com.example.permissionapp.ui.theme.PermissionAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val db = MeetingDatabase.getDatabase(this)
            val dao =db.meetingDao()
            PermissionAppTheme {
              // PermissionScreen()
              //  DatePickerExamples()
                //myDatePicker()
                // TimePickerExamples()
                //screen()
                // MenusExamples()

                val db = MeetingDatabase.getDatabase(this)
                val meetingDao = db.meetingDao()
                // Repository & UseCase
                val repository = MeetingRepositoryImpl(meetingDao)
                val getMeetingsUseCase = GetMeetingsUseCase(repository)

                // ViewModel
                val viewModel = HomeViewModel(getMeetingsUseCase)

                setContent {
                    HomeScreen(viewModel = viewModel)
                }
             //   val viewModel = MeetingView(meetingDao)
               // AppNavGraph(viewModel = viewModel, dao = meetingDao )
                        //  mainScreen(viewModel,meetingDao)
                 //  DialogExamples()
              //  val meetings by viewModel.meeting.collectAsState()
                //myDatePicker(dao,viewModel)
               // MeetingListScreen(meetings = meetings) {
                 //   myDatePicker(dao,viewModel)
                //}

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PermissionAppTheme {
        Greeting("Android")
    }
}