package com.example.nabchallenge

import android.content.Context
import com.example.nabchallenge.common.activity.dj.ActivityModule
import com.example.nabchallenge.common.fragment.dj.FragmentModule
import com.example.nabchallenge.repository.RepositoryImp
import com.example.nabchallenge.repository.datastore.dj.DataStoreModule
import com.example.nabchallenge.repository.network.dj.NetworkModule
import com.example.nabchallenge.repository.Repository
import com.example.nabchallenge.repository.local.dj.LocalModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, DataStoreModule::class, LocalModule::class, ActivityModule::class, FragmentModule::class])
class AppModule(private val app: App) {

    @Singleton
    @Provides
    fun provideApplicationContext(): Context = app

    @Singleton
    @Provides
    fun provideRepository(): Repository {
        return RepositoryImp(app)
    }
}