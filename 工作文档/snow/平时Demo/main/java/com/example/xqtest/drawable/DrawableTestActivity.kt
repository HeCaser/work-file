package com.example.xqtest.drawable

import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.example.xqtest.R
import kotlinx.android.synthetic.main.activity_drawable_test.*

class DrawableTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawable_test)

        llDrawable.setOnClickListener {
            testBgsWithSame()
        }
    }

    /**
     * 同一个drawable 给不同 View 设置背景,可以自适应宽高
     */
    private fun testBgsWithSame() {
        val shape = RoundRectShape(
            FloatArray(8) { 16f },
            null,
            null
        )
        val drawable = ShapeDrawable(shape)
        drawable.paint.setColor(Color.RED)
//        drawable.paint.style = Paint.Style.FILL

        val tv1 = TextView(this).apply {
            setText("0000")
            setBackgroundDrawable(drawable)
            setPadding(10, 10, 10, 10)
        }
        val tv2 = TextView(this).apply {
            setText("阿道夫ad放假啊阿道夫阿道夫")
            setBackgroundDrawable(drawable)
        }

        llDrawable.addView(
            tv1,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        llDrawable.addView(
            tv2,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
    }


}