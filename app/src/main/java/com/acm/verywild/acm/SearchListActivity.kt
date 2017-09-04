package com.acm.verywild.acm

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.acm.verywild.acm.dapter.SearchAdapter
import com.acm.verywild.acm.data.entities.SearchItmeData
import com.acm.verywild.acm.presentation.SearchPresent
import com.acm.verywild.acm.view.SearchListMvpView
import kotlinx.android.synthetic.main.activity_list.*

class SearchListActivity : AppCompatActivity(), SearchListMvpView {

    companion object {
        val SEARCH_TEXT = "SearchText"
        val RETURN_RESULT = "ReturnResult"
    }

    private val presenter: SearchPresent by lazy { App.instance.component.presenter() }
    private val adapter by lazy {
        SearchAdapter(mutableListOf<SearchItmeData>()) {
            Log.d("lhd_read", "click~$it")

            val t = intent
            t.putExtra(RETURN_RESULT, "result_test")
            setResult(Activity.RESULT_OK, t)
            finish()
        }
    }
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        initUi()

        presenter.searchText = intent.getStringExtra(SEARCH_TEXT)
        Log.d("lhd_read", "SearchListActivityIntentData~${presenter.searchText}")
        presenter.attachView(this)
        if (adapter.itemCount == 0) {
            presenter.searchLocale()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onResume() {
        super.onResume()
    }


    private fun initUi() {
        layoutManager = LinearLayoutManager(this)
        RC_cardInfo.layoutManager = layoutManager
        RC_cardInfo.adapter = adapter

        RC_cardInfo.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                val lastVisibleItemPosition = layoutManager.findFirstVisibleItemPosition() + layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                presenter.onScrollChanged(lastVisibleItemPosition, totalItemCount)
            }
        })

    }

    override fun addSearchResultToList(searchItemData: Array<SearchItmeData>) {
        adapter.addData(searchItemData)
    }

    override fun showError() {
        Toast.makeText(this, "Couldn't load data", Toast.LENGTH_SHORT).show()
    }
}