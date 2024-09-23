package com.example.xqtest.function

/**
 * @author: hepan
 * @date: 2022/9/22
 * @desc:
 */
class OrderSortFunction {

    fun testOrder() {
        var list = arrayListOf<Int?>(1, 2, null, 4, 3)
        println("hepan before order $list")
        // 返回新集合,原集合不变
        val sort = list.sortByDescending { it }
        println("hepan after order $list result = $sort")

    }
}