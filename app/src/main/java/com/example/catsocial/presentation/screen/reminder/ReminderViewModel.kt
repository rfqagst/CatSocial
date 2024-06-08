package com.example.catsocial.presentation.screen.reminder

import android.app.AlarmManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catsocial.data.room.entity.Reminder
import com.example.catsocial.data.room.repository.ReminderRepository
import com.example.catsocial.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor(
    private val reminderRepository: ReminderRepository,
    private val notificationManager: NotificationManagerCompat,
    private val notificationBuilder: NotificationCompat.Builder,
    private val alarmManager: AlarmManager
) : ViewModel() {

    private val _allReminders = MutableStateFlow<Resource<List<Reminder>>>(Resource.Loading())
    val allReminders: StateFlow<Resource<List<Reminder>>> = _allReminders

    private val _insertReminders = MutableStateFlow<Resource<Unit>>(Resource.Loading())
    val insertReminders: StateFlow<Resource<Unit>> = _insertReminders

    private val _updateReminders = MutableStateFlow<Resource<Unit>>(Resource.Loading())
    val updateReminders: StateFlow<Resource<Unit>> = _updateReminders

    private val _deleteReminders = MutableStateFlow<Resource<Unit>>(Resource.Loading())
    val deleteReminders: StateFlow<Resource<Unit>> = _deleteReminders

    fun getReminders() {
        viewModelScope.launch {
            reminderRepository.getReminders().collect { resource ->
                _allReminders.value = resource
            }
        }
    }

    fun insertReminder(reminder: Reminder) {
        viewModelScope.launch {
            reminderRepository.insertReminder(reminder).collect { resource ->
                _insertReminders.value = resource
            }
        }
    }

    fun updateReminder(reminder: Reminder) {
        viewModelScope.launch {
            reminderRepository.updateReminder(reminder).collect { resource ->
                _updateReminders.value = resource
            }
        }
    }

    fun deleteReminder(reminder: Reminder) {
        viewModelScope.launch {
            reminderRepository.deleteReminder(reminder).collect { resource ->
                _deleteReminders.value = resource
            }
        }
    }


    fun showNotification() {
        notificationManager.notify(1, notificationBuilder.build())
    }

}
