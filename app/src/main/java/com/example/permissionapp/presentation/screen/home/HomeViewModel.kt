package com.example.permissionapp.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.permissionapp.domain.use_case.GetMeetingsUseCase
import com.example.permissionapp.domain.model.Meeting
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class HomeState(
    val meetings: List<Meeting> = emptyList(),
    val isLoading: Boolean = false
)

class HomeViewModel(
    private val getMeetingsUseCase: GetMeetingsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        loadMeetings()
    }

    private fun loadMeetings() {
        viewModelScope.launch {
            getMeetingsUseCase()
                .onStart { _state.value = _state.value.copy(isLoading = true) }
                .catch { /* Hata işle */ }
                .collect { meetings ->
                    _state.value = HomeState(meetings = meetings, isLoading = false)
                }
        }
    }
}