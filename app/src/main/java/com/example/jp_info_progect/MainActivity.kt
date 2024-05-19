package com.example.jp_info_progect

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jp_info_progect.repository.ItemRepository
import com.example.jp_info_progect.ui.theme.JP_info_progectTheme
import com.example.jp_info_progect.ui_components.DrawerMenu
import com.example.jp_info_progect.ui_components.MainListItem
import com.example.jp_info_progect.ui_components.MainTopBar
import com.example.jp_info_progect.utils.DrawerEvents
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //для открытия дравер меню
            val scaffoldState = rememberScaffoldState()
            //создаем эту корутину для того чтобы закрывать панель дравер меню
            val corutineScope = rememberCoroutineScope()
            // вот это для списка основного каталога
            val repository = ItemRepository()
            // это как раз в тот список, в который через Репозиторий, мы грузим список
            val mainList = remember {
                mutableStateOf(repository.getListItemsByIndex(0, this))
            }
            //


            val topBarTitle = remember {
                mutableStateOf("Aries")
            }
            JP_info_progectTheme {
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
                                mainList.value = repository.getListItemsByIndex(event.index, this@MainActivity)
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
                                MainListItem(item = item)
                            }
                        }
                }
            }
        }
    }
}

