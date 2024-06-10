package com.example.catsocial.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.catsocial.presentation.screen.reminder.ReminderViewModel
import com.example.catsocial.ui.theme.CatSocialTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val reminderViewModel: ReminderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatSocialTheme {
                CatSocialApp()
            }
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S && !reminderViewModel.canScheduleExactAlarms()) {
            reminderViewModel.requestExactAlarmPermission()
        }

    }


}

