package com.example.jp_info_progect.ui_components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jp_info_progect.R


@Composable
fun DrawerMenu() {
}

@Composable
fun Header() {
    // modifier = Modifier.fillMaxWidth() - начит что займет всю ширину элемента
    // shape - здесь реализуем закругление углов
    // border - рамка вокруг элемента
    // contentScale - здесь можем указать то что нужно подогнать картинку к элементу (Crop сохранит пропорции но заполнит все пространство)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        // контейнер Box чтобы мы могли расположить картинку и текст внизу
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
            ){
                Image(painter = painterResource(id = R.drawable.header_bg),
                    contentDescription = "Header image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Text(text = "Constellations",
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(10.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                    )
        }
    }
}

@Composable
fun Body(){
    val list = stringArrayResource(id = R.array.drawer_list)
    // для создания списка
    LazyColumn(modifier = Modifier.fillMaxSize()){
        itemsIndexed(list){ index, title ->
            Card(modifier = Modifier.fillMaxWidth()
                .padding(3.dp),
                backgroundColor = Color.Transparent
            ) {
                Text(text = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { // это слушатель нажатий

                        }
                        .padding(10.dp)
                        .wrapContentWidth(), // ставит текст по центру
                    fontWeight = FontWeight.Bold,

                    )
            }

        }
    }
}