package com.example.xqtest.function

import java.util.*
import kotlin.Comparator

/**
 * @author: hepan
 * @date: 2022/6/24
 * @desc: 测试 sort 方法
 */
class CompareSortFunction {

    companion object {
        val listNormal = arrayListOf<String>("1", "2", "3")
        val listRev = arrayListOf<String>("3", "2", "1")
    }

    /**
     * 比较器返回 1
     * 结果: 原序
     */
    fun sortReturnOne() {
        Collections.sort(listNormal, Comparator { s: String, s1: String ->
            return@Comparator 1
        })
        println("hepan normal $listNormal")

        Collections.sort(listRev, Comparator { s: String, s1: String ->
            return@Comparator 1
        })
        println("hepan rev $listRev")
    }

    /**
     * 比较器返回 0
     * 结果: 原序
     */
    fun sortReturnZero() {
        Collections.sort(listNormal, Comparator { s: String, s1: String ->
            return@Comparator 0
        })
        println("hepan normal $listNormal")

        Collections.sort(listRev, Comparator { s: String, s1: String ->
            return@Comparator 0
        })
        println("hepan rev $listRev")
    }

    /**
     * 比较器返回 -1
     * 结果: 倒序
     */
    fun sortReturnFu() {
        Collections.sort(listNormal, Comparator { s: String, s1: String ->
            return@Comparator -1
        })
        println("hepan normal $listNormal")

        Collections.sort(listRev, Comparator { s: String, s1: String ->
            return@Comparator -1
        })
        println("hepan rev $listRev")
    }

    /**
     * 要求: 空数据在最后,其他正常排序
     * 比较器返回的结果, 1 代表右边的值大于前边的值.
     * 备注: 对比方法要满足自反性,传递性等逻辑,否则会抛出 Comparison method violates its general contract!
     * 下面方法模拟错误, 当 s 和 s1 都是空时, 理论上应该得到 0, 但是当两者交换顺序传入时,可能得到 s < s1 或者 s > s1.
     * 传递性同样原理,当空数据过多时,可能得到如下情况 A > B && B > C ,但是 A < C.
     */
    fun sortEmptyError() {
        val list = arrayListOf<String>(
            "1.0722",
            "",
            "",
            "2.1655",
            "0.5222",
            "1.5027",
            "",
            "1.5084",
            "1.8120",
            "",
            "",
            "",
            "",
            "",
            "5.5861",
            "0.9056",
            "1.8541",
            "0.9782",
            "2.8313",
            "",
            "",
            "",
            "3.2665",
            "2.0354",
            "2.4981",
            "0.4314",
            "1.7993",
            "0.5095",
            "",
            "",
            "1.1461",
            "2.7363",
            "1.6119",
            "",
            "",
            "0.7188",
            "1.4196",
            "0.9972",
            "0.9760",
            "4.5838",
            "2.3124",
            "1.7596",
            "0.8481",
            "0.9105"
        )
        Collections.sort(list, Comparator { s: String?, s1: String? ->
            // 这里处理逻辑是错误的,当两个值都为空时,交换顺序对比会得到 1 和 -1 两种结果. 违反逻辑
            val res = if (s.isNullOrEmpty()) {
                1
            } else if (s1.isNullOrEmpty()) {
                -1
            } else {
                s.toFloat().compareTo(s1.toFloat()) * -1
            }
            println("hepan $s 对比 $s1 = $res")
            return@Comparator res
        })

        println("hepan res =  $list")
    }

    /**
     * 对上面模拟错误的修正
     */
    fun sortEmptyRight() {
        val list = arrayListOf<String>(
            "1.0722",
            "",
            "",
            "2.1655",
            "0.5222",
            "1.5027",
            "",
            "1.5084",
            "1.8120",
            "",
            "",
            "",
            "",
            "",
            "5.5861",
            "0.9056",
            "1.8541",
            "0.9782",
            "2.8313",
            "",
            "",
            "",
            "3.2665",
            "2.0354",
            "2.4981",
            "0.4314",
            "1.7993",
            "0.5095",
            "",
            "",
            "1.1461",
            "2.7363",
            "1.6119",
            "",
            "",
            "0.7188",
            "1.4196",
            "0.9972",
            "0.9760",
            "4.5838",
            "2.3124",
            "1.7596",
            "0.8481",
            "0.9105"
        )
        Collections.sort(list, Comparator { s: String?, s1: String? ->
            val res = if (s.isNullOrEmpty() && s1.isNullOrEmpty()) {
                0
            } else if (s.isNullOrEmpty()) {
                1
            } else if (s1.isNullOrEmpty()) {
                -1
            } else {
                s.toFloat().compareTo(s1.toFloat()) * -1
            }
            println("hepan $s 对比 $s1 = $res")
            return@Comparator res
        })

        println("hepan res =  $list")
    }
}