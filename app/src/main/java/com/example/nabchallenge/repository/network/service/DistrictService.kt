package com.example.nabchallenge.repository.network.service

import com.example.nabchallenge.model.District
import com.example.nabchallenge.repository.network.model.NetworkDataSource

interface DistrictService {
    suspend fun getDistrictList(): NetworkDataSource<List<District>>
}