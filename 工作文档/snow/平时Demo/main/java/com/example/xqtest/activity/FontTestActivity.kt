package com.example.xqtest.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.TypefaceSpan
import com.example.xqtest.MainActivity
import com.example.xqtest.R
import com.example.xqtest.util.FontUtil
import kotlinx.android.synthetic.main.activity_font_test.*

/**
 * 字体测试结论
 * din 字体会改变数字样式
 * din 字体的 regular medium ,对于汉字没有影响(和 textview)一样. 对数字的粗细有影响
 * din 字体的 bold, 数字和汉字都会加粗, 和 TextView 属性的  android:textStyle="bold" 效果类似.
 */
class FontTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_font_test)

        setFont()
        setAuto()


    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun finish() {
        super.finish()
        println("hepan view finish")


    }

    /**
     * @description 设置字体为 Din 字体. 适用于整个 TextView
     */
    private fun setFont() {
        val msg = "连续 1234567890abcd 季度正收益 "
        tvDefault.text = "$msg no font"

        tvRegular.text = "$msg din Regular"
        FontUtil.setNumberRegularFont(this, tvRegular)

        tvMedium.text = "$msg din Medium"
        FontUtil.setNumberMediumFont(this, tvMedium)

        tvBold.text = "$msg din Bold"
        FontUtil.setNumberBoldFont(this, tvBold)
    }

    /**
     * 部分设置为din 字体.
     * 配合手机系统字体粗细, 与 setFont 方法效果对比.
     */
    private fun setAuto() {
        for (i in 0 until 3) {
            val sbBuilder = SpannableStringBuilder()
            sbBuilder.append("连续 ")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val span = SpannableString("40 abcd")
                val typefaceSpan = when (i) {
                    0 -> {
                        TypefaceSpan(FontUtil.getNumberRegularFont(this@FontTestActivity))
                    }
                    1 -> TypefaceSpan(FontUtil.getNumberMediumFont(this))
                    else -> TypefaceSpan(FontUtil.getNumberBoldFont(this))
                }

                span.setSpan(typefaceSpan, 0, span.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
                sbBuilder.append(span)
            }
            sbBuilder.append(" 季度正收益")
            val type = when(i){
                0-> "Regular"
                1-> "Medium"
                else-> "Bold"
            }
            sbBuilder.append(type)
            if (i==0){
                tvRegular2.text = sbBuilder
            }
            if (i==1){
                tvMedium2.text = sbBuilder
            }
            if (i==2){
                tvBold2.text = sbBuilder
            }
        }
    }
}