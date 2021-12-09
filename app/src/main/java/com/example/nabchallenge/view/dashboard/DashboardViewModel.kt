package com.example.nabchallenge.view.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nabchallenge.common.viewmodel.BaseViewModel
import com.example.nabchallenge.model.WeatherInfo
import com.example.nabchallenge.repository.Repository
import com.example.nabchallenge.view.dashboard.state.GetWeatherState
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
    var getWeatherStateLive = MutableLiveData(GetWeatherState())

    private var keySearch: String = ""
    private val limit = 17
    //endregion

    fun searchWeatherInfo(key: String) = viewModelScope.launch {
        val dashboardState = getWeatherStateLive.value ?: return@launch
        if (key.isEmpty() || dashboardState.isLoading) return@launch
        getWeatherStateLive.postValue(dashboardState.copy(isLoading = true))
        keySearch = key

        val result = repository.getWeatherInfoList(keySearch, limit)

        result.error?.let { error ->
            ensureActive()
            getWeatherStateLive.postValue(dashboardState.copy(isLoading = false, weatherList = emptyList(), error = error))
        }

        result.success?.let { weatherList ->
            ensureActive()
            getWeatherStateLive.postValue(dashboardState.copy(isLoading = false, weatherList, null))
        }
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