package com.example.catsocial.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.catsocial.presentation.components.BottomBarComponentAdoption
import com.example.catsocial.presentation.components.BottomBarComponentAdoptionDetail
import com.example.catsocial.presentation.components.TopBarComponent
import com.example.catsocial.presentation.components.TopBarComponentAdoption
import com.example.catsocial.presentation.navigation.NavGraph
import com.example.catsocial.presentation.navigation.Screen
import com.example.catsocial.presentation.navigation.bottomNavItem
import com.example.catsocial.presentation.screen.auth.AuthViewModel
import com.example.catsocial.ui.theme.OrangePrimary

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun CatSocialApp(
    authViewModel: AuthViewModel
) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    val currentUser = authViewModel.currentUser?.displayName ?: "Refresh To Load"

    Scaffold(
        topBar = {
            when (currentDestination) {
                Screen.Adoption.route -> TopBarComponentAdoption(
                    name = currentUser,
                    navController = navController
                )

                Screen.AdoptionAdd.route -> TopBarComponent(
                    title = "Adopsikan Anabul",
                    navController = navController
                )

                Screen.CatListDetail.route + "/{catId}" -> TopBarComponent(
                    title = "Detail Anabul",
                    navController = navController
                )

                Screen.AdoptionDetail.route + "/{adoptionId}" -> TopBarComponent(
                    title = "Detail Anabul",
                    navController = navController
                )

                Screen.Map.route -> TopBarComponent(
                    title = "Lokasi Anabul",
                    navController = navController
                )

                Screen.Profile.route -> TopBarComponent(
                    title = "Profil",
                    navController = navController
                )
                Screen.EditPassword.route -> TopBarComponent(
                    title = "Edit Password",
                    navController = navController
                )
                Screen.EditProfile.route -> TopBarComponent(
                    title = "Edit Profile",
                    navController = navController
                )
                Screen.AddReminder.route -> TopBarComponent(
                    title = "Tambah Reminder Makan",
                    navController = navController)

                Screen.EditReminder.route + "/{reminderId}" -> TopBarComponent(
                    title = "Edit Reminder Makan",
                    navController = navController)


                Screen.Map.route + "/{catId}" -> TopBarComponent(
                    title = "Lokasi Anabul",
                    navController = navController
                )
            }
        },
        bottomBar = {
            when (currentDestination) {
                Screen.Adoption.route -> BottomBarComponentAdoption(
                    navController = navController,
                    items = bottomNavItem
                )

                Screen.CatList.route -> BottomBarComponentAdoption(
                    navController = navController,
                    items = bottomNavItem
                )

                Screen.Reminder.route -> BottomBarComponentAdoption(
                    navController = navController,
                    items = bottomNavItem
                )

                Screen.Profile.route -> BottomBarComponentAdoption(
                    navController = navController,
                    items = bottomNavItem
                )

                Screen.AdoptionDetail.route + "/{adoptionId}" -> BottomBarComponentAdoptionDetail(
                    navController = navController,
                )

            }
        },
        floatingActionButton = {
            when (currentDestination) {
                Screen.Reminder.route -> FloatingActionButton(
                    onClick = { navController.navigate(Screen.AddReminder.route) },
                    containerColor = OrangePrimary
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add", tint = Color.White)
                }
            }

        }
    ) { paddingValues ->
        NavGraph(navController = navController, modifier = Modifier.padding(paddingValues))

    }


}