package com.acm.verywild.acm

import android.app.Application
import com.acm.verywild.acm.di.components.ApplicationComponent
import com.acm.verywild.acm.di.components.DaggerApplicationComponent
import com.acm.verywild.acm.di.modules.ApplicationModule
import com.acm.verywild.acm.extensions.DelegatesExt

/**
 * Created by lineplus on 28/08/2017.
 */
class App : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        component.inject(this)
    }
}