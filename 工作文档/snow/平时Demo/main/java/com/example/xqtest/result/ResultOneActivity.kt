package com.example.xqtest.result

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.xqtest.R
import com.example.xqtest.activity.ViewTestActivity
import kotlinx.android.synthetic.main.activity_result_one.*

class ResultOneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_one)
        tvResut1.setOnClickListener {
            setResult(RESULT_OK)
            finish()
            startActivity(Intent(this, ViewTestActivity::class.java))
            it.postDelayed({
                println("hepan 再次启动")
                startActivity(Intent(this, ViewTestActivity::class.java)) }, 4000)
        }
    }
}