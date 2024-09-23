package com.example.xqtest.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * @author: hepan
 * @date: 2022/8/16
 * @desc:
 */
class BlurView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    val mBlurPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
            style = Paint.Style.FILL
        maskFilter = BlurMaskFilter(2f, BlurMaskFilter.Blur.NORMAL)
    }

    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        val rect = Rect(30,40,width-30,height-30)
//        canvas.drawRect(rect,mPaint)
//        mPaint.setColor(Color.BLUE)
//        canvas.drawCircle(100f,100f,50f,mPaint)
//        mPaint.textSize = 60f
        canvas.drawText("hahdfakhdfa",30f,100f,mPaint)
        drawBlur(canvas)
    }

    private fun drawBlur(canvas: Canvas) {
        val rect = Rect(0,0,width,height)

        canvas.drawRect(rect,mBlurPaint)
    }
}