package com.example.xqtest.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.xqtest.R
import com.google.android.material.appbar.AppBarLayout
import kotlin.random.Random


/**
 * 私募回访列表 View
 */

/**
 *  @JvmOverloads 会自动产生重载构造方法, 注意,只会针对可为空的字段产生.
 *  如果构造参数不可为空类型,则构造参数必定包含此字段.
 *  在 xml 中使用 View 要求有 (Contex,AttributeSet) 两个入参的构造方法,如果 defStyleAttr 的类型是 Int, 则不会产生需要的
 *  构造方案.
 */
class PrivatePlacementFeedBackView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int?=0,
    defStyleRes: Int?=0
) :
    FrameLayout(context, attrs) {

    var ivClose: ImageView
    var tvTitle: TextView
    var llItems: LinearLayout
    init {
        val view = LayoutInflater.from(getContext())
            .inflate(R.layout.view_private_placement_feedback, this, true)
        ivClose = view.findViewById(R.id.ivClose)
        tvTitle = view.findViewById(R.id.tvTitle)
        llItems = view.findViewById(R.id.llItems)

        tvTitle.setOnClickListener {
           setData(ArrayList())
        }
    }

     fun setData(data: ArrayList<String>) {
         val size = Random.nextInt(4)+1
         llItems.removeAllViews()

    }



}