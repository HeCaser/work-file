package com.example.xqtest.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

/**
 * @author: hepan
 * @date: 2022/9/21
 * @desc: 改变子 View 的位置.
 */
class FixItemLinearLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    var mFixView:View?=null
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)


        val count = childCount
        for (i in 0 until count){
            val view = getChildAt(i)
            if (view.tag == "fix"){
                mFixView = view
//
            }
        }
    }


    /**
     * 改变某个 View 的位置
     */
    fun changeLayout(){
        mFixView?.layout(0,0,30,40)
    }
}