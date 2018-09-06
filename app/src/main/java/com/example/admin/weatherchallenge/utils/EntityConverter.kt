package com.example.admin.weatherchallenge.utils

import com.example.admin.weatherchallenge.model.entity.Forecast
import com.example.admin.weatherchallenge.model.entity.WeatherResponse

object EntityConverter {
    fun fromForecastList(forecastList: List<Forecast>): List<WeatherResponse> {
        var response:List<WeatherResponse> = forecastList.map{fc ->
            WeatherResponse(fc.id, fc.stateName, fc.stateAbbr, fc.date, fc.temperature, fc.wind_speed, fc.humidity)
        }.toList()

        return response
    }
}
