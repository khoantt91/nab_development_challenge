package com.example.nabchallenge.repository.network.core.exception

import java.io.IOException

/***
 * Created by Khoa Nguyen on 08/12/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

class NoConnectivityException : IOException() {
    override val message: String
        get() = "No Internet Connection"
}