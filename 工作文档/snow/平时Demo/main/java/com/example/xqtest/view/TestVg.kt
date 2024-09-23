package com.example.xqtest.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.TextView

class TestVg(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
    ViewGroup(context, attrs, defStyleAttr, defStyleRes) {

    init {
        val tv = TextView(getContext(),null).apply {
            text = "1"
        }
        addView(tv)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

    }

}