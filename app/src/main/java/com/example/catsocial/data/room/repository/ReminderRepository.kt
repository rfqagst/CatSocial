package com.example.catsocial.data.room.repository

import com.example.catsocial.data.room.dao.ReminderDao
import com.example.catsocial.data.room.entity.Reminder
import com.example.catsocial.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReminderRepository @Inject constructor(
    private val reminderDao: ReminderDao,

    ) {

    suspend fun insertReminder(reminder: Reminder): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            val resource = reminderDao.insertReminder(reminder)
            emit(Resource.Success(resource))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))

        }
    }

    suspend fun deleteReminder(reminder: Reminder): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            val resource = reminderDao.deleteReminder(reminder)
            emit(Resource.Success(resource))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))

        }
    }

    suspend fun updateReminder(reminder: Reminder): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            val resource = reminderDao.updateReminder(reminder)
            emit(Resource.Success(resource))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))

        }
    }

    suspend fun getReminders(): Flow<Resource<List<Reminder>>> = flow {
        emit(Resource.Loading())
        try {
            reminderDao.getAllReminders().collect { resource ->
                emit(Resource.Success(resource))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))

        }


    }


    suspend fun getRemindersById(reminderId: Int): Flow<Resource<Reminder>> = flow {
        emit(Resource.Loading())
        try {
            val reminder = reminderDao.getReminderById(reminderId)
            emit(Resource.Success(reminder!!))

        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))

        }


    }
}