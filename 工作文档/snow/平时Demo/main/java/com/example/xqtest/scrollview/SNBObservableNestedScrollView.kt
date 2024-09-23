package com.example.xqtest.scrollview

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Created by gonglei on 2020/5/18 1:46 PM
 *  基于SNBNestedScrollView，能监听滚动状态
 */
class SNBObservableNestedScrollView : SNBNestedScrollView {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    companion object {
        /**
         * The view is not scrolling. Note navigating the list using the trackball counts as
         * being in the idle state since these transitions are not animated.
         */
        const val SCROLL_STATE_IDLE = 0

        /**
         * The user is scrolling using touch, and their finger is still on the screen
         */
        const val SCROLL_STATE_TOUCH_SCROLL = 1

        /**
         * The user had previously been scrolling using touch and had performed a fling. The
         * animation is now coasting to a stop
         */
        const val SCROLL_STATE_FLING = 2

        const val MSG_SCROLL = 1

        const val CHECK_SCROLL_STOP_DELAY_MILLIS = 80L
    }

    interface OnScrollListener {
        fun onScrollStateChanged(view: SNBObservableNestedScrollView?, scrollState: Int)
    }

    private var mIsTouched = false
    private var mScrollState: Int = SCROLL_STATE_IDLE
    private var mOnScrollListener: OnScrollListener? = null

    private val mHandler: Handler = Handler(Looper.getMainLooper(), object : Handler.Callback {
        private var mLastY = Int.MIN_VALUE
        override fun handleMessage(msg: Message): Boolean {
            if (msg.what == MSG_SCROLL) {
                val scrollY = scrollY
                if (!mIsTouched && mLastY == scrollY) {
                    mLastY = Int.MIN_VALUE
                    setScrollState(SCROLL_STATE_IDLE)
                } else {
                    mLastY = scrollY
                    restartCheckStopTiming()
                }
                return true
            }
            return false
        }
    })

    private fun restartCheckStopTiming() {
        mHandler.removeMessages(MSG_SCROLL)
        mHandler.sendEmptyMessageDelayed(MSG_SCROLL, CHECK_SCROLL_STOP_DELAY_MILLIS)
    }

    fun setOnScrollListener(onScrollListener: OnScrollListener?) {
        mOnScrollListener = onScrollListener
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        handleDownEvent(ev)
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        handleUpEvent(ev)
        return super.onTouchEvent(ev)
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        if (mIsTouched) {
            setScrollState(SCROLL_STATE_TOUCH_SCROLL)
        } else {
            setScrollState(SCROLL_STATE_FLING)
            restartCheckStopTiming()
        }
    }

    private fun handleDownEvent(ev: MotionEvent) {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                mIsTouched = true
            }
        }
    }

    private fun handleUpEvent(ev: MotionEvent) {
        when (ev.action) {
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                mIsTouched = false
                restartCheckStopTiming()
            }
        }
    }

    private fun setScrollState(state: Int) {
        if (mScrollState != state) {
            mScrollState = state
            if (mOnScrollListener != null) {
                mOnScrollListener!!.onScrollStateChanged(this, state)
            }
        }
    }

    override fun onDetachedFromWindow() {
        mHandler.removeMessages(MSG_SCROLL)
        mOnScrollListener = null
        bottomSheetBehavior = null
        super.onDetachedFromWindow()
    }
}