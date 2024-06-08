package com.example.catsocial.presentation.screen.reminder

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catsocial.presentation.components.ReminderCard

@Composable
fun ReminderScreen(modifier: Modifier, viewModel: ReminderViewModel) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Reminder Aktif", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        ReminderCard(
            modifier = Modifier,
            reminderName = "Makan Pagi",
            reminderTime = "4 Jam 30 Menit"
        )

        Button(onClick = {
            viewModel.showNotification()
        }) {
            Text(text = "Notification")

        }
    }
}