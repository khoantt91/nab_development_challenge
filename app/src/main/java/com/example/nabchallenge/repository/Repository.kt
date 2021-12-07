package com.example.nabchallenge.repository

import com.example.nabchallenge.model.District
import com.example.nabchallenge.repository.model.RepositoryDataSource

interface Repository {

    suspend fun getDistrict(): RepositoryDataSource<List<District>>

    suspend fun getCurrentToken(): RepositoryDataSource<String>

    suspend fun login(userName: String, password: String) : RepositoryDataSource<Boolean>

    suspend fun logout() : RepositoryDataSource<Boolean>

}