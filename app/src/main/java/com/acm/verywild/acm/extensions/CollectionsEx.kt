package com.acm.verywild.acm.extensions

/**
 * Created by lineplus on 28/08/2017.
 */
fun <K, V : Any> Map<K, V?>.toVarargArray(): Array<out Pair<K, V>> =
        map({ Pair(it.key, it.value!!) }).toTypedArray()

