package com.example.jp_info_progect.ui_components

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.jp_info_progect.utils.ListItem


@Composable
fun InfoScreen(item: ListItem) {

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
        ) {
    // переиспользуем AssetImage что мы сделали в MainListItem
            // передадим картинку из item ту же самую

            AssetImage(
                imageName = item.imageName,
                contentDescriptor = item.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            // здесь мы будем получать xml из assets и выводить его то есть выводить view
            // и для этого в ListItem мы еще добавим htmlName
            HtmlLoader(htmlName = item.htmlName)
        }

    }
}

// в этой функции будем получать нужный файл из папки ассетс
@Composable
fun HtmlLoader(htmlName: String){
    // нам нужен контекст чтобы обратится к папке assets
    val context = LocalContext.current
    val assetManager = context.assets
    val inputStream = assetManager.open("html/$htmlName")
    // нам нужно узнать размер инпутстрима
    val size = inputStream.available()
    val buffer = ByteArray(size)
    // читаем в массив буфер файл
    inputStream.read(buffer)

    val htmlString = String(buffer)
    AndroidView(factory = {
        WebView(it).apply {
            webViewClient = WebViewClient()
            loadData(htmlString, "text/html", "utf-8")
        } //factory дает нам контекст it
    })

}