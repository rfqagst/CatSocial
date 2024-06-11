package com.example.catsocial.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.catsocial.presentation.screen.map.MapsScreen
import com.example.catsocial.presentation.screen.reminder.ReminderScreen
import com.example.catsocial.presentation.screen.reminder.ReminderViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier) {

    NavHost(navController, startDestination = Screen.Adoption.route) {

        composable(Screen.Adoption.route) {
            val adoptionViewModel: AdoptionViewModel = hiltViewModel()
            AdoptionScreen(modifier, navController, adoptionViewModel)
        }

        composable(Screen.AdoptionAdd.route) {
            val adoptionViewModel: AdoptionViewModel = hiltViewModel()
            AddAdoptionScreen(modifier, adoptionViewModel)
        }

        composable(Screen.AdoptionDetail.route + "/{adoptionId}") {
            val adoptionViewModel: AdoptionViewModel = hiltViewModel()
            val adoptionId = it.arguments?.getString("adoptionId") ?: ""
            AdoptionDetailScreen(modifier, adoptionViewModel, adoptionId, navController)
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

            CatListScreen(modifier, catListViewModel, navController)
        }

        composable(Screen.CatListDetail.route + "/{catId}") {
            val catId = it.arguments?.getString("catId") ?: ""
            val catListViewModel: CatListViewModel = hiltViewModel()

            CatListDetailScreen(modifier, catId, catListViewModel)
        }


        composable(Screen.Reminder.route) {
            val reminderViewModel: ReminderViewModel = hiltViewModel()

            ReminderScreen(modifier, reminderViewModel)
        }


        composable(Screen.Map.route + "/{catId}") {
            val catId = it.arguments?.getString("catId") ?: ""
            val adoptionViewModel: AdoptionViewModel = hiltViewModel()

            MapsScreen(modifier, navController, catId, adoptionViewModel)
        }

    }
}