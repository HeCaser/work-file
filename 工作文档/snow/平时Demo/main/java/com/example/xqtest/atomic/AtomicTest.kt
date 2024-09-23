package com.example.xqtest.atomic

import android.os.SystemClock
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread

/**
 * @author: hepan
 * @date: 2022/8/11
 * @desc:
 */
object AtomicTest {

    fun testCompareAndSet(){
        val ato = AtomicBoolean(true)

        // 如果当前值和对比值相等,则原子设置为更新的值,返回 true, 否则返回 false
        if (ato.compareAndSet(false,true)){
            println("hepan 0000")
        }

        println("hepan 111")
    }


    /**
     * 多线程创建对象, 保证每个对象有唯一的 key
     */
    private val list = arrayListOf<BeanWithUniqueKey>()

    fun testUniqueKey() {
        list.clear()
        thread {
            while (list.size < 10) {
                list.add(BeanWithUniqueKey(Thread.currentThread().name))
                SystemClock.sleep(20)
            }
        }

        thread {
            while (list.size < 10) {
                list.add(BeanWithUniqueKey(Thread.currentThread().name))
                SystemClock.sleep(30)
            }
        }
    }

    fun printList() {
        list.forEach {
            println("hepan ${it}")
        }
    }
}