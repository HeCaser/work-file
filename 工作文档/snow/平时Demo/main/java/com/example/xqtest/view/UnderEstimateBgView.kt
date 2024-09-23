package com.example.xqtest.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * @author : hepan
 * Data:2023/03/27
 * Description: 14.15 估值背景修改
 * https://xueqiu.feishu.cn/docx/Lcg3dizsJorXkoxMmL5c9WYxndf
 */
class UnderEstimateBgView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val mRadius = 14f

    private var paint = Paint()
    private var bgRectF: RectF? = null
    private var mSelectType: String? = ""


    // 色值 list 注意保持长度统一,并和 mItemSize 匹配
    private val mDefaultColorList = arrayListOf<Int>()
    private val mSelectColorList = arrayListOf<Int>()
    private val mItemSize = 3


    /**
     * 低估类型: 0 低估, 1适中 2 偏高
     */
    fun setEstimate(estimate: String?) {
        mSelectType = estimate
        invalidate()
    }

    init {
        paint.isAntiAlias = true
        mDefaultColorList.add(Color.GRAY)
        mDefaultColorList.add(Color.GREEN)
        mDefaultColorList.add(Color.LTGRAY)

        mSelectColorList.add(Color.RED)
        mSelectColorList.add(Color.RED)
        mSelectColorList.add(Color.RED)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bgRectF = RectF(0f, 0f, w.toFloat(), h.toFloat())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        bgRectF?.apply {
            val itemWidth = width() / mItemSize
            for (i in 0 until mItemSize) {
                paint.color = getBgColor(i)
                val path = Path()
                val rectf = RectF(itemWidth * i, this.top, itemWidth * (i + 1), this.bottom)
                path.addRoundRect(rectf, getCorners(i), Path.Direction.CW)
                canvas.drawPath(path, paint)
            }

        }

    }

    /**
     * 获取圆角参数
     * @param index 第几个
     */
    private fun getCorners(index: Int): FloatArray {
        when (index) {
            0 -> {
                return floatArrayOf(
                    mRadius, mRadius,
                    0f, 0f,
                    0f, 0f,
                    mRadius, mRadius
                )
            }
            mItemSize - 1 -> {
                return floatArrayOf(
                    0f, 0f,
                    mRadius, mRadius,
                    mRadius, mRadius,
                    0f, 0f
                )
            }
            else -> {
                return floatArrayOf(
                    0f, 0f,
                    0f, 0f,
                    0f, 0f,
                    0f, 0f
                )
            }
        }
    }

    /**
     * 获取背景色
     * @param index 第几个
     */
    private fun getBgColor(index: Int): Int {
        val type = mSelectType?.toIntOrNull()
        if (type == null || type < 0 || type >= mItemSize) {
            return mDefaultColorList[index]
        }
        return if ("$index" == mSelectType){
            // 选中
            mSelectColorList[index]
        }else{
            mDefaultColorList[index]
        }
    }
}