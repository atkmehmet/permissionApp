package com.example.permissionapp.Data.repository

import com.example.permissionapp.Data.local.MeetingDao
import com.example.permissionapp.Data.local.MeetingEntity
import com.example.permissionapp.domain.model.Meeting
import com.example.permissionapp.domain.repository.MeetingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
class MeetingRepositoryImpl(
    private val dao: MeetingDao
) : MeetingRepository {

 override   suspend fun getMeetings(): Flow<List<Meeting>> {
        return dao.MeetingAll().map { list ->
            list.map { Meeting(it.name, it.surname, it.dateMeeting,it.meetingStartTime,it.meetingDuration,it.hourPrice) }
        }
    }

    override suspend fun addMeeting(meeting: Meeting) {
        val entity = MeetingEntity(name = meeting.name, meetingStartTime = meeting.meetingStartTime, meetingDuration = meeting.meetingDuration, dateMeeting = meeting.dateMeeting, hourPrice = meeting.hourPrice, surname = meeting.surname)
        dao.insertMeeting(entity)
    }
}