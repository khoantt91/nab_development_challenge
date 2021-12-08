package com.example.nabchallenge.view

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.example.nabchallenge.common.activity.BaseActivity
import com.example.nabchallenge.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    //region Variables
    private val mainViewModel: MainViewModel by viewModels { viewModelFactory }
    //endregion

    override fun provideBinding(inflater: LayoutInflater): ActivityMainBinding = ActivityMainBinding.inflate(inflater)

    //region Init View
    override fun setupView() = Unit
    //endregion

    //region Update UI

    //endregion

    //region Event Listener

    //endregion

    //region Private Support Methods

    //endregion

}