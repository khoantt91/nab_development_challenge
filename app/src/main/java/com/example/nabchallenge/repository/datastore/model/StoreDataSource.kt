package com.example.nabchallenge.repository.datastore.model

import com.example.nabchallenge.repository.model.RepositoryDataSourceError

data class StoreDataSource<T>(var success: T? = null, var error: StoreDataSourceError? = null)

data class StoreDataSourceError(override val code: Int, override val message: String) : RepositoryDataSourceError(code, message)