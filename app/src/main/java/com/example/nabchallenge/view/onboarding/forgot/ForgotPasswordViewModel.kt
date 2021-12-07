package com.example.nabchallenge.view.onboarding.forgot

import androidx.lifecycle.MutableLiveData
import com.example.nabchallenge.common.viewmodel.BaseViewModel
import com.example.nabchallenge.repository.Repository

/***
 * Created by Khoa Nguyen on 01/11/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

class ForgotPasswordViewModel constructor(repository: Repository) : BaseViewModel(repository) {

    fun forgotPassword(): MutableLiveData<String> {
        val userNamePasswordHint = MutableLiveData<String>()
        userNamePasswordHint.postValue("Username: admin - Password: 123")
        return userNamePasswordHint
    }

}