package com.example.xqtest.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.xqtest.R
import com.example.xqtest.model.NestingAddItems
import com.example.xqtest.model.NestingAddStockInfo
import kotlinx.android.synthetic.main.nesting_add_optional_view.view.*

/**
 * @author: hepan
 * @date: 2022/6/14
 * @desc: 一键加自选 View
 */
class NestingAddOptionalView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val itemViewList = arrayListOf<NestingAddOptionalItem>()

    init {
        LayoutInflater.from(getContext())
            .inflate(R.layout.nesting_add_optional_view, this, true)
        tvAddAll.setOnClickListener { handleAddAll() }
    }


    fun setItems(list: List<NestingAddItems>) {
        itemViewList.clear()
        llItemList.removeAllViews()
        tvAddOptionalTitle.text = "空空如也，挑选你感兴趣的基金吧"
        list.forEach {
            val itemView = NestingAddOptionalItem(context) {
                onSelectChanged()
            }
            itemViewList.add(itemView)
            itemView.setItemInfo(it)
            llItemList.addView(itemView)
        }
    }

    private fun onSelectChanged() {
        var count = 0
        itemViewList.forEach {
            count += it.getSelectedCount()
        }
        tvAddAll.text = "$count"

    }

    private fun handleAddAll() {
        val list = getSelectedStockInfo()
        println("hepan 选中list = $list")
    }

    // 获取选中的标的数据
    private fun getSelectedStockInfo(): List<NestingAddStockInfo> {
        val list = arrayListOf<NestingAddStockInfo>()
        itemViewList.forEach {
            list.addAll(it.getSelectedStockInfo())
        }
        return list
    }


}