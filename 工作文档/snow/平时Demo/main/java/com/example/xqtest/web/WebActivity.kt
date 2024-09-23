package com.example.xqtest.web

import android.app.Activity
import android.os.Bundle
import android.webkit.*
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.xqtest.R


class WebAppInterface(var mActivity: Activity) {
    @JavascriptInterface // 注意：在 Android 4.2 及以上版本中，必须使用此注解来暴露给 JavaScript 的方法。
    fun createWalletFromMnemonic(jsonData: String) {
        // 在这里处理从 JavaScript 函数返回的 JSON 数据。
        // 例如，你可以解析 JSON 数据并在 Android 中使用它，或者显示一个 Toast 消息。
        Toast.makeText(mActivity, "Received JSON Data: $jsonData", Toast.LENGTH_LONG).show()
        println("hepan 得到数据 $jsonData")
    }

    @JavascriptInterface // 注意：在 Android 4.2 及以上版本中，必须使用此注解来暴露给 JavaScript 的方法。
    fun receiveJsonData(jsonData: String) {
        println("hepan 得到数据 key = $jsonData")
    }
}

class WebActivity : AppCompatActivity() {

    lateinit var webView: WebView
    lateinit var btnClick: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)


        btnClick = findViewById(R.id.btnClick)
        btnClick.setOnClickListener { callJSFund() }
        webView = findViewById(R.id.webView)
        webView.webChromeClient = object : WebChromeClient(

        ) {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
            }

        }

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val b = super.shouldOverrideUrlLoading(view, request)

                return b
            }
        }
        webView.settings.apply {
            javaScriptEnabled = true
        }
//        webView.loadUrl("https://www.baidu.com")
        webView.loadUrl("file:///android_asset/build/index.html")



        webView.addJavascriptInterface(WebAppInterface(this), "Android")
//        webView.loadUrl("file:///android_asset/index2.html")

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
            }
        }
    }


    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
            return
        }
        super.onBackPressed()

    }

    private fun callJSFund() {
        webView.loadUrl("javascript:Android.createWalletFromMnemonic(createWalletFromMnemonic());")
        webView.loadUrl("javascript:Android.receiveJsonData(getJsonData());")

//        webView.loadUrl("javascript:Android.receiveJsonData(getJsonData_1());")


        // 异步 getJsonData2()
//        webView.loadUrl("javascript:getJsonData_1(function(jsonData) { Android.receiveJsonData(jsonData); });");
    }
}