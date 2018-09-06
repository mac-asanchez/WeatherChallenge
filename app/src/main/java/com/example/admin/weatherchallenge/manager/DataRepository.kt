package com.example.admin.weatherchallenge.manager

import android.annotation.SuppressLint
import android.util.Log
import com.example.admin.weatherchallenge.model.entity.Forecast

import com.example.admin.weatherchallenge.model.entity.WeatherResponse
import com.example.admin.weatherchallenge.model.local.ForecastDataSource
import com.example.admin.weatherchallenge.model.local.LocalCallback
import com.example.admin.weatherchallenge.model.remote.RemoteDataSource
import com.example.admin.weatherchallenge.model.remote.WeatherCallback
import com.example.admin.weatherchallenge.utils.EntityConverter

import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DataRepository(private var remoteDataSource: RemoteDataSource, private var localDataSource: ForecastDataSource) {

    val availableCities: List<String>
        get() {
            val availableCities = ArrayList<String>()

            availableCities.add("Gothenburg")
            availableCities.add("Stockholm")
            availableCities.add("Mountain View")
            availableCities.add("London")
            availableCities.add("New York")
            availableCities.add("Berlin")

            return availableCities
        }

    private fun getCityId(cityName: String): Int {
        when (cityName) {
            "Gothenburg" -> return 890869
            "Stockholm" -> return 906057
            "Mountain View" -> return 2455920
            "London" -> return 44418
            "New York" -> return 2459115
            "Berlin" -> return 638242
            else -> return 0
        }
    }

    fun getWeather(cityName: String, callback: WeatherCallback) {
        Log.d(TAG, "getWeather: cityName: {$cityName}")

        //region get parameters
        val cityId = getCityId(cityName)
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val tomorrow = calendar.time

        @SuppressLint("SimpleDateFormat") val dateFormat = SimpleDateFormat("yyyy/MM/dd")
        val date = dateFormat.format(tomorrow)
        //endregion

        getLocalWeather(cityId, date, callback)
    }

    private fun getRemoteWeather(cityId: Int, date: String, callback: WeatherCallback) {
        remoteDataSource.getWeatherFromNetwork(cityId.toString(), date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<WeatherResponse>> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(detailResponses: List<WeatherResponse>) {
                        Log.d(TAG, "getRemoteWeather: onNext: " + detailResponses.size.toString())
                        callback.onRemoteResponse(detailResponses)
                        updateLocalWeather(cityId, date, detailResponses)
                    }

                    override fun onError(e: Throwable) {
                        callback.onRemoteFailure(e.toString())
                    }

                    override fun onComplete() {

                    }
                })
    }

    private fun getLocalWeather(cityId: Int, date: String, callback: WeatherCallback) {
        Log.d(TAG, "getLocalWeather: cityId: $cityId,  date: $date")

        localDataSource.getForecast(cityId, date, object : LocalCallback {
            override fun onGetForecast(forecastList: List<Forecast>) {
                Log.d(TAG, "onGetForecast: " + forecastList.size.toString())

                if (forecastList.isNotEmpty()) {
                    //get local info
                    var detailResponses: List<WeatherResponse> = EntityConverter.fromForecastList(forecastList)

                    //convert from local to response
                    callback.onRemoteResponse(detailResponses)
                } else {
                    //get online info
                    getRemoteWeather(cityId, date, callback)
                }
            }

            override fun onLocalFailure(error: String) {
                callback.onRemoteFailure(error)
                getRemoteWeather(cityId, date, callback)
            }
        })
    }

    private fun updateLocalWeather(cityId: Int, date: String, forecastList: List<WeatherResponse>) {
        Log.d(TAG, "updateLocalWeather: cityId: $cityId, date: $date, size: " + forecastList.size.toString())

        forecastList.forEach { onlineForecast ->
            run {
                val localForecast = Forecast()
                localForecast.cityId = cityId
                localForecast.id = onlineForecast.id
                localForecast.stateName = onlineForecast.weatherStateName
                localForecast.stateAbbr = onlineForecast.weatherStateAbbr
                localForecast.date = date
                localForecast.temperature = onlineForecast.theTemp!!
                localForecast.wind_speed = onlineForecast.windSpeed!!
                localForecast.humidity = onlineForecast.humidity!!

                localDataSource.insert(forecast = localForecast)
            }
        }
    }

    companion object {
        private val TAG = DataRepository::class.java.simpleName + "_TAG"
    }
}
