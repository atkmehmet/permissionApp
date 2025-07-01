package com.example.permissionapp.domain.repository

import com.example.permissionapp.Data.local.Meeting
import kotlinx.coroutines.flow.Flow

interface MeetingRepository {
    fun getAllMeetings(): Flow<List<Meeting>>
    suspend fun addMeeting(meeting: Meeting)
}