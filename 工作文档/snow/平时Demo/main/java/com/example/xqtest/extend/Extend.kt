package com.example.xqtest.extend

import android.app.Activity
import android.content.Context
import android.content.Intent

inline fun  Context.nextAct(c:Class<Activity>){
    startActivity(Intent(this,c))
}

inline fun Context.dp2px(dipValue: Float): Float {
    val scale: Float = this.resources.displayMetrics.density
    return (dipValue * scale + 0.5f)
}
inline fun Context.dp2pxInt(dipValue: Float): Int {
    val scale: Float = this.resources.displayMetrics.density
    return (dipValue * scale + 0.5f).toInt()
}