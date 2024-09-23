package com.example.xqtest.inter

/**
 * @author: hepan
 * @date: 2022/10/11
 * @desc:
 */

interface ISomeKInterface {
    val flag: Int
        get() {
            return 1
        }

    fun onProc(data: String) {
        if (flag == 1) {
            // do something_1
        } else if (flag == 2) {
            // do something_2
        } else {
            // do the other
        }
    }
}
class InterTest{


  val 啊=   object : ISomeKInterface {
      override val flag: Int
          get() = super.flag

      fun hh(){
       val hhh =   super.flag
      }
  }
}

