package com.example.xqtest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * @author: hepan
 * @date: 2022/6/14
 * @desc:
 */
data class NestingAddItems(
    @Expose
    @SerializedName("list")
    val list: List<NestingAddStockInfo>?,
    @Expose
    @SerializedName("subtitle1")
    val subtitle1: String?,
    @Expose @SerializedName("title")
    val title: String?
)

data class NestingAddStockInfo(
    @Expose
    @SerializedName("current")
    val current: Double?,
    @Expose
    @SerializedName("custom_percent")
    val customPercent: Double?,
    @Expose
    @SerializedName("custom_tag")
    val customTag: String?,
    @Expose
    @SerializedName("name")
    val name: String?,
    @Expose
    @SerializedName("percent")
    val percent: Double?,
    @Expose
    @SerializedName("symbol")
    val symbol: String?,
    @Expose
    @SerializedName("tag")
    val tag: String?
)