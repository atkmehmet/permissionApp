package com.example.permissionapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MeetingDao {
    @Insert
  suspend  fun insertMeeting(meeting: Meeting)

    @Query("SELECT * FROM Meeting")
  suspend  fun MeetingAll():List<Meeting>
}