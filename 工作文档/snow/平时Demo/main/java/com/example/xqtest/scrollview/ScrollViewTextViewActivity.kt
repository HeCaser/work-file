package com.example.xqtest.scrollview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.NestedScrollView
import com.example.xqtest.R
import kotlinx.android.synthetic.main.activity_scroll_view_fix_head.*
import kotlinx.android.synthetic.main.activity_scroll_view_text_view.*

/**
 * 根据滚动调整 tv 大小,使其看起来有个过度动画
 * https://stackoverflow.com/questions/73905085/how-to-animate-textview-text-to-resize-when-scrollview-is-scrolling
 *
 * 利用 setTextSize 改变文字大小会有闪动, 用 scale 实现会好一些
 */
class ScrollViewTextViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_view_text_view)
        scrollViewTv.setOnScrollChangeListener(object : SNBNestedScrollView.OnScrollChangeListener {
            override fun onScrollChange(
                v: NestedScrollView?,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {
                val height = v?.height ?: 1
                val scrollPercent = scrollY / (height * 1.0f)

                tvAnimation.pivotX = 0f
                tvAnimation.pivotY = 0f
                tvAnimation.scaleX = scrollPercent + 0.5f
                tvAnimation.scaleY = scrollPercent + 0.5f


            }
        })
    }
}