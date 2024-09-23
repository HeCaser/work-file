package com.example.xqtest.view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import com.example.xqtest.R

/**
 * @author: hepan
 * @date: 2022/4/21
 * @desc:
 */
class PingFangTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatTextView(context, attrs) {

    companion object {
        const val WEIGHT_REGULAR = 0
        const val WEIGHT_MEDIUM = 1
    }


    private var mWeight = WEIGHT_REGULAR

    init {
        context?.obtainStyledAttributes(
            attrs,
            R.styleable.PingFangTextView,
            0, 0
        )?.apply {
            try {
                mWeight = getInt(R.styleable.PingFangTextView_pingfang_weight, 0)
                setPingFangWeight(mWeight)
            } finally {
                recycle()
            }
        }
    }

    fun setPingFangWeight(w: Int) {
        when (w) {
            WEIGHT_MEDIUM -> {
                setTypeface(typeface, Typeface.BOLD)
            }
            else -> {
                typeface = Typeface.create(typeface, Typeface.NORMAL)
            }
        }
    }
}