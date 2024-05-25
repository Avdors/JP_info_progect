package com.example.jp_info_progect.repository

import android.content.Context
import com.example.jp_info_progect.utils.IdArrayList
import com.example.jp_info_progect.utils.ListItem

// здесь мы реализуем заполнение списка по индексу выранного элемента в дравер меню
class ItemRepository() {

    fun getListItemsByIndex(index: Int, context: Context): List<ListItem>{

    val list = ArrayList<ListItem>()
        val arrayList = context.resources.getStringArray(IdArrayList.listId[index])
        arrayList.forEach { item ->
            val itemArray = item.split("|")
//            list.add(
//                ListItem(
//                    itemArray[0],
//                    itemArray[1],
//                    itemArray[2]
//                )
//            )
        }
        return list
    }

}