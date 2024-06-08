package com.example.catsocial.presentation.screen.reminder

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catsocial.presentation.components.ReminderCard
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Composable
fun ReminderScreen(modifier: Modifier, viewModel: ReminderViewModel) {
    val remainingTime by viewModel.remainingTime.collectAsState()

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Reminder Aktif", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        ReminderCard(
            modifier = Modifier,
            reminderName = "Makan Pagi",
            reminderTime = formatDuration(remainingTime)
        )

        Button(onClick = {
            val hours = 0
            val minutes = 1
            val millisInFuture = (hours * 3600000 + minutes * 60000).toLong()
            viewModel.startCountdown(millisInFuture)
        }) {
            Text(text = "Notification")
        }
    }
}

@Composable
fun formatDuration(millis: Long): String {
    val duration = millis.toDuration(DurationUnit.MILLISECONDS)
    val hours = duration.inWholeHours
    val minutes = duration.inWholeMinutes % 60
    val seconds = duration.inWholeSeconds % 60
    return String.format("%02d Jam %02d Menit %02d Detik", hours, minutes, seconds)
}