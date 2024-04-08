package com.example.pistalibreandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pistalibreandroid.login.ui.LoginScreen
import com.example.pistalibreandroid.login.ui.LoginViewModel
import com.example.pistalibreandroid.navigation.AppScreens
import com.example.pistalibreandroid.registro.registroClub.ui.SignClubScreen
import com.example.pistalibreandroid.registro.registroClub.ui.SignClubViewModel
import com.example.pistalibreandroid.registro.registroJugador.ui.RegistroJugadorScreen
import com.example.pistalibreandroid.registro.registroJugador.ui.RegistroJugadorViewModel
import com.example.pistalibreandroid.registro.ui.RegistroScreen
import com.example.pistalibreandroid.splash.ui.SplashScreen
import com.example.pistalibreandroid.ui.theme.PistaLibreAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel:LoginViewModel by viewModels()
    private val registroJugadorViewModel:RegistroJugadorViewModel by viewModels()
    private val signClubViewModel:SignClubViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PistaLibreAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController, startDestination = AppScreens.SplashScreen.route ){
                        composable(AppScreens.SplashScreen.route){ SplashScreen(navigationController)}
                        composable(AppScreens.LoginScreen.route){ LoginScreen(loginViewModel, navigationController)}
                        composable(AppScreens.RegistroScreen.route){ RegistroScreen(navigationController)}
                        composable(AppScreens.RegistroJugadorScreen.route){ RegistroJugadorScreen(registroJugadorViewModel, navigationController)}
                        composable(AppScreens.SignClubScreen.route){ SignClubScreen(signClubViewModel, navigationController)}
                    }
                }
            }
        }
    }
}