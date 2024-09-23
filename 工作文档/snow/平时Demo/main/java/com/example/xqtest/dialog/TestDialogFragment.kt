package com.example.xqtest.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.DialogFragment

/**
 * @author: hepan
 * @date: 2022/8/31
 * @desc:
 */
class TestDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val tv = TextView(activity)
        tv.setBackgroundColor(Color.RED)
        tv.post {
            val param = tv.layoutParams
            if (param is ViewGroup.MarginLayoutParams) {
                param.marginStart = 200
                tv.layoutParams = param
            }
        }
        return tv
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if(dialog != null){
            dialog.window?.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.TOP)
            val p = dialog.window?.attributes
            p?.horizontalMargin = 100f
            p?.width = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.attributes = p
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

}