package com.example.xqtest.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.example.xqtest.R

/**
 * @author: hepan
 * @date: 2022/6/30
 * @desc: 私募认证弹框
 */
class PrivateConfirmDialog(context: Context, callBack: () -> Unit) : Dialog(context) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.clearFlags(WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER)
        setContentView(R.layout.nesting_private_check_dlg)
        findViewById<View>(R.id.btnConfirm).setOnClickListener {
            callBack.invoke()
        }
    }

}