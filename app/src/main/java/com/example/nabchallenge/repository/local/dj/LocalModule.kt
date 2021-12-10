package com.example.nabchallenge.repository.local.dj

import android.content.Context
import androidx.room.Room
import com.example.nabchallenge.repository.local.constant.LocalConstant
import com.example.nabchallenge.repository.local.config.AppDatabase
import com.example.nabchallenge.repository.local.config.WeatherInfoDao
import com.example.nabchallenge.repository.local.service.WeatherLocalService
import com.example.nabchallenge.repository.local.service.WeatherLocalServiceImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/***
 * Created by Khoa Nguyen on 10/12/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

@Module
class LocalModule {

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, LocalConstant.NAME_DATABASE)
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideWeatherInfoDao(appDatabase: AppDatabase): WeatherInfoDao = appDatabase.weatherInfoDao()

    @Singleton
    @Provides
    fun provideWeatherLocalService(weatherInfoDao: WeatherInfoDao): WeatherLocalService = WeatherLocalServiceImp(weatherInfoDao)

}