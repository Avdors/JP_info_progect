package com.example.jp_info_progect

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jp_info_progect.db.MainDb
import com.example.jp_info_progect.utils.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//он нам нужен чтобы мы с любого экрана получали доступ к базе данных

//аннотация для инъекции базы данных
@HiltViewModel
class MainViewModel @Inject constructor(
    val mainDb: MainDb
): ViewModel() {

    val mainList = mutableStateOf(emptyList<ListItem>())
    fun gettAllitemByCategory(cat: String) = viewModelScope.launch {
        mainList.value = mainDb.dao.getAllItemsByCategory(cat)
    }
    fun insertItem(item: ListItem) = viewModelScope.launch {
        mainDb.dao.insertItem(item)
    }
    fun deleteItem(item: ListItem) = viewModelScope.launch {
        mainDb.dao.deleteItem(item)
    }
}