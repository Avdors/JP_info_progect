package com.example.jp_info_progect.ui_components

import android.graphics.BitmapFactory
import android.media.audiofx.AudioEffect.Descriptor
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jp_info_progect.R
import com.example.jp_info_progect.ui.theme.Bg_transparent2
import com.example.jp_info_progect.utils.ListItem

// здесь макет единицы списка каталога, когда выбираем какой то знак открывается каталог
@Composable
fun MainListItem(item: ListItem, onClick: (ListItem)->Unit) { // передаем сюда элемент того списка ListItem
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(5.dp)
            .clickable { // здесь мы слушаем нажатие чтобы открыть INFO_SCREEN
                //выше в параметры функции мы добавим функцию onClick:
                onClick(item)
            },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        // контейнер Box чтобы мы могли расположить картинку и текст внизу
//        Box(modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.BottomCenter
//        ){
//            AssetImage(imageName = item.imageName,
//                contentDescriptor = item.title,
//                Modifier.fillMaxSize())
//            Text(text = item.title, //  здесь мы тогда пишем заголовок из Item
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color.Black)
//                    .padding(10.dp),
//                textAlign = TextAlign.Center,
//                fontWeight = FontWeight.Bold,
//                color = Color.White
//            )
//        }

        // заменяю на констрейнт, прописал под него зависимость в градле
                ConstraintLayout(modifier = Modifier.fillMaxSize(),

        ){
        // нам нужно придумать название для трех элементов,
        val (image, text, favoriteButton) = createRefs()

            AssetImage(imageName = item.imageName,
                contentDescriptor = item.title,
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    } //здесь даем название для констрейнта и все привязки
            )


            Text(text = item.title, //  здесь мы тогда пишем заголовок из Item
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(10.dp)
                    .constrainAs(text) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
                    
                    IconButton(onClick = { 
                        
                    },
                        modifier = Modifier
                            .constrainAs(favoriteButton){
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                        }) {
                        Icon(imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite",
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Bg_transparent2)
                                .padding(5.dp))
                        
                    }
        }

    }
}

// создаем свою функцию для обработки картинки
// и так же будем сюда передавать модифаер, чтобы можно было её переиспользовать
@Composable
fun AssetImage(imageName: String, contentDescriptor: String, modifier: Modifier){

    // нам нужен контекст чтобы обратится к папке assets
    val context = LocalContext.current
    val assetManager = context.assets
    val inputStream = assetManager.open(imageName)
    val bitMap = BitmapFactory.decodeStream(inputStream)  // декодирует данные из asset, чтобы получить картинку
    Image(bitmap = bitMap.asImageBitmap(),
        contentDescription = "Header image",
        modifier = modifier,
        contentScale = ContentScale.Crop,
        
    )
}
