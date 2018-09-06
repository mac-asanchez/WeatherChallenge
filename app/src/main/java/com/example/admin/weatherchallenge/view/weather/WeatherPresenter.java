package com.example.admin.weatherchallenge.view.weather;

import android.util.Log;

import com.example.admin.weatherchallenge.manager.DataRepository;
import com.example.admin.weatherchallenge.model.entity.WeatherResponse;
import com.example.admin.weatherchallenge.model.remote.WeatherCallback;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WeatherPresenter implements WeatherContract.Presenter {
    private static String TAG = WeatherPresenter.class.getSimpleName() + "_TAG";
    private WeatherContract.View view;
    private DataRepository dataRepository;

    public WeatherPresenter(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void getWeather(@NotNull String city) {
        Log.d(TAG, "getWeather: " + city);
        dataRepository.getWeather(city, new WeatherCallback() {

            @Override
            public void onRemoteResponse(@NotNull List<WeatherResponse> weatherResponseList) {
                Log.d(TAG, "onRemoteResponse: " + weatherResponseList.size());
                view.onGetWeather(weatherResponseList);
            }

            @Override
            public void onRemoteFailure(@NotNull String error) {
                view.showError(error);
            }
        });
    }

    @Override
    public void attachView(@NotNull WeatherContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
