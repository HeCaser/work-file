package com.xueqiu.android.commonui.design.views

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.NumberPicker
import androidx.annotation.IntDef
import androidx.appcompat.widget.AppCompatTextView
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy


/**
 *

 * @Title:      DesignTextView
 * @Description:     java类作用描述
 * @Author:         chenhq
 * @CreateDate:     2021/1/19 4:39 PM
 * @UpdateUser:     更新者
 * @UpdateDate:     2021/1/19 4:39 PM
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
open class DesignTextView : AppCompatTextView {
    companion object {
        const val FONT_NORMAL = 0
        const val FONT_DIN = 1
        const val FONT_AUTO = 2

        const val STYLE_REGULAR = 0
        const val STYLE_MEDIUM = 1
        const val STYLE_BOLD = 2

    }


    private var mTextStyle = STYLE_REGULAR
    private var mFont = FONT_NORMAL
    private var mAutoResize = false
    private var mAutoResizeMixTextSize = 0f
    private var mHeightOptimize = false
    private var mIsFeed = false


    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {


    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun setTextSize(unit: Int, size: Float) {
        super.setTextSize(unit, size)

    }

    override fun getTopFadingEdgeStrength(): Float {
        return .9f
    }

    override fun getLeftFadingEdgeStrength(): Float {
        return super.getLeftFadingEdgeStrength()
    }

    fun setTypeface(typeface: Int) {

    }


    fun setIsFeed(isFeed: Boolean) {
        mIsFeed = isFeed
    }


    @IntDef(value = [Typeface.NORMAL, Typeface.BOLD, Typeface.ITALIC, Typeface.BOLD_ITALIC])
    @Retention(RetentionPolicy.SOURCE)
    annotation class Style


}