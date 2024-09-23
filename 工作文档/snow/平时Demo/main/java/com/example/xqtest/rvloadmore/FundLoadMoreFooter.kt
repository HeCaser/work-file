package com.example.xqtest.rvloadmore

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.example.xqtest.R
import com.scwang.smart.refresh.classics.ClassicsAbstract
import com.scwang.smart.refresh.layout.api.RefreshFooter
import com.scwang.smart.refresh.layout.api.RefreshKernel
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.RefreshState
import kotlinx.android.synthetic.main.fund_load_more_footer.view.*

open class FundLoadMoreFooter @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ClassicsAbstract<FundLoadMoreFooter>(context, attrs, defStyleAttr), RefreshFooter {


    companion object{
        const val FINISH_DURATION = 5000L
    }
    protected var mTextPulling = "刷一刷，发现好基金"
    protected var mTextRelease = "释放立即加载"
    protected var mTextLoading = "为你发现好基金..."
    protected var mTextRefreshing = "为你发现好基金..."
    protected var mTextFinish = "又刷到 X 只好基金"
    protected var mTextFailed = "加载失败"
    protected var mTextNothing = "没有更多数据了"


    protected var mNoMoreData = false
    private  var mKernel: RefreshKernel?=null
    private val mNormalTextColor = 0xff797D8D
    private val mRefreshTextColor = 0xff287DFF
    init {
        LayoutInflater.from(context).inflate(R.layout.fund_load_more_footer, this)

        footerBg.setBackgroundResource(R.drawable.load_footer_bg_night)
        setBackgroundColor(resources.getColor(R.color.transparent))
        mTitleText = tvTitle
        loadingProgress.invisible()
        setPrimaryColor(Color.TRANSPARENT)
        mFinishDuration = FINISH_DURATION.toInt()
        // 去掉 ClassicsAbstract 中默认的垂直 Padding
        setPadding(0,1,0,1)
        // 基类中用到，这里直接创建，防止空指针
        mArrowView = ImageView(context)
        mProgressView = ImageView(context)
        mTitleText.text = mTextPulling
    }

    override fun setAccentColor(accentColor: Int): FundLoadMoreFooter {
        return super.setAccentColor(accentColor)
    }
//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        val heightSpec = MeasureSpec.makeMeasureSpec(400, MeasureSpec.EXACTLY)
//        super.onMeasure(widthMeasureSpec, heightSpec)
//    }

    override fun onFinish(refreshLayout: RefreshLayout, success: Boolean): Int {
        super.onFinish(refreshLayout, success)
        if (!mNoMoreData) {
            mTitleText.text = if (success) mTextFinish else mTextFailed
            loadingProgress.invisible()
            showLottie()
            return mFinishDuration
        }
        return 0
    }

    private fun showLottie(){
        ivLottieLoading.visible()
        ivLottieLoading.setAnimation("fund/fund_get_success.json")
        ivLottieLoading.playAnimation()
    }
    /**
     * 设置数据全部加载完成，将不能再次触发加载功能
     */
    override fun setNoMoreData(noMoreData: Boolean): Boolean {
        if (mNoMoreData != noMoreData) {
            mNoMoreData = noMoreData
            if (noMoreData) {
                mTitleText.text = mTextNothing
            } else {
                mTitleText.text = mTextPulling
            }
        }
        return true
    }

    override fun onStateChanged(
        refreshLayout: RefreshLayout,
        oldState: RefreshState,
        newState: RefreshState
    ) {
        if (!mNoMoreData) {
            setViewStyle(newState)
        }
    }

    override fun onInitialized(kernel: RefreshKernel, height: Int, maxDragHeight: Int) {
        super.onInitialized(kernel, height, maxDragHeight)
        mKernel = kernel
    }

    override fun onMoving(
        isDragging: Boolean,
        percent: Float,
        offset: Int,
        height: Int,
        maxDragHeight: Int
    ) {
//        setViewAlpha(percent)
        super.onMoving(isDragging, percent, offset, height, maxDragHeight)

    }

    /**
     * 根据状态设置样式
     */
   private fun setViewStyle(state:RefreshState){
        when (state) {
            RefreshState.None ,RefreshState.PullUpToLoad-> {
                ivArrow.visible()
                loadingProgress.invisible()
                ivLottieLoading.invisible()
                mTitleText.text = mTextPulling
                mTitleText.setTextColor(mNormalTextColor.toInt())
            }

            RefreshState.Loading, RefreshState.LoadReleased -> {
                ivArrow.invisible()
                ivLottieLoading.invisible()
                loadingProgress.visible()
                mTitleText.text = mTextLoading
                mTitleText.setTextColor(mRefreshTextColor.toInt())
            }
            RefreshState.ReleaseToLoad -> {
//                mTitleText.text = mTextRelease
//                mTitleText.setTextColor(mRefreshTextColor.toInt())
            }
            RefreshState.Refreshing -> {
                ivArrow.gone()
                mTitleText.setTextColor(mRefreshTextColor.toInt())
                mTitleText.text = mTextRefreshing+"000"
            }
//            RefreshState.LoadFinish -> {
//                ivArrow.gone()
//                mTitleText.setTextColor(mRefreshTextColor.toInt())
//            }
        }
    }


    fun setFinishText(msg:String){
        mTextFinish = msg
    }

//    fun setViewAlpha(alpha: Float) {
//        ivLoading.alpha = alpha
//    }



}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}