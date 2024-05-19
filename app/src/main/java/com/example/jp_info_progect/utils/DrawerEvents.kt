package com.example.jp_info_progect.utils


// мы собираем здесь разные дата классы, но в событии нажатии по кнопке передаем общий DrawerEvents
sealed class DrawerEvents {
    data class OnItemClick(val title: String, val index: Int): DrawerEvents()
}