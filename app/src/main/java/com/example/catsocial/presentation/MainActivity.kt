package com.example.catsocial.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.example.catsocial.presentation.screen.auth.AuthViewModel
import com.example.catsocial.presentation.screen.reminder.ReminderViewModel
import com.example.catsocial.ui.theme.CatSocialTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val authViewModel: AuthViewModel by viewModels()
        setContent {
            CatSocialTheme {
                CatSocialApp(authViewModel = authViewModel)
            }
        }

    }


}

