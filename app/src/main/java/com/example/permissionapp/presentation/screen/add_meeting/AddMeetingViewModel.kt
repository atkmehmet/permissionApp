package com.example.permissionapp.presentation.screen.add_meeting


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.permissionapp.domain.model.Meeting
import com.example.permissionapp.domain.use_case.GetMeetingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddMeetingViewModel(
    private val addMeetingUseCase: GetMeetingsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AddMeetingState())
    val state: StateFlow<AddMeetingState> = _state

    fun onTitleChanged(title: String) {
        _state.value = _state.value.copy(title = title)
    }

    fun onDateChanged(date: String) {
        _state.value = _state.value.copy(date = date)
    }

    fun saveMeeting() {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(isSaving = true)
                val meeting = Meeting(id = 0, title = state.value.title, date = state.value.date)
                addMeetingUseCase(meeting)
                _state.value = _state.value.copy(isSaving = false, success = true)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isSaving = false, error = e.message)
            }
        }
    }
}
