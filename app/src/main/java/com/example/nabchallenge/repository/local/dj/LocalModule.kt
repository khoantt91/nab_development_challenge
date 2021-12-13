package com.example.nabchallenge.repository.local.dj

import android.content.Context
import androidx.room.Room
import com.example.nabchallenge.repository.datastore.core.PreferenceDataStore
import com.example.nabchallenge.repository.local.constant.LocalConstant
import com.example.nabchallenge.repository.local.config.AppDatabase
import com.example.nabchallenge.repository.local.config.WeatherInfoDao
import com.example.nabchallenge.repository.local.core.AppRoomCore
import com.example.nabchallenge.repository.local.core.AppRoomCoreImp
import com.example.nabchallenge.repository.local.service.WeatherLocalService
import com.example.nabchallenge.repository.local.service.WeatherLocalServiceImp
import dagger.Module
import dagger.Provides
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

/***
 * Created by Khoa Nguyen on 10/12/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

@Module
class LocalModule {

    @Singleton
    @Provides
    fun provideAppRoomCore(context: Context, preferenceDataStore: PreferenceDataStore): AppRoomCore {
        return AppRoomCoreImp(context, preferenceDataStore)
    }

    @Singleton
    @Provides
    fun provideWeatherLocalService(appRoomCore: AppRoomCore): WeatherLocalService = WeatherLocalServiceImp(appRoomCore)

}