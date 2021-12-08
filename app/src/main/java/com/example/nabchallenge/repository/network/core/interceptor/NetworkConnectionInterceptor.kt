package com.example.nabchallenge.repository.network.core.interceptor

import android.content.Context
import android.net.ConnectivityManager
import com.example.nabchallenge.repository.network.core.exception.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response


/***
 * Created by Khoa Nguyen on 08/12/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

class NetworkConnectionInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected()) throw NoConnectivityException()
        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }

}