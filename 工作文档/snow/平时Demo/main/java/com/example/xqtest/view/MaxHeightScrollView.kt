package com.example.xqtest.view

import android.content.Context
import android.util.AttributeSet
import androidx.core.widget.NestedScrollView
import com.example.xqtest.R

/**
 *

 * @Title:      MaxHeightScrollView
 * @Description:     java类作用描述
 * @Author:         chenhq
 * @CreateDate:     2019-10-26 18:42
 * @UpdateUser:     更新者
 * @UpdateDate:     2019-10-26 18:42
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */

class MaxHeightScrollView : NestedScrollView {
    private var maxHeight: Float

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.MaxHeightScrollView)
        maxHeight = ta.getDimension(R.styleable.MaxHeightScrollView_maxHeight, 0f)
        ta.recycle()

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var newHeightMeasureSpec = heightMeasureSpec
        if (maxHeight > 0) {
            newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(maxHeight.toInt(), MeasureSpec.AT_MOST)
        }

        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec)
    }

    fun setMaxHeight(maxHeight: Float){
        this.maxHeight = maxHeight
        requestLayout()
    }


}