package com.example.xqtest.viewmodel

import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommonDataModel : ViewModel() {
    val mStringList = MutableLiveData<List<String>>()

    private val mHandler = Handler(Looper.getMainLooper())

    private val mList = arrayListOf<String>()

    init {
        for (i in 0 until 10) {
            mList.add("Hello, 你好啊 ")
        }
    }

    fun getStringList() {
        Thread {
            SystemClock.sleep(1000)
            mStringList.postValue(mList)
        }.start()
    }
}