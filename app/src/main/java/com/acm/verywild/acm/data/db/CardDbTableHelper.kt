package com.acm.verywild.acm.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.acm.verywild.acm.App
import org.jetbrains.anko.db.*

/**
 * Created by lineplus on 28/08/2017.
 */
class CardDbTableHelper(ctx: Context = App.instance) : ManagedSQLiteOpenHelper(ctx, CardDbTableHelper.DB_NAME, null, CardDbTableHelper.DB_VERSION) {

    companion object {
        val DB_NAME = "cardInfo.db"
        val DB_VERSION = 1
        val instance by lazy { CardDbTableHelper() }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(CardUsedInfoTable.NAME, true,
                CardUsedInfoTable.ID to INTEGER + PRIMARY_KEY,
                CardUsedInfoTable.CARD_CP_NAME to TEXT,
                CardUsedInfoTable.USED_DATE to INTEGER,
                CardUsedInfoTable.USED_TIME to INTEGER,
                CardUsedInfoTable.PRICE to INTEGER,
                CardUsedInfoTable.USED_ST_NAME to TEXT,
                CardUsedInfoTable.EXTRA_TEXT to TEXT)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(CardUsedInfoTable.NAME, true)
        onCreate(db)
    }
}