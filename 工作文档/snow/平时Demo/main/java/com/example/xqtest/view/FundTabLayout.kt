package com.example.xqtest.view

import android.content.Context
import android.graphics.*
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.xqtest.R
import com.example.xqtest.util.UIUtil

/**
 * @author: hepan
 * @date: 2022/9/13
 * @desc: 基金分类 tab
 */
class FundTabLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : HorizontalScrollView(context, attrs) {

    // 左右 padding
    private val mHorizontalPadding = 36

    // Item 间隔
    private val mItemSpace = 18

    private val mItemContainer: LinearLayout = LinearLayout(getContext()).apply {
        val hDp = mHorizontalPadding
        setPadding(hDp, 0, hDp, 0)
    }

    private val mTabItemList = arrayListOf<TabItem>()
     var mSelectPos = 0
    private var mTabSelectChangeListener: TabSelectChangeListener? = null


    init {
        // Disable the Scroll Bar
        isHorizontalScrollBarEnabled = false
        super.addView(
            mItemContainer,
            0,
            LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT
            )
        )
    }


    fun setTabs(list: List<String>?) {
        post {
            mItemContainer.removeAllViews()
            mTabItemList.clear()
            mSelectPos = 0
            if (list.isNullOrEmpty()) return@post
            val w = getItemWidth(list.size)
            list.forEachIndexed { index, s ->
                val param = LinearLayout.LayoutParams(
                    w,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                if (index != 0) {
                    param.marginStart = mItemSpace
                }
                val item = TabItem(context, s)
                item.setSelect(index == mSelectPos)
                item.setOnClickListener {

                    mTabSelectChangeListener?.onTabClick(index)
                    setSelectPos(index)
                }
                mTabItemList.add(item)
                mItemContainer.addView(item, param)
            }
        }
    }

    fun setSelectPos(pos: Int) {
        if (pos < 0 || pos >= mTabItemList.size) {
            return
        }
        if (pos == mSelectPos) {
            return
        }

        mTabItemList[mSelectPos].setSelect(false)
        mTabItemList[pos].setSelect(true)
        mTabSelectChangeListener?.onTabSelectChanged(pos, mSelectPos)
        mSelectPos = pos
        autoScrollToFit()
    }


    private fun autoScrollToFit(){
        val item = mTabItemList[mSelectPos]
        val bonds = Rect()
        item.getGlobalVisibleRect(bonds)
        println("hepan pos = $mSelectPos   left = ${bonds.left}  right = ${bonds.right}")

        if (bonds.left <= 0) {
            scrollBy(-item.measuredWidth, 0)
        }
        if (bonds.right >= UIUtil.getScreenWidth(context)) {
            scrollBy(item.measuredWidth, 0)
        }
    }
    /**
     * 获取 Item 宽度
     * 规则: 分类数量在 4 个及以下等分
     * 分类数量 >4 支持横滑，每个宽度占屏幕宽度 19%，间距固定 6
     */
    private fun getItemWidth(size: Int): Int {
        val totalWidth =  measuredWidth
        return if (size <= 4) {
            (totalWidth - mHorizontalPadding * 2 - (size - 1) * mItemSpace) / size
        } else (totalWidth * 0.19f).toInt()
    }


    internal inner class TabItem(context: Context, name: String?) : LinearLayout(context) {

        private var isSelect = false
        private var tv: TextView? = null
        private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

        init {
            setWillNotDraw(false)
            orientation = VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL

            tv = TextView(context).apply {
                text = "$name"
                maxLines = 1
                ellipsize = TextUtils.TruncateAt.END
                gravity = Gravity.CENTER
            }

            val param = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 120)
            addView(tv, param)
        }

        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)
            if (isSelect) {
                canvas?.drawPath(getTrianglePath(), mPaint.apply {
                    color = resources.getColor(R.color.purple_200)
                })
            }
        }

        /**
         * 获取三角 path
         */
        private fun getTrianglePath(): Path {
            val path = Path()
            val xCenter = measuredWidth / 2f
            val triangleWidth = 66f
            val yStart = tv!!.bottom - 2f
            val triangleHeight = 38f
            val quadStartPercent = .7f

            path.moveTo(xCenter - triangleWidth / 2f, yStart)
            path.lineTo(
                xCenter - triangleWidth / 2f * (1 - quadStartPercent),
                yStart + triangleHeight * quadStartPercent
            )
            path.quadTo(
                xCenter,
                yStart + triangleHeight,
                xCenter + triangleWidth / 2f * (1 - quadStartPercent),
                yStart + triangleHeight * quadStartPercent
            )
            path.lineTo(xCenter + triangleWidth / 2f, yStart)
            path.close()
            return path
        }

        fun setSelect(s: Boolean) {
            isSelect = s
            setStyle()
            postInvalidate()
        }

        private fun setStyle() {
            tv?.apply {
                setBackgroundColor(resources.getColor(if (isSelect) R.color.purple_200 else R.color.white))
                setTextColor(resources.getColor(if (isSelect) R.color.white else R.color.black))
            }
        }



        override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        }
    }

    fun setOnTabSelectChangeListener(l: TabSelectChangeListener) {
        mTabSelectChangeListener = l
    }

    interface TabSelectChangeListener {
        fun onTabSelectChanged(pos: Int, old: Int)
        fun onTabClick(pos: Int)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var remeasure = false

        if ("hepan" == tag) {
            val size = mTabItemList.size
            if (mTabItemList.isNotEmpty() && mTabItemList[0].width == 0) {
                remeasure = true
                mTabItemList.forEach {
                    val newWidth = getItemWidth(size)
                    it.layoutParams.width = newWidth
                }
            }

        }
        if (remeasure) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}