package com.example.pistalibreandroid.registro.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScopeInstance.align
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pistalibreandroid.R
import com.example.pistalibreandroid.registro.registroJugador.ui.RegistroJugadorViewModel
import com.example.pistalibreandroid.ui.theme.angkorFamily


@Composable
fun RegistroScreen(navigationController: NavHostController) {
    Box(
        Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(8.dp)
    ) {
        HeaderRegistro(Modifier.align(Alignment.TopCenter).padding(32.dp))
        BodyRegistro(Modifier.align(Alignment.Center), navigationController)
        Spacer(modifier = Modifier.size(80.dp))
        FooterRegistro(Modifier.align(Alignment.BottomCenter), navigationController)
    }

}

@Composable
fun HeaderRegistro(modifier: Modifier) {
    Row(modifier = modifier) {
        TextRegistro(modifier = modifier)
    }
}

@Composable
fun FooterRegistro(modifier: Modifier, navController: NavHostController) {
    Column(modifier = modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.size(24.dp))
        LogIn(navController)
        Spacer(modifier = Modifier.size(24.dp))

    }
}

@Composable
fun LogIn(navController: NavHostController) {
    val colorVerde = colorResource(id = R.color.verdeApp)
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
        Text(text = "¿Ya tienes una cuenta?", fontSize = 12.sp, color = Color.White)
        Text(text = "Iniciar sesión", Modifier.padding(horizontal = 8.dp).clickable { navController.navigate("login") }, fontSize = 12.sp, color = colorVerde)
    }
}

@Composable
fun BodyRegistro(modifier: Modifier, navController: NavHostController) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.size(80.dp))
        JugadorButton(
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
fun JugadorButton(modifier: Modifier, navController: NavHostController) {
    val colorVerde = colorResource(id = R.color.verdeApp)
    Button(
        onClick = { navController.navigate("registroJugador") },
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
        onClick = { navController.navigate("registroClub") },
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
fun TextRegistro(modifier: Modifier) {
    val colorVerde = colorResource(id = R.color.verdeApp)
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
        Text(text = "Registr", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = colorVerde, fontFamily = angkorFamily)
        Text(text = "o", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White, fontFamily = angkorFamily)
    }
}

