package com.acm.verywild.acm.di.modules

import com.acm.verywild.acm.App
import com.acm.verywild.acm.model.UserRepository
import com.acm.verywild.acm.model.services.SearchService
import com.acm.verywild.acm.presentation.SearchPresent
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Created by lineplus on 31/08/2017.
 */
@Module
class ApplicationModule(val application: App) {
    private val BASE_URL = "https://openapi.naver.com/v1/search/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .baseUrl(BASE_URL)
            .build()

    @Provides
    @Singleton
    fun provideUserRepository(retrofit: Retrofit) = UserRepository(retrofit.create(SearchService::class.java))

    @Provides
    @Singleton
    fun providePresenter(userRepository: UserRepository) = SearchPresent(userRepository)

}