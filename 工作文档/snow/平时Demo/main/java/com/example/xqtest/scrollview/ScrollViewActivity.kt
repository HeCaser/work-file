package com.example.xqtest.scrollview

import android.os.Bundle
import android.util.Pair
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.xqtest.R
import kotlinx.android.synthetic.main.activity_scroll_view.*

/**
 * ScrollView 埋点，子 View 曝光，隐藏，展示时长等。
 */
class ScrollViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_view)
        initView()
    }

    private fun initView() {

        scrollView.setOnScrollListener(object : SNBObservableNestedScrollView.OnScrollListener {
            override fun onScrollStateChanged(
                view: SNBObservableNestedScrollView?,
                scrollState: Int
            ) {
                if (scrollState == SNBObservableNestedScrollView.SCROLL_STATE_IDLE){
                    showChildViewState()
                }
            }
        })
    }

    private fun showChildViewState(){
        val count = llContainer.childCount

        //Scroll view location on screen
        val scrollViewLocation = intArrayOf(0, 0)
        scrollView.getLocationOnScreen(scrollViewLocation)

        //Scroll view height
        val scrollViewHeight: Int = scrollView.height

        for (i in 0 until count){
            val child: View = llContainer.getChildAt(i)

            if ( child.visibility == View.VISIBLE) {
                val viewLocation = IntArray(2)
                child.getLocationOnScreen(viewLocation)
                val viewHeight: Int = child.height
                getViewVisibilityOnScrollStopped(
                    i+1,
                    scrollViewLocation,
                    scrollViewHeight,
                    viewLocation,
                    viewHeight
                )
            }
        }
    }

    private fun getViewVisibilityOnScrollStopped(
        pos:Int,
        scrollViewLocation: IntArray, //location of scroll view on screen
        scrollViewHeight: Int, // height of scroll view
        viewLocation: IntArray, // location of view on screen, you can use the method of view class's getLocationOnScreen method.
        viewHeight: Int //  height of view
    ) {
        println("hepan1  ==== view positon index=$pos ${viewLocation[0] }  ${viewLocation[1] }")
        var visiblePercent: Float
        val viewBottom = viewHeight + viewLocation[1] //Get the bottom of view.
        if (viewLocation[1] >= scrollViewLocation[1]) {  //if view's top is inside the scroll view.
            visiblePercent = 100f
            val scrollBottom =
                scrollViewHeight + scrollViewLocation[1] //Get the bottom of scroll view
            if (viewBottom >= scrollBottom) {   //If view's bottom is outside from scroll view
                val visiblePart =
                    scrollBottom - viewLocation[1] //Find the visible part of view by subtracting view's top from scrollview's bottom
                visiblePercent = visiblePart.toFloat() / viewHeight * 100
            }
//            Toast.makeText(
//                this,
//                "Visibility of the view: " + visiblePercent.toInt() + "%",
//                Toast.LENGTH_SHORT
//            ).show()
            println("hepan pos = $pos percenet = ${visiblePercent.toInt()} %")
        } else {      //if view's top is outside the scroll view.
            if (viewBottom > scrollViewLocation[1]) { //if view's bottom is outside the scroll view
                val visiblePart =
                    viewBottom - scrollViewLocation[1] //Find the visible part of view by subtracting scroll view's top from view's bottom
                visiblePercent = visiblePart.toFloat() / viewHeight * 100
//                Toast.makeText(
//                    this,
//                    "Visibility of the view: " + visiblePercent.toInt() + "%",
//                    Toast.LENGTH_SHORT
//                ).show()
                println("hepan pos = $pos percenet = ${visiblePercent.toInt()} %")

            }
        }
    }


}