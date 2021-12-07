package com.example.nabchallenge.repository.network.constant

object NetworkConstant {

    private val NETWORK_DEBUG_URL = "https://api.openweathermap.org/data/2.5/"

    // Base on environment will return corresponding url
    fun getNetworkUrl(): String = NETWORK_DEBUG_URL

    // Endpoint
    val FORECAST_ENDPOINT = "forecast/daily?appid=60c6fbeb4b93ac653c492ba806fc346d"

    val TIME_OUT = 60000L

}