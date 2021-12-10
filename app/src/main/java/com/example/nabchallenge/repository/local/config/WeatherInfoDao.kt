package com.example.nabchallenge.repository.local.config

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nabchallenge.model.WeatherInfo

/***
 * Created by Khoa Nguyen on 10/12/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

@Dao
interface WeatherInfoDao {

    @Query("SELECT * FROM weatherinfo WHERE citySearchName = :cityName ORDER BY createdTime ASC LIMIT :limit ")
    fun getAllByCitySearchName(cityName: String, limit: Int): List<WeatherInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(weatherInfoList: List<WeatherInfo>)

}