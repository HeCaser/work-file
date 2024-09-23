package com.example.xqtest.scrollview

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.xqtest.R
import kotlinx.android.synthetic.main.activity_scroll_view_trace.*

/**
 * 埋点
 */
class ScrollViewTrackActivity : AppCompatActivity() {

    lateinit var mScrollViewHandler:ScrollViewTrackHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_view_trace)
        init()
    }

    private fun init(){
        mScrollViewHandler = ScrollViewTrackHandler(scrollView)
        mScrollViewHandler.findALlItemsAuto()

        scrollView.setOnScrollListener(object : SNBObservableNestedScrollView.OnScrollListener {
            override fun onScrollStateChanged(
                view: SNBObservableNestedScrollView?,
                scrollState: Int
            ) {
              if (scrollState == SNBObservableNestedScrollView.SCROLL_STATE_IDLE){

                  mScrollViewHandler.onScrollIdle()
                  testViewVisible(view1)
              }
            }
        })

        scrollView.postDelayed({
            println("hepan scroolview height = ${scrollView.height} llheight = ${llContainer.height}")
        },1000)
    }


    override fun onResumeFragments() {
        super.onResumeFragments()
        mScrollViewHandler.onPageShow()
    }
    override fun onPause() {
        super.onPause()
        mScrollViewHandler.onPageHidden()
    }


    /**
     * 验证子 View 是否可见, 可以按照比例,可以按照绝对值判断
     */
    private fun testViewVisible(child:View){
        val visibleRect = Rect()
        // view 有可见区域, 准确描述是其在父 View 中的可见区域, 不会自动识别覆盖在自己上面的蒙层 View
        if (child.getLocalVisibleRect(visibleRect)){
            val visibleHeight = visibleRect.height()
            val itemHeight= child.height


            if (visibleHeight >= itemHeight *0.8){
                println("hepan 展示范围大于 80% visibleHeight = $visibleHeight  itemHeight = $itemHeight")
            }
            if (visibleHeight>100){
                println("hepan 展示高度大于 100 px")
            }
        }
    }
}