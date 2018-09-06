package com.example.admin.weatherchallenge.view.main

import com.example.admin.weatherchallenge.view.base.BasePresenter
import com.example.admin.weatherchallenge.view.base.BaseView

//interface used for communication
interface MainContract {
    //for communication from Presenter to View
    interface View : BaseView {
        fun onGetAvailableCities(availableCities: List<String>)
    }

    //for communication from View to presenter
    interface Presenter : BasePresenter<View> {
        fun getAvailableCities()
    }
}
