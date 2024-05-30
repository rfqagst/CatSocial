package com.example.catsocial.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Timer
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

var bottomNavItem = listOf(
    BottomNavItem(
        title = "Adopsi",
        icon = Icons.Default.Pets,
        route = "home"
    ),
    BottomNavItem(
        title = "Informasi",
        icon = Icons.Default.Search,
        route = "informasi"
    ),
    BottomNavItem(
        title = "Reminder",
        icon = Icons.Default.Timer,
        route = "reminder"
    )
)
