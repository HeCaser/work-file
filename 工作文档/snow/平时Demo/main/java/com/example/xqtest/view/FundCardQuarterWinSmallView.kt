package com.example.xqtest.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.example.xqtest.R
import kotlinx.android.synthetic.main.fund_card_quarter_win_small.view.*
import kotlin.collections.ArrayList
import kotlin.random.Random

/**
 * @author: hepan
 * @date: 2022/5/9
 * @desc: 连续 x 季度盈利,小货卡
 */
class FundCardQuarterWinSmallView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val lineColor = Color.BLACK
    private val rectColor = Color.RED

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
    }
    private val mLinePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = lineColor
        strokeWidth = 1f
        val dash = dp2px(getContext(),2f)
        pathEffect = DashPathEffect(floatArrayOf(dash,dash), 0f)
    }


    var mList = ArrayList<Double>()

    // 阈值
    private val mRectMinSpacing = dp2px(getContext(), 2f)
    private val mRectMinWidth = dp2px(getContext(), 3f)
    private val mRectMaxWidth = dp2px(getContext(), 4f)

    // 横线纵坐标
    private var mTopLineY = .0f
    private var mBottomLineY = .0f

    // 柱状图信息
    private var mRectTopY = .0f
    private var mRectBottomY = .0f
    private var mRectScore = RectF()
    private val mRectRadius = dp2px(getContext(), 1f)
    private val mCircleRadius = dp2px(getContext(), 1f)
    private var mRectWidth = mRectMaxWidth
    private var mRectSpacing = dp2px(getContext(), 12f)

    init {
        View.inflate(context, R.layout.fund_card_quarter_win_small, this)
        setWillNotDraw(false)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        println("hepan onmeasure")
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        println("hepan onDraw")

        initParam()
        calculateWidthAndSpacing()
        drawRectAndLine(canvas)
    }

    private fun drawRectAndLine(canvas: Canvas) {
        if (mList.isEmpty()) return
        var minValue = mList[0]
        var maxValue = mList[0]


        mList.forEachIndexed { index, it ->
            if (it < minValue) {
                minValue = it
            }
            if (it > maxValue) {
                maxValue = it
            }
        }


        val size = mList.size
        var minValueDraw = false
        var maxValueDraw = false

        for (i in 0 until size) {
            val left = (mRectSpacing + mRectWidth) * i
            val right = left + mRectWidth

            // 所有值都一样
            val top = if (maxValue == minValue) {
                tvBottomWin.visibility = View.INVISIBLE
                mRectTopY
            } else {
                // 柱状图 top 值, y = -ax+b
                val a = (mRectBottomY - mRectTopY) / (maxValue - minValue)
                -a * (mList[i] - minValue) + mRectBottomY
            }

            mPaint.color = rectColor
            canvas.drawRoundRect(
                left,
                top.toFloat(),
                right,
                mRectScore.bottom,
                mRectRadius,
                mRectRadius,
                mPaint
            )

            // 最小值横线和圆点
            if (!minValueDraw && mList[i] == minValue && maxValue!=minValue) {
                minValueDraw = true
                mPaint.color = Color.WHITE
                canvas.drawCircle(left + mRectWidth / 2, mBottomLineY, mCircleRadius, mPaint)
                tvBottomWin.visibility = View.VISIBLE
                canvas.drawLine(right, mBottomLineY, width.toFloat(), mBottomLineY, mLinePaint)
            }
            // 最大值横线和圆点
            if (!maxValueDraw && mList[i] == maxValue) {
                maxValueDraw = true
                mPaint.color = Color.WHITE
                canvas.drawCircle(left + mRectWidth / 2, mTopLineY, mCircleRadius,mPaint )
                canvas.drawLine(right, mTopLineY, width.toFloat(), mTopLineY, mLinePaint)
            }
        }

    }

    private fun initParam() {
        mTopLineY = tvTopWin.top + tvTopWin.height / 2f
        mBottomLineY = tvBottomWin.top + tvBottomWin.height / 2f

        // 柱子纵坐标(最大/小) = 横线纵坐标 - 3dp
        mRectTopY = mTopLineY - dp2px(context, 3f)
        mRectBottomY = mBottomLineY - dp2px(context, 3f)

        // 柱状图绘制范围
        mRectScore.left = 1.0f
        mRectScore.top = 1.0f
        // 右侧增加冗余量
        mRectScore.right =
            (measuredWidth - tvTopWin.width - dp2px(context, 10f))
        mRectScore.bottom = (measuredHeight - tvRightTitle.height).toFloat()
    }

    private fun calculateWidthAndSpacing() {
        var size = mList.size
        if (size == 1) {
            return
        }
        mRectWidth = mRectMinWidth
        if (mRectMinWidth * size + mRectMinSpacing * (size - 1) > mRectScore.width()) {
            // 需要截取数据情况
            while (size > 1 && (mRectMinWidth * size + mRectMinSpacing * (size - 1) > mRectScore.width())) {
                size--
            }
            mList = ArrayList(mList.subList(0, size))
        } else {
            if (mRectMaxWidth * size + mRectMinSpacing * (size - 1) <= mRectScore.width()) {
                mRectWidth = mRectMaxWidth
            }
        }
        // 间距永远是均分
        mRectSpacing = (mRectScore.width() - mRectWidth * size) / (size - 1)
    }

    fun setData() {
        mList.clear()
        for (i in 5 until Random.nextInt(10, 30)) {
            mList.add(Random.nextDouble(0.0, 10.0))
//            mList.add(i.toDouble())
        }
        if (mList.isEmpty()) {
            return
        }
        invalidate()
    }

    private fun dp2px(context: Context, dipValue: Float): Float {
        val scale: Float = context.resources.displayMetrics.density
        return (dipValue * scale + 0.5f)
    }
}