package com.example.jp_info_progect.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jp_info_progect.utils.ListItem


@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ListItem)
    @Delete
    suspend fun deleteItem(item: ListItem)
    @Query("SELECT * FROM main WHERE category LIKE :category")
    suspend fun getAllItemsByCategory(category: String): List<ListItem>
}