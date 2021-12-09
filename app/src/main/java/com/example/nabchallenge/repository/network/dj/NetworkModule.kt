@file:Suppress("unused")

package com.example.nabchallenge.repository.network.dj

import android.content.Context
import com.example.nabchallenge.repository.network.constant.NetworkConstant
import com.example.nabchallenge.repository.network.core.NetworkCore
import com.example.nabchallenge.repository.network.core.NetworkService
import com.example.nabchallenge.repository.network.core.interceptor.NetworkConnectionInterceptor
import com.example.nabchallenge.repository.network.service.WeatherService
import com.example.nabchallenge.repository.network.service.WeatherServiceImp
import com.example.nabchallenge.utils.log.wLog
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor { mes -> wLog(mes) }.apply {
        // Config Http Logging Interceptor
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideNetworkConnectionInterceptor(context: Context): NetworkConnectionInterceptor = NetworkConnectionInterceptor(context)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        context: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        networkConnectionInterceptor: NetworkConnectionInterceptor
    ): OkHttpClient = OkHttpClient().newBuilder().apply {
        // Config OkHttp
        readTimeout(NetworkConstant.TIME_OUT, TimeUnit.MILLISECONDS)
        writeTimeout(NetworkConstant.TIME_OUT, TimeUnit.MILLISECONDS)
        addInterceptor(ChuckInterceptor(context))
        addInterceptor(httpLoggingInterceptor)
        addInterceptor(networkConnectionInterceptor)
    }.build()

    @Singleton
    @Provides
    fun provideGsonConverter(): GsonConverterFactory {
        // Config gson converter
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gsonConverter: GsonConverterFactory): Retrofit = Retrofit.Builder().baseUrl(NetworkConstant.getNetworkUrl())
        .addConverterFactory(gsonConverter)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideNetworkService(retrofit: Retrofit): NetworkService = retrofit.create(NetworkService::class.java)

    @Singleton
    @Provides
    fun provideNetworkCore(networkService: NetworkService): NetworkCore = NetworkCore(networkService)

    @Singleton
    @Provides
    fun provideWeatherService(networkCore: NetworkCore): WeatherService = WeatherServiceImp(networkCore)

}