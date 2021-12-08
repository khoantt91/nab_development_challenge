package com.example.nabchallenge.model

import com.example.nabchallenge.utils.ifNotNull
import com.example.nabchallenge.utils.round

/***
 * Created by Khoa Nguyen on 07/12/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

data class WeatherInfo(
    val minTemp: Long?,
    val maxTemp: Long?,
    val pressure: Int?,
    val humidity: Int?,
    val description: String?,
    val createdTime: Long?                      // It's second unit not millisecond unit
) {

    fun getAverageTemp(): Int? {
        ifNotNull(minTemp, maxTemp) { min, max ->
            return ((min + max) / 2).toInt()
        }
        return null
    }

    fun compareForDashboardAdapter(other: WeatherInfo): Boolean {
        if (other.minTemp != this.minTemp) return false
        if (other.maxTemp != this.maxTemp) return false
        if (other.pressure != this.pressure) return false
        if (other.humidity != this.humidity) return false
        if (other.description != this.description) return false
        if (other.createdTime != this.createdTime) return false
        return true
    }
}