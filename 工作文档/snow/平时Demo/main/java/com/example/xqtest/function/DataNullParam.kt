package com.example.xqtest.function

import com.google.gson.Gson
import com.google.gson.annotations.Expose

/**
 * @author: hepan
 * @date: 2022/9/19
 * @desc:
 */
class DataNullParam {


    data class Hepan(@Expose val name: String) {
        fun getTagType(): String {
            println("hepan 方法 name = $name")
            return when (name) {
                "name" -> "hepan 方法返回 正常"
                else -> "hepan 方法返回 else"
            }
        }
    }

    fun testBean(){
        val json1 = "{\n" +
                "    \"name\": \"name\",\n" +
                "    \"name2\": \"44.14\"\n" +
                "}"
        val p1 = Gson().fromJson<Hepan>(json1,Hepan::class.java)

        when(p1.name){
            "name"->{
                println("hepan testBean 正常")
            }
            else->{
                println("hepan testBean else")
            }
        }
        println("hepan ----- ${p1.getTagType()}")

        val json2 = "{\n" +
                "    \"name1\": \"name\",\n" +
                "    \"name2\": \"44.14\"\n" +
                "}"
        val p2 = Gson().fromJson<Hepan>(json2,Hepan::class.java)

        when(p2.name){
            "name"->{
                println("hepan testBean 正常")
            }
            else->{
                println("hepan testBean else")
            }
        }
        println("hepan ----- ${p2.getTagType()}")

    }

    fun testValue(){
        var name: String? = "2"
        when (name) {
            "2" -> {
                println("hepan testValue 正常")
            }
            else->{
                println("hepan testValue else")
            }
        }

        name = null
        when (name) {
            "2" -> {
                println("hepan testValue 正常")
            }
            else->{
                println("hepan testValue else")
            }
        }
    }
}