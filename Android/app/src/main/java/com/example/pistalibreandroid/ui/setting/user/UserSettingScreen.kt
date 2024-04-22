package com.example.pistalibreandroid.ui.setting.user

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.pistalibreandroid.R
import com.example.pistalibreandroid.model.ResponseError
import com.example.pistalibreandroid.model.ResponseLoading
import com.example.pistalibreandroid.model.ResponseOk
import com.example.pistalibreandroid.model.ResponseState
import com.example.pistalibreandroid.ui.navigation.Navigation
import com.example.pistalibreandroid.ui.navigation.NavigationController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserSettingScreen(userSettingViewModel: UserSettingViewModel) {
    
    Scaffold(
        topBar = { UserSettingScreen_TopAppBar() }
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(color = Color.Black)
                .padding(it)
        ) {
            UserSettingScreen_Body(userSettingViewModel)
        }
    }
    
}


@Composable
fun UserSettingScreen_Body(userSettingViewModel: UserSettingViewModel) {
    val context = LocalContext.current
    val isConfigEnable: Boolean by userSettingViewModel.isConfigEnable.collectAsState()
    val state: ResponseState by userSettingViewModel.state.collectAsState()
    
    if (state is ResponseError) {
        Toast.makeText(context, (state as ResponseError).msg, Toast.LENGTH_LONG).show()
        userSettingViewModel.resetState()
    }

    if (state is ResponseOk) {
        Toast.makeText(context, "Guardado", Toast.LENGTH_LONG).show()
        userSettingViewModel.resetState()
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(20.dp))
        
        Image(
          painter = rememberAsyncImagePainter(""),
            contentDescription = null,
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
                .background(colorResource(id = R.color.grisclaro))
        )

        Spacer(modifier = Modifier.size(10.dp))
        
        TextButton(onClick = {
            Toast.makeText(context, "Future events", Toast.LENGTH_LONG).show()
        }) {
            Text(text = "Cambiar foto")
        }
        
        UserSettingScreen_Form(userSettingViewModel)
        
        Spacer(modifier = Modifier.size(20.dp))
        
        Button(
            onClick = {
                userSettingViewModel.postConfig()
            },
            modifier = Modifier
                .width(160.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.verdeApp)),
            shape = RoundedCornerShape(5.dp),
            enabled = isConfigEnable
        ) {
            Text(
                if (state is ResponseLoading) { "..." } else { "Guardar cambios" },
                color = colorResource(id = R.color.white)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserSettingScreen_Form(userSettingViewModel: UserSettingViewModel) {
    val fullname: String by userSettingViewModel.fullname.collectAsState()
    val username: String by userSettingViewModel.username.collectAsState()
    
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Nombre:", color = colorResource(R.color.grisclaro))
        Spacer(modifier = Modifier.size(10.dp))
        TextField(
            value = fullname,
            placeholder = { Text(text = "Nuevo nombre completo")},
            maxLines = 1,
            singleLine = true,
            onValueChange = { 
                userSettingViewModel.onConfigChanged(it, username) 
            },
            modifier = Modifier.width(300.dp).height(55.dp).align(Alignment.CenterHorizontally),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Gray,
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.size(20.dp))
        
        Text(text = "Nombre de usuario:", color = colorResource(R.color.grisclaro))
        Spacer(modifier = Modifier.size(10.dp))
        TextField(
            value = username,
            placeholder = { Text(text = "Nuevo nombre de usuario")},
            maxLines = 1,
            singleLine = true,
            onValueChange = {
                userSettingViewModel.onConfigChanged(fullname, it)
            },
            modifier = Modifier.width(300.dp).height(55.dp).align(Alignment.CenterHorizontally),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Gray,
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.size(20.dp))
        
        Text(text = "Posicion preferida:", color = colorResource(R.color.grisclaro))
        Spacer(modifier = Modifier.size(10.dp))
        UserSettingScreen_Dropdown(userSettingViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserSettingScreen_Dropdown(userSettingViewModel: UserSettingViewModel) {
    val coffeeDrinks = arrayOf("Lado derecho", "Lado izquierdo")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(coffeeDrinks[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            coffeeDrinks.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        selectedText = item
                        expanded = false
                        userSettingViewModel.changeSidePlay(index)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserSettingScreen_TopAppBar() {
    val navController = NavigationController.controller()
    
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = colorResource(id = R.color.gristop)),
        title = { 
            Text(
                text = "Ajustes",
                color = colorResource(id = R.color.white)
            )
        },
        navigationIcon = {
            IconButton(onClick = { 
                navController.navigate(Navigation.HOMEPLAYER_ROUTE)
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIosNew,
                    contentDescription = null,
                    tint = colorResource(id = R.color.verdeApp)
                )
            }
        },
        actions = {
            IconButton(onClick = {
                navController.navigate(Navigation.LOGIN_ROUTE)
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Logout,
                    contentDescription = null,
                    tint = colorResource(id = R.color.verdeApp)
                )
            }
        }
    )
}