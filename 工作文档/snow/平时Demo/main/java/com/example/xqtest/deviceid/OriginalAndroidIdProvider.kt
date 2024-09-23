package com.example.xqtest.deviceid

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.text.TextUtils
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

/**
 * android id提供者
 */
object OriginalAndroidIdProvider {


    @SuppressLint("HardwareIds")
    @JvmStatic
    fun generatorAndroidId(ctx: Context): String {
        var androidId = ""
        try {
            androidId = Settings.Secure.getString(ctx.contentResolver, Settings.Secure.ANDROID_ID)
            println("hepan get id from setting")
        } catch (e: Exception) {
        }
        if (TextUtils.isEmpty(androidId)) {
            androidId = UUID.randomUUID().toString()
            println("hepan get id from uuid")
        } else {
        }
        return androidId
    }

    @JvmStatic
    fun md5(data: String?): String {
        if (TextUtils.isEmpty(data)) {
            return ""
        }
        var md5 = ""
        val md: MessageDigest
        try {
            md = MessageDigest.getInstance("MD5")
            md.update(data!!.toByteArray(charset("UTF-8")))
            val rs = md.digest()
            md5 = toHex(rs)
        } catch (e: NoSuchAlgorithmException) {
        } catch (e: UnsupportedEncodingException) {
        }

        return md5
    }
    private fun toHex(src: ByteArray?): String {
        val stringBuilder = StringBuilder("")
        if (src == null || src.size <= 0) {
            return ""
        }
        for (i in 0..src.size-1) {
            val v = src[i].toInt() and 0xFF
            val hv = Integer.toHexString(v)
            if (hv.length < 2) {
                stringBuilder.append(0)
            }
            stringBuilder.append(hv)
        }
        return stringBuilder.toString()
    }

}