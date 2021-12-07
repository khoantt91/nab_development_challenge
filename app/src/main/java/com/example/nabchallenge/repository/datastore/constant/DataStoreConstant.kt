package com.example.nabchallenge.repository.datastore.constant

import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreConstant {

    val DATA_STORE_NAME = "Settings"

    /* Define Reference Key */
    object PreferenceKey {
        val TOKEN_KEY = stringPreferencesKey("Token")
    }

}