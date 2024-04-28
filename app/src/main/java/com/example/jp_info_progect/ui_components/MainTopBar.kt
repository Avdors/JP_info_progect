package com.example.jp_info_progect.ui_components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainTopBar(title: String, scaffoldState: ScaffoldState) {
//меню мы можем открыть только черз корутину
    val corutine = rememberCoroutineScope()
    TopAppBar (
        title = {
            Text(text = title)
        },
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
        }
            )

}