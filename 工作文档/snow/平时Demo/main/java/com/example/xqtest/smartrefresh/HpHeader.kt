package com.example.xqtest.smartrefresh

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.xqtest.R
import com.scwang.smart.drawable.ProgressDrawable
import com.scwang.smart.refresh.layout.api.RefreshHeader
import com.scwang.smart.refresh.layout.api.RefreshKernel
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.scwang.smart.refresh.layout.constant.SpinnerStyle


/**
 * @author: hepan
 * @date: 2022/3/25
 * @desc: 自定义刷新头
 */
class HpHeader @JvmOverloads constructor(context: Context, attrs: AttributeSet?=null, defStyleAttr: Int=0) :
    LinearLayout(context, attrs, defStyleAttr),RefreshHeader {
    private var mHeaderText //标题文本
            : TextView? = null

    init {
        gravity = Gravity.CENTER_HORIZONTAL
        mHeaderText = TextView(context).apply {
            text="刷新"
        }
        LayoutInflater.from(context).inflate(R.layout.activity_main,this,false)

        addView(mHeaderText, 0)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = MeasureSpec.makeMeasureSpec(300,MeasureSpec.EXACTLY)
        super.onMeasure(widthMeasureSpec, height)
    }

    override fun onStateChanged(
        refreshLayout: RefreshLayout,
        oldState: RefreshState,
        newState: RefreshState
    ) {
        mHeaderText?.text="new state = ${newState.name}"
        when(newState){
            RefreshState.PullDownToRefresh->{}
            RefreshState.Refreshing->{}
            RefreshState.ReleaseToRefresh->{

            }
        }
    }

    override fun getView(): View {
        return this
    }

    override fun getSpinnerStyle(): SpinnerStyle {
        return SpinnerStyle.Translate // 平移
    }

    override fun setPrimaryColors(vararg colors: Int) {
    }

    override fun onInitialized(kernel: RefreshKernel, height: Int, maxDragHeight: Int) {
    }

    override fun onMoving(
        isDragging: Boolean,
        percent: Float,
        offset: Int,
        height: Int,
        maxDragHeight: Int
    ) {

    }

    override fun onReleased(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {
    }

    override fun onStartAnimator(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {
        mHeaderText?.text = "onStartAnimator"
    }

    override fun onFinish(refreshLayout: RefreshLayout, success: Boolean): Int {
        mHeaderText?.text = "onFinish success $success"
        return 500
    }

    override fun onHorizontalDrag(percentX: Float, offsetX: Int, offsetMax: Int) {
        mHeaderText?.text ="onHorizontalDrag"
    }

    override fun isSupportHorizontalDrag(): Boolean {
      return false
    }
}