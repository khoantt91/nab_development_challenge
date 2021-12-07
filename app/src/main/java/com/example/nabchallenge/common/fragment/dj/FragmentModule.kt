package com.example.nabchallenge.common.fragment.dj

import com.example.nabchallenge.common.viewmodel.ViewModelFactory
import com.example.nabchallenge.repository.Repository
import dagger.Module
import dagger.Provides

/***
 * Created by Khoa Nguyen on 01/11/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

@Module
class FragmentModule {

    @FragmentScope
    @Provides
    fun provideViewModelFactory(repository: Repository): ViewModelFactory = ViewModelFactory(repository)

}