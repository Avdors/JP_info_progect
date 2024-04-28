package com.example.jp_info_progect

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jp_info_progect.ui.theme.JP_info_progectTheme
import com.example.jp_info_progect.ui_components.MainTopBar

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //для открытия дравер меню
            val scaffoldState = rememberScaffoldState()
            val topBarTitle = remember {
                mutableStateOf("Созвездия")
            }
            JP_info_progectTheme {
                Scaffold(scaffoldState = scaffoldState,
                topBar = {
                    MainTopBar(title = topBarTitle.value,
                    scaffoldState)
                }) {


                }
            }
        }
    }
}

