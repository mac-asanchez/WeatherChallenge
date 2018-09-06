package com.example.admin.weatherchallenge.view.weather

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.admin.weatherchallenge.R
import com.example.admin.weatherchallenge.manager.DataRepository
import com.example.admin.weatherchallenge.model.entity.WeatherResponse
import com.example.admin.weatherchallenge.model.remote.RemoteDataSource
import com.example.admin.weatherchallenge.utils.Constant
import com.example.admin.weatherchallenge.view.main.MainPresenter

class WeatherActivity : AppCompatActivity(), WeatherContract.View {
    private var presenter: WeatherPresenter? = null
    private var rvWeather: RecyclerView? = null
    var cityId: String = ""

    override fun onGetWeather(weatherResponseList: List<WeatherResponse>) {

    }

    override fun showError(error: String) {
        toast(error)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        cityId = intent.getStringExtra(Constant.KEY_CITY_ID)

        presenter = WeatherPresenter(DataRepository(RemoteDataSource()))
        rvWeather = findViewById(R.id.rvWeather)
    }

    override fun onStart() {
        super.onStart()
        presenter!!.attachView(this)
        presenter!!.getWeather(cityId)
    }

    override fun onStop() {
        super.onStop()
        presenter!!.detachView()
    }

    //region Toast
    private fun Context.toast(message: CharSequence) =
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    //endregion
}
