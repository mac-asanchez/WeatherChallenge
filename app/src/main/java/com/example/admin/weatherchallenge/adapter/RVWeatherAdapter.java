package com.example.admin.weatherchallenge.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.weatherchallenge.R;
import com.example.admin.weatherchallenge.model.entity.WeatherResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RVWeatherAdapter extends RecyclerView.Adapter<RVWeatherAdapter.ViewHolder> {
    Context context;
    private List<WeatherResponse> weatherResponseList;

    public RVWeatherAdapter(Context context, List<WeatherResponse> weatherResponseList) {
        this.weatherResponseList = weatherResponseList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherResponse weather = weatherResponseList.get(position);

        //region Get Date
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        String date = dateFormat.format(today);
        //endregion

        holder.tvCurrentTime.setText(date);
        holder.tvState.setText(weather.getWeatherStateName());
        holder.tvTemperature.setText(String.valueOf(weather.getTheTemp()));
        holder.tvHumidity.setText(String.valueOf(weather.getHumidity()) + "%");
        holder.tvWindSpeed.setText(String.valueOf(weather.getWindSpeed()));

        String imageUrl = "https://www.metaweather.com/static/img/weather/png/64/" + weather.getWeatherStateAbbr() + ".png";

        Glide.with(context).load(imageUrl).into(holder.ivState);
    }

    @Override
    public int getItemCount() {
        return weatherResponseList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCurrentTime;
        TextView tvState;
        TextView tvTemperature;
        TextView tvHumidity;
        TextView tvWindSpeed;
        ImageView ivState;


        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCurrentTime = itemView.findViewById(R.id.tvCurrentTime);
            tvState = itemView.findViewById(R.id.tvState);
            tvTemperature = itemView.findViewById(R.id.tvTemperature);
            tvHumidity = itemView.findViewById(R.id.tvHumidity);
            tvWindSpeed = itemView.findViewById(R.id.tvWindSpeed);

            ivState = itemView.findViewById(R.id.ivState);
        }
    }
}
