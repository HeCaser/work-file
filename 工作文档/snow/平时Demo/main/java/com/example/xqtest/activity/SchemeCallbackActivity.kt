package com.example.xqtest.activity

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.xqtest.MainActivity
import com.example.xqtest.R
import com.example.xqtest.recycleview.RvItemRemoveActivity
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

class SchemeCallbackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textview = TextView(this)
        setContentView(textview)

        val uri: Uri? = intent.data
        if (uri !== null) {
            textview.text = uri.toString()
            handleUri(uri)
//            startActivity(Intent(this, MainActivity::class.java).apply {
////                putExtra("next_url",url)
//            })
        }

    }

    override fun onResume() {
        super.onResume()
//        finish()
    }

    private fun handleUri(uri: Uri){

        try {
            val path = uri.path
            if (path.isNullOrEmpty()){
                return
            }
            val decodedURL = decodeMultipleTimes(uri.toString())

            if (path.startsWith("/router/to/")){
                // 动态路由
                val index = decodedURL.indexOf("/router/to/")
                val targetUrl = decodedURL.substring(index).replace("/router/to/","")
                if (targetUrl.isNotEmpty()){
                    println("hepan 动态路由跳转 $targetUrl")
                }
            }else{
                // 严格匹配
                val targetUrl = Uri.parse(decodedURL).buildUpon().scheme("https").build().toString()
                println("hepan 严格匹配 url = $targetUrl")
            }
        } catch (e: Exception) {

        }

    }

    /**
     * 尝试多次解码 URI
     * 1.系统浏览器可能会对 URI 进行编码
     * 2.web 开发者可能多次编码 URI
     */
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
}