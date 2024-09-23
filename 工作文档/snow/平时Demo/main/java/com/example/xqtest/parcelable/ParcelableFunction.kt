package com.example.xqtest.parcelable

import android.content.Context
import android.content.Intent

/**
 * @author: hepan
 * @date: 2022/6/27
 * @desc:
 */
class ParcelableFunction {

    /**
     * 总结
     * 1. 通过 Intent 传递是有大小限制的. 大约 1m
     * 2. transient 关键字可以屏蔽 Serializable 对象序列化
     *    用 transient 标记的成员变量,在自动生成 Parcelable 代码时会被忽略,但如果手动写   dest.writeParcelable(market, flags), 则该成员变量仍然是会序列化的.
     *
     *  3. 重点: Parcel 和 Write 和 Read 要一一对应. 顺序要一致.
     */
    fun goAct(ctx: Context) {
        ctx.startActivity(Intent(ctx,ParcelableTestActivity::class.java).apply {

            val  list = arrayListOf<StockQuote>()
            // parcel size
            for (i in 0 until 1000){
                val market = Market(ParcelableTestActivity.BYTE_LENGTH)
                val stock = StockQuote(market)
                list.add(stock)

            }
            putParcelableArrayListExtra(ParcelableTestActivity.KEY,list)
        })
    }

}