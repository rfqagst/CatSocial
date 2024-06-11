package com.example.catsocial.presentation.screen.reminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catsocial.data.room.entity.Reminder
import com.example.catsocial.data.room.repository.ReminderRepository
import com.example.catsocial.presentation.receiver.AlarmReceiver
import com.example.catsocial.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor(
    private val reminderRepository: ReminderRepository,
    private val alarmManager: AlarmManager,
    @ApplicationContext private val context: Context

) : ViewModel() {

    private val _allReminders = MutableStateFlow<Resource<List<Reminder>>>(Resource.Loading())
    val allReminders: StateFlow<Resource<List<Reminder>>> = _allReminders

    private val _insertReminders = MutableStateFlow<Resource<Unit>>(Resource.Loading())
    val insertReminders: StateFlow<Resource<Unit>> = _insertReminders

    private val _updateReminders = MutableStateFlow<Resource<Unit>>(Resource.Loading())
    val updateReminders: StateFlow<Resource<Unit>> = _updateReminders

    private val _deleteReminders = MutableStateFlow<Resource<Unit>>(Resource.Loading())
    val deleteReminders: StateFlow<Resource<Unit>> = _deleteReminders


    private val countdownTimers = mutableMapOf<Int, CountDownTimer>()

    private val _remainingTimeMap = MutableStateFlow<Map<Int, Long>>(emptyMap())
    val remainingTimeMap: StateFlow<Map<Int, Long>> = _remainingTimeMap

    init {
        getReminders()
    }

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


//
//    fun requestExactAlarmPermission() {
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S && !alarmManager.canScheduleExactAlarms()) {
//            val intent = Intent(android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
//            context.startActivity(intent)
//        }
//    }


    fun startCountdown(reminderId: Int, millisInFuture: Long, reminderName : String) {
        if (millisInFuture > 0) {
            _remainingTimeMap.value = _remainingTimeMap.value.toMutableMap().apply {
                this[reminderId] = millisInFuture
            }
            countdownTimers[reminderId]?.cancel()
            countdownTimers[reminderId] = object : CountDownTimer(millisInFuture, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    _remainingTimeMap.value = _remainingTimeMap.value.toMutableMap().apply {
                        this[reminderId] = millisUntilFinished
                    }
                }

                override fun onFinish() {
                    _remainingTimeMap.value = _remainingTimeMap.value.toMutableMap().apply {
                        this[reminderId] = 0L
                    }
                    setAlarm(reminderId, millisInFuture ,reminderName)
                }
            }.start()
        } else {
            Log.d("ReminderViewModel", "The countdown time for reminder $reminderId is in the past.")
        }
    }



//    private fun setAlarm(reminderId : Int, millisInFuture: Long, reminderName : String) {
//        if (canScheduleExactAlarms()) {
//            val intent = Intent(context, AlarmReceiver::class.java).apply {
//                putExtra("reminderName", reminderName)
//            }
//            val pendingIntent = PendingIntent.getBroadcast(
//                context,
//                reminderId,
//                intent,
//                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
//            )
//
//            alarmManager.setExact(
//                AlarmManager.RTC_WAKEUP,
//                millisInFuture,
//                pendingIntent
//            )
//        } else {
//            requestExactAlarmPermission()
//        }
//    }

    fun canScheduleExactAlarms(): Boolean {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            alarmManager.canScheduleExactAlarms()
        } else {
            true
        }
    }

    private fun setAlarm(reminderId : Int, millisInFuture: Long, reminderName : String) {

            val intent = Intent(context, AlarmReceiver::class.java).apply {
                putExtra("reminderName", reminderName)
            }
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                reminderId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                millisInFuture,
                pendingIntent
            )
        }
    }

