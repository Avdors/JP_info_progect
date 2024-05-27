package com.example.jp_info_progect

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jp_info_progect.db.MainDb
import com.example.jp_info_progect.utils.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

//он нам нужен чтобы мы с любого экрана получали доступ к базе данных

//аннотация для инъекции базы данных
@HiltViewModel
class MainViewModel @Inject constructor(
    val mainDb: MainDb
): ViewModel() {

    val mainList = mutableStateOf(emptyList<ListItem>())
    // для того чтобы прослушивать и отменять подписку на Flow
    private var job: Job? = null
    fun gettAllitemByCategory(cat: String) {
        job?.cancel() //если job не null то завершаем его перед использованием
        job = viewModelScope.launch {
            // здесь переделываем под получение flow и мы подписываемся только либо на job c основными элементами, либо с избранным
            mainDb.dao.getAllItemsByCategory(cat).collect{list->
                mainList.value = list

            }
        }
    }
    fun gettFavorites(){
        job?.cancel() //если job не null то завершаем его перед использованием
        job = viewModelScope.launch {
            // здесь переделываем под получение flow
            mainDb.dao.getFavorites().collect{list->
                mainList.value = list
            }
        }
    }
    fun insertItem(item: ListItem) = viewModelScope.launch {
        mainDb.dao.insertItem(item)
    }
    fun deleteItem(item: ListItem) = viewModelScope.launch {
        mainDb.dao.deleteItem(item)
    }
}