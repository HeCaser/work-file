package com.example.xqtest

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.databinding.DataBindingUtil
import com.example.xqtest.activity.ThemeActivity
import com.example.xqtest.databinding.ActivityMainBinding
import com.example.xqtest.web.WebActivity
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import java.util.logging.Logger


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)


        binding.tvTest.setOnClickListener { testFun() }
        binding.tvTest.setOnLongClickListener {
            return@setOnLongClickListener true
        }
        binding.tvTest2.setOnClickListener { testFun2() }


        ViewCompat.setAccessibilityDelegate(binding.tvTest, object : AccessibilityDelegateCompat() {
            override fun onInitializeAccessibilityNodeInfo(
                v: View,
                info: AccessibilityNodeInfoCompat
            ) {
                super.onInitializeAccessibilityNodeInfo(v, info)
                info.addAction(
                    AccessibilityNodeInfoCompat.AccessibilityActionCompat(
                        AccessibilityNodeInfoCompat.ACTION_CLICK, "进入点击功能"
                    )
                )
                info.addAction(
                    AccessibilityNodeInfoCompat.AccessibilityActionCompat(
                        AccessibilityNodeInfoCompat.ACTION_LONG_CLICK, "进入长按功能"
                    )
                )
            }
        })

        handleIntent(intent)

        val androidVersion = Build.VERSION.RELEASE
        val sdkVersion = Build.VERSION.SDK_INT


        println("Android Version: $androidVersion")
        println("SDK Version: $sdkVersion")
    }

    override fun onResume() {
        super.onResume()


        // comint 1
        // comit 2
        // comit 3
        // comit 4
    }


    private fun testFun() {
        val url = "http://ci.mpaas.snowballfinance.com/view/%E8%9B%8B%E5%8D%B7-Android/job/%E8%9B%8B%E5%8D%B7-Android-%E5%8A%9F%E8%83%BD%E5%8C%85-%E5%B9%B6%E8%A1%8C/3040/console"
        println("hepan ${decodeMultipleTimes(url)}")
//        GsonTestFunction().testNullParam()
//     RegexFunction.regexGroupMatchDemo()

//   Base64Util.test()

        startActivity(Intent(this, WebActivity::class.java))
//        RegexFunction.testGroup()
//        val id = OriginalAndroidIdProvider.generatorAndroidId(this)
//        val md5 = OriginalAndroidIdProvider.md5(id) //02553877d4d9630b2385b83180bd7ce7
//        val b = byteArrayOf(2, 0xdd.toByte())
//        val i = b[1].toInt() and 0xff
//        val hv = Integer.toHexString(i)
//        println("hepan id = $i hex = $hv")
//        startActivity(Intent(this, ScrollViewTrackActivity::class.java))


    }

    override fun onStart() {
        super.onStart()
    }

    private fun  decodeMultipleTimes(encodedUrl: String, maxAttempts: Int = 6): String {
        var decodedUrl = encodedUrl
        for (i in 1..maxAttempts) {
            try {
                decodedUrl = URLDecoder.decode(decodedUrl, StandardCharsets.UTF_8.toString())
            } catch (e: IllegalArgumentException) {
                // 如果解码失败（通常意味着 URL 已经是最简形式），则退出循环
                break
            }
        }
        return decodedUrl
    }
    private fun testFun2() {

        try {
            // 持仓页 url
//            val targetUrl = "https://danjuanfunds.com/rn/PrivateFund/PfMyMoney"
            val targetUrl = "https://danjuanfunds.com/rn/pension-assets/list?type=pension"
            val uri = Uri.parse("danjuanapp://danjuanfunds.com/router/to/${targetUrl}")
            startActivity(Intent().apply {
                data = uri
            })
        } catch (e: Exception) {
            println("jump error")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun setDefault(origin: Boolean?, default: Boolean): Boolean {
        return origin ?: default
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        val appLinkAction = intent.action
        val url = intent.extras?.getString("next_url")
        if (TextUtils.isEmpty(url)) {
            binding.tvTest.setText("我发现url")
        } else {
            binding.tvTest.setText(url)
        }


    }

}