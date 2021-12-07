package com.example.nabchallenge.model

open class AppError(open val code: Int, message: String) : Throwable(message) {

    // Define app error code
    enum class AppErrorCode(val code: Int) {
        INVALID_USERNAME_ERROR(1000),
        INVALID_PASSWORD_ERROR(1001);

        companion object {
            fun makeFromCode(code: Int): AppErrorCode? = values().find { it.code == code }
        }
    }
}