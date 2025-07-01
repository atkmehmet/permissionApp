package com.example.permissionapp.Data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Meeting::class], version = 1)
abstract class MeetingDatabase:RoomDatabase() {

    abstract fun meetingDao(): MeetingDao

    companion object {
        @Volatile private var INSTANCE: MeetingDatabase? = null

        fun getDatabase(context: Context): MeetingDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MeetingDatabase::class.java,
                    "user_db"
                ).build().also { INSTANCE = it }
            }
        }
    }

}