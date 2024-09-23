package com.example.xqtest.bugfix

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xqtest.R
import com.example.xqtest.adapter.RvCommonAdapter
import kotlinx.android.synthetic.main.activity_fund_detail_scroll_view_bug.*

/**
 * 模拟bug,
 * 个基页-历史业绩左右滑动后，切到近期净值 不能上下滑动页面.
 *
 * 复现步骤
 * 1. 一个手指横向滑动子 RecyclerView
 * 2. 同时用另一个手指点击切换按钮,隐藏最外层 RecyclerView, 展示 viewInner
 * 3. 此时滑动最外层 SNBObservableNestedScrollView
 * 现象: 当手指滑动未设置点击事件的子 View 时, SNBObservableNestedScrollView 可以滑动. 当滑动设置了点击事件的子 View 时, SNBObservableNestedScrollView 不会滑动,但是会触发子View的点击回调(满足一定条件)
 *
 * 解决办法: 手动停止 RecyclerView 的的内部滚动逻辑 stopNestedScroll()
 */
class FundDetailScrollViewBugActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fund_detail_scroll_view_bug)

        rvList.apply {
            layoutManager = LinearLayoutManager(this@FundDetailScrollViewBugActivity,LinearLayoutManager.VERTICAL,false)
            adapter = RowAdapter()
        }

        tvChange.setOnClickListener {
            if (rvList.visibility != View.GONE){
                if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){

                    rvList.focusable=View.NOT_FOCUSABLE
                }
                rvList.apply {
                    stopNestedScroll() // 解决办法
                    visibility = View.GONE
                }
                viewInner.visibility = View.VISIBLE
                viewInner.setOnClickListener {
                    println("hepan in 00")
                }
            }else{
                rvList.visibility = View.VISIBLE
                viewInner.visibility = View.GONE
            }
        }

//        view2.setOnClickListener {
//            println("hepan view2")
//        }
        // 设置了点击事件会引起滑动异常
        view1.setOnClickListener {
            println("hepan view1")
        }
    }


    class RowAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val rv = RecyclerView(parent.context).apply {
                layoutManager = LinearLayoutManager(parent.context,LinearLayoutManager.HORIZONTAL,false)
                layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT)
                val mDataList = ArrayList<String>()
                for (i in 0 until 10){
                    mDataList.add("$i")
                }
                adapter = RvCommonAdapter(mDataList)
            }

            return RvCommonHolder(rv)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as RvCommonHolder).apply {
                holder.rv
            }
        }

        override fun getItemCount(): Int {
            return 3
        }


        inner class RvCommonHolder(view: RecyclerView) : RecyclerView.ViewHolder(view) {
            val rv = view
        }
    }
}