package com.example.xqtest.function

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.LeadingMarginSpan
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.xqtest.util.SpanUtil

class TextViewFunction {


    /**
     * 可以设置首行 Margin
     */
    fun marginSpanTest(tv: TextView, firstLineMargin: Int) {
        val src = "河北河间通报巡逻队“带羊做核酸”一事：河北河间通报巡逻队“带羊做核酸”一事：9名9名涉案犯罪嫌疑人驾驶仿冒警车，冒充派出所巡逻人员，侵害群众利益，目前已被依"
        val spn = SpannableString(src)
        spn.setSpan(
            LeadingMarginSpan.Standard(firstLineMargin, 40),
            0,
            src.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        tv.text = spn
    }

    fun userSpanUtil(tv: TextView) {
        val text =
            SpanUtil.with(null).append("2").setBold().setForegroundColor(Color.RED)
                .setFontSize(20, true).create()
        val sbBuilder = SpannableStringBuilder()
        sbBuilder.append(text)
        val text2 = "只产品已过24小时冷静期，现需要您完成回访问卷，大约耽误您10分钟，感谢配合。"
        sbBuilder.append(text2)
        tv.text = sbBuilder
    }

    /**
     * @description 高亮被 && 包裹的字符串
     * @param
     * @return
     */
    fun heightSubString(tv: TextView) {

//        val content ="&&2&&年增长率 0123456789"
        val content = "啊&& 2 &&年增长率 0123456789&&0&&"
        val split = content.split("&&")
//        val target = if (content.startsWith("&&")) 0 else 1

        val sb = SpannableStringBuilder()
        for (i in split.indices) {
            if (split[i].isNullOrEmpty()) continue
            if (i % 2 != 0) {
                sb.append(
                    SpanUtil.with(null).append(split[i]).setForegroundColor(Color.RED)
                        .setFontSize(20, true).create()
                )
            } else {
                sb.append(split[i])
            }
        }
        println("hepan $split")
        tv.text = sb
    }


    // 根据 name 获取颜色
    fun getColorFromString(context:Context,name:String,tv:TextView){
        // kotlin
        val res: Resources = context.resources
        val packageName: String = context.packageName

        val colorId: Int = res.getIdentifier(name, "color", packageName)
        val desiredColor: Int = ContextCompat.getColor(context, colorId)
        tv.setTextColor(desiredColor)

    }
}