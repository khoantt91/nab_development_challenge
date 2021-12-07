package com.example.nabchallenge.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nabchallenge.repository.Repository
import com.example.nabchallenge.view.MainViewModel
import com.example.nabchallenge.view.onboarding.forgot.ForgotPasswordViewModel
import com.example.nabchallenge.view.onboarding.login.LoginViewModel
import com.example.nabchallenge.view.onboarding.splash.SplashViewModel

/***
 * Created by Khoa Nguyen on 01/11/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */
@Suppress("UNCHECKED_CAST", "unused")
class ViewModelFactory constructor(val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(this.repository) as T
            }
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
                SplashViewModel(this.repository) as T
            }
            modelClass.isAssignableFrom(ForgotPasswordViewModel::class.java) -> {
                ForgotPasswordViewModel(this.repository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(this.repository) as T
            }
//            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
//                HomeViewModel(this.repository) as T
//            }
            else -> {
                throw IllegalArgumentException("ViewModel isn't defined")
            }
        }
    }
}