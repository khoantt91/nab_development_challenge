package com.example.nabchallenge.repository.datastore.core

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.nabchallenge.repository.datastore.constant.DataStoreConstant
import com.example.nabchallenge.repository.datastore.model.StoreDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferenceDataStoreImp @Inject constructor(private val context: Context) : PreferenceDataStore {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DataStoreConstant.DATA_STORE_NAME)

    override suspend fun saveToken(token: String) {
        context.dataStore.edit {
            it[DataStoreConstant.PreferenceKey.TOKEN_KEY] = token
        }
    }

    override suspend fun getToken(): StoreDataSource<String> = context.dataStore.data.map {
        StoreDataSource(it[DataStoreConstant.PreferenceKey.TOKEN_KEY])
    }.first()

    override suspend fun deleteToken() {
        context.dataStore.edit {
            it[DataStoreConstant.PreferenceKey.TOKEN_KEY] = ""
        }
    }
}