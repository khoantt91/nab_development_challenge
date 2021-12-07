package com.example.nabchallenge.repository.network.model

import com.google.gson.JsonElement

/***
 *   Define cloud data response model.
 *   This format will base on definition of server
 */
data class CloudResponse(
    var result: Boolean? = false,
    var code: Int? = null,
    var message: String? = null,
    var data: JsonElement? = null,
    var rawJson: String? = null
)
