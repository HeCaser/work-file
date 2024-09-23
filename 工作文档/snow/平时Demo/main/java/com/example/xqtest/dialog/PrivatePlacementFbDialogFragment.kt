package com.example.xqtest.dialog

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.xqtest.view.BaseDialogFragment
import com.example.xqtest.view.PrivatePlacementFeedBackView

class PrivatePlacementFbDialogFragment() : BaseDialogFragment(mOutCancel=true) {
    override fun getDialogView(inflater: LayoutInflater, container: ViewGroup?): View {
        val view = PrivatePlacementFeedBackView(requireActivity(), null,0,0)
        return view
    }

    override fun isShowBottom(): Boolean {
        return true
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }

    override fun onResume() {
        super.onResume()
        println("hepan ~~~~")
    }
}