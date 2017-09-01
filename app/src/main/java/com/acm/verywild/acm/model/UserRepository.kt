package com.acm.verywild.acm.model

import android.util.Log
import com.acm.verywild.acm.model.services.SearchService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by lineplus on 31/08/2017.
 */
class UserRepository(private val searchService: SearchService) {

    fun searchLocale(query: String) {
        Log.d("lhd_read", "UserRepository~searchLocale")
        searchService.searchLocale(query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t ->
                    Log.d("lhd_read", "UserRepository~searchLocale~success~" + t)
                },
                        {
                            Log.d("lhd_read", "UserRepository~searchLocale~fail~")
                        })
    }
}