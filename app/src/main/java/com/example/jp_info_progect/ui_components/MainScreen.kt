package com.example.jp_info_progect.ui_components

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.jp_info_progect.repository.ItemRepository
import com.example.jp_info_progect.utils.DrawerEvents
import com.example.jp_info_progect.utils.ListItem
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
// и здесь прокидываем эту функцию onClick: (ListItem)->Unit, чтобы нам добраться до MainActivity
fun MainScreen(context: Context, onClick: (ListItem)->Unit) {
    //для открытия дравер меню
    val scaffoldState = rememberScaffoldState()
    //создаем эту корутину для того чтобы закрывать панель дравер меню
    val corutineScope = rememberCoroutineScope()
    // вот это для списка основного каталога
    val repository = ItemRepository()
    // это как раз в тот список, в который через Репозиторий, мы грузим список
    val mainList = remember {
        mutableStateOf(repository.getListItemsByIndex(0, context))
    }
    //
    val topBarTitle = remember {
        mutableStateOf("Aries")
    }
    Scaffold(scaffoldState = scaffoldState,
        topBar = {
            MainTopBar(title = topBarTitle.value,
                scaffoldState)
        },
        drawerContent = {
            // и здесь мы реализуем передачу этого события, то что начали в DrawerEvents
            // и здесь внутри мы определяем какое конкретно событие
            DrawerMenu(){ event ->
                when(event){
                    // если это событие то запускаем код в фиг скобках, возьмем и изменим title
                    // здесь мы реализуем предачу индекса при нажатии кнопки в дравер меню
                    is DrawerEvents.OnItemClick -> {
                        topBarTitle.value = event.title
                        // так как мы внутри ColumnScope то к контексту вот так обращаемся
                        mainList.value = repository.getListItemsByIndex(event.index, context)
                    }
                }
                // запускаем корутину, чтобы внутри нее
                corutineScope.launch {
                    scaffoldState.drawerState.close()
                }
            } // здесь мы добавляем наше созданное меню
        }
    ) { //здесь расположен список основной каталог на странице
        // вот он будет запускаться много раз и будет рисоваться список
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            // сюда нужно передать список
            items(mainList.value){ item ->
                // обавляем здесь обработку нажатия по item
                MainListItem(item = item){ listItem ->

                    //снова вызываем функцию что в параметрах
                    onClick(listItem)
                }
            }
        }
    }
}