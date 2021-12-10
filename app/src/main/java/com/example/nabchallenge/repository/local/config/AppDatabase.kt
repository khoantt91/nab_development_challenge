package com.example.nabchallenge.repository.local.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nabchallenge.model.WeatherInfo

/***
 * Created by Khoa Nguyen on 10/12/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */
@Database(entities = [WeatherInfo::class], version = 3)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherInfoDao(): WeatherInfoDao

}