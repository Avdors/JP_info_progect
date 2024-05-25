package com.example.jp_info_progect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jp_info_progect.ui.theme.JP_info_progectTheme
import com.example.jp_info_progect.ui_components.InfoScreen
import com.example.jp_info_progect.ui_components.MainScreen
import com.example.jp_info_progect.utils.Constant
import com.example.jp_info_progect.utils.ListItem
import dagger.hilt.android.AndroidEntryPoint

// ctrl + ait + o убрать импорт
//так как в этом классе мы используем зависимости то ставим @AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // создадим здесь контроллер для переключения экранов
            val navController = rememberNavController()
            // заводим этот item чтобы в него складывать значение выбранного в Main Sreen itema
            var item: ListItem? = null
            JP_info_progectTheme {
                // переключаем экраны внутри новхост, передаем в него контроллер и экран по умолчанию - startDestination
                NavHost(navController = navController, startDestination = Constant.MAIN_SCREEN ){
                    composable(Constant.MAIN_SCREEN){
                        MainScreen{listItem -> // вот здесь мы получаем тот листайтем
                            //сохраняем item нажатый в переменную
                            item = listItem
                            // и наконец мы запускаем ИНФО СКРИН
                            navController.navigate(Constant.INFO_SCREEN)

                        }
                        //при нажатии на элемент мы должны открывать INFO_SREEN для этого идем в наш список
                        //MainListItem и в нём добавляем слушатель нажатий
                    }
                    composable(Constant.INFO_SCREEN){
                        InfoScreen(item = item as ListItem)
                    }
                }

            }
        }
    }
}

