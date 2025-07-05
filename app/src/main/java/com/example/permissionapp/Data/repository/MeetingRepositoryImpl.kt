package com.example.permissionapp.Data.repository

import com.example.permissionapp.data.local.dao.MeetingDao
import com.yourapp.meetingapp.data.local.entity.MeetingEntity
import com.yourapp.meetingapp.domain.model.Meeting
import com.yourapp.meetingapp.domain.repository.MeetingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
class MeetingRepositoryImpl(
    private val dao: MeetingDao
) : MeetingRepository {

    override fun getMeetings(): Flow<List<Meeting>> {
        return dao.getAllMeetings().map { list ->
            list.map { Meeting(it.id, it.title, it.date) }
        }
    }

    override suspend fun addMeeting(meeting: Meeting) {
        val entity = MeetingEntity(title = meeting.title, date = meeting.date)
        dao.insertMeeting(entity)
    }
}