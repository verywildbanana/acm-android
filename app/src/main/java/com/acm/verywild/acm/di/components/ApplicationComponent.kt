package com.acm.verywild.acm.di.components

import com.acm.verywild.acm.App
import com.acm.verywild.acm.di.modules.ApplicationModule
import com.acm.verywild.acm.presentation.SearchPresent
import dagger.Component
import javax.inject.Singleton

/**
 * Created by lineplus on 31/08/2017.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(application: App)
    fun presenter(): SearchPresent
}