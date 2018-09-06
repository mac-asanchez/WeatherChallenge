package com.example.admin.weatherchallenge.view.base

interface BasePresenter<V : BaseView> {
    fun attachView(view: V)
    fun detachView()
}
