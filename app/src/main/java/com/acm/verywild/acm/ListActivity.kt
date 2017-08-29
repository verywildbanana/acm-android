package com.acm.verywild.acm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.acm.verywild.acm.dapter.CardInfoAdapter
import com.acm.verywild.acm.data.db.CardDb
import com.acm.verywild.acm.data.db.CardUsedInfoData
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class ListActivity : AppCompatActivity() {

    val cardDb: CardDb = CardDb()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        RC_cardInfo.layoutManager = LinearLayoutManager(this)

    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    private fun loadForecast() = async(UI) {
        val result = bg { cardDb.getCardUsedInfoList() }
        updateUI(result.await())
    }

    private fun updateUI(cardInfoList: List<CardUsedInfoData>) {
        val adapter = CardInfoAdapter(cardInfoList) {
            Log.d("lhd_read", "click~$it")
        }
        RC_cardInfo.adapter = adapter
    }
}