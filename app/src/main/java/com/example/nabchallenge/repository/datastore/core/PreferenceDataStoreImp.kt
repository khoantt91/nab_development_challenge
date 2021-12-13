package com.example.nabchallenge.repository.datastore.core

import android.content.Context
import android.os.Build
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.nabchallenge.repository.datastore.constant.DataStoreConstant
import com.example.nabchallenge.repository.datastore.model.StoreDataSource
import com.example.nabchallenge.utils.security.KeystoreCrypto
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.security.SecureRandom
import javax.inject.Inject

class PreferenceDataStoreImp @Inject constructor(private val context: Context, private val keystoreUtils: KeystoreCrypto) : PreferenceDataStore {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DataStoreConstant.DATA_STORE_NAME)

    override suspend fun getRoomKey(): StoreDataSource<ByteArray> {
        var encryptedRoomKey = context.dataStore.data.map { it[DataStoreConstant.PreferenceKey.ROOM_KEY] }.firstOrNull()
        if (encryptedRoomKey == null || encryptedRoomKey.isEmpty()) {
            // Generate a new key (need implement error handler ...)
            encryptedRoomKey = keystoreUtils.encryptData(DataStoreConstant.ALIAS_KEY_STORE, generateRandomKey()) ?: ""
            context.dataStore.edit { it[DataStoreConstant.PreferenceKey.ROOM_KEY] = encryptedRoomKey }
        }
        return StoreDataSource(keystoreUtils.decryptData(DataStoreConstant.ALIAS_KEY_STORE, encryptedRoomKey)?.toByteArray())
    }

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
        }.firstOrNull() ?: return StoreDataSource(null, null)
        return StoreDataSource(keystoreUtils.decryptData(DataStoreConstant.ALIAS_KEY_STORE, encryptedToken))
    }

    override suspend fun deleteToken() {
        context.dataStore.edit {
            it[DataStoreConstant.PreferenceKey.TOKEN_KEY] = ""
        }
    }

    //region Private Support Methods
    private fun generateRandomKey(): ByteArray = ByteArray(32).apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            SecureRandom.getInstanceStrong().nextBytes(this)
        } else {
            SecureRandom().nextBytes(this)
        }
    }
    //endregion

}