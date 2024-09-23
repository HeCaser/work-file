package com.example.xqtest

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import java.util.concurrent.TimeUnit

/**
 * @author: hepan
 * @date: 2023/8/10
 * @desc: Application
 */

/**
 * Displays a message when app comes to foreground and goes to background.
 */

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(AppLifecycleObserver())
    }

    class AppLifecycleObserver : LifecycleObserver {
        // 前后台切换一逻辑触发一般需要满足一定时间间隔
        private val intervalMills = TimeUnit.SECONDS.toMillis(1)
        var enterBackgroundTime = 0L

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onEnterForeground() {
            println("到前台")
            if (enterBackgroundTime != 0L && System.currentTimeMillis() - enterBackgroundTime >= intervalMills) {
                appComeToForeground()
            }
        }


        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onEnterBackground() {
            enterBackgroundTime = System.currentTimeMillis()
        }

        private fun appComeToForeground() {
            println("一定时间后切换到了前台")

        }
    }
}