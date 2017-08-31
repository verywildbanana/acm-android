package com.acm.verywild.acm.dapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acm.verywild.acm.R
import com.acm.verywild.acm.data.db.CardUsedInfoData
import kotlinx.android.synthetic.main.item_card_info.view.*

/**
 * Created by lineplus on 29/08/2017.
 */
class CardInfoAdapter(val cardInfoData: List<CardUsedInfoData>, val itemClick: (CardUsedInfoData) -> Unit)
    : RecyclerView.Adapter<CardInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_info, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(cardInfoData[position])
    }

    override fun getItemCount() = cardInfoData.size


    class ViewHolder(view: View, val itemClick: (CardUsedInfoData) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bindForecast(cardInfo: CardUsedInfoData) {
            with(cardInfo) {
                itemView.TV_corp.text = corpName
                itemView.TV_time.text = "${usedDate} ${usedTime}"
                itemView.TV_price.text = id.toString()
                itemView.TV_store.text = usedStoreName
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}