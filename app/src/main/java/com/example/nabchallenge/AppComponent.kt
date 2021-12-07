package com.example.nabchallenge

import com.example.nabchallenge.repository.RepositoryImp
import com.example.nabchallenge.repository.Repository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(repository: RepositoryImp)

    /* Expose repository */
    fun repository(): Repository

}