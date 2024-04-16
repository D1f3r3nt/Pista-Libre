package com.example.pistalibreandroid.ui.login

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
import androidx.navigation.NavController
import com.example.pistalibreandroid.R
import com.example.pistalibreandroid.model.ResponseError
import com.example.pistalibreandroid.model.ResponseLoading
import com.example.pistalibreandroid.model.ResponseOk
import com.example.pistalibreandroid.model.ResponseState
import com.example.pistalibreandroid.ui.navigation.Navigation
import com.example.pistalibreandroid.ui.navigation.NavigationController
import com.example.pistalibreandroid.ui.theme.angkorFamily
import com.example.pistalibreandroid.ui.theme.robotoBold

@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navController: NavController) {
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
        val state: ResponseState by loginViewModel.state.collectAsState()
        val navController = NavigationController.controller()

        when (state) {
            is ResponseError -> {
                Text(text = (state as ResponseError).msg)
            }
            is ResponseLoading -> {
                Box(
                    Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)){
                    Text(text = "Loading")
                }
            }
            is ResponseOk -> {
                navController.navigate(Navigation.HOMEPLAYER_ROUTE)
            }
            else -> {
                Body(Modifier.align(Alignment.Center), loginViewModel, navController)
                Footer(Modifier.align(Alignment.BottomCenter))
            }
        }
    }

}

@Composable
fun Footer(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.size(24.dp))
        SignUp()
        Spacer(modifier = Modifier.size(24.dp))

    }
}

@Composable
fun SignUp() {
    val colorVerde = colorResource(id = R.color.verdeApp)
    val navController = NavigationController.controller()
    
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
        Text(text = "¿No tienes cuenta?", fontSize = 18.sp, color = Color.White)
        Text(text = "Crear una cuenta",
            Modifier
                .padding(horizontal = 8.dp)
                .clickable { navController.navigate(Navigation.SIGN_UP_ROUTE) }, fontSize = 18.sp, color = colorVerde)
    }
}

@Composable
fun Body(modifier: Modifier, loginViewModel: LoginViewModel, navController: NavController) {
    val email: String by loginViewModel.email.collectAsState()
    val password: String by loginViewModel.password.collectAsState()
    val isLoginEnable: Boolean by loginViewModel.isLoginEnable.collectAsState()


    Column(modifier = modifier) {
        TextLogin(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(100.dp))
        Email(email, {
            loginViewModel.onLoginChanged(email= it, password = password)
        }, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(30.dp))
        Password(password, {
            loginViewModel.onLoginChanged(email = email, password = it)
        }, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(62.dp))
        LoginButton(isLoginEnable = isLoginEnable, onLoginSuccess = {
            loginViewModel.onLoginSelected()
        }, modifier = Modifier.width(100.dp).height(34.dp).align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(48.dp))
    }
}

@Composable
fun TextLogin(modifier: Modifier) {
    val colorVerde = colorResource(id = R.color.verdeApp)
    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
        Text(text = "Log", fontSize = 48.sp, fontWeight = FontWeight.Bold, color = Color.White, fontFamily = angkorFamily)
        Text(text = "In", Modifier.padding(horizontal = 8.dp), fontSize = 48.sp, fontWeight = FontWeight.Bold, color = colorVerde, fontFamily = angkorFamily)
    }
}

@Composable
fun LoginButton(isLoginEnable: Boolean, onLoginSuccess: () -> Unit, modifier: Modifier = Modifier) {
    val colorVerde = colorResource(id = R.color.verdeApp)
    Button(
        onClick = { onLoginSuccess() },
        enabled = isLoginEnable,
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorVerde,
            disabledContainerColor = Color(0x689DAC84),
            contentColor = Color.White,
            disabledContentColor = Color(0xA3CACACA)
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text = "Entrar", color = Color.White, fontFamily = robotoBold)
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(password: String, onTextChanged: (String) -> Unit, modifier: Modifier = Modifier) {
    var passwordVisibility by remember { mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = { onTextChanged(it) },
        modifier = modifier.width(300.dp).height(45.dp),
        placeholder = { Text(text = "Contraseña", fontSize = 14.sp, color = Color.Gray)},
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
            val imagen = if(passwordVisibility){
                Icons.Filled.VisibilityOff
            }else{
                Icons.Filled.Visibility
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility}){
                Icon(imageVector = imagen, contentDescription = "mostrar contraseña")
            }
        },
        visualTransformation = if(passwordVisibility){
            VisualTransformation.None
        }else{
            PasswordVisualTransformation()
        },
        shape = RoundedCornerShape(10.dp))
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(email: String, onTextChanged: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        value = email,
        onValueChange = { onTextChanged(it) },
        modifier = modifier.width(300.dp).height(45.dp),
        placeholder = { Text(text = "Correo electrónico", fontSize = 14.sp, color = Color.Gray)},
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(10.dp)
        )
}
