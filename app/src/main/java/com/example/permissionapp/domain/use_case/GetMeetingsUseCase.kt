package com.example.permissionapp.domain.use_case


import com.example.permissionapp.domain.model.Meeting
import com.example.permissionapp.domain.repository.MeetingRepository
import kotlinx.coroutines.flow.Flow

class GetMeetingsUseCase(private val repository: MeetingRepository) {
    suspend operator fun invoke (): Flow<List<Meeting>> = repository.getMeetings()
    suspend operator  fun invoke addMeeting() =  repository.
}
class AddMeetingUseCase(private val meeting: Meeting)
{

}