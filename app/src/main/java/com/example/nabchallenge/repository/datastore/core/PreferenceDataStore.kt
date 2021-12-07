package com.example.nabchallenge.repository.datastore.core

import com.example.nabchallenge.repository.datastore.model.StoreDataSource

interface PreferenceDataStore {

    suspend fun saveToken(token: String)

    suspend fun getToken(): StoreDataSource<String>

    suspend fun deleteToken()

}