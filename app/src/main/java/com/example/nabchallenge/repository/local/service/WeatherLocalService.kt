package com.example.nabchallenge.repository.local.service

import com.example.nabchallenge.model.WeatherInfo
import com.example.nabchallenge.repository.local.model.LocalDataSource

/***
 * Created by Khoa Nguyen on 10/12/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

interface WeatherLocalService {

    suspend fun getAllWeatherInfo(cityName: String, limit: Int): LocalDataSource<List<WeatherInfo>>

    suspend fun insertAllWeatherInfo(weatherInfoList: List<WeatherInfo>)

}