package com.example.catsocial.presentation.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    @Inject
    lateinit var notificationBuilder: NotificationCompat.Builder

    @Inject
    lateinit var notificationManager: NotificationManagerCompat

    override fun onReceive(context: Context, intent: Intent) {
        val reminderName = intent.getStringExtra("reminderName") ?: "Reminder"

        showNotification(reminderName)
    }
    private fun showNotification(reminderName: String) {
        notificationManager.notify(1, notificationBuilder
            .setContentTitle("Reminder Pemberian Makan")
            .setContentText("$reminderName: Saatnya memberi makan si Anabul.")
            .build()
        )
    }

}
