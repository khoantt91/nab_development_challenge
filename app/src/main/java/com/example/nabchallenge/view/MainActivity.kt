package com.example.nabchallenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.nabchallenge.App
import com.example.nabchallenge.R
import com.example.nabchallenge.utils.log.eLog
import com.example.nabchallenge.utils.log.wLog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = (applicationContext as App).appComponent.repository()
        GlobalScope.launch {
            val result = repository.getWeatherInfoList("saigon", 17)
            result.error?.let { error ->
                eLog("Error = ${error.message}")
            }
            result.success?.let { weatherList ->
                wLog("Result = $weatherList")
            }
        }
    }

}