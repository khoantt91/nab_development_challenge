package com.example.nabchallenge.repository.local.model

import com.example.nabchallenge.repository.model.RepositoryDataSourceError

data class LocalDataSource<T>(var success: T? = null, var error: LocalDataSourceError? = null)

data class LocalDataSourceError(override val code: Int, override val message: String) : RepositoryDataSourceError(code, message)