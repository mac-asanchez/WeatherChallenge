package com.example.admin.weatherchallenge.model.local

import com.example.admin.weatherchallenge.model.entity.Forecast

interface LocalCallback {
    fun onGetForecast(forecastList: List<Forecast>)
    fun onLocalFailure(error: String)
}
