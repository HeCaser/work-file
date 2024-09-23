package com.example.xqtest.viewpager

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.example.xqtest.MainActivity
import com.example.xqtest.R
import com.example.xqtest.fragments.AddedFragment
import com.example.xqtest.fragments.FundFragment
import kotlinx.android.synthetic.main.activity_view_pager.*

/**
 * 背景: 在开发雪球需求的时候, 调用 mAdapter.notifyDataSetChanged(); 会触发 Fragment 的 onResume 方法,
 * 雪球代码:
 * @see PortfolioFragmentPagerAdapter
 *
 * 原因: PortfolioFragmentPagerAdapter 重写了 getItemPosition ,返回 POSITION_NONE.
 * 这样当 mFragmentAdapter.notifyDataSetChanged() 时,
 * 依次触发 1.  ViewPager.dataSetChanged() 方法中的  mAdapter.destroyItem(this, ii.position, ii.object); 方法(因为满足 if (newPos == PagerAdapter.POSITION_NONE))
 * 2. PortfolioFragmentPagerAdapter 中的 destroyItem() ,会给 mCurTransaction 赋值
 * 3. PortfolioFragmentPagerAdapter 的 finishUpdate() 方法,因为 mCurTransaction 不为 null, 所以触发  this.mCurTransaction.commitNowAllowingStateLoss();
 * 4. 最终触发 Fragment onResume 方法.
 *
 *
 */
class ViewPagerActivity : AppCompatActivity() {

    private val mAdapter = MyAdapter()
    private val mFragmentAdapter = MyFragmentPagerAdapter(supportFragmentManager)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
//        viewPager.adapter = mAdapter
        viewPager.adapter = mFragmentAdapter
        // 缓存数量会和子 Fragment 的onResume 触发有关
        viewPager.setOffscreenPageLimit(1000)
        tvTest.setOnClickListener {
//            mFragmentAdapter.notifyDataSetChanged()
            startActivity(Intent(this,MainActivity::class.java))
        }
    }


    class MyAdapter: PagerAdapter() {
        override fun getCount(): Int {
            return 3
        }

        override fun isViewFromObject(view: View, temp: Any): Boolean {
            return view == temp
        }

        override fun destroyItem(container: ViewGroup, position: Int, temp: Any) {
            println("hepan destroyItem container $container" )
//            super.destroyItem(container, position, `object`)
            container.removeView(temp as View?)
        }
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            println("hepan instantiateItem container $container" )
            val tv =  TextView(container.context).apply {
                text = "$position"
                setBackgroundColor(Color.RED)
            }
            container.addView(tv)
            return tv
        }

    }

    class MyFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int {
            return 10
        }

        override fun getItem(position: Int): Fragment {
            if (position ==0) return AddedFragment()
            return FundFragment()
        }

        // 返回 POSITION_NONE ,当调用 notifyDataSetChanged 会触发 Fragment 对应的 onResume 方法.
        override fun getItemPosition(`object`: Any): Int {
            return PagerAdapter.POSITION_NONE
        }

    }
}