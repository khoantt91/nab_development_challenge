package com.example.nabchallenge.repository

import android.content.Context
import com.example.nabchallenge.App
import com.example.nabchallenge.model.District
import com.example.nabchallenge.repository.datastore.core.PreferenceDataStore
import com.example.nabchallenge.repository.model.RepositoryDataSource
import com.example.nabchallenge.repository.model.toRepositoryData
import com.example.nabchallenge.repository.network.service.DistrictService
import com.example.nabchallenge.repository.local.service.DistrictLocalService
import javax.inject.Inject

/***
 *   Repository has responsibility for executing logic of data source.
 *   Such as: get from server, save to local or get from local etc.....
 */
class RepositoryImp @Inject constructor(private val context: Context) : Repository {

    @Inject
    lateinit var districtService: DistrictService

    @Inject
    lateinit var preferenceDataStore: PreferenceDataStore

    init {
        (context as App).appComponent.inject(this)
    }

    override suspend fun getDistrict(): RepositoryDataSource<List<District>> {
        val result = districtService.getDistrictList()
        if (result.error != null) return result.toRepositoryData()
        return result.toRepositoryData()
    }

    override suspend fun getCurrentToken(): RepositoryDataSource<String> = preferenceDataStore.getToken().toRepositoryData()

    override suspend fun login(userName: String, password: String): RepositoryDataSource<Boolean> {
        // Assuming login user flow
        preferenceDataStore.saveToken("$userName-$password")
        return RepositoryDataSource(true)
    }

    override suspend fun logout(): RepositoryDataSource<Boolean> {
        // Assuming login user flow
        preferenceDataStore.deleteToken()
        return RepositoryDataSource(true)
    }

}