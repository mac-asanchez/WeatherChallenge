package com.example.admin.weatherchallenge.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.admin.weatherchallenge.R

class RVCityAdapter(private val cityList: List<String>, private val listener: (String) -> Unit) : RecyclerView.Adapter<RVCityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.city_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = cityList[position]
        holder.tvCity.text = city

        holder.bind(city, listener)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    interface OnItemClickListener {
        fun onItemClick(city: String)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tvCity: TextView = itemView.findViewById(R.id.tvCity)

        fun bind(city: String, clickListener: (String) -> Unit) {
            itemView.setOnClickListener { clickListener(city) }
        }
    }
}
