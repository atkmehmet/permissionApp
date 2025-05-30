package com.example.permissionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                val viewModel = MeetingView(meetingDao)
                    mainScreen(viewModel,meetingDao)
                 //  DialogExamples()
                            //     myDatePicker(dao)

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