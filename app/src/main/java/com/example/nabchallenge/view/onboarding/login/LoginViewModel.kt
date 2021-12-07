package com.example.nabchallenge.view.onboarding.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nabchallenge.common.viewmodel.BaseViewModel
import com.example.nabchallenge.model.AppError
import com.example.nabchallenge.repository.Repository
import kotlinx.coroutines.launch

/***
 * Created by Khoa Nguyen on 01/11/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

class LoginViewModel constructor(val repository: Repository) : BaseViewModel(repository) {

    var errorLogin: MutableLiveData<AppError> = MutableLiveData()

    fun login(userName: String?, password: String?): MutableLiveData<Boolean> {
        val completed: MutableLiveData<Boolean> = MutableLiveData()

        if (userName?.trim()?.isEmpty() == true || userName?.trim() != "admin") {
            errorLogin.postValue(AppError(AppError.AppErrorCode.INVALID_USERNAME_ERROR.code, "Invalid username"))
            return completed
        }

        if (password?.trim()?.isEmpty() == true || password?.trim() != "123") {
            errorLogin.postValue(AppError(AppError.AppErrorCode.INVALID_PASSWORD_ERROR.code, "Invalid password"))
            return completed
        }

        //Login successfully
        viewModelScope.launch {
            val result = repository.login(userName, password)

            // TODO handle error

            result.success?.let { completed.postValue(true) }
        }

        return completed
    }

}