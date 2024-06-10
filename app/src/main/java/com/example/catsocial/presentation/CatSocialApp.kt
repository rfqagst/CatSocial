package com.example.catsocial.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.catsocial.presentation.components.BottomBarComponentAdoption
import com.example.catsocial.presentation.components.BottomBarComponentAdoptionDetail
import com.example.catsocial.presentation.components.TopBarComponent
import com.example.catsocial.presentation.components.TopBarComponentAdoption
import com.example.catsocial.presentation.navigation.NavGraph
import com.example.catsocial.presentation.navigation.Screen
import com.example.catsocial.presentation.navigation.bottomNavItem

@Composable
fun CatSocialApp(

) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            when (currentDestination) {
                Screen.Adoption.route -> TopBarComponentAdoption(
                    name = "Rizal Hitam Manis",
                    navController = navController
                )

                Screen.AdoptionAdd.route  -> TopBarComponent(
                    title = "Adopsikan Anabul",
                    navController = navController
                )

                Screen.CatListDetail.route  + "/{catId}" -> TopBarComponent(
                    title = "Detail Anabul",
                    navController = navController
                )

                Screen.AdoptionDetail.route + "/{adoptionId}" -> TopBarComponent(
                    title = "Detail Anabul",
                    navController = navController
                )

                Screen.Map.route  -> TopBarComponent(
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

                Screen.AdoptionDetail.route + "/{adoptionId}" -> BottomBarComponentAdoptionDetail(
                    navController = navController,
                )
            }
        },
    ) { paddingValues ->
        NavGraph(navController = navController, modifier = Modifier.padding(paddingValues))

    }


}