package com.example.nabchallenge.view.onboarding.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nabchallenge.common.viewmodel.BaseViewModel
import com.example.nabchallenge.repository.Repository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/***
 * Created by Khoa Nguyen on 01/11/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

class SplashViewModel constructor(val repository: Repository) : BaseViewModel(repository) {

    //region Variables
    private var prepareDataCompleted: MutableLiveData<Boolean> = MutableLiveData()
    //endregion

    fun loadPrepareData(): MutableLiveData<Boolean> {
        viewModelScope.launch {
            delay(1000)
            /* Assuming the user is logged in or not? */
            val result = repository.getCurrentToken()
            if (result.success?.isNotEmpty() == true) prepareDataCompleted.postValue(true)
            else prepareDataCompleted.postValue(false)
        }
        return prepareDataCompleted
    }

}