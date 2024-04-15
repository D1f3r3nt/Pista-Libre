package com.example.pistalibreandroid.ui.clubs

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pistalibreandroid.R
import com.example.pistalibreandroid.data.network.response.ClubsListResponse
import com.example.pistalibreandroid.ui.theme.robotoBold

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClubsScreen(viewModel: ClubsViewModel){
    val clubList by viewModel.clubsList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(bottomBar = { BottomBar()}) {
        if (isLoading){
            //Mostramos un indicador de carga
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
                CircularProgressIndicator()
            }
        }else {
            MainContent(clubList)
        }
    }
}

@Composable
fun MainContent(clubList: List<ClubsListResponse>){
    val colorVerde = colorResource(id = R.color.verdeApp)
    Column(modifier = Modifier
        .fillMaxSize()
        .background(colorVerde)) {
        TopBar()
        ClubsList(clubList)
    }
}

@Composable
fun ClubsList(clubList: List<ClubsListResponse>){
    Column(modifier = Modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
        .background(color = Color.Black)) {
        TextQuestion()
        FavoriteClubs(clubList)
        Spacer(modifier = Modifier.height(16.dp))
        LocationClubs(clubList)
    }
}

@Composable
fun TextQuestion(){
    Row(modifier = Modifier.padding(16.dp)) {
        Text(text = "¿Te apetece jugar hoy?",
            color = Color.White,
            fontSize = 24.sp,
            fontFamily = robotoBold,
            fontWeight = FontWeight.Bold)
    }
}

@Composable
fun FavoriteClubs(clubList: List<ClubsListResponse>){
    Column {
        Text(
            text = "Tus clubs favoritos",
            color = Color.White,
            fontFamily = robotoBold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
            items(clubList) { club ->
                ClubItem(club = club)
            }
        }
    }
}

@Composable
fun LocationClubs(clubList: List<ClubsListResponse>) {
    Column {
        Text(text = "Clubs más cercanos",
            color = Color.White,
            fontFamily = robotoBold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            items(clubList) { club ->
                ClubItem(club = club)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    val colorVerde = colorResource(id = R.color.verdeApp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorVerde)
            .padding(top = 50.dp, bottom = 10.dp, start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = "¡Hola, Pablo!",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            fontFamily = robotoBold,
            modifier = Modifier.weight(1f)  // Tomar espacio disponible manteniendo el ícono a la derecha
        )

        IconButton(
            onClick = {  }
        ) {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "Settings",
                tint = Color.Black  // Icono de color negro
            )
        }
    }
}

@Composable
fun BottomBar(){
    var index by remember {
        mutableStateOf(0)
    }
    val colorgrisicons = colorResource(id = R.color.grisicons)
    val colorVerde = colorResource(id = R.color.verdeApp)

    Box{

        NavigationBar(containerColor = Color.Black,contentColor = colorgrisicons, modifier = Modifier.padding(top = 2.dp)) {
            NavigationBarItem(selected = index == 0, onClick = { index = 0 }, icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "home"
                ) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorVerde,
                    unselectedIconColor = colorgrisicons,
                    indicatorColor = Color.Black
                ))
            NavigationBarItem(selected = index == 1, onClick = { index = 1 }, icon = { Icon(
                imageVector = Icons.Default.ChatBubble,
                contentDescription = "social"
            ) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorVerde,
                    unselectedIconColor = colorgrisicons,
                    indicatorColor = Color.Black
                ))
        }

        Divider(color = colorVerde, thickness = 1.dp, modifier = Modifier.fillMaxWidth())
    }


}

@Composable
fun ClubItem(club: ClubsListResponse) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .height(200.dp)
            .background(color = Color.Black),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.imageclub),
                contentDescription = "Club Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Text(
                text = club.name,
                color = Color.White,
                fontFamily = robotoBold,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewClubItem() {
    ClubItem(club = ClubsListResponse(1, "Club Olimpo", "Avenida andalucia cabra cordoba"))
}