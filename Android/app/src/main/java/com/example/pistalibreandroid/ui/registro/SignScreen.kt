package com.example.pistalibreandroid.ui.registro

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pistalibreandroid.R
import com.example.pistalibreandroid.ui.navigation.Navigation
import com.example.pistalibreandroid.ui.navigation.NavigationController
import com.example.pistalibreandroid.ui.theme.angkorFamily


@Composable
fun SignScreen() {

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
    ) {
        HeaderSign(Modifier.align(Alignment.TopCenter).padding(32.dp))
        BodySign(Modifier.align(Alignment.Center))
        Spacer(modifier = Modifier.size(80.dp))
        FooterSign(Modifier.align(Alignment.BottomCenter))
    }

}

@Composable
fun HeaderSign(modifier: Modifier) {
    Row(modifier = modifier) {
        TextSign(modifier = modifier)
    }
}

@Composable
fun FooterSign(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.size(24.dp))
        LogIn()
        Spacer(modifier = Modifier.size(24.dp))

    }
}

@Composable
fun LogIn() {
    val navController = NavigationController.controller()
    val colorVerde = colorResource(id = R.color.verdeApp)
    
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
        Text(text = "¿Ya tienes una cuenta?", fontSize = 12.sp, color = Color.White)
        Text(text = "Iniciar sesión", Modifier.padding(horizontal = 8.dp).clickable { navController.navigate(Navigation.LOGIN_ROUTE) }, fontSize = 12.sp, color = colorVerde)
    }
}

@Composable
fun BodySign(modifier: Modifier) {
    val navController = NavigationController.controller()
    
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.size(80.dp))
        UserButton(
            Modifier
                .align(Alignment.CenterHorizontally)
                .width(150.dp), navController)
        Spacer(modifier = Modifier.size(8.dp))
        ClubButton(
            Modifier
                .align(Alignment.CenterHorizontally)
                .width(150.dp), navController)
    }
}

@Composable
fun UserButton(modifier: Modifier, navController: NavHostController) {
    val colorVerde = colorResource(id = R.color.verdeApp)
    Button(
        onClick = { navController.navigate(Navigation.SIGN_UP_USER_ROUTE) },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorVerde,
            disabledContainerColor = Color(0x689DAC84),
            contentColor = Color.White,
            disabledContentColor = Color(0xA3CACACA)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text = "Jugador", color = Color.White, fontWeight = FontWeight.Bold)

    }
}

@Composable
fun ClubButton(modifier: Modifier, navController: NavHostController) {
    val colorVerde = colorResource(id = R.color.verdeApp)
    Button(
        onClick = { navController.navigate(Navigation.SIGN_UP_CLUB_ROUTE) },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorVerde,
            disabledContainerColor = Color(0x689DAC84),
            contentColor = Color.White,
            disabledContentColor = Color(0xA3CACACA)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text = "Club", color = Color.White, fontWeight = FontWeight.Bold)

    }
}

@Composable
fun TextSign(modifier: Modifier) {
    val colorVerde = colorResource(id = R.color.verdeApp)
    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
        Text(text = "Registr", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = colorVerde, fontFamily = angkorFamily)
        Text(text = "o", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White, fontFamily = angkorFamily)
    }
}

