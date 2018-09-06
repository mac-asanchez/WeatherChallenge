package com.example.admin.weatherchallenge.model.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

import java.util.Date

import io.reactivex.annotations.NonNull

@Entity(tableName = "forecast")
class Forecast {
    @NonNull
    @PrimaryKey
    var id: Long = 0

    var cityId: Int = 0
    var stateName: String? = null
    var stateAbbr: String? = null
    var date: String? = null
    var temperature: Double = 0.toDouble()
    var wind_speed: Double = 0.toDouble()
    var humidity: Int = 0
}
