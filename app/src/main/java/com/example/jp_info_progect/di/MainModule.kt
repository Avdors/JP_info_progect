package com.example.jp_info_progect.di

import android.app.Application
import androidx.room.Room
import com.example.jp_info_progect.db.MainDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


//этот модуль нужен нам для инициализации базы данных, её создания

//аннотация для дагер hilt
@Module
//означает что он будет создан в аппликатион и доступен во всем приложении
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    //одна и та же инстанция на протяжении жизни всего приложения
    // и мы делаем именно так, потомучто MainDb это абстрактный класс, нам нужно вначале создать файл БД
    // app: Application в параметрах нам нужен для контекста
    @Singleton
    fun provideMainDb(app: Application): MainDb{
        return Room.databaseBuilder(
            app,
            MainDb::class.java,
            "info.db"
        ).createFromAsset("db/info.db").build()
        // тут мы используем createFromAsset чтобы база при запуске приложения уже была на пустая а заполнялась из файла подготовленного
        // который лежит в asset
    }
}