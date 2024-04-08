package com.example.pistalibreandroid.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen: AppScreens("splash_screen")
    object LoginScreen: AppScreens("login")
    object RegistroScreen: AppScreens("registro")
    object RegistroJugadorScreen: AppScreens("registroJugador")
    object SignClubScreen: AppScreens("registroClub")
}