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
    mainViewModel: MainViewModel = hiltViewModel(), // для того чтобы обработать клик по избраному
    title: String,
    scaffoldState: ScaffoldState) {
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
            }) {
                Icon(imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite"
                ) //картинка кнопки
            }
        }
            )

}