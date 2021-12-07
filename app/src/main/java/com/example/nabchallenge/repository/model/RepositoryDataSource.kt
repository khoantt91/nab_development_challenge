package com.example.nabchallenge.repository.model

import com.example.nabchallenge.model.AppError
import com.example.nabchallenge.repository.datastore.model.StoreDataSource
import com.example.nabchallenge.repository.local.model.LocalDataSource
import com.example.nabchallenge.repository.network.model.NetworkDataSource

data class RepositoryDataSource<T>(var success: T? = null, var error: RepositoryDataSourceError? = null)

open class RepositoryDataSourceError(override val code: Int, override val message: String) : AppError(code, message)

// Extension for data converting
fun <T> NetworkDataSource<T>.toRepositoryData(): RepositoryDataSource<T> = RepositoryDataSource(this.success, this.error)

fun <T> LocalDataSource<T>.toRepositoryData(): RepositoryDataSource<T> = RepositoryDataSource(this.success, this.error)

fun <T> StoreDataSource<T>.toRepositoryData(): RepositoryDataSource<T> = RepositoryDataSource(this.success, this.error)