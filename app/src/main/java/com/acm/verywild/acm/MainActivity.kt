package com.acm.verywild.acm

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.acm.verywild.acm.data.db.CardDb
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivityForResult

class MainActivity : AppCompatActivity() {

    companion object {
        val SELECT_STORE = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BT_confirm.setOnClickListener { v ->

            setAnalysisList("신한체크승인 이*동(0*1*) 08/24 13:56 8,000원 동상이몽 잔액21,845,615원")
//            setAnalysisList("씨티카드(7*1*)\n" +
//                    "이＊동님\n" +
//                    "08/19 10:03\n" +
//                    "일시불 4,700원\n" +
//                    "누계 317,760원\n" +
//                    "개인택시한국스마트카")
//            setAnalysisList(ET_paste.text.toString())

//            setAnalysisList("하나(4*9*) 승인 신*현님 9,000원 일시불 07/21 10:51 세븐일레븐미아 누적 843,970원 ")
        }
        BT_send.setOnClickListener { v ->
            startActivityForResult<SearchListActivity>(SELECT_STORE, SearchListActivity.SEARCH_TEXT to "김밥천국")
//            startActivity<SearchListActivity>(SearchListActivity.SEARCH_TEXT to "김밥천국")
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("lhd_read", "onActivityResult_$requestCode , $resultCode")
        when (setOf(requestCode, resultCode)) {
            setOf(SELECT_STORE, Activity.RESULT_OK) -> Log.d("lhd_read", "onActivityResult_OK")

        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private val cardCorpNames = listOf("신한", "국민", "씨티")
    private val cardPriceExtraTexts = listOf("일시불", "누계", "잔액", "누적")
    private var checkList: MutableCollection<String>? = null

    private var catchCardCorpName: String = " "
    private var catchCardDate: String? = null
    private var catchCardTime: String? = null
    private var catchCardPrice: String? = null
    private var catchCardUsedStoreName: String = " "
    private val cardDb: CardDb = CardDb()

    private fun setAnalysisList(text: String) {
        val textList = text.replace("\n", " ").replace(" ", "#$#").split("#$#").filter { it != null && !it.isBlank() }
        checkList = mutableListOf()
        textList.forEachIndexed textLoop@ { index, searchText ->
            (checkList as MutableList<String>).add(searchText)

            Log.d("lhd_read", "msg~" + searchText)
            if (catchCardCorpName.isNullOrBlank()) {
                cardCorpNames.forEachIndexed { i, corp ->
                    if (searchText.contains(corp)) {
                        catchCardCorpName = corp
                        (checkList as MutableList<String>).remove(searchText)
                        return@textLoop
                    }
                }
            }

            if (catchCardDate.isNullOrBlank() && searchText.matches(Regex("\\d{2}/\\d{2}"))) {
                catchCardDate = searchText
                (checkList as MutableList<String>).remove(searchText)
            }

            if (catchCardTime.isNullOrBlank() && searchText.matches(Regex("\\d{2}:\\d{2}"))) {
                catchCardTime = searchText
                (checkList as MutableList<String>).remove(searchText)
            }

            if (searchText.contains("원")) {
                if (catchCardPrice.isNullOrBlank()) {
                    catchCardPrice = searchText
                }
                (checkList as MutableList<String>).remove(searchText)
            }

            if (searchText.contains("*") || searchText.contains("＊")) {

                (checkList as MutableList<String>).remove(searchText)
            }
        }

        (checkList as MutableList<String>).forEach {
            cardPriceExtraTexts.forEachIndexed { index, s ->
                if (!it.equals(s)) {
                    catchCardUsedStoreName = it
                }
            }
        }
        Log.d("lhd_read", "catch~cardCorp~" + catchCardCorpName)
        Log.d("lhd_read", "catch~date~" + catchCardDate)
        Log.d("lhd_read", "catch~time~" + catchCardTime)
        Log.d("lhd_read", "catch~catchCardPrice~" + catchCardPrice)
        Log.d("lhd_read", "catch~catchCardUsedStoreName~" + catchCardUsedStoreName)



        cardDb.saveCardUsedInfo(catchCardCorpName, 0, 0, 0, catchCardUsedStoreName, "d")
    }
}
