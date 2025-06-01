package com.example.permissionapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MeetingView(private val dao: MeetingDao):ViewModel() {

     var _uistate by mutableStateOf(UIState())
         private set

     private val _meeting = MutableStateFlow<List<Meeting>>(emptyList())
     val meeting: StateFlow<List<Meeting>> = _meeting



    fun loadMeeting() {
        viewModelScope.launch {
            _meeting.value = dao.MeetingAll()
        }
    }

    fun onNameChange (newName:String){
        _uistate = _uistate.copy(driverName = newName)
    }

    fun onSurnameChange(newSurname:String){
        _uistate = _uistate.copy(driverSurName = newSurname)
    }

    fun onTimeChange(newTime:String){
        _uistate = _uistate.copy(

        )
    }

}