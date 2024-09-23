package com.example.xqtest.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.xqtest.R
import com.example.xqtest.model.NestingAddStockInfo
import kotlinx.android.synthetic.main.nesting_add_optional_item_stock.view.*

/**
 * @author: hepan
 * @date: 2022/6/14
 * @desc: 标的信息展示
 */
class NestingAddStockItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    private val onSelectChanged: ((isSelected: Boolean) -> Unit)? = null
) : FrameLayout(context, attrs) {

    var stockInfoBean :NestingAddStockInfo?=null

    init {
        LayoutInflater.from(getContext())
            .inflate(R.layout.nesting_add_optional_item_stock, this, true)
        ivSelect.isSelected = true
        ivSelect.setOnClickListener { onSelectViewClick() }
    }


    // 选中按钮的点击逻辑
    private fun onSelectViewClick() {
        ivSelect.isSelected = !ivSelect.isSelected
        if (ivSelect.isSelected) {
            ivSelect.setImageResource(R.drawable.icon_m_checkbox_selected_day)
        } else {
            ivSelect.setImageResource(R.drawable.icon_m_checkbox_default_day)
        }
        onSelectChanged?.invoke(ivSelect.isSelected)
    }

    // 标的是否被选中
    fun isStockSelected() = ivSelect.isSelected


    fun setStockInfo(info: NestingAddStockInfo?) {
        stockInfoBean = info
        info?.apply {
            tvName.text = name
            tvTag.text = tag
            tvPercent.text = "$percent"
            tvCustomTag.text = customTag
        }
    }




}