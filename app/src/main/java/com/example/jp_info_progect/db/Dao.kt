package com.example.jp_info_progect.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jp_info_progect.utils.ListItem
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ListItem)
    @Delete
    suspend fun deleteItem(item: ListItem)
    @Query("SELECT * FROM main WHERE category LIKE :category")
    fun getAllItemsByCategory(category: String): Flow<List<ListItem>> // при использовании флоу при каждом изменении обновится отображение
    @Query("SELECT * FROM main WHERE isFavorit = 1")
    fun getFavorites(): Flow<List<ListItem>>
}