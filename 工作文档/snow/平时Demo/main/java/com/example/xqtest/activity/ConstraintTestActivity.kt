package com.example.xqtest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.xqtest.R
import kotlinx.android.synthetic.main.activity_const_test.*

class ConstraintTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_const_test)


        tvContent.setOnClickListener {


        }
    }

    /**
     * 代码设置 GuideLine 属性
     */
    private fun setGuideLinePercent(percent:Float){
        guideLine.setGuidelinePercent(percent)
    }


}