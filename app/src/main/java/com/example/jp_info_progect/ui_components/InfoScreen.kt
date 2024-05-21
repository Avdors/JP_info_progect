package com.example.jp_info_progect.ui_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
                modifier = Modifier.fillMaxWidth()
                    .height(200.dp)
            )
            // здесь мы будем получать xml из assets и выводить его то есть выводить view

        }

    }
}


@Composable
fun HtmlLoader(htmlName: String){

}