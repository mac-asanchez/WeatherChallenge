package com.example.admin.weatherchallenge.view.main;

import android.util.Log;

import com.example.admin.weatherchallenge.manager.DataRepository;
import com.example.admin.weatherchallenge.model.entity.WeatherResponse;
import com.example.admin.weatherchallenge.model.remote.WeatherCallback;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {
    private static String TAG = MainPresenter.class.getSimpleName() + "_TAG";
    private MainContract.View view;
    private DataRepository dataRepository;


    public MainPresenter(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    //region attach/detach view
    @Override
    public void attachView(@NotNull MainContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
    //endregion

    @Override
    public void getAvailableCities() {
        List<String> availableCitires = dataRepository.getAvailableCities();
        view.onGetAvailableCities(availableCitires);
    }
}
