package com.example.nabchallenge.repository.local.core

import com.example.nabchallenge.repository.local.config.AppDatabase

/***
 * Created by Khoa Nguyen on 13/12/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

interface AppRoomCore {

    fun retrieveAppDatabase(): AppDatabase

}