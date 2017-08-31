package com.acm.verywild.acm.presentation

import android.util.Log
import com.acm.verywild.acm.model.UserRepository

/**
 * Created by lineplus on 31/08/2017.
 */
class SearchPresent(private val userRepository: UserRepository) {

    fun searchLocale(text: String) {
        Log.d("lhd_read", "SearchPresent~searchLocale~$text")
        userRepository.searchLocale(text)
    }
}