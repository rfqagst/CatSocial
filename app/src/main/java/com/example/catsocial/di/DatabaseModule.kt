package com.example.catsocial.di

import android.content.Context
import androidx.room.Room
import com.example.catsocial.data.room.dao.CatDao
import com.example.catsocial.data.room.dao.ReminderDao
import com.example.catsocial.data.room.database.AdoptionDatabase
import com.example.catsocial.data.room.database.ReminderDatabase
import com.example.catsocial.data.room.repository.AdoptionRepository
import com.example.catsocial.data.room.repository.ReminderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAdoptionDatabase(@ApplicationContext context: Context): AdoptionDatabase {
        return Room.databaseBuilder(context, AdoptionDatabase::class.java, "adoption_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideCatDao(database: AdoptionDatabase): CatDao = database.catDao()


    @Provides
    fun provideAdoptionRepository(catDao: CatDao): AdoptionRepository = AdoptionRepository(catDao)


    @Provides
    @Singleton
    fun provideReminderDatabase(@ApplicationContext context: Context): ReminderDatabase {
        return Room.databaseBuilder(context, ReminderDatabase::class.java, "reminder_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideReminderDao(database: ReminderDatabase): ReminderDao = database.reminderDao()


    @Provides
    fun provideReminderRepository(
        reminderDao: ReminderDao,
    ): ReminderRepository =
        ReminderRepository(reminderDao)
}

