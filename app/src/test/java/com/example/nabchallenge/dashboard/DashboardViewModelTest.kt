package com.example.nabchallenge.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nabchallenge.model.WeatherInfo
import com.example.nabchallenge.repository.Repository
import com.example.nabchallenge.repository.model.RepositoryDataSource
import com.example.nabchallenge.repository.model.RepositoryDataSourceError
import com.example.nabchallenge.utils.captureValues
import com.example.nabchallenge.view.dashboard.DashboardViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.kotlin.given
import org.robolectric.RobolectricTestRunner

/***
 * Created by Khoa Nguyen on 12/09/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class DashboardViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository
    private lateinit var dashboardViewModel: DashboardViewModel

    private val testCoroutineDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testCoroutineDispatcher)

    @Before
    fun setup() = runBlocking {
        repository = mock(Repository::class.java)
        dashboardViewModel = DashboardViewModel(repository)
    }

    @Test
    fun testLoadingStateWhenGetSuccess() {
        testScope.runTest {
            given(repository.getWeatherInfoList(anyString(), anyInt())).willReturn(RepositoryDataSource(emptyList(), null))
            dashboardViewModel.loadingProgressLiveData.captureValues {
                dashboardViewModel.searchWeatherInfo("saigon")
                assertSendsValues(100, false, true, false)
            }
        }
    }

    @Test
    fun testLoadingStateWhenGetError() {
        testScope.runTest {
            given(repository.getWeatherInfoList(anyString(), anyInt())).willReturn(RepositoryDataSource(null, RepositoryDataSourceError(100, "Error happened")))
            dashboardViewModel.loadingProgressLiveData.captureValues {
                dashboardViewModel.searchWeatherInfo("saigon")
                assertSendsValues(100, false, true, false)
            }
        }
    }

    @Test
    fun testWeatherListWhenGetSuccess() {
        testScope.runTest {
            given(repository.getWeatherInfoList(anyString(), anyInt())).willReturn(RepositoryDataSource(listOf(WeatherInfo()), null))
            dashboardViewModel.weatherListLiveData.captureValues {
                dashboardViewModel.searchWeatherInfo("saigon")
                val weatherList = this.values.first()
                assertThat(weatherList).isNotNull()
                assertThat(weatherList).isNotEmpty()
            }
        }
    }

    @Test
    fun testWeatherListWhenGetError() {
        testScope.runTest {
            given(repository.getWeatherInfoList(anyString(), anyInt())).willReturn(RepositoryDataSource(emptyList(), RepositoryDataSourceError(100, "Error happened")))
            dashboardViewModel.errorLiveData.captureValues {
                dashboardViewModel.searchWeatherInfo("saigon")
                val error = this.values.first()
                assertThat(error).isNotNull()
                assertThat(error?.code == 100)
            }
        }
    }

}