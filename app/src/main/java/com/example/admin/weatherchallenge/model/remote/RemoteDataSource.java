package com.example.admin.weatherchallenge.model.remote;

import android.util.Log;

import com.example.admin.weatherchallenge.model.entity.WeatherResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource {
    private static final String BASE_URL = "https://www.metaweather.com/";
    private static String TAG = RemoteDataSource.class.getSimpleName() + "_TAG";

    private Retrofit createInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit;
    }

    public Observable<List<WeatherResponse>> getWeatherFromNetwork(String cityId, String date) {
        Log.d(TAG, "getCityInfoFromNetwork: ");
        return createInstance().create(RemoteService.class).getWeather(cityId, date);
    }
}
