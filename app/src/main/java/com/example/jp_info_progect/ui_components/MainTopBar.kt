package com.example.jp_info_progect.ui_components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jp_info_progect.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun MainTopBar(
    title: String,
    scaffoldState: ScaffoldState,
 //   mainViewModel: MainViewModel = hiltViewModel(), // для того чтобы обработать клик по избраному
    onFavClick: ()-> Unit // чтобы при нажатии в MainScreen эту функцию дергать и менять заголовок и еще её вызвать ниже здесь в процедуре нажатия IconButton(onClick
     ) {
//меню мы можем открыть только черз корутину
    val corutine = rememberCoroutineScope()
    TopAppBar (
        title = {
            Text(text = title)
        },
        backgroundColor = Color.White,
        navigationIcon = {  // кнопка открытия дравер меню
           IconButton(onClick = {
                corutine.launch {
                    scaffoldState.drawerState.open() // вот здесь непосредственно открываем меню
                }
           }) {
                Icon(imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                ) //картинка кнопки
           }
        }, // actions - добавляем еще кнопку с избранным
        actions = {
            IconButton(onClick = {
//                corutine.launch {
//                    scaffoldState.drawerState.open()
//                }
             //   mainViewModel.gettFavorites() //сделали это в MainScreen
                onFavClick()
            }) {
                Icon(imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite"
                ) //картинка кнопки
            }
        }
            )

}