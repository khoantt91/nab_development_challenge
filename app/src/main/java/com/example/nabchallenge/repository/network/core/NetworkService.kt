package com.example.nabchallenge.repository.network.core

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface NetworkService {
    @GET
    suspend fun makeGetRequest(@Url url: String?): Response<ResponseBody>

    @POST
    suspend fun makePostRequest(@Url url: String?, @Body requestBody: RequestBody?): Response<ResponseBody>

    @PUT
    suspend fun makePutRequest(@Url url: String?, @Body requestBody: RequestBody?): Response<ResponseBody>

    @DELETE
    suspend fun makeDeleteRequest(@Url url: String?): Response<ResponseBody>
}