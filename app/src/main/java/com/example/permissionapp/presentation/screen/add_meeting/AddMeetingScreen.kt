package com.example.permissionapp.presentation.screen.add_meeting

@Composable
fun AddMeetingScreen(viewModel: AddMeetingViewModel, onMeetingSaved: () -> Unit) {
    val state by viewModel.state.collectAsState()

    if (state.success) {
        onMeetingSaved()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = state.title,
            onValueChange = viewModel::onTitleChanged,
            label = { Text("Başlık") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = state.date,
            onValueChange = viewModel::onDateChanged,
            label = { Text("Tarih") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.saveMeeting() },
            enabled = !state.isSaving,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Kaydet")
        }

        if (state.isSaving) {
            CircularProgressIndicator()
        }

        state.error?.let {
            Text("Hata: $it", color = Color.Red)
        }
    }
}
