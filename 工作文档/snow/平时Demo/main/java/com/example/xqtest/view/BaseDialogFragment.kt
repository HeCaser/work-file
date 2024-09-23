package com.example.xqtest.view

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import androidx.annotation.Nullable
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.xqtest.R

/**
 * DialogFragment 基类
 */
abstract class BaseDialogFragment(
    private val mOutCancel: Boolean = true,
    private val mDimAmount: Float = 0.4f,
   private val mWidthDp: Int = 0,
    private val mHeightDp: Int = 0,
) : DialogFragment() {


    private lateinit var mContext: Context


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.dialog_style)
    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View {
        return getDialogView(inflater, container)
    }

    /**
     * 返回根 View. 基类不对 View 做处理。
     */
    abstract fun getDialogView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?
    ): View

    open fun isShowBottom(): Boolean {
        return false
    }

    override fun onStart() {
        super.onStart()
        initParams()
    }

    private fun initParams() {
        val window: Window? = dialog?.window
        if (window != null) {
            val params = window.attributes
            params.dimAmount = mDimAmount

            //设置dialog显示位置
            if (isShowBottom()) {
                params.gravity = Gravity.BOTTOM
            }

            //设置dialog宽度
            if (mWidthDp == 0) {
                params.width = getScreenWidth(mContext)
            } else {
                params.width = dp2px(mContext, mWidthDp.toFloat())
            }

            //设置dialog高度
            if (mHeightDp == 0) {
                params.height = WindowManager.LayoutParams.WRAP_CONTENT
            } else {
                params.height = dp2px(mContext, mHeightDp.toFloat())
            }

            window.attributes = params
        }
        isCancelable = mOutCancel
    }



    fun show(manager: FragmentManager): BaseDialogFragment {
        super.show(manager, System.currentTimeMillis().toString())
        return this
    }


    companion object {
        /**
         * 获取屏幕宽度
         * @param context
         * @return
         */
        fun getScreenWidth(context: Context): Int {
            val displayMetrics: DisplayMetrics = context.resources.displayMetrics
            return displayMetrics.widthPixels
        }

        fun dp2px(context: Context, dipValue: Float): Int {
            val scale: Float = context.resources.displayMetrics.density
            return (dipValue * scale + 0.5f).toInt()
        }
    }
}