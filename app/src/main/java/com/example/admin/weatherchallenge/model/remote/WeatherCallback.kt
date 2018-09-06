package com.example.admin.weatherchallenge.model.remote

import com.example.admin.weatherchallenge.model.entity.WeatherResponse

interface WeatherCallback {
    fun onRemoteResponse(weatherResponse: List<WeatherResponse>)
    fun onRemoteFailure(error: String)
}
