package com.example.admin.weatherchallenge.model.remote

import com.example.admin.weatherchallenge.model.entity.WeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteService {

    @GET("api/location/{cityId}/{date}")
    fun getWeather(@Path("cityId") cityId: String, @Path("date") date: String): Observable<List<WeatherResponse>>
}
