package com.example.jp_info_progect.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jp_info_progect.utils.ListItem

@Database(
    entities = [ListItem::class],
    version = 1
)
abstract class MainDb: RoomDatabase() {
}