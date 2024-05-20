package com.example.jp_info_progect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.jp_info_progect.ui.theme.JP_info_progectTheme
import com.example.jp_info_progect.ui_components.MainScreen
// ctrl + ait + o убрать импорт
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            JP_info_progectTheme {
                MainScreen(context = this)
            }
        }
    }
}

