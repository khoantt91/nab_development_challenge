package com.example.nabchallenge.repository.datastore.core

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.nabchallenge.repository.datastore.constant.DataStoreConstant
import com.example.nabchallenge.repository.datastore.model.StoreDataSource
import com.example.nabchallenge.utils.security.KeystoreCrypto
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferenceDataStoreImp @Inject constructor(private val context: Context, private val keystoreUtils: KeystoreCrypto) : PreferenceDataStore {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DataStoreConstant.DATA_STORE_NAME)

    override suspend fun saveToken(token: String) {
        val encryptedToken = keystoreUtils.encryptData(DataStoreConstant.ALIAS_KEY_STORE, token) ?: return
        context.dataStore.edit {
            it[DataStoreConstant.PreferenceKey.TOKEN_KEY] = encryptedToken
        }
        return
    }

    override suspend fun getToken(): StoreDataSource<String> {
        val encryptedToken = context.dataStore.data.map {
            it[DataStoreConstant.PreferenceKey.TOKEN_KEY]
        }.first() ?: return StoreDataSource(null, null)
        return StoreDataSource(keystoreUtils.decryptData(DataStoreConstant.ALIAS_KEY_STORE, encryptedToken))
    }

    override suspend fun deleteToken() {
        context.dataStore.edit {
            it[DataStoreConstant.PreferenceKey.TOKEN_KEY] = ""
        }
    }
}