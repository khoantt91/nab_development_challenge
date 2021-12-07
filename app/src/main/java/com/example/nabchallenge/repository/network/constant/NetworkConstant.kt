package com.example.nabchallenge.repository.network.constant

object NetworkConstant {

    private val NETWORK_DEBUG_URL = "http://dev-3cx-v1.k8s.propzy.asia"

    // Base on environment will return corresponding url
    fun getNetworkUrl(): String = NETWORK_DEBUG_URL

    // Endpoint
    val GET_DISTRICT_ENDPOINT = "/sam-dashboard/api/districts/1"
    val GET_DETAIL_DISTRICT_ENDPOINT = ""

    val TIME_OUT = 60000L

}