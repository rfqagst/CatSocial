package com.example.catsocial.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.catsocial.presentation.navigation.BottomNavItem
import com.example.catsocial.presentation.navigation.Screen
import com.example.catsocial.ui.theme.BlackPrimary
import com.example.catsocial.ui.theme.OrangePrimary

@Composable
fun BottomBarComponentAdoption(navController: NavController, items: List<BottomNavItem>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = item.title,
                        tint = if (currentDestination == item.route) OrangePrimary else Color.Gray
                    )
                },
                label = {
                    Text(
                        item.title,
                        fontSize = 14.sp,
                        color = if (currentDestination == item.route) OrangePrimary else Color.Gray
                    )
                },
                selected = currentDestination == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}


@Composable
fun BottomBarComponentAdoptionDetail(
    navController: NavController,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .weight(5f)
                .fillMaxSize()
                .background(Color.White)
                .clickable {
                    navController.popBackStack()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Kembali",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = BlackPrimary,
                modifier = Modifier.padding(top = 6.dp)
            )

        }

        Box(
            modifier = Modifier
                .weight(5f)
                .fillMaxSize()
                .background(OrangePrimary)
                .clickable {
                    navController.navigate(Screen.AdoptionConfirmation.route)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Adopsi",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier.padding(top = 6.dp)
            )
        }


    }
}