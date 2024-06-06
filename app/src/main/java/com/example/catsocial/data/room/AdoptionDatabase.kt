package com.example.catsocial.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catsocial.data.room.dao.CatDao
import com.example.catsocial.data.room.entity.Cat

@Database(
    entities = [
        Cat::class
    ],
    version = 1
)
abstract class AdoptionDatabase : RoomDatabase() {
    abstract fun catDao(): CatDao


}