package com.example.nabchallenge.repository.network.service

import com.example.nabchallenge.model.WeatherInfo
import com.example.nabchallenge.repository.network.model.NetworkDataSource

interface WeatherService {
    suspend fun getWeatherInfoList(cityName: String, limit: Int = 20): NetworkDataSource<List<WeatherInfo>>
}