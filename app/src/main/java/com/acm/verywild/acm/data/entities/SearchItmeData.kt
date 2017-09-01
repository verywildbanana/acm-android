package com.acm.verywild.acm.data.entities

/**
 * Created by lineplus on 31/08/2017.
 */
data class SearchItmeData(
        val title: String,
        val link: String,
        val category: String,
        val description: String,
        val telephone: String,
        val address: String,
        val roadAddress: String,
        val mapx: Long,
        val mapy: Long) {


}

/*
<item>
<title>조선옥</title>
<link />
<category>한식&gt;육류,고기요리</category>
<description>연탄불 한우갈비 전문점.</description>
<telephone>02-2266-0333</telephone>
<address>서울특별시 중구 을지로3가 229-1 </address>
<roadAddress>서울특별시 중구 을지로15길 6-5 </roadAddress>
<mapx>311277</mapx>
<mapy>552097</mapy>
</item>*/
