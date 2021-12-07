/*
 *
 *  Created by Khoa Nguyen on 10/21/21, 12:07 AM
 *  Email: khoantt91@gmail.com
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 10/21/21, 12:07 AM
 *
 */

package com.example.nabchallenge

import android.app.Application

class App : Application() {

    val appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()

}