package com.acm.verywild.acm.data.db

import android.content.ContentValues
import android.util.Log
import com.acm.verywild.acm.extensions.parseListEx
import org.jetbrains.anko.db.select

/**
 * Created by lineplus on 28/08/2017.
 */
class CardDb(val cardDbHelper: CardDbTableHelper = CardDbTableHelper.instance) {


    fun saveCardUsedInfo(corp: String,
                         date: Long,
                         time: Long,
                         price: Long,
                         store: String,
                         extraText: String) = cardDbHelper.use {


        insert(CardUsedInfoTable.NAME, null, setContentValues(corp, date, time, price, store, extraText))
        getCardUsedInfoList()
    }

    fun getCardUsedInfoList() = cardDbHelper.use {

        val info = select(CardUsedInfoTable.NAME).parseListEx { CardUsedInfoData(HashMap(it)) }

        info?.let {
            for (i in it) {
                Log.d("lhd_read", i.corpName + " : " + i.price);
            }
        }
    }

    private fun setContentValues(corp: String,
                                 date: Long,
                                 time: Long,
                                 price: Long,
                                 store: String,
                                 extraText: String): ContentValues {
        val cv: ContentValues = ContentValues()
        cv.put(CardUsedInfoTable.CARD_CP_NAME, corp)
        cv.put(CardUsedInfoTable.USED_DATE, date)
        cv.put(CardUsedInfoTable.USED_TIME, time)
        cv.put(CardUsedInfoTable.PRICE, price)
        cv.put(CardUsedInfoTable.USED_ST_NAME, store)
        cv.put(CardUsedInfoTable.EXTRA_TEXT, extraText)
        return cv
    }
}