package com.example.nabchallenge.repository

import android.content.Context
import android.text.format.DateUtils
import com.example.nabchallenge.App
import com.example.nabchallenge.model.WeatherInfo
import com.example.nabchallenge.repository.datastore.core.PreferenceDataStore
import com.example.nabchallenge.repository.local.service.WeatherLocalService
import com.example.nabchallenge.repository.model.RepositoryDataSource
import com.example.nabchallenge.repository.model.toRepositoryData
import com.example.nabchallenge.repository.network.service.WeatherService
import javax.inject.Inject

/***
 *   Repository has responsibility for executing logic of data source.
 *   For example: get data from server, save data to local or get data from local etc.....
 */
class RepositoryImp @Inject constructor(context: Context) : Repository {

    @Inject
    lateinit var weatherService: WeatherService

    @Inject
    lateinit var weatherLocalService: WeatherLocalService

    @Inject
    lateinit var preferenceDataStore: PreferenceDataStore

    init {
        (context as App).appComponent.inject(this)
    }

    override suspend fun getWeatherInfoList(cityName: String, limit: Int): RepositoryDataSource<List<WeatherInfo>> {

        val resultLocal = weatherLocalService.getAllWeatherInfo(cityName, limit)
        resultLocal.success?.firstOrNull()?.createdTime?.let {
            if (DateUtils.isToday(it)) return resultLocal.toRepositoryData()
        }

        val result = weatherService.getWeatherInfoList(cityName, limit).toRepositoryData()
        if (result.error != null) return result
        result.success?.let { weatherList ->
            weatherList.onEach { it.citySearchName = cityName }
            weatherLocalService.insertAllWeatherInfo(weatherList)
        }

        return weatherLocalService.getAllWeatherInfo(cityName, limit).toRepositoryData()
    }

    override suspend fun getCurrentToken(): RepositoryDataSource<String> = preferenceDataStore.getToken().toRepositoryData()

    override suspend fun login(userName: String, password: String): RepositoryDataSource<Boolean> {
        // Assuming login user flow
        preferenceDataStore.saveToken("$userName-$password")
        return RepositoryDataSource(true)
    }

    override suspend fun logout(): RepositoryDataSource<Boolean> {
        // Assuming login user flow
        preferenceDataStore.deleteToken()
        return RepositoryDataSource(true)
    }

}