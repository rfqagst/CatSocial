package com.example.catsocial.presentation.navigation

sealed class Screen(val route : String) {

    data object AdoptionConfirmation : Screen("adoptionConfirmationScreen")
    data object AdoptionDetail : Screen("adoptionDetailScreen")
    data object Adoption : Screen("adoptionScreen")
    data object AdoptionAdd : Screen("adoptionAddScreen")


    data object Map : Screen("mapScreen")


    data object Login : Screen("loginScreen")
    data object Register : Screen("registerScreen")


    data object CatList : Screen("catlistScreen")
    data object CatListDetail : Screen("catlistDetailScreen")


    data object Reminder : Screen("reminderScreen")
    data object AddReminder : Screen("addreminderScreen")

    data object Home : Screen("home")

    data object ForgotPassword : Screen("forgot_password")

    data object Profile : Screen("profileScreen")

    data object EditProfile : Screen("editprofile")
    data object EditPassword : Screen("editpassword")

}