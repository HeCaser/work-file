package com.example.xqtest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.xqtest.R
import kotlinx.android.synthetic.main.fragment_out.*


class OutFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_out, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    private fun initViewPager(){
        viewPager2.adapter = MyAdapter(this)
//        viewPager2.setCurrentItem(1,false)

    }
    override fun onResume() {
        super.onResume()
        println("hepan  OutFragment onResume ")

    }

    override fun onPause() {
        super.onPause()
        println("hepan  OutFragment onPause")

    }

    private class MyAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
            return  when(position){
                0-> MineFragment()
                else-> SocialFragment()
            }
        }

    }
}