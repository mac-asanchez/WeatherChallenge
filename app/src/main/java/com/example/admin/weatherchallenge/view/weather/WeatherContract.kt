package com.example.admin.weatherchallenge.view.weather

import com.example.admin.weatherchallenge.model.entity.WeatherResponse
import com.example.admin.weatherchallenge.view.base.BasePresenter
import com.example.admin.weatherchallenge.view.base.BaseView

interface WeatherContract {
    interface View : BaseView {
        fun onGetWeather(weatherResponseList: List<WeatherResponse>)
    }

    interface Presenter : BasePresenter<View> {
        fun getWeather(city: String)
    }
}