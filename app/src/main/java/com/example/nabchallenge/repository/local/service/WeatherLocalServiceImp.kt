package com.example.nabchallenge.repository.local.service

import com.example.nabchallenge.model.WeatherInfo
import com.example.nabchallenge.repository.local.config.WeatherInfoDao
import com.example.nabchallenge.repository.local.core.AppRoomCore
import com.example.nabchallenge.repository.local.model.LocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/***
 * Created by Khoa Nguyen on 10/12/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

class WeatherLocalServiceImp @Inject constructor(private val appRoomCore: AppRoomCore) : WeatherLocalService {

    private val weatherInfoDao by lazy { appRoomCore.retrieveAppDatabase().weatherInfoDao() }

    override suspend fun getAllWeatherInfo(cityName: String, limit: Int): LocalDataSource<List<WeatherInfo>> = withContext(Dispatchers.IO) {
        LocalDataSource(weatherInfoDao.getAllByCitySearchName(cityName, limit))
    }

    override suspend fun insertAllWeatherInfo(weatherInfoList: List<WeatherInfo>) = withContext(Dispatchers.IO) {
        return@withContext weatherInfoDao.insertAll(weatherInfoList)
    }

}