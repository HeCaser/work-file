package com.example.xqtest.function

/**
 * @author: hepan
 * @date: 2022/9/5
 * @desc:
 */
class PointerTest {

    /**
     * 创建对象 O1, Hashmap 存储对象 O1, 然后把O1指向为 null, HashMap 存储的指向是不变的.
     * Hashmap 存储 O1 的本质是指向 O1 当前指向的地址.
     */
    fun setPointerNull(){
        var user :User?= User("hean")
        val map = HashMap<String,User>()
        map.put("hepan",user!!)
        println("hepan rers = ${map["hepan"]}")
        user = null
        println("hepan rers = ${map["hepan"]} user=$user")

    }

    fun  testFundParseByValue(){
        val user = User("hepan")
        changeUserName(user)
        println("hepan user name = ${user.name}")
    }

    /**
     * 测试见
     * @see com.example.xqtest.function.PointerJavaTest.testFundParseByValue
     */
    private fun changeUserName(user: User){
//        user = User("1")
    }


   private val managerMap = HashMap<String,User?>()
    private var instance :User? = null
    private var xueqiuManager:Manager?=null
    private  var danjuanManager:Manager?=null

    fun initXueqiuManager(){
        instance  = User("xueqiu")
        managerMap["xueqiu"] = instance
        xueqiuManager = Manager(instance)

        println("hepan 雪球管理器 = ${xueqiuManager?.user}")
    }

    fun newDanjuanManager(){
        instance = User("danjuan")
        println("hepan 雪球管理器 = ${xueqiuManager?.user}")
        println("hepan 雪球管理器map = ${managerMap["xueqiu"]?.name}")
        danjuanManager = Manager(instance)
        println("hepan 蛋卷管理器 = ${danjuanManager?.user}")

    }
}

data class User (var name:String)
data class Manager(var user:User?)