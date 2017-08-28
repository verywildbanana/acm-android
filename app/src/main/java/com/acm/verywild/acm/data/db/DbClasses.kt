package com.acm.verywild.acm.data.db

/**
 * Created by lineplus on 28/08/2017.
 */
data class CardUsedInfoData(val map: MutableMap<String, Any?>) {

    var id: Long by map
    var corpName: String by map
    var usedDate: Long by map
    var usedTime: Long by map
    var price: Long by map
    var usedStoreName: String by map
    var extraText: String by map

    constructor(id: Long,
                corpName: String,
                usedDate: Long,
                usedTime: Long,
                price: Long,
                usedStoreName: String,
                extraText: String) : this(HashMap()) {

        this.id = id
        this.corpName = corpName
        this.usedDate = usedDate
        this.usedTime = usedTime
        this.price = price
        this.usedStoreName = usedStoreName
        this.extraText = extraText
    }
}