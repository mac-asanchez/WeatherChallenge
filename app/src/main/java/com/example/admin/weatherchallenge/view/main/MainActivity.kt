package com.example.admin.weatherchallenge.view.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.AdapterView
import android.widget.Toast
import com.example.admin.weatherchallenge.R
import com.example.admin.weatherchallenge.adapter.RVCityAdapter
import com.example.admin.weatherchallenge.manager.DataRepository
import com.example.admin.weatherchallenge.model.local.ForecastDataSource
import com.example.admin.weatherchallenge.model.remote.RemoteDataSource
import com.example.admin.weatherchallenge.utils.Constant
import com.example.admin.weatherchallenge.view.weather.WeatherActivity

class MainActivity : AppCompatActivity(), MainContract.View {
    private var presenter: MainPresenter? = null
    private var rvCities: RecyclerView? = null

    //region Events
    override fun onGetAvailableCities(availableCities: List<String>) {
        val layoutManager = LinearLayoutManager(this)
        val adapter = RVCityAdapter(availableCities) { city: String -> cityClicked(city)}

        rvCities!!.layoutManager = layoutManager
        rvCities!!.adapter = adapter
    }

    override fun showError(error: String) {
        toast(error)
    }
    //endregion

    //region Activity Life Cycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(DataRepository(RemoteDataSource(), ForecastDataSource(this)))
        rvCities = findViewById(R.id.rvCities)
    }

    override fun onStart() {
        super.onStart()

        presenter!!.attachView(this)
        presenter!!.getAvailableCities()
    }

    override fun onStop() {
        super.onStop()
        presenter!!.detachView()
    }
    //endregion

    //region Toast
    private fun Context.toast(message: CharSequence) =
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    //endregion

    private fun cityClicked(city : String) {
        var intent:Intent = Intent(applicationContext, WeatherActivity::class.java)
        intent.putExtra(Constant.KEY_CITY_ID, city)
        startActivity(intent)
    }
}
