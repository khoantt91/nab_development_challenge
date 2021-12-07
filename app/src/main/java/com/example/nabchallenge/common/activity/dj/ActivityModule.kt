package com.example.nabchallenge.common.activity.dj

import com.example.nabchallenge.repository.Repository
import com.example.nabchallenge.common.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides

/***
 * Created by Khoa Nguyen on 01/11/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

@Module
class ActivityModule {

    @ActivityScope
    @Provides
    fun provideViewModelFactory(repository: Repository): ViewModelFactory = ViewModelFactory(repository)

}