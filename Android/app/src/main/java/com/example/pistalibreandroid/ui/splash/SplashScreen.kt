package com.example.pistalibreandroid.ui.splash

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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pistalibreandroid.R
import com.example.pistalibreandroid.ui.navigation.Navigation
import com.example.pistalibreandroid.ui.navigation.NavigationController
import com.example.pistalibreandroid.ui.theme.angkorFamily
import kotlinx.coroutines.delay


@Composable
fun SplashScreen() {

    val navController = NavigationController.controller()
    
    LaunchedEffect(key1 = true){
        delay(1000)
        navController.popBackStack()
        navController.navigate(Navigation.LOGIN_ROUTE)
    }

    Splash()
}

@Composable
fun Splash(){
    val colorVerde = colorResource(id = R.color.verdeApp)
    val colorgrisoscuro = colorResource(id = R.color.grisoscuro)
    val gradient = Brush.linearGradient(
        0f to Color.Black,
        0.30f to Color.Black,
        1f to colorgrisoscuro,
        start = Offset(0f, 0f),
        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(brush = gradient)
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