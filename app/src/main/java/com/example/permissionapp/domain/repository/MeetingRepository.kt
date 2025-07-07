package com.example.permissionapp.domain.repository


import com.example.permissionapp.domain.model.Meeting
import kotlinx.coroutines.flow.Flow

interface MeetingRepository {
    suspend fun getMeetings(): Flow<List<Meeting>>
    suspend fun addMeeting(meeting: Meeting)
}