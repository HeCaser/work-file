package com.example.xqtest.scrollview

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.widget.NestedScrollView

/**
 * Created by lyj on 2020/4/20.
 */

open class SNBNestedScrollView : NestedScrollView {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    protected var bottomSheetBehavior: SNBBottomSheetBehavior<View>? = null
    private var placeholderView: View? = null
    var scrollByOutsideView = false
    private var onScrollChangeListener: OnScrollChangeListener? = null

    init {
        setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->

            if (!scrollByOutsideView) {
                bottomSheetBehavior?.let {
                    val isPlaceholderViewVisible = isPlaceholderViewVisible()
                    it.setSlideByOutsideView(isPlaceholderViewVisible)
                    it.setOutsideView(this)

                    if (isPlaceholderViewVisible) {
                        var slideHeight = measuredHeight - (placeholderView!!.top - getScrollY()) + it.peekHeight
                        if (scrollY == 0) {
                            slideHeight = 0
                        }
                        it.slideHeight = slideHeight
                    }
                }
            }

            scrollByOutsideView = false
            onScrollChangeListener?.onScrollChange(v, scrollX, scrollY, oldScrollX, oldScrollY)
        }
    }

    fun resetBottomSheetBehavior() {
        bottomSheetBehavior?.let {
            val y = (it.slideHeight - it.peekHeight) * -1
            scrollBy(0, y)
            it.setSlideByOutsideView(false)
            it.setState(SNBBottomSheetBehavior.STATE_COLLAPSED)
        }
    }

    fun bindSNBBottomSheetBehavior(bottomSheetBehavior: SNBBottomSheetBehavior<View>) {
        if (this.bottomSheetBehavior != null) {
            return
        }

        this.bottomSheetBehavior = bottomSheetBehavior
        val child = getChildAt(0) as ViewGroup

        placeholderView = View(context)
        placeholderView?.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, bottomSheetBehavior.getCollapsedOffset())
        child.addView(placeholderView)
    }

    fun updateBindSNBBottomSheetBehavior(bottomSheetBehavior: SNBBottomSheetBehavior<View>) {

        this.bottomSheetBehavior = bottomSheetBehavior
        val child = getChildAt(0) as ViewGroup
        if (placeholderView != null) {
            child.removeView(placeholderView)
        }
        placeholderView = View(context)
        placeholderView?.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, bottomSheetBehavior.getCollapsedOffset())
        child.addView(placeholderView)

    }

    private fun isPlaceholderViewVisible(): Boolean {
        if (placeholderView == null) {
            return false
        }

        val scrollBounds = Rect()
        getHitRect(scrollBounds)
        return placeholderView!!.getLocalVisibleRect(scrollBounds)
    }

    open fun isChildVisible(child: View?, totallyVisible: Boolean): Boolean {
        if (child == null) return false
        val rect = Rect()
        child.getLocalVisibleRect(rect)
        return if (totallyVisible) {
            rect.top == 0
        } else {
            rect.top >= 0
        }
    }

    fun setOnScrollChangeListener(listener: OnScrollChangeListener) {
        this.onScrollChangeListener = listener
    }

    interface OnScrollChangeListener {
        fun onScrollChange(v: NestedScrollView?, scrollX: Int, scrollY: Int,
                           oldScrollX: Int, oldScrollY: Int)
    }
}