package com.example.nabchallenge.repository.network.core

import com.example.nabchallenge.common.gson.adapter.WeatherInfoDeserializer
import com.example.nabchallenge.model.WeatherInfo
import com.example.nabchallenge.repository.network.constant.HttpStatusCode
import com.example.nabchallenge.repository.network.model.CloudResponse
import com.example.nabchallenge.repository.network.model.Method
import com.example.nabchallenge.repository.network.model.NetworkDataSource
import com.example.nabchallenge.repository.network.model.NetworkDataSourceError
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class NetworkCore(val networkService: NetworkService) : CoroutineScope {

    //region Variables
    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    var gson: Gson = GsonBuilder().apply {
        registerTypeAdapter(WeatherInfo::class.java, WeatherInfoDeserializer())
    }.create()
    //endregion

    suspend inline fun <reified T> makeCall(endpoint: String, method: Method, params: HashMap<String, Any>? = null): NetworkDataSource<T> {
        return try {
            val calledApi: Response<ResponseBody> = when (method) {
                Method.GET -> networkService.makeGetRequest(endpoint)
                Method.DELETE -> networkService.makeDeleteRequest(endpoint)
                Method.POST -> networkService.makePostRequest(endpoint, params?.toRequestBody())
                Method.PUT -> networkService.makePutRequest(endpoint, params?.toRequestBody())
            }
            executeResponse(calledApi)
        } catch (ex: Exception) {
            NetworkDataSource(error = NetworkDataSourceError(10000, ex.message.toString()))
        }
    }

    /***
     *   Base on format response from server
     *   Define the way to handle success or error response data.
     */
    inline fun <reified T> executeResponse(response: Response<ResponseBody>): NetworkDataSource<T> {
        // Error
        val httpStatusCode = HttpStatusCode.makeFromCode(response.raw().code)
        if (httpStatusCode != HttpStatusCode.SUCCESS) {
            val errorResponse = gson.fromJson(response.errorBody()?.string(), CloudResponse::class.java)
            return NetworkDataSource(error = NetworkDataSourceError(errorResponse.code?.toIntOrNull() ?: HttpStatusCode.UNKNOWN.code, errorResponse.message.toString()))
        }

        // Success: handle 2 case list object or single object
        val successResponse = gson.fromJson(response.body()?.string(), CloudResponse::class.java)
        if (successResponse.list != null) return NetworkDataSource(gson.fromJson(successResponse?.list, object : TypeToken<T>() {}.type))
        if (successResponse.single != null) return NetworkDataSource(gson.fromJson(successResponse?.single, object : TypeToken<T>() {}.type))
        return NetworkDataSource(null)
    }
}

fun HashMap<String, Any>.toRequestBody(): RequestBody {
    val jsonObject = JSONObject(this as Map<*, *>)
    return jsonObject.toString().toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
}