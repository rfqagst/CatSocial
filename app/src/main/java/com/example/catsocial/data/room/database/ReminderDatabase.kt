package com.example.catsocial.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catsocial.data.room.dao.ReminderDao
import com.example.catsocial.data.room.entity.Cat
import com.example.catsocial.data.room.entity.Reminder

@Database(
    entities = [
        Reminder::class
    ],
    version = 1
)
abstract class ReminderDatabase : RoomDatabase() {
    abstract fun reminderDao() : ReminderDao
}