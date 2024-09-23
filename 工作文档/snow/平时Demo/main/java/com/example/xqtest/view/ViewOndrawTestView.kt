package com.example.xqtest.view

import android.content.Context
import android.graphics.*
import android.os.SystemClock
import android.util.AttributeSet
import android.view.View
import kotlin.concurrent.thread

/**
 * @author: hepan
 * @date: 2022/8/16
 * @desc: 测试 onDraw 调用
 */
class ViewOndrawTestView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    init {
        invalidate()
        println("hepan 初始化调用 invalidate")
    }

    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(400,MeasureSpec.EXACTLY),MeasureSpec.makeMeasureSpec(400,MeasureSpec.EXACTLY))
        println("hepan onMeasure")
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        println("hepan ondraw 调用")

        thread {
            val start = SystemClock.elapsedRealtime()
            println("hepan 绘制开始 ${Thread.currentThread().name}")
            for (i in 0 until 4) {
                SystemClock.sleep(200)
                canvas.drawText("hahdfakhdfa $i", 30f, 10f, mPaint)
            }
            val end = SystemClock.elapsedRealtime()

            println("hepan 绘制结束 耗时 ${end-start} ms")

        }
        println("hepan ondraw 结束")

    }


}