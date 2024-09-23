package com.example.xqtest.rvloadmore

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xqtest.R
import com.example.xqtest.adapter.RvCommonAdapter
import com.example.xqtest.viewmodel.CommonDataModel
import kotlinx.android.synthetic.main.rv_load_more_activity.*


/**
 * 雪球需求，修改加载更多的交互。
 *
 * 上拉 Footer ，有文案，有 lottie。
 */
class RvLoadMoreActivity : AppCompatActivity() {

    private lateinit var viewModel: CommonDataModel
    private lateinit var mAdapter: RvCommonAdapter
    private val mDataList = ArrayList<String>()
    private var mPageNum = 1

    private var mDragRate = 0.8f  // 阻尼比率
    private var mFooterMaxDragRate = 1.1f //最大拖动比率(最大高度/Footer高度)
    private var mFooterTriggerRate = 0.9f //触发加载距离 与 FooterHeight 的比率

    private val mLoadFooterView: FundLoadMoreFooter by lazy {
        FundLoadMoreFooter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rv_load_more_activity)
        viewModel = ViewModelProviders.of(this).get(CommonDataModel::class.java)
        initListener()
        initViewAndData()
        getData()
    }

    fun initListener() {
        tvTop.setOnClickListener {
            getData()
        }
        tvScroll.setOnClickListener {
            recyclerView.smoothScrollToPosition(4)
        }
    }

    fun initViewAndData() {
        smartRefresh?.apply {
            setRefreshFooter(mLoadFooterView)
            setFooterTriggerRate(mFooterTriggerRate)
            setFooterMaxDragRate(mFooterMaxDragRate)
            setDragRate(mDragRate)
            setEnableRefresh(false)
            setEnableAutoLoadMore(false)
            setOnLoadMoreListener {
                viewModel.getStringList()
            }
        }
        mAdapter = RvCommonAdapter(mDataList)
        recyclerView.apply {
            addItemDecoration(CustomItemDecoration())
            setHasFixedSize(true)
            layoutManager =
                SnappingLinearLayoutManager(
                    this@RvLoadMoreActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = mAdapter
        }

        viewModel.mStringList.observe(this) {
            handleRequestSuccess(it)
        }

//       Handler().postDelayed({setViewLevel()},2000)

    }

    fun getData() {
        viewModel.getStringList()
    }

    private fun setViewLevel(){
        val size = smartRefresh.childCount
        for (i in 0 until size){
            val temp = smartRefresh.getChildAt(i)
            if (temp is FundLoadMoreFooter){
                println("hepan ")
                smartRefresh.bringChildToFront(temp)
            }
            println("hepan pos = $i view = ${smartRefresh.getChildAt(i)}")
        }
    }

    private fun handleRequestSuccess(l: List<String>) {
        val size = mDataList.size

        mLoadFooterView.setFinishText("又刷到 ${l.size} 只好基金")
        smartRefresh.finishLoadMore()
        mDataList.addAll(l)
        mAdapter.notifyDataSetChanged()

        if (mPageNum != 1) {
            // 滚动到目标位置，延时应该大于 Footer 的 finish 动画执行时间
            Handler().postDelayed({
                recyclerView.smoothScrollToPosition(size)
            }, FundLoadMoreFooter.FINISH_DURATION +300)
        }else{
            mDataList.addAll(l)
            mAdapter.notifyDataSetChanged()
        }
        mPageNum++


    }
}