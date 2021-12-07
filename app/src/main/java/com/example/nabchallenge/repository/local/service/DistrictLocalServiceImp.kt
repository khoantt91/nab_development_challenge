/*
 *
 *   Created by Khoa Nguyen on 10/23/21, 5:22 PM
 *   Email: khoantt91@gmail.com
 *   Copyright (c) 2021 . All rights reserved.
 *    Last modified 10/23/21, 5:22 PM
 *
 */

package com.example.nabchallenge.repository.local.service

import com.example.nabchallenge.model.District
import com.example.nabchallenge.repository.local.model.LocalDataSource
import javax.inject.Inject

class DistrictLocalServiceImp @Inject constructor(private val districtRealm: District) : DistrictLocalService {

    override suspend fun getDistrictList(): LocalDataSource<List<District>> {
        TODO()
    }

    override suspend fun saveDistrictList(districts: List<District>) {
        TODO()
    }

    override suspend fun deleteAll() {
        TODO()
    }

}