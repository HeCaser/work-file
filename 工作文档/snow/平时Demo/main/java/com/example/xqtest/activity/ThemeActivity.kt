package com.example.xqtest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.xqtest.R

/**
 * 利用 attr + style 定义切换主题
 * 1.手动切换无效, 需在  super.onCreate(savedInstanceState) 之前给 Activity 设置
 */
class ThemeActivity : AppCompatActivity() {
    companion object{
        var ischagne = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        changeTheme()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_theme)


        findViewById<Button>(R.id.btnClick).setOnClickListener {
            changeTheme()
        }
    }

    private fun changeTheme() {
        ischagne = !ischagne
        if (ischagne) {
            setTheme(R.style.Design_Theme_Light)
        } else {
            setTheme(R.style.Design_Theme_Night)
        }
    }
}