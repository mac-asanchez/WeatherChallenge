package com.example.admin.weatherchallenge.view.main

import com.example.admin.weatherchallenge.manager.DataRepository

class MainPresenter(private val dataRepository: DataRepository) : MainContract.Presenter {
    private var view: MainContract.View? = null

    //region attach/detach view
    override fun attachView(view: MainContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
    //endregion

    override fun getAvailableCities() {
        val availableCities = dataRepository.availableCities
        view!!.onGetAvailableCities(availableCities)
    }

    companion object {
        private val TAG = MainPresenter::class.java.simpleName + "_TAG"
    }
}
