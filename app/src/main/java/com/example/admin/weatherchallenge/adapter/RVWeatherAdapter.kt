package com.example.admin.weatherchallenge.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.example.admin.weatherchallenge.R
import com.example.admin.weatherchallenge.model.entity.WeatherResponse

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class RVWeatherAdapter(internal var context: Context, private val weatherResponseList: List<WeatherResponse>) : RecyclerView.Adapter<RVWeatherAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_list_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weather = weatherResponseList[position]

        holder.tvState.text = weather.weatherStateName
        holder.tvTemperature.text = Math.round(weather.theTemp!!).toString() + context.getString(R.string.degrees)
        holder.tvHumidity.text = context.getString(R.string.humidity) + " " + weather.humidity.toString() + "%"
        holder.tvWindSpeed.text = context.getString(R.string.wind_speed) + " " + Math.round(weather.windSpeed!!).toString() + " KPH"

        val imageUrl = "https://www.metaweather.com/static/img/weather/png/64/" + weather.weatherStateAbbr + ".png"

        Glide.with(context).load(imageUrl).into(holder.ivState)
    }

    override fun getItemCount(): Int {
        return weatherResponseList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvState: TextView = itemView.findViewById(R.id.tvState)
        var tvTemperature: TextView = itemView.findViewById(R.id.tvTemperature)
        var tvHumidity: TextView = itemView.findViewById(R.id.tvHumidity)
        var tvWindSpeed: TextView = itemView.findViewById(R.id.tvWindSpeed)
        var ivState: ImageView = itemView.findViewById(R.id.ivState)
    }
}
