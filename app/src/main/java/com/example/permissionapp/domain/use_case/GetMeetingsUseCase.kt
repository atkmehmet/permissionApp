package com.example.permissionapp.domain.use_case


import com.example.permissionapp.Data.local.Meeting
import com.example.permissionapp.domain.repository.MeetingRepository
import kotlinx.coroutines.flow.Flow

class GetMeetingsUseCase(private val repository: MeetingRepository) {
    operator fun invoke (): Flow<List<Meeting>> = repository.getAllMeetings()
}