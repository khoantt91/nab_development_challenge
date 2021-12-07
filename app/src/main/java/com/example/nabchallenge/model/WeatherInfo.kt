package com.example.nabchallenge.model

/***
 * Created by Khoa Nguyen on 07/12/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

data class WeatherInfo(
    val createdTime: Long,
    val minTemp: Long,
    val maxTemp: Long,
    val pressure: Int,
    val humidity: Int,
    val description: String
)