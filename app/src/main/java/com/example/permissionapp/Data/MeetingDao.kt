package com.example.permissionapp.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MeetingDao {
    @Insert
  suspend  fun insertMeeting(meeting: Meeting)

    @Query("SELECT * FROM Meeting")
  suspend  fun MeetingAll():List<Meeting>

  @Query("SELECT COUNT(*) FROM Meeting order by id asc ")
  suspend fun RecordCount():Int
}