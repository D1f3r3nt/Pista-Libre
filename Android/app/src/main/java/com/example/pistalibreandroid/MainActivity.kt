package com.example.pistalibreandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pistalibreandroid.ui.login.LoginScreen
import com.example.pistalibreandroid.ui.login.LoginViewModel
import com.example.pistalibreandroid.ui.navigation.Navigation
import com.example.pistalibreandroid.ui.navigation.NavigationController
import com.example.pistalibreandroid.ui.registro.SignScreen
import com.example.pistalibreandroid.ui.registro.club.SignClubScreen
import com.example.pistalibreandroid.ui.registro.club.SignClubViewModel
import com.example.pistalibreandroid.ui.registro.player.SignUserScreen
import com.example.pistalibreandroid.ui.registro.player.SignUserViewModel
import com.example.pistalibreandroid.ui.setting.club.ClubSettingScreen
import com.example.pistalibreandroid.ui.setting.club.ClubSettingViewModel
import com.example.pistalibreandroid.ui.setting.user.UserSettingScreen
import com.example.pistalibreandroid.ui.setting.user.UserSettingViewModel
import com.example.pistalibreandroid.ui.splash.SplashScreen
import com.example.pistalibreandroid.ui.theme.PistaLibreAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val signUserViewModel: SignUserViewModel by viewModels()
    private val signClubViewModel: SignClubViewModel by viewModels()
    private val userSettingViewModel: UserSettingViewModel by viewModels()
    private val clubSettingViewModel: ClubSettingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavigationController.init(navController)

            PistaLibreAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController, startDestination = Navigation.SPLASH_ROUTE) {
                        composable(Navigation.SPLASH_ROUTE) { 
                            SplashScreen() 
                        }
                        
                        composable(Navigation.LOGIN_ROUTE) { 
                            LoginScreen(loginViewModel)
                        }
                        
                        composable(Navigation.SIGN_UP_ROUTE) { 
                            SignScreen()
                        }
                        
                        composable(Navigation.SIGN_UP_USER_ROUTE) { 
                            SignUserScreen(signUserViewModel)
                        }
                        
                        composable(Navigation.SIGN_UP_CLUB_ROUTE) { 
                            SignClubScreen(signClubViewModel)
                        }

                        composable(Navigation.SETTING_USER_ROUTE) {
                            UserSettingScreen(userSettingViewModel)
                        }

                        composable(Navigation.SETTING_CLUB_ROUTE) {
                            ClubSettingScreen(clubSettingViewModel)
                        }
                    }
                }
            }
        }
    }
}