package com.example.nabchallenge.repository.local.core

import android.content.Context
import androidx.room.Room
import com.example.nabchallenge.repository.datastore.core.PreferenceDataStore
import com.example.nabchallenge.repository.local.config.AppDatabase
import com.example.nabchallenge.repository.local.constant.LocalConstant
import kotlinx.coroutines.*
import net.sqlcipher.database.SupportFactory
import kotlin.coroutines.CoroutineContext

/***
 * Created by Khoa Nguyen on 13/12/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

class AppRoomCoreImp(private val context: Context, private val preferenceDataStore: PreferenceDataStore) : AppRoomCore, CoroutineScope {

    override val coroutineContext: CoroutineContext = Job() + Dispatchers.IO

    private lateinit var appDatabase: AppDatabase

    init {
        // Config room database
        launch {
            val passphrase = preferenceDataStore.getRoomKey().success
            appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, LocalConstant.NAME_DATABASE)
                .fallbackToDestructiveMigration()
                .openHelperFactory(SupportFactory(passphrase))
                .build()
        }
    }

    override fun retrieveAppDatabase(): AppDatabase = appDatabase

}