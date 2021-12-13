package com.example.nabchallenge.repository.datastore.dj

import android.content.Context
import com.example.nabchallenge.repository.datastore.core.PreferenceDataStore
import com.example.nabchallenge.repository.datastore.core.PreferenceDataStoreImp
import com.example.nabchallenge.utils.security.KeystoreCrypto
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataStoreModule {

    @Singleton
    @Provides
    fun providePreferenceDataStore(context: Context, keystoreCrypto: KeystoreCrypto): PreferenceDataStore = PreferenceDataStoreImp(context, keystoreCrypto)

}