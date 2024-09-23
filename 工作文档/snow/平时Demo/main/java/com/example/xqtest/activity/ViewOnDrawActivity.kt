package com.example.xqtest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import com.example.xqtest.R
import com.example.xqtest.view.ViewOndrawTestView
import kotlinx.android.synthetic.main.activity_view_on_draw.*
import kotlin.concurrent.thread

class ViewOnDrawActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_on_draw)
        btnOnDrawAdd.setOnClickListener {
            createViewInThread()
        }
    }


    /**
     * 在子线程创建 View
     * 结论: 子线程创建 View 是没有问题的, 只要 View 不被添加到 View 树种即可 (Only the original thread that created a view hierarchy can touch its views.)
     * 未在 View 树种的 View, 调用 invalidate 等方法是不会触发 onDraw 等相关方法的
     */
    private fun createViewInThread() {
        thread {
            println("hepan 创建")
            val v = ViewOndrawTestView(this@ViewOnDrawActivity)
            SystemClock.sleep(1500)
            addView(v)
        }
    }

    /**
     * 把 View 添加到 View 树中,一定要在 UI 线程
     * 添加方法并不耗时, 添加完毕会触发 requestLayout invalidate 方法, 这样在下一次刷新周期时就会调用 View 的 onDraw 方法. 若 onDraw 比较耗时, 此时才会有所体现.
     * 总结: 当 View 被添加的 View 树种, 测量,绘制相关方法才会被执行, 否则其仅仅是一个简单的对象.
     */
    private fun addView(v: View) {
        runOnUiThread {
            println("hepan 添加")
            llOnDraw.addView(v)
            println("hepan 添加结束")
        }
    }

    /**
     * Q: ViewOndrawTestView 的 onDraw 中, canvas 绘制能否放在主线程
     * A: 可以放在子线程, 目前发现问题: 绘制内容不会主动生效. 添加第二个 View 时,第一个 View 才展示出来
     */


}