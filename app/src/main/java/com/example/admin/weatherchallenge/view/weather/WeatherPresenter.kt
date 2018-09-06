package com.example.admin.weatherchallenge.view.weather

import android.util.Log

import com.example.admin.weatherchallenge.manager.DataRepository
import com.example.admin.weatherchallenge.model.entity.WeatherResponse
import com.example.admin.weatherchallenge.model.remote.WeatherCallback

class WeatherPresenter(private val dataRepository: DataRepository) : WeatherContract.Presenter {
    private var view: WeatherContract.View? = null

    override fun getWeather(city: String) {
        Log.d(TAG, "getWeather: $city")
        dataRepository.getWeather(city, object : WeatherCallback {

            override fun onRemoteResponse(weatherResponseList: List<WeatherResponse>) {
                Log.d(TAG, "onRemoteResponse: " + weatherResponseList.size)
                view!!.onGetWeather(weatherResponseList)
            }

            override fun onRemoteFailure(error: String) {
                view!!.showError(error)
            }
        })
    }

    override fun attachView(view: WeatherContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    companion object {
        private val TAG = WeatherPresenter::class.java.simpleName + "_TAG"
    }
}
