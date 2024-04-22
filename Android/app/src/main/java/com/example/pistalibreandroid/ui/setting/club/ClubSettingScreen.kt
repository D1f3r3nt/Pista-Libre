package com.example.pistalibreandroid.ui.setting.club

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.pistalibreandroid.R
import com.example.pistalibreandroid.model.ResponseError
import com.example.pistalibreandroid.model.ResponseLoading
import com.example.pistalibreandroid.model.ResponseOk
import com.example.pistalibreandroid.model.ResponseState
import com.example.pistalibreandroid.model.setting.club.CourtList
import com.example.pistalibreandroid.ui.navigation.Navigation
import com.example.pistalibreandroid.ui.navigation.NavigationController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClubSettingScreen(clubSettingViewModel: ClubSettingViewModel) {

    Scaffold(
        topBar = { ClubSettingScreen_TopAppBar() }
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(color = Color.Black)
                .padding(it)
        ) {
            ClubSettingScreen_Body(clubSettingViewModel)
        }
    }

}


@Composable
fun ClubSettingScreen_Body(clubSettingViewModel: ClubSettingViewModel) {
    val context = LocalContext.current
    val isConfigEnable: Boolean by clubSettingViewModel.isConfigEnable.collectAsState()
    val state: ResponseState by clubSettingViewModel.state.collectAsState()

    if (state is ResponseError) {
        Toast.makeText(context, (state as ResponseError).msg, Toast.LENGTH_LONG).show()
        clubSettingViewModel.resetState()
    }

    if (state is ResponseOk) {
        Toast.makeText(context, "Guardado", Toast.LENGTH_LONG).show()
        clubSettingViewModel.resetState()
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
                .fillMaxWidth()
                .height(100.dp)
                .background(colorResource(id = R.color.grisclaro))
        )

        Spacer(modifier = Modifier.size(10.dp))

        TextButton(onClick = {
            Toast.makeText(context, "Future events", Toast.LENGTH_LONG).show()
        }) {
            Text(text = "Cambiar foto")
        }

        ClubSettingScreen_Form(clubSettingViewModel)

        Spacer(modifier = Modifier.size(20.dp))

        Button(
            onClick = {
                clubSettingViewModel.postConfig()
            },
            modifier = Modifier
                .width(160.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.verdeApp)),
            shape = RoundedCornerShape(5.dp),
            enabled = isConfigEnable
        ) {
            Text(
                if (state is ResponseLoading) {
                    "..."
                } else {
                    "Guardar cambios"
                },
                color = colorResource(id = R.color.white)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClubSettingScreen_Form(clubSettingViewModel: ClubSettingViewModel) {
    val name: String by clubSettingViewModel.name.collectAsState()
    val direction: String by clubSettingViewModel.direction.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Nombre:", color = colorResource(R.color.grisclaro))
        Spacer(modifier = Modifier.size(10.dp))
        TextField(
            value = name,
            placeholder = { Text(text = "Nuevo nombre") },
            maxLines = 1,
            singleLine = true,
            onValueChange = {
                clubSettingViewModel.onConfigChanged(it, direction)
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

        Text(text = "Dirección:", color = colorResource(R.color.grisclaro))
        Spacer(modifier = Modifier.size(10.dp))
        TextField(
            value = direction,
            placeholder = { Text(text = "Nueva dirección") },
            maxLines = 1,
            singleLine = true,
            onValueChange = {
                clubSettingViewModel.onConfigChanged(name, it)
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = "Pistas:", color = colorResource(R.color.grisclaro))
            
            Button(
                onClick = { 
                    clubSettingViewModel.addNewCourt()
                },
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.verdeApp))
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
        ClubSettingScreen_Courts(clubSettingViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClubSettingScreen_Courts(clubSettingViewModel: ClubSettingViewModel) {
    val courts: List<CourtList> by clubSettingViewModel.courts.collectAsState()
    
    LazyColumn(
        state = rememberLazyListState(),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(courts) {index, court ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = court.number.toString(),
                    onValueChange = {
                        clubSettingViewModel.onCourtChange(index, it.toIntOrNull(), court.indoor, court.price)
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.width(100.dp)
                )
                Switch(checked = court.indoor, onCheckedChange = {
                    clubSettingViewModel.onCourtChange(index, court.number, !court.indoor, court.price)
                })
                TextField(
                    value = court.price.toString(),
                    onValueChange = {
                        clubSettingViewModel.onCourtChange(index, court.number, court.indoor, it.toFloatOrNull())
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.width(100.dp)
                )
                Button(
                    onClick = {
                        clubSettingViewModel.removeCourt(index)
                    },
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.width(70.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Remove, contentDescription = null)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClubSettingScreen_TopAppBar() {
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