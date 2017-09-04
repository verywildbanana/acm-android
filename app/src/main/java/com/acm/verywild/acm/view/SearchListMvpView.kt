package com.acm.verywild.acm.view

import com.acm.verywild.acm.data.entities.SearchItmeData

/**
 * Created by lineplus on 04/09/2017.
 */
interface SearchListMvpView {
    fun addSearchResultToList(searchItemData: Array<SearchItmeData>)
    fun showError()
}