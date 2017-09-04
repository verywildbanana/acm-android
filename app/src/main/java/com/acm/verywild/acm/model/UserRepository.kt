package com.acm.verywild.acm.model

import com.acm.verywild.acm.model.services.SearchService

/**
 * Created by lineplus on 31/08/2017.
 */
class UserRepository(private val searchService: SearchService) {

    fun searchLocale(query: String, display: Int, start: Int) = searchService.searchLocale(query, display = display, start = start)
}