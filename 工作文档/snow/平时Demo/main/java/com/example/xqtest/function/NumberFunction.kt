package com.example.xqtest.function

import android.widget.TextView
import org.json.JSONObject
import java.math.BigDecimal
import java.text.DecimalFormat

/**
 * @author: hepan
 * @date: 2022/4/6
 * @desc: 数值相关测试
 */
class NumberFunction {

    fun bigDecimalRound(double:Double):String{
        val bd2 = BigDecimal(double.toString())
        // 两位小数，四舍五入。末尾是0的话会省略。
        val round =  bd2.setScale(2, BigDecimal.ROUND_HALF_UP)
        val format = DecimalFormat("0.00")
        return format.format(round)
    }


    fun changeString2Double(){
//        val src ="0.88"
        val src ="25" // 得到  25.0 而不是 25
        var res = Double.MIN_VALUE
        try {
            res = src.toDouble()
        } catch (ignore: Exception) {

        }

        println("hepan double = $res")
    }

    /**
     * Double 接收数据会自动补充小数位. 23->23.0
     */
    fun getDoubleOrString(){
        val src = "{\n" +
                "    \"d\": 23\n" +
                "}"
        val  jb = JSONObject(src)
        val d = jb.optDouble("d") // 23.0 自动补充小数位
        val s = jb.optString("d") // 23
        println("hepan $d $s")
    }
}