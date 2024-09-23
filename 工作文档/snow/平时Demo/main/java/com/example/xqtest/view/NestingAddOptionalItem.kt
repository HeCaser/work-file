package com.example.xqtest.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.example.xqtest.R
import com.example.xqtest.extend.dp2pxInt
import com.example.xqtest.model.NestingAddItems
import com.example.xqtest.model.NestingAddStockInfo
import kotlinx.android.synthetic.main.nesting_add_optional_item.view.*

/**
 * @author: hepan
 * @date: 2022/6/14
 * @desc: 一键加自选 Item view
 */
class NestingAddOptionalItem @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    private var onSelectChanged: ((isSelected: Boolean) -> Unit)? = null
) : FrameLayout(context, attrs) {


    private val mStockViewList = arrayListOf<NestingAddStockItem>()

    init {
        LayoutInflater.from(getContext())
            .inflate(R.layout.nesting_add_optional_item, this, true)
    }

    fun setItemInfo(item: NestingAddItems) {
        mStockViewList.clear()
        tvTitle.text = item.title
        tvSubTitle.text = item.subtitle1
        val stocks = item.list
        if (!stocks.isNullOrEmpty()) {
            stocks.forEachIndexed { index, nestingAddStockInfo ->
                val stockView = NestingAddStockItem(context) {
                    onSelectChanged?.invoke(it)
                }
                stockView.setStockInfo(nestingAddStockInfo)
                val param = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                if (index != 0) {
                    param.topMargin = context.dp2pxInt(8f)
                }
                mStockViewList.add(stockView)
                llStockList.addView(stockView, param)
            }
        }
    }

    // 获取选中的数量
    fun getSelectedCount(): Int {
        var count = 0
        mStockViewList.forEach {
            if (it.isStockSelected()) {
                count++
            }
        }
        return count
    }

    fun getSelectedStockInfo(): List<NestingAddStockInfo> {
        val list = arrayListOf<NestingAddStockInfo>()
        mStockViewList.forEach {
            if (it.isStockSelected() && it.stockInfoBean != null) {
                list.add(it.stockInfoBean!!)
            }
        }
        return list
    }

}