package com.example.admin.weatherchallenge.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherResponse {

    @SerializedName("id")
    @Expose
    var id: Long = 0
    @SerializedName("weather_state_name")
    @Expose
    var weatherStateName: String? = null
    @SerializedName("weather_state_abbr")
    @Expose
    var weatherStateAbbr: String? = null
    @SerializedName("wind_direction_compass")
    @Expose
    var windDirectionCompass: String? = null
    @SerializedName("created")
    @Expose
    var created: String? = null
    @SerializedName("applicable_date")
    @Expose
    var applicableDate: String? = null
    @SerializedName("min_temp")
    @Expose
    var minTemp: Double? = null
    @SerializedName("max_temp")
    @Expose
    var maxTemp: Double? = null
    @SerializedName("the_temp")
    @Expose
    var theTemp: Double? = null
    @SerializedName("wind_speed")
    @Expose
    var windSpeed: Double? = null
    @SerializedName("wind_direction")
    @Expose
    var windDirection: Double? = null
    @SerializedName("air_pressure")
    @Expose
    var airPressure: Double? = null
    @SerializedName("humidity")
    @Expose
    var humidity: Int? = null
    @SerializedName("visibility")
    @Expose
    var visibility: Any? = null
    @SerializedName("predictability")
    @Expose
    var predictability: Int? = null

    constructor(id: Long, weatherStateName: String?, weatherStateAbbr: String?, applicableDate: String?, theTemp: Double?, windSpeed: Double?, humidity: Int?) {
        this.id = id
        this.weatherStateName = weatherStateName
        this.weatherStateAbbr = weatherStateAbbr
        this.applicableDate = applicableDate
        this.theTemp = theTemp
        this.windSpeed = windSpeed
        this.humidity = humidity
    }
}