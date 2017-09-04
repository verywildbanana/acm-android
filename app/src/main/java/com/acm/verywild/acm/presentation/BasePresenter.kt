package com.acm.verywild.acm.presentation

/**
 * Created by lineplus on 04/09/2017.
 */
abstract class BasePresenter<T> {

    var view: T? = null
        private set

    fun attachView(view: T) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

    val isViewAttached: Boolean
        get() = view != null
}