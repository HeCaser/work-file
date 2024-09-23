package com.example.xqtest.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.xqtest.R
import com.example.xqtest.fragments.AddedFragment
import com.example.xqtest.fragments.FundFragment
import com.example.xqtest.fragments.MineFragment
import com.example.xqtest.fragments.OutFragment
import kotlinx.android.synthetic.main.activity_scrolltable_test.view.*
import kotlinx.android.synthetic.main.activity_view_pager_test.*

/**
 * 目的: 页面可见是,只有一个 Fragment 触发 onResume. 切换 tab 时, 触发当前 Fragment 的 onPause , 新展示 Fragment 的 onResume
 * 利用 ViewPager2 可直接实现
 */
class ViewPagerTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager_test)
        initViewPager()
    }

    private fun initViewPager(){
        viewPager.adapter = MyAdapter(this)
        // 快速定位,不会触发第一个 Fragment 的创建等生命周期
//        viewPager.setCurrentItem(1,false)

    }

    private class MyAdapter(act: FragmentActivity) : FragmentStateAdapter(act) {
        override fun getItemCount(): Int {
            return 3
        }

        override fun createFragment(position: Int): Fragment {
          return  when(position){
                0-> FundFragment()
                1-> AddedFragment()
                else-> OutFragment()
            }
        }

    }
}