package com.acm.verywild.acm.model.services

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created by lineplus on 31/08/2017.
 */
interface SearchService {

    @Headers("X-Naver-Client-Id:tXI8EllTJpddHf62ornc", "X-Naver-Client-Secret:Ojg85iWAeK")
    @GET("shop.json")
    fun searchLocale(@Query("query") query: String,
                     @Query("display") display: Int = 10,
                     @Query("start") start: Int = 1,
                     @Query("sort") sort: String = "sim"): Single<Any>
}
