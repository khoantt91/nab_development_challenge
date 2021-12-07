package com.example.nabchallenge.repository.network.service

import com.example.nabchallenge.model.WeatherInfo
import com.example.nabchallenge.repository.network.constant.NetworkConstant
import com.example.nabchallenge.repository.network.core.NetworkCore
import com.example.nabchallenge.repository.network.model.Method
import com.example.nabchallenge.repository.network.model.NetworkDataSource
import javax.inject.Inject

class WeatherServiceImp @Inject constructor(private val networkCore: NetworkCore) : WeatherService {

    override suspend fun getWeatherInfoList(cityName: String, limit: Int): NetworkDataSource<List<WeatherInfo>> {
        return networkCore.makeCall(NetworkConstant.FORECAST_ENDPOINT + "&q=$cityName&cnt=$limit", Method.GET)
    }

}