package com.example.xqtest.activity

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.example.xqtest.R
import kotlinx.android.synthetic.main.activity_handler_test.*

class HandlerTestActivity : AppCompatActivity() {

    /**
     * Handler 验证
     * 1. 当 Activity 关闭, 会触发 onDestroy
     * 2. 因为 mHandler 持有当前 Activity ,所有 Activity 不会被回收,其拥有的变量也不会回收.
     * 3. 可以利用 isFinishing isDestroyed 等方法判断当前 Activity 是否关闭.
     * 4. 当手动抛出异常时, Activity 崩溃. onDestroy 不触发, 并且 mHandler 回调也不再执行.
     */
    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            loop()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_test)
        loop()
        tvLoop.setOnClickListener {
            throw IllegalStateException("hepan 手动抛异常")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        println("hepan OnDestroy")
    }

    private fun loop(){
        if (isDestroyed){
            println("hepan 我已经被关闭了")
            return
        }
        println("hepan 循环")
        println("hepan view = "+tvLoop)
        mHandler.sendEmptyMessageDelayed(1,3000)
    }
}