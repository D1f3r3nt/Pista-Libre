package com.example.pistalibreandroid.ui.registro.club

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pistalibreandroid.R
import com.example.pistalibreandroid.ui.navigation.Navigation
import com.example.pistalibreandroid.ui.navigation.NavigationController
import com.example.pistalibreandroid.ui.theme.angkorFamily


@Composable
fun SignClubScreen(
    signClubViewModel: SignClubViewModel
) {
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
        Column(Modifier.align(Alignment.TopCenter)) {
            HeaderSignClub(
                Modifier
                    .padding(10.dp)
            )
            BodySignClub(
                Modifier
                    .padding(top = 1.dp)
                    .align(Alignment.CenterHorizontally), signClubViewModel
            )
        }
        FooterSignClub(Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun FooterSignClub(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        GoToRegisterPlayer()
        Spacer(modifier = Modifier.size(16.dp))
        LogIn()
        Spacer(modifier = Modifier.size(16.dp))
    }
}

@Composable
fun GoToRegisterPlayer() {
    val colorVerde = colorResource(id = R.color.verdeApp)
    val navController = NavigationController.controller()

    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(text = "Registrarme como jugador",
            Modifier
                .padding(horizontal = 8.dp)
                .clickable { navController.navigate(Navigation.SIGN_UP_USER_ROUTE) }, fontSize = 12.sp, color = colorVerde)
    }
}

@Composable
fun LogIn() {
    val colorVerde = colorResource(id = R.color.verdeApp)
    val navController = NavigationController.controller()
    
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(text = "¿Ya tienes una cuenta?", fontSize = 12.sp, color = Color.White)
        Text(text = "Iniciar sesión",
            Modifier
                .padding(horizontal = 8.dp)
                .clickable { navController.navigate(Navigation.LOGIN_ROUTE) }, fontSize = 12.sp, color = colorVerde)
    }
}

@Composable
fun BodySignClub(modifier: Modifier, signClubViewModel: SignClubViewModel) {
    val clubName by signClubViewModel.clubName.collectAsState()
    val directionClub by signClubViewModel.directionClub.collectAsState()
    val email: String by signClubViewModel.email.collectAsState()
    val password: String by signClubViewModel.password.collectAsState()
    val signClubEnable by signClubViewModel.isSignClubEnable.collectAsState()
    
    var repeatpasswordclub by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier.width(300.dp)) {
        ClubName(clubName, { signClubViewModel.onSignClubChanged(email = email, password = password, clubName = it) }
            , modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        DirectionClub(directionClub, { signClubViewModel.onSignClubChanged(email = email, password = password, directionClub = it) }
            , modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        Email(email, { signClubViewModel.onSignClubChanged(email = it, password = password) }
            , modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        Password(password, { signClubViewModel.onSignClubChanged(email = email, password = it) }
            , modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        RepeatPasswordClub(repeatpasswordclub, { repeatpasswordclub = it }
            , modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(48.dp))
        SingUpClubButton(signClubEnable, signClubViewModel, modifier = Modifier.width(150.dp).align(Alignment.CenterHorizontally))

    }
}


@Composable
fun SingUpClubButton(signClubEnable: Boolean, signClubViewModel: SignClubViewModel, modifier: Modifier = Modifier) {
    val colorVerde = colorResource(id = R.color.verdeApp)
    Button(
        onClick = { signClubViewModel.onSignUpClubSelected() },
        enabled = signClubEnable,
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorVerde,
            disabledContainerColor = Color(0x689DAC84),
            contentColor = Color.White,
            disabledContentColor = Color(0xA3CACACA)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text = "Registrar club", color = Color.White)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DirectionClub(directionClub: String, onTextChanged: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        value = directionClub,
        onValueChange = { onTextChanged(it) },
        modifier = modifier.width(300.dp).height(45.dp),
        placeholder = { Text(text = "Dirección completa", fontSize = 14.sp, color = Color.Gray) },
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(12.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClubName(clubName: String, onTextChanged: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        value = clubName,
        onValueChange = { onTextChanged(it) },
        modifier = modifier.width(300.dp).height(45.dp),
        placeholder = { Text(text = "Nombre del club", fontSize = 14.sp, color = Color.Gray) },
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(12.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(email: String, onTextChanged: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        value = email,
        onValueChange = { onTextChanged(it) },
        modifier = modifier.width(300.dp).height(45.dp),
        placeholder = { Text(text = "Correo electrónico", fontSize = 14.sp, color = Color.Gray) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(12.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(password: String, onTextChanged: (String) -> Unit, modifier: Modifier = Modifier) {
    var passwordVisibility by remember { mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = { onTextChanged(it) },
        modifier = modifier.width(300.dp).height(45.dp),
        placeholder = { Text(text = "Contraseña", fontSize = 14.sp, color = Color.Gray) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        trailingIcon = {
            val imagen = if (passwordVisibility) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = imagen, contentDescription = "mostrar contraseña")
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        shape = RoundedCornerShape(12.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepeatPasswordClub(repeatpasswordclub: String, onTextChanged: (String) -> Unit, modifier: Modifier = Modifier) {
    var passwordVisibility by remember { mutableStateOf(false) }
    TextField(
        value = repeatpasswordclub,
        onValueChange = { onTextChanged(it) },
        modifier = modifier.width(300.dp).height(45.dp),
        placeholder = { Text(text = "Repetir contraseña", fontSize = 14.sp, color = Color.Gray) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        trailingIcon = {
            val imagen = if (passwordVisibility) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = imagen, contentDescription = "mostrar contraseña")
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
fun HeaderSignClub(modifier: Modifier) {
    Column(modifier = modifier) {
        TextSignClub(modifier = modifier)
    }
}

@Composable
fun TextSignClub(modifier: Modifier) {
    val colorVerde = colorResource(id = R.color.verdeApp)
    Column(
        modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Registro", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White, fontFamily = angkorFamily)
        Text(text = "club", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = colorVerde, fontFamily = angkorFamily)
    }
}
