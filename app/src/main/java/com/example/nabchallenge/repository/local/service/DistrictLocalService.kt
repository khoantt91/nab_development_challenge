package com.example.nabchallenge.repository.local.service

import com.example.nabchallenge.model.District
import com.example.nabchallenge.repository.local.model.LocalDataSource

interface DistrictLocalService {

    suspend fun getDistrictList(): LocalDataSource<List<District>>

    suspend fun saveDistrictList(districts: List<District>)

    suspend fun deleteAll()

}