package com.example.nabchallenge.view.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nabchallenge.common.viewmodel.BaseViewModel
import com.example.nabchallenge.model.AppError
import com.example.nabchallenge.model.WeatherInfo
import com.example.nabchallenge.repository.Repository
import kotlinx.coroutines.Job
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

/***
 * Created by Khoa Nguyen on 12/08/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

class DashboardViewModel constructor(private val repository: Repository) : BaseViewModel(repository) {

    //region Variables
    @Volatile
    var loadingProgressLiveData = MutableLiveData<Boolean>(false)
    var weatherListLiveData = MutableLiveData<List<WeatherInfo>>()
    var errorLiveData = MutableLiveData<AppError>()

    private var keySearch: String = ""
    private val limit = 17
    //endregion

    fun searchWeatherInfo(key: String) = viewModelScope.launch {
        if (key == keySearch || key.isEmpty() || loadingProgressLiveData.value == true) return@launch
        loadingProgressLiveData.postValue(true)
        keySearch = key

        val result = repository.getWeatherInfoList(keySearch, limit)

        result.error?.let { error ->
            ensureActive()
            errorLiveData.postValue(error)

            weatherListLiveData.postValue(emptyList())
        }

        result.success?.let { weatherList ->
            ensureActive()
            weatherListLiveData.postValue(weatherList)
        }

        loadingProgressLiveData.postValue(false)
    }

    fun clickWeatherInfo(weatherInfo: WeatherInfo) {

    }

    fun logout(): MutableLiveData<Boolean> {
        val complete = MutableLiveData<Boolean>()
        viewModelScope.launch {
            val result = repository.logout()
            result.success?.let { complete.postValue(true) }
        }
        return complete
    }

}