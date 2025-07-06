package com.example.permissionapp.Data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Meeting")
data class MeetingEntity (

    @PrimaryKey(autoGenerate = true) val id:Int = 0,
     val name:String,
     val surname:String,
     val dateMeeting: String,
     val meetingStartTime:String,
     val meetingDuration:String,
     val hourPrice:String
)