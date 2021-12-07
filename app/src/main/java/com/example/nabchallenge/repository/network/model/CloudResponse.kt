package com.example.nabchallenge.repository.network.model

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

/***
 *   Define cloud data response model.
 *   This format will base on definition of server
 */
data class CloudResponse(
    @SerializedName("cod")
    var code: String? = null,
    var message: String? = null,
    var list: JsonElement? = null,
    var single: JsonElement? = null
)
