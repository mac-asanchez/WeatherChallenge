package com.example.admin.weatherchallenge.model.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

import com.example.admin.weatherchallenge.model.entity.Forecast

import io.reactivex.Observable

@Dao
interface ForecastDAO {

    @Query("SELECT * FROM Forecast AS a WHERE a.cityId = :cityId AND a.date = :date")
    fun getForecast(cityId: Int, date: String): List<Forecast>

    @Insert
    fun insertForecast(forecast: Forecast): Long
}
