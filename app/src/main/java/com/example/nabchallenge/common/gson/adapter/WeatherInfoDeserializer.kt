package com.example.nabchallenge.common.gson.adapter

import com.example.nabchallenge.model.WeatherInfo
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

/***
 * Created by Khoa Nguyen on 07/12/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

class WeatherInfoDeserializer : JsonDeserializer<WeatherInfo> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): WeatherInfo {
        val weatherJson = json?.asJsonObject
        val temperatureJson = weatherJson?.get("temp")?.asJsonObject
        val description = weatherJson?.get("weather")?.asJsonArray?.joinToString { it?.asJsonObject?.get("description")?.asString.toString() }
        return WeatherInfo(
            null,
            temperatureJson?.get("min")?.asLong,
            temperatureJson?.get("max")?.asLong,
            weatherJson?.get("pressure")?.asInt,
            weatherJson?.get("humidity")?.asInt,
            description,
            weatherJson?.get("city")?.asJsonObject?.get("name")?.asString,
            weatherJson?.get("dt")?.asLong?.let { it * 1000 }               // Convert second to millisecond
        )
    }
}