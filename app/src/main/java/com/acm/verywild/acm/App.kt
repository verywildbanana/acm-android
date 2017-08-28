package com.acm.verywild.acm

import android.app.Application
import com.acm.verywild.acm.extensions.DelegatesExt

/**
 * Created by lineplus on 28/08/2017.
 */
class App : Application() {

    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}