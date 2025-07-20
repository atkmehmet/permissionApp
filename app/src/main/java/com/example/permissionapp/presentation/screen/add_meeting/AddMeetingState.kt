package com.example.permissionapp.presentation.screen.add_meeting

data class AddMeetingState(
    val title: String = "",
    val date: String = "",
    val isSaving: Boolean = false,
    val success: Boolean = false,
    val error: String? = null
)