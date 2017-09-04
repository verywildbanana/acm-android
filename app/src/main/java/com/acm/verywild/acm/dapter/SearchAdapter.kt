package com.acm.verywild.acm.dapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acm.verywild.acm.R
import com.acm.verywild.acm.data.entities.SearchItmeData
import kotlinx.android.synthetic.main.item_search_data.view.*

/**
 * Created by lineplus on 29/08/2017.
 */
class SearchAdapter(val searchItemData: MutableList<SearchItmeData>, val itemClick: (SearchItmeData) -> Unit)
    : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_data, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(searchItemData[position])
    }

    override fun getItemCount() = searchItemData.size


    fun addData(newData: Array<SearchItmeData>) {
        searchItemData.addAll(newData)
        notifyDataSetChanged()
    }

    fun clearUsers() {
        searchItemData.clear()
    }

    class ViewHolder(view: View, val itemClick: (SearchItmeData) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bindForecast(searchItemData: SearchItmeData) {
            with(searchItemData) {
                itemView.TV_title.text = title
                itemView.TV_address.text = address
//                Glide.with(this).load(url).into(itemView.IV_search)
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}