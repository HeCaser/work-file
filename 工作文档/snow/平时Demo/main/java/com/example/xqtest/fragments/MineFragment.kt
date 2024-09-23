package com.example.xqtest.fragments

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.xqtest.R
import com.example.xqtest.databinding.FragmentMineBinding
import com.example.xqtest.dialog.PrivatePlacementFbDialogFragment
import com.example.xqtest.util.SpanUtil
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MineFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val mDialogList = LinkedList<Int>()

    lateinit var mBinding: FragmentMineBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        mDialogList.add(0)
        mDialogList.add(1)
        mDialogList.add(2)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMineBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        val text =
            SpanUtil.with(null).append("测试1").setBold().setForegroundColor(Color.RED).create()
        mBinding.tvMine.text = text
        mBinding.tvMine.setOnClickListener { test1() }
        mBinding.tvTest.setOnClickListener { test2() }
        return mBinding.root
    }

    private fun test1() {
        val dialog = PrivatePlacementFbDialogFragment()
        dialog.show(parentFragmentManager)
    }

    private fun test2() {
        popupNextDialogOrView()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MineFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MineFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun popupNextDialogOrView() {
        if (mDialogList.isEmpty()) {
            return
        }
        if (!isVisible) {
            println("hepan 不显示")
            return
        }
        val top = mDialogList.poll()
        println("hepan show ${top}")

        if (top == 0) {
            showDialog(0)
        }
        if (top == 1) {
            showDialog(1)
        }
        if (top == 2) {
            showDialog(2)
        }
    }

    private fun showDialog(p: Int) {
        val dialog = DialogFragment(R.layout.activity_main)
        dialog.show(parentFragmentManager, "1")
        Handler().postDelayed({
            dialog.dismissAllowingStateLoss()
            Handler().postDelayed({ popupNextDialogOrView() }, 1000)
        }, 1000)

    }

    override fun onResume() {
        super.onResume()
        println("hepan  MineFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        println("hepan  MineFragment onPause")

    }

}