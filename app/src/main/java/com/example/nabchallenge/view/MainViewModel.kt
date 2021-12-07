package com.example.nabchallenge.view

import androidx.lifecycle.viewModelScope
import com.example.nabchallenge.common.viewmodel.BaseViewModel
import com.example.nabchallenge.repository.Repository
import com.example.nabchallenge.utils.log.eLog
import com.example.nabchallenge.utils.log.wLog
import kotlinx.coroutines.launch

/***
 * Created by Khoa Nguyen on 01/11/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

class MainViewModel constructor(private val repository: Repository) : BaseViewModel(repository) {

    //region Variables

    //endregion

    fun getWeather() {
        viewModelScope.launch {
            val result = repository.getWeatherInfoList("saigon", 17)
            result.success?.let { weatherList -> wLog("Success=$weatherList") }
            result.error?.let { error -> eLog("Error=$error") }
        }
    }
}