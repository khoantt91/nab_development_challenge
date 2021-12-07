package com.example.nabchallenge.model

open class District(
    var districtId: Int? = null,
    var districtName: String? = null
) {

    fun compareForAdapter(other: District): Boolean = when {
        this.districtId != other.districtId -> false
        this.districtName != other.districtName -> false
        else -> true
    }

}

