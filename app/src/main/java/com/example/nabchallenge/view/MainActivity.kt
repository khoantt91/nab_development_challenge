package com.example.nabchallenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import com.example.nabchallenge.App
import com.example.nabchallenge.R
import com.example.nabchallenge.common.activity.BaseActivity
import com.example.nabchallenge.databinding.ActivityMainBinding
import com.example.nabchallenge.utils.log.eLog
import com.example.nabchallenge.utils.log.wLog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>() {

    //region Variables
    private val mainViewModel: MainViewModel by viewModels { viewModelFactory }
    //endregion

    override fun provideBinding(inflater: LayoutInflater): ActivityMainBinding = ActivityMainBinding.inflate(inflater)

    //region Init View
    override fun setupView() {
        mainViewModel.getWeather()
    }
    //endregion

    //region Update UI

    //endregion

    //region Event Listener

    //endregion

    //region Private Support Methods

    //endregion

}