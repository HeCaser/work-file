package com.example.xqtest.activity

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.xqtest.MainActivity
import com.example.xqtest.R

/**
 * 在开发中遇到一个问题, 利用 ClickSpan 设置点击点击事件, 当 ClickSpan 点击触发时, 会同时触发 TextView 的点击事件. 并且这种现象在雪球登录页面不出现, 但是弹框登录时出现.
 * 本类对上述问题做一个验证
 *
 * 结论: 正常使用 ClickSpan 都会存在此问题,
 *
 *Q 为什么 在雪球登录 Activity 反而正常?
 *A 点击了 ClickSpan 后调用  DJCommonRouter.getInstance().openPageByUrl(XueqiuLoginPage.this, url); 跳转新的 Activity ,
 *补充: 登录弹框也会启动 Activity 但是不会解决此问题
 */
class ClickSpanTestActivity : AppCompatActivity() {

    lateinit var btnClick: Button
    lateinit var tvProtocol: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click_span_test)
        btnClick = findViewById(R.id.btnClick)
        tvProtocol = findViewById(R.id.tvProtocol)
        initProtocol()
    }


    private fun initProtocol() {

        //我已阅读并同意 雪球服务协议、雪球隐私政策
        val mStrAgreement = "雪球服务协议"
        val mStrPolicy = "雪球隐私政策"
        val mStrInfo = String.format("我已阅读并同意 %1\$s、%2\$s ", mStrAgreement, mStrPolicy)

        val spannableString = SpannableString(mStrInfo)
        spannableString.setSpan(
            object : ClickableSpan() {
                override fun updateDrawState(ds: TextPaint) {
                    ds.isAntiAlias = true
                    ds.isUnderlineText = false
                }

                override fun onClick(widget: View) {
                    println("hepan 点击协议 1")
                }
            },
            mStrInfo.indexOf(mStrAgreement),
            mStrInfo.indexOf(mStrAgreement) + mStrAgreement.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )

        spannableString.setSpan(
            object : ClickableSpan() {
                override fun updateDrawState(ds: TextPaint) {
                    ds.isAntiAlias = true
                    ds.isUnderlineText = false
                }

                override fun onClick(widget: View) {
                    println("hepan 点击协议 2")
                    // 跳转新的 Activity 后不会再次触发 TextView 的点击事件
//                    startActivity(Intent(this@ClickSpanTestActivity, MainActivity::class.java))
                }
            }, mStrInfo.indexOf(mStrPolicy), mStrInfo.indexOf(mStrPolicy) + mStrPolicy.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )

        tvProtocol.text = spannableString
        tvProtocol.movementMethod = LinkMovementMethod.getInstance()
        tvProtocol.highlightColor = resources.getColor(android.R.color.transparent)

        tvProtocol.setOnClickListener {
            println("hepan textview 本身点击")
        }

        fixClickBug()
    }

    /**
     * 解决方案. 分开响应不同点击
     */
    private fun fixClickBug() {
        tvProtocol.setOnTouchListener { v: View?, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_UP && v is TextView) {
                val tv = v as TextView
                val layout = (v as TextView).layout
                var x = event.x.toInt()
                var y = event.y.toInt()
                x -= tv.totalPaddingLeft
                y -= tv.totalPaddingTop
                x += tv.scrollX
                y += tv.scrollY
                val line = layout.getLineForVertical(y)
                val offset = layout.getOffsetForHorizontal(line, x.toFloat())
                val charSequence = tv.text
                if (charSequence is Spannable) {
                    val spannable = charSequence as Spannable
                    val link = spannable.getSpans(
                        offset, offset,
                        ClickableSpan::class.java
                    )
                    if (link.size != 0) {
                        link[0].onClick(v)
                        return@setOnTouchListener true
                    }
                }
            }
            false
        }
    }
}