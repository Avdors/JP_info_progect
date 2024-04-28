package com.example.jp_info_progect.ui_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DrawerMenu() {
}

@Composable
fun Header() {
    // modifier = Modifier.fillMaxWidth() - начит что займет всю ширину элемента
    Card(
        modifier = Modifier.fillMaxWidth()
            .height(170.dp)
            .padding(5.dp)
    ) {
        
    }
}