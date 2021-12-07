package com.example.nabchallenge.repository.network.service

import com.example.nabchallenge.model.District
import com.example.nabchallenge.repository.network.constant.NetworkConstant
import com.example.nabchallenge.repository.network.core.NetworkCore
import com.example.nabchallenge.repository.network.model.Method
import com.example.nabchallenge.repository.network.model.NetworkDataSource
import javax.inject.Inject

class DistrictServiceImp @Inject constructor(private val networkCore: NetworkCore) : DistrictService {

    override suspend fun getDistrictList(): NetworkDataSource<List<District>> {
        return networkCore.makeCall(NetworkConstant.GET_DISTRICT_ENDPOINT, Method.GET)
    }

}