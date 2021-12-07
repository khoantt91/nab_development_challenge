package com.example.nabchallenge.common.fragment.dj

import androidx.viewbinding.ViewBinding
import com.example.nabchallenge.AppComponent
import com.example.nabchallenge.common.fragment.BaseFragment
import dagger.Component
import javax.inject.Scope

/***
 * Created by Khoa Nguyen on 01/11/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): FragmentComponent
    }

    fun inject(fragment: BaseFragment<ViewBinding>)

}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScope