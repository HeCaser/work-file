package com.example.xqtest.smartrefresh

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.example.xqtest.R
import com.scwang.smart.refresh.classics.ClassicsAbstract
import com.scwang.smart.refresh.layout.api.RefreshFooter

/**
 * @author: hepan
 * @date: 2022/3/25
 * @desc: 雪球加载更多 View
 */
class SnowBallLoadingFooter @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ClassicsAbstract<SnowBallLoadingFooter>(context, attrs,0), RefreshFooter {


    init {
        LayoutInflater.from(context).inflate(R.layout.snow_ball_loading_footer,this)
        mTitleText = findViewById(R.id.mTitleText)
        mProgressView = findViewById(R.id.ivProgress)
        mArrowView = findViewById(R.id.mArrowView)
    }

}