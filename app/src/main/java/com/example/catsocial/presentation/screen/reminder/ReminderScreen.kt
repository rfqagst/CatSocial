package com.example.catsocial.presentation.screen.reminder

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catsocial.data.room.entity.Reminder
import com.example.catsocial.presentation.components.ReminderCard
import com.example.catsocial.ui.theme.OrangePrimary
import com.example.catsocial.util.Resource
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Composable
fun ReminderScreen(modifier: Modifier, viewModel: ReminderViewModel) {
    val remainingTimeMap by viewModel.remainingTimeMap.collectAsState()

    val remindersState by viewModel.allReminders.collectAsState()


    LaunchedEffect(remindersState) {
        if (remindersState is Resource.Success) {
            val reminders = (remindersState as Resource.Success<List<Reminder>>).data
            reminders?.forEach { reminder ->
                val reminderTimeInMillis = reminder.time
                val currentTimeInMillis = System.currentTimeMillis()
                val millisInFuture = reminderTimeInMillis - currentTimeInMillis

                Log.d(
                    "ReminderScreen",
                    "Current time: $currentTimeInMillis, Reminder time: $reminderTimeInMillis, Millis in future: $millisInFuture"
                )

                if (millisInFuture > 0) {
                    viewModel.startCountdown(reminder.id, millisInFuture, reminder.name)
                } else {
                    Log.d("ReminderScreen", "The reminder time is in the past.")
                }
            }
        }
    }

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Reminder Aktif", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val newReminderTime = System.currentTimeMillis() + 3000
            viewModel.insertReminder(
                Reminder(
                    id = 20,
                    name = "Makan Pagi",
                    time = newReminderTime
                )
            )
        }) {
            Text(text = "Tambah Reminder")
        }

        when (remindersState) {

            is Resource.Error -> {
                Text(text = (remindersState as Resource.Error).message ?: "An error occurred")

            }

            is Resource.Idle -> {
                // s
            }

            is Resource.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(60.dp),
                        color = OrangePrimary,
                    )
                }
            }

            is Resource.Success -> {
                remindersState.data?.let { reminderList ->
                    LazyColumn {
                        items(reminderList.size) { index ->
                            val reminder = reminderList[index]
                            val remainingTime = remainingTimeMap[reminder.id] ?: 0L
                            val reminderTime = if (remainingTime > 0) {
                                formatDuration(remainingTime)
                            } else {
                                "00 Jam : 00 Menit : 00 Detik"
                            }
                            ReminderCard(
                                modifier = Modifier,
                                reminderName = reminder.name,
                                reminderTime = reminderTime
                            )
                        }

                    }


                }


            }
        }

        Row {
            Text(text = "Disclamer : Reminder mungkin delay beberapa detik.", color = Color.Gray)

        }

    }
}

@Composable
fun formatDuration(millis: Long): String {
    val duration = millis.toDuration(DurationUnit.MILLISECONDS)
    val hours = duration.inWholeHours
    val minutes = duration.inWholeMinutes % 60
    val seconds = duration.inWholeSeconds % 60
    Log.d("formatDuration", "Formatting duration: $hours hours, $minutes minutes, $seconds seconds")
    return String.format("%02d Jam %02d Menit %02d Detik", hours, minutes, seconds)
}
