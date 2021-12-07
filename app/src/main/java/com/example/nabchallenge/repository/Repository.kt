package com.example.nabchallenge.repository

import com.example.nabchallenge.model.WeatherInfo
import com.example.nabchallenge.repository.model.RepositoryDataSource

interface Repository {

    suspend fun getWeatherInfoList(cityName: String, limit: Int): RepositoryDataSource<List<WeatherInfo>>

    suspend fun getCurrentToken(): RepositoryDataSource<String>

    suspend fun login(userName: String, password: String): RepositoryDataSource<Boolean>

    suspend fun logout(): RepositoryDataSource<Boolean>

}