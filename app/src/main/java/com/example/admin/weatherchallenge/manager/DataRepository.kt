package com.example.admin.weatherchallenge.manager

import android.annotation.SuppressLint
import android.util.Log

import com.example.admin.weatherchallenge.model.entity.WeatherResponse
import com.example.admin.weatherchallenge.model.remote.RemoteDataSource
import com.example.admin.weatherchallenge.model.remote.WeatherCallback

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Date

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DataRepository(private var remoteDataSource: RemoteDataSource) {

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

    private fun getCityId(city: String): String {
        when (city) {
            "Gothenburg" -> return "890869"
            "Stockholm" -> return "906057"
            "Mountain View" -> return "2455920"
            "London" -> return "44418"
            "New York" -> return "2459115"
            "Berlin" -> return "638242"
            else -> return ""
        }
    }

    fun getWeather(city: String, callback: WeatherCallback) {
        //region get parameters
        val cityId = getCityId(city)
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val tomorrow = calendar.time

        @SuppressLint("SimpleDateFormat") val dateFormat = SimpleDateFormat("yyyy/MM/dd")
        val date = dateFormat.format(tomorrow)
        //endregion

        remoteDataSource.getWeatherFromNetwork(cityId, date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<WeatherResponse>> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(detailResponses: List<WeatherResponse>) {
                        callback.onRemoteResponse(detailResponses)
                    }

                    override fun onError(e: Throwable) {
                        callback.onRemoteFailure(e.toString())
                    }

                    override fun onComplete() {

                    }
                })
    }

    companion object {
        private val TAG = DataRepository::class.java.simpleName + "_TAG"
    }
}
