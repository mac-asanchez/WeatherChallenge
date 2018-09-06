package com.example.admin.weatherchallenge.model.remote

import android.util.Log

import com.example.admin.weatherchallenge.model.entity.WeatherResponse
import com.example.admin.weatherchallenge.model.remote.RemoteDataSource.Companion.BASE_URL

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    private fun createInstance(): Retrofit {

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    fun getWeatherFromNetwork(cityId: String, date: String): Observable<List<WeatherResponse>> {
        Log.d(TAG, "getWeatherFromNetwork: ")
        return createInstance().create(RemoteService::class.java).getWeather(cityId, date)
    }

    companion object {
        private const val BASE_URL = "https://www.metaweather.com/"
        private val TAG = RemoteDataSource::class.java.simpleName + "_TAG"
    }
}
