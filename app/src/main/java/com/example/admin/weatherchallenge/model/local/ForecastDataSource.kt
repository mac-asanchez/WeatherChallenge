package com.example.admin.weatherchallenge.model.local

import android.arch.persistence.room.Room
import android.content.Context
import android.util.Log

import com.example.admin.weatherchallenge.model.entity.Forecast

import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class ForecastDataSource(context: Context) {
    private val forecastDAO: ForecastDAO

    init {
        val forecastDatabase = Room.databaseBuilder(context, ForecastDatabase::class.java, "forecast-database").build()
        forecastDAO = forecastDatabase.forecastDAO()
    }

    fun insert(forecast: Forecast) {
        Completable.fromAction {
            val id = forecastDAO.insertForecast(forecast)
            forecast.id = id
        }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {
                        Log.d(TAG, "onSubscribe: ")
                    }

                    override fun onComplete() {

                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "onError: " + e.message!!)
                    }
                })
    }

    fun getForecast(cityId: Int, date: String, callback: LocalCallback) {
        getForecastBD(cityId, date)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({result ->
                    callback.onGetForecast(result)
                }, {error ->
                    callback.onLocalFailure(error = error.toString())
                })
    }

    private fun getForecastBD(cityId: Int, date: String): Observable<List<Forecast>> {
        return Observable.create { subscriber ->

            val forecastList = forecastDAO?.getForecast(cityId = cityId, date = date)
            subscriber.onNext(forecastList)
            subscriber.onComplete()
        }
    }

    companion object {
        private val TAG = ForecastDataSource::class.java.simpleName + "_TAG"
    }
}
