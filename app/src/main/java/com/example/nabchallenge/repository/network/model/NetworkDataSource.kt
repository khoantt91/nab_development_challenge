package com.example.nabchallenge.repository.network.model

import com.example.nabchallenge.repository.model.RepositoryDataSourceError

data class NetworkDataSource<T>(var success: T? = null, var error: NetworkDataSourceError? = null)

data class NetworkDataSourceError(override val code: Int, override val message: String) : RepositoryDataSourceError(code, message)