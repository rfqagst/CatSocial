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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatSocialTheme {
                CatSocialApp()
            }
        }

    }


}

