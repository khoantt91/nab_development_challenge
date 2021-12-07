package com.example.nabchallenge.repository.network.core

import com.example.nabchallenge.repository.network.constant.HttpStatusCode
import com.example.nabchallenge.repository.network.model.CloudResponse
import com.example.nabchallenge.repository.network.model.Method
import com.example.nabchallenge.repository.network.model.NetworkDataSource
import com.example.nabchallenge.repository.network.model.NetworkDataSourceError
import com.google.gson.Gson
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

@Suppress("unused")
class NetworkCore(val networkService: NetworkService) : CoroutineScope {

    //region Variables
    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    var gson = Gson()
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
     *   Base on business logic of server, format of successful data or error data, etc... Will define the way to handle response here.
     *   Example current server using http status code 200 for both of successful data & error data.
     */
    inline fun <reified T> executeResponse(response: Response<ResponseBody>): NetworkDataSource<T> {
        /* STEP 1: HANDLE ERROR */
        /* HTTP STATUS ERROR */
        val httpStatusCode = HttpStatusCode.makeFromCode(response.raw().code)
        if (httpStatusCode != HttpStatusCode.SUCCESS) return NetworkDataSource(error = NetworkDataSourceError(response.raw().code, response.raw().message))

        /* SERVER BUSINESS LOGIC ERROR */
        val cloudResponse = gson.fromJson(response.body()?.string(), CloudResponse::class.java)
        if (cloudResponse.code != HttpStatusCode.SUCCESS.code)
            return NetworkDataSource(error = NetworkDataSourceError(cloudResponse.code ?: HttpStatusCode.UNKNOWN.code, cloudResponse.message.toString()))


        /* STEP 2: HANDLE SUCCESS */
        return NetworkDataSource(gson.fromJson(cloudResponse.data, object : TypeToken<T>() {}.type))
    }
}

fun HashMap<String, Any>.toRequestBody(): RequestBody {
    val jsonObject = JSONObject(this as Map<*, *>)
    return jsonObject.toString().toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
}