package com.example.pistalibreandroid.splash.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pistalibreandroid.R
import com.example.pistalibreandroid.navigation.AppScreens
import com.example.pistalibreandroid.ui.theme.angkorFamily
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navigationController: NavHostController) {

    LaunchedEffect(key1 = true){
        delay(5000)
        navigationController.popBackStack()
        navigationController.navigate(AppScreens.LoginScreen.route)
    }

    Splash()
}

@Composable
fun Splash(){
    val colorVerde = colorResource(id = R.color.verdeApp)

    Box(
        Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(8.dp)
    ){
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(text = "PISTA", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White, fontFamily = angkorFamily)
            Text(text = "LIBRE", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = colorVerde, fontFamily = angkorFamily)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    Splash()
}