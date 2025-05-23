package com.example.permissionapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MeetingDao {
    @Insert
    fun insertMeeting(meeting: Meeting)

    @Query("SELECT * FROM Meeting")
    fun MeetingAll():List<Meeting>
}