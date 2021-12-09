package com.example.nabchallenge.view.dashboard.state

import com.example.nabchallenge.model.AppError
import com.example.nabchallenge.model.WeatherInfo

/***
 * Created by Khoa Nguyen on 09/12/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

data class GetWeatherState(
    var isLoading: Boolean = false,
    var weatherList: List<WeatherInfo>? = null,
    var error: AppError? = null
)
