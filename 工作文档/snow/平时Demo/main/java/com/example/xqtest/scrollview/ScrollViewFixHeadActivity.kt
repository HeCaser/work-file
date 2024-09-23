package com.example.xqtest.scrollview

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.example.xqtest.MainActivity
import com.example.xqtest.R
import com.example.xqtest.view.FundTabLayout
import kotlinx.android.synthetic.main.activity_scroll_view_fix_head.*


/**
 * 固定顶部 View
 */
class ScrollViewFixHeadActivity : AppCompatActivity() {

    private val mAnchorViewList = arrayListOf<View>()


    private var isScrolling = false
    private var scrollAnimationRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_view_fix_head)
        initView()
    }

    private fun initView() {
        val list = arrayListOf<String>()
        for (i in 0 until 6) {
            list.add("${i + 1}")
        }
        println("hepan 设置数据")
        tabTop.setTabs(list)
        tabInner.visibility = View.VISIBLE
        tabInner.setTabs(list)

        mAnchorViewList.clear()
        mAnchorViewList.add(view1)
        mAnchorViewList.add(view2)
        mAnchorViewList.add(view3)
        mAnchorViewList.add(view4)
        mAnchorViewList.add(view5)
        mAnchorViewList.add(view6)

        scrollView.setOnScrollChangeListener(object : SNBNestedScrollView.OnScrollChangeListener {
            override fun onScrollChange(
                v: NestedScrollView?,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {

                val firstTop: Int = tabInner.top
                if (scrollY >= firstTop) {
                    if (tabTop.visibility != View.VISIBLE){
                        tabTop.setVisibility(View.VISIBLE)
                        tabTop.scrollX = tabInner.scrollX
                    }
                } else {
                    if (tabTop.visibility != View.GONE){
                        tabTop.setVisibility(View.GONE)
                        tabInner.scrollX = tabTop.scrollX
                    }
                }

                if (scrollAnimationRunning) {
                    return
                }
                setSelectTabItem(scrollY + getOffset())

            }
        })

        scrollView.setOnScrollListener(object : SNBObservableNestedScrollView.OnScrollListener {
            override fun onScrollStateChanged(
                view: SNBObservableNestedScrollView?,
                scrollState: Int
            ) {
                isScrolling = scrollState != SNBObservableNestedScrollView.SCROLL_STATE_IDLE
            }
        })

        tabInner.setOnTabSelectChangeListener(object : FundTabLayout.TabSelectChangeListener {
            override fun onTabSelectChanged(pos: Int, old: Int) {

            }

            override fun onTabClick(pos: Int) {
                tabTop.setSelectPos(pos)
                scrollToAnchorView(pos)
            }
        })
        tabTop.setOnTabSelectChangeListener(object : FundTabLayout.TabSelectChangeListener {
            override fun onTabSelectChanged(pos: Int, old: Int) {

            }

            override fun onTabClick(pos: Int) {
                tabInner.setSelectPos(pos)
                scrollToAnchorView(pos)
            }
        })
    }

    private fun getOffset(): Int {
        return tabTop.height
    }

    private fun scrollToAnchorView(pos: Int) {
        val anchorView = mAnchorViewList.get(pos)
        val y = anchorView.top - getOffset()
        scrollToPosition(y)

    }

    private fun setSelectTabItem(scrollY: Int) {
        var targetIndex = -1
        for (index in mAnchorViewList.indices) {
            val top: Int = mAnchorViewList[index].top
            if (scrollY >= top) {
                    targetIndex = index
            }
        }

        if (targetIndex != -1){
            tabInner.setSelectPos(targetIndex)
            tabTop.setSelectPos(targetIndex)
        }
    }

    private fun scrollToPosition(scrollY: Int) {
        scrollAnimationRunning = true
        val animator = ObjectAnimator.ofInt(scrollView, "scrollY", scrollY)
        animator.duration = 250L
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                scrollAnimationRunning = false
            }

            override fun onAnimationCancel(animation: Animator) {
                scrollAnimationRunning = false
            }

            override fun onAnimationRepeat(animation: Animator) {}
        })
        animator.start()
    }

}

