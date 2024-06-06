package com.example.catsocial.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.catsocial.presentation.screen.adoption.AddAdoptionScreen
import com.example.catsocial.presentation.screen.adoption.AdoptionConfirmationScreen
import com.example.catsocial.presentation.screen.adoption.AdoptionDetailScreen
import com.example.catsocial.presentation.screen.adoption.AdoptionScreen
import com.example.catsocial.presentation.screen.adoption.AdoptionViewModel
import com.example.catsocial.presentation.screen.auth.login.LoginScreen
import com.example.catsocial.presentation.screen.auth.register.RegisterScreen
import com.example.catsocial.presentation.screen.catlist.CatListDetailScreen
import com.example.catsocial.presentation.screen.catlist.CatListScreen
import com.example.catsocial.presentation.screen.catlist.CatListViewModel
import com.example.catsocial.presentation.screen.reminder.ReminderScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier) {

    NavHost(navController, startDestination = Screen.Adoption.route) {

        composable(Screen.Adoption.route) {
            AdoptionScreen(modifier, navController)
        }

        composable(Screen.AdoptionAdd.route) {
            val adoptionViewModel: AdoptionViewModel = hiltViewModel()
            AddAdoptionScreen(modifier, adoptionViewModel)
        }

        composable(Screen.AdoptionDetail.route) {
            AdoptionDetailScreen(modifier)
        }

        composable(Screen.AdoptionConfirmation.route) {
            AdoptionConfirmationScreen(modifier)
        }



        composable(Screen.Register.route) {
            RegisterScreen(modifier)
        }

        composable(Screen.Login.route) {
            LoginScreen(modifier)
        }




        composable(Screen.CatList.route) {
            val catListViewModel: CatListViewModel = hiltViewModel()
            CatListScreen(modifier, catListViewModel)
        }

        composable(Screen.CatListDetail.route) {
            CatListDetailScreen(modifier)
        }


        composable(Screen.Reminder.route) {
            ReminderScreen(modifier)
        }


    }
}