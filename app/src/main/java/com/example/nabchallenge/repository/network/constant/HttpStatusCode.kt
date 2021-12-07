@file:Suppress("unused")

package com.example.nabchallenge.repository.network.constant

enum class HttpStatusCode constructor(val code: Int, val message: String) {

    BAD_REQUEST(400, "Bad Request"),

    UNAUTHORIZED(401, "Unauthorized"),

    FORBIDDEN(403, "Request forbidden"),

    NOT_FOUND(404, "Resource not found"),

    REQUEST_TIME_OUT(408, "Request Timeout (Http Status: 408)"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error (Http Status: 500)"),

    NO_CONNECTION_SERVER(502, "No Connection To Server (Http Status: 502)"),

    SUCCESS(200, "Success"),

    UNKNOWN(1000, "Unknown");


    companion object {
        fun makeFromCode(code: Int): HttpStatusCode = HttpStatusCode.values().find { it.code == code } ?: UNKNOWN
    }

}