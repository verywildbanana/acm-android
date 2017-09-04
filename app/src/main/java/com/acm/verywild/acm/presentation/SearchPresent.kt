package com.acm.verywild.acm.presentation

import android.util.Log
import com.acm.verywild.acm.model.UserRepository
import com.acm.verywild.acm.view.SearchListMvpView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by lineplus on 31/08/2017.
 */
class SearchPresent(private val userRepository: UserRepository) : BasePresenter<SearchListMvpView>() {

    var offset = 10
    var page = 1
    var loading = false
    var searchText: String = ""

    fun searchLocale() {
        Log.d("lhd_read", "SearchPresent~searchLocale~$searchText~$page")
        loading = true
        userRepository.searchLocale(searchText, offset, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t ->
                    Log.d("lhd_read", "UserRepository~searchLocale~success~" + t)
                    loading = true
                    view?.addSearchResultToList(t.items)
                    page++
                },
                        {
                            Log.d("lhd_read", "UserRepository~searchLocale~fail~")
                            loading = false
                            view?.showError()
                        })
    }

    fun resetPage() {
        page = 1
    }

    fun onScrollChanged(lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (!loading) {
            if (lastVisibleItemPosition >= totalItemCount - offset) {
                searchLocale()
            }
        }

        if (loading && lastVisibleItemPosition >= totalItemCount) {
        }
    }
}