package com.example.xqtest.smartrefresh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.xqtest.R
import com.scwang.smart.refresh.layout.SmartRefreshLayout

class SmartRefreshActivity : AppCompatActivity() {
    private lateinit var smartRefresh: SmartRefreshLayout
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smart_refresh)
        smartRefresh = findViewById(R.id.smartRefresh)
        recyclerView = findViewById(R.id.recyclerView)

        smartRefresh.setRefreshHeader(HpHeader(this))
        smartRefresh.setRefreshFooter(SnowBallLoadingFooter(this))

        smartRefresh.apply {
            setEnableAutoLoadMore(true)
            setOnRefreshListener {
                println("hepan onRefresh")
                postDelayed({smartRefresh.finishRefresh()},1000)
            }
            setOnLoadMoreListener {
                println("hepan onLoadMore")
                postDelayed({smartRefresh.finishLoadMore()},4000)
            }
        }

    }


}