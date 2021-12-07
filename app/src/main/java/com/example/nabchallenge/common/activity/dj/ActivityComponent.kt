package com.example.nabchallenge.common.activity.dj

import androidx.viewbinding.ViewBinding
import com.example.nabchallenge.AppComponent
import com.example.nabchallenge.common.activity.BaseActivity
import dagger.Component
import javax.inject.Scope

/***
 * Created by Khoa Nguyen on 01/11/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): ActivityComponent
    }

    fun inject(activity: BaseActivity<ViewBinding>)

}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope