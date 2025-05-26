package com.example.permissionapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MeetingView(private val dao: MeetingDao):ViewModel() {
     private val _meeting = MutableStateFlow<List<Meeting>>(emptyList())
     val meeting: StateFlow<List<Meeting>> = _meeting

    fun loadMeeting() {
        viewModelScope.launch {
            _meeting.value = dao.MeetingAll()
        }
    }

}