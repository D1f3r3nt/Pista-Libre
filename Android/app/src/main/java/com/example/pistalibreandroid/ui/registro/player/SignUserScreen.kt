package com.example.pistalibreandroid.ui.registro.player

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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.navigation.NavHostController
import com.example.pistalibreandroid.R
import com.example.pistalibreandroid.model.ResponseError
import com.example.pistalibreandroid.model.ResponseLoading
import com.example.pistalibreandroid.model.ResponseOk
import com.example.pistalibreandroid.model.ResponseState
import com.example.pistalibreandroid.ui.navigation.Navigation
import com.example.pistalibreandroid.ui.navigation.NavigationController
import com.example.pistalibreandroid.ui.theme.angkorFamily
import kotlinx.coroutines.delay


@Composable
fun SignUserScreen(
    signUserViewModel: SignUserViewModel
){
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
        val state: ResponseState by signUserViewModel.state.collectAsState()
        val navController = NavigationController.controller()

        when (state) {
            is ResponseError -> {
                Text(text = (state as ResponseError).msg)
                LaunchedEffect(key1 = Unit) {
                    delay(3000)
                    signUserViewModel.resetState()
                }
            }
            is ResponseLoading -> {
                Box(
                    Modifier
                        .fillMaxSize()
                        .align(Alignment.TopCenter)){
                    Text(text = "Loading")
                }
            }
            is ResponseOk -> {
                navController.navigate(Navigation.LOGIN_ROUTE)
                signUserViewModel.resetState()
            }
            else -> {
                Column(Modifier.align(Alignment.TopCenter)) {
                    HeaderSignUser(
                        Modifier
                            .padding(10.dp))
                    BodySignUser(
                        Modifier
                            .padding(top = 1.dp)
                            .align(Alignment.CenterHorizontally), signUserViewModel)
                }
                FooterUserSign(Modifier.align(Alignment.BottomCenter))
            }
        }
    }
}

@Composable
fun HeaderSignUser(modifier: Modifier) {
    Column(modifier = modifier) {
        TextSignUser(modifier = modifier)
    }
}

@Composable
fun BodySignUser(modifier: Modifier, signUserViewModel: SignUserViewModel) {
    val fullName: String by signUserViewModel.fullname.collectAsState()
    val userName: String by signUserViewModel.username.collectAsState()
    val email: String by signUserViewModel.email.collectAsState()
    val password:String by signUserViewModel.password.collectAsState()
    val isRegisterEnable:Boolean by signUserViewModel.isUserSignEnable.collectAsState()
    
    var repeatpassword by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier.width(300.dp)) {
        FullName(fullName, { signUserViewModel.onUserSignChanged(email = email, password = password, fullname = it) }
            , modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        UserName(userName, { signUserViewModel.onUserSignChanged(email = email, password = password, username = it) }
            , modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        Email(email, { signUserViewModel.onUserSignChanged(email = it, password = password) }
            , modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        Password(password, { signUserViewModel.onUserSignChanged(email = email, password = it)}
            , modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        RepeatPassword(repeatpassword, { repeatpassword = it}
            , modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(48.dp))
        RegisterButton(isRegisterEnable, signUserViewModel, modifier = Modifier.width(150.dp).align(Alignment.CenterHorizontally))
    }
}

@Composable
fun RegisterButton(registerEnable: Boolean, signUserViewModel: SignUserViewModel, modifier: Modifier = Modifier) {
    val colorVerde = colorResource(id = R.color.verdeApp)
    Button(onClick = { signUserViewModel.onSignUpSelected()},
        enabled = registerEnable,
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorVerde,
            disabledContainerColor = Color(0x689DAC84),
            contentColor = Color.White,
            disabledContentColor = Color(0xA3CACACA)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text = "Registrate", color = Color.White)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullName(fullName:String, onTextChanged: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(value = fullName,
        onValueChange = { onTextChanged(it) },
        modifier = modifier.width(300.dp).height(55.dp),
        placeholder = { Text(text = "Nombre completo", fontSize = 14.sp, color = Color.Gray)},
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(12.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserName(userName:String, onTextChanged: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(value = userName,
        onValueChange = { onTextChanged(it) },
        modifier = modifier.width(300.dp).height(55.dp),
        placeholder = { Text(text = "Nombre de usuario", fontSize = 14.sp, color = Color.Gray)},
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(12.dp))
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(password:String, onTextChanged: (String) -> Unit, modifier: Modifier = Modifier) {
    var passwordVisibility by remember { mutableStateOf(false) }
    
    TextField(value = password,
        onValueChange = { onTextChanged(it) },
        modifier = modifier.width(300.dp).height(55.dp),
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
        shape = RoundedCornerShape(12.dp))
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepeatPassword(repeatpassword:String, onTextChanged: (String) -> Unit, modifier: Modifier = Modifier) {
    var passwordVisibility by remember { mutableStateOf(false) }
    TextField(value = repeatpassword,
        onValueChange = { onTextChanged(it) },
        modifier = modifier.width(300.dp).height(55.dp),
        placeholder = { Text(text = "Repetir contraseña", fontSize = 14.sp, color = Color.Gray)},
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
        shape = RoundedCornerShape(12.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(email:String, onTextChanged: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(value = email,
        onValueChange = { onTextChanged(it) },
        modifier = modifier.width(300.dp).height(55.dp),
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
        shape = RoundedCornerShape(12.dp))
}


@Composable
fun TextSignUser(modifier: Modifier) {
    val colorVerde = colorResource(id = R.color.verdeApp)
    Column(
        modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Registro", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White, fontFamily = angkorFamily)
        Text(text = "jugador", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = colorVerde, fontFamily = angkorFamily)
    }
}

@Composable
fun FooterUserSign(modifier: Modifier) {
    val navController = NavigationController.controller()
    
    Column(modifier = modifier.fillMaxWidth()) {
        GoToRegisterClub(navController)
        Spacer(modifier = Modifier.size(16.dp))
        LogIn(navController)
        Spacer(modifier = Modifier.size(16.dp))
    }
}

@Composable
fun GoToRegisterClub(navController: NavHostController){
    val colorVerde = colorResource(id = R.color.verdeApp)
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
        Text(text = "Registrarme como club",
            Modifier
                .padding(horizontal = 8.dp)
                .clickable { navController.navigate(Navigation.SIGN_UP_CLUB_ROUTE) }, fontSize = 12.sp, color = colorVerde)
    }
}

@Composable
fun LogIn(navController: NavHostController) {
    val colorVerde = colorResource(id = R.color.verdeApp)
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
        Text(text = "¿Ya tienes una cuenta?", fontSize = 12.sp, color = Color.White)
        Text(text = "Iniciar sesión",
            Modifier
                .padding(horizontal = 8.dp)
                .clickable { navController.navigate(Navigation.LOGIN_ROUTE) }, fontSize = 12.sp, color = colorVerde)
    }
}