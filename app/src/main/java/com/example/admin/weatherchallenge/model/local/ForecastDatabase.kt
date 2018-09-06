package com.example.admin.weatherchallenge.model.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

import com.example.admin.weatherchallenge.model.entity.Forecast

@Database(entities = arrayOf(Forecast::class), version = 1, exportSchema = false)
abstract class ForecastDatabase : RoomDatabase() {
    abstract fun forecastDAO(): ForecastDAO
}
