package com.example.permissionapp.Data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MeetingDao {
    @Insert
  suspend  fun insertMeeting(meeting: MeetingEntity)

    @Query("SELECT * FROM MeetingEntity")
    fun MeetingAll():Flow<List<MeetingEntity>>

  @Query("SELECT COUNT(*) FROM MeetingEntity order by id asc ")
  suspend fun RecordCount():Int
}