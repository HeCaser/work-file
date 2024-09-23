package com.example.xqtest.scrollview

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * @author: hepan
 * @date: 2022/9/20
 * @desc:
 */
class ItemScrollTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs),OnItemScrollListener {
    override fun onShow() {
        println("hepan $tag  onShow")
    }

    override fun onHide() {
        println("hepan $tag  onHide")
    }

    override fun onScroll() {
    }

    override fun onPageHidden() {
        println("hepan $tag  onPageHidden")
    }
}