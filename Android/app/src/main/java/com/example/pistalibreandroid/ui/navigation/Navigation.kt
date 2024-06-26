package com.example.pistalibreandroid.ui.navigation

sealed class Navigation(val route: String){
    companion object {
        const val SPLASH_ROUTE = "splash"
        const val LOGIN_ROUTE = "login"
        const val SIGN_UP_ROUTE = "signUp"
        const val SIGN_UP_CLUB_ROUTE = "signUpClub"
        const val SIGN_UP_USER_ROUTE = "signUpUser"
        const val HOMEPLAYER_ROUTE = "homeplayer"
        const val SETTING_USER_ROUTE = "settingUser"
        const val SETTING_CLUB_ROUTE = "settingClub"
    }
    
    object SPLASH: Navigation(SPLASH_ROUTE)
    object LOGIN: Navigation(LOGIN_ROUTE)
    object SIGN_UP: Navigation(SIGN_UP_ROUTE)
    object SIGN_UP_CLUB: Navigation(SIGN_UP_CLUB_ROUTE)
    object SIGN_UP_USER: Navigation(SIGN_UP_USER_ROUTE)
    object HOMEPLAYER: Navigation(HOMEPLAYER_ROUTE)
    object SETTING_USER: Navigation(SETTING_USER_ROUTE)
    object SETTING_CLUB: Navigation(SETTING_CLUB_ROUTE)
}