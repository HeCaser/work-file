package com.example.xqtest.fundbanner

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.xqtest.R
import com.example.xqtest.glide.GlideApp
import kotlinx.android.synthetic.main.activity_fund_banner.*

/**
 * 基金货架 Banner
 */
class FundBannerActivity : AppCompatActivity() {
    val uri = "https://xqimg.imedao.com/17161f3b5f01a78c3fefe392.gif"
    val img ="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F7ad1e283fc93d4eea2e392eceedea69d8b33618019160-z1Va2S_fw658&refer=http%3A%2F%2Fhbimg.b0.upaiyun.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1653558710&t=1fd5740ed1d642d68d23d9e173649b8e"
    val heartGif ="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202003%2F29%2F20200329043918_2FUvk.thumb.400_0.gif&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1655430665&t=bbc97d73af251dfef49a4d76b7a398e6"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fund_banner)

//        loadGif()
        loadGifFirstFrame()
    }
    companion object{

        var bitmapCache:Bitmap?=null
    }
    @SuppressLint("CheckResult")
    private fun loadGif(){
        println("hepan start")
//        GlideApp.with(this).load(img).into(ivBanner)
//        GlideApp.with(this).asGif().load(heartGif).into(ivBanner)
        Glide.with(this).load(heartGif).listener(@SuppressLint("CheckResult")
        object :RequestListener<Drawable>{
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                println("hepan su $resource")
                if (resource is GifDrawable){
                    // 设置播放次数
                    resource.setLoopCount(1)
                    if (bitmapCache == null){
                        bitmapCache = resource.firstFrame?.copy(Bitmap.Config.ARGB_8888,false)
                    }
                    if (bitmapCache != null){
                        ivBanner.postDelayed({
                            ivBanner.background = BitmapDrawable(bitmapCache)
                        },2000)
                    }
                }

                return false
            }
        }).into(ivBanner)
    }

    /**
     * @description 加载第一帧,实际还是最后一帧
     * @param
     * @return
     */
    private fun loadGifFirstFrame(){
        GlideApp
            .with(this)
            .asBitmap()
            .load(heartGif)
            .into(ivBanner);
    }
}


