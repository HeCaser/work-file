package com.example.xqtest.gson

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import org.json.JSONObject

/**
 * @author: hepan
 * @date: 2022/6/10
 * @desc:
 */
class GsonTestFunction {

    val mGson = Gson()
    fun changeJSONObjectListToJsonElement() {
        val list = ArrayList<JSONObject>()
        list.add(JSONObject().apply {
            put("name", "hepan")
        })
        // mGson 的 toJson 会产生 nameValuePairs 参数
        val srcTree = mGson.toJsonTree(list) // [{"nameValuePairs":{"name":"hepan"}}]
        val src = mGson.toJson(list) // [{"nameValuePairs":{"name":"hepan"}}]

        // 直接用 toString 即可
        val srcString = list.toString() // [{"name":"hepan"}]

        val je = mGson.fromJson(list.toString(), JsonElement::class.java)
        println("hepan = " + je)
    }

    /**
     * 测试字段为 null 时的异常
     *
     * 通过 Gson 转换为 JsonObject, 然后获取字段, 如果字段内容为 null ,会得到一个 com.google.gson.JsonNull 类型的对象
     */
    fun testNullParam() {
        try {
            val json = "{\n" +
                    "    \"data\": null,\n" +
                    "    \"result_code\": 999001,\n" +
                    "    \"message\": \"参数错误\",\n" +
                    "    \"detail\": null,\n" +
                    "    \"error_type\": null\n" +
                    "}"

            val bean = mGson.fromJson(json, JsonObject::class.java)
            if (bean.has("error_type")) {
                val v1 = bean.get("error_type") // 此时 v1 是 com.google.gson.JsonNull 类型
                println("hepan v1 类型 JsonNull ${v1.isJsonNull}") // true
                val v = v1?.asInt //  直接报错  java.lang.UnsupportedOperationException: JsonNull

            } else {
                println("hepan 不包含")
            }
        } catch (e: Exception) {
            println("hepan 异常 = ${e.message}")
        } finally {
        }
    }

}