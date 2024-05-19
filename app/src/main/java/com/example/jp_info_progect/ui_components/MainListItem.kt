package com.example.jp_info_progect.ui_components

import android.graphics.BitmapFactory
import android.media.audiofx.AudioEffect.Descriptor
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jp_info_progect.R
import com.example.jp_info_progect.utils.ListItem

// здесь макет единицы списка каталога, когда выбираем какой то знак открывается каталог
@Composable
fun MainListItem(item: ListItem) { // передаем сюда элемент того списка ListItem
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
            AssetImage(imageName = item.imageName,
                contentDescriptor = item.title)
            Text(text = item.title, //  здесь мы тогда пишем заголовок из Item
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

// создаем свою функцию для обработки картинки
@Composable
fun AssetImage(imageName: String, contentDescriptor: String){

    // нам нужен контекст чтобы обратится к папке assets
    val context = LocalContext.current
    val assetManager = context.assets
    val inputStream = assetManager.open(imageName)
    val bitMap = BitmapFactory.decodeStream(inputStream)  // декодирует данные из asset, чтобы получить картинку
    Image(bitmap = bitMap.asImageBitmap(),
        contentDescription = "Header image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        
    )
}
