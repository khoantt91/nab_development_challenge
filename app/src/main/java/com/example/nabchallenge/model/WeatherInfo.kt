package com.example.nabchallenge.model

import com.example.nabchallenge.utils.ifNotNull
import com.example.nabchallenge.utils.round

/***
 * Created by Khoa Nguyen on 07/12/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

data class WeatherInfo(
    val minTemp: Long? = null,
    val maxTemp: Long? = null,
    val pressure: Int? = null,
    val humidity: Int? = null,
    val description: String? = null,
    val cityName: String? = null,
    val createdTime: Long? = null                      // It's second unit not millisecond unit
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