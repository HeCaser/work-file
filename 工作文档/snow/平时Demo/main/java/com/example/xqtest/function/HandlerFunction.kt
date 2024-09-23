package com.example.xqtest.function

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message

/**
 * @author: hepan
 * @date: 2022/7/12
 * @desc:
 */
class HandlerFunction {

    val MSG_WHAT = 1
    @SuppressLint("HandlerLeak")
    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MSG_WHAT-> {
                    println("hepan 触发了")
                }
                else -> {
                }
            }
        }
    }


    fun sendMsg(){
        mHandler.sendEmptyMessageDelayed(MSG_WHAT,4000)
        println("hepan 发送msg")
    }

    /**
     * 会移除所有未触发的 msg
     */
    fun removeMsg(){
        mHandler.removeMessages(MSG_WHAT)
        println("hepan 移除msg")

    }
}