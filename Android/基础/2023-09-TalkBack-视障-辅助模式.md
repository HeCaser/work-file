# TalkBack 模式, 视障辅助模式

## 开启/关闭 辅助模式
- pixcel 音量 + 音量 - 一起按住 3 秒
- 华为 无障碍设置 -> 屏幕朗读


## 参考文档

[常用 API - 外网](https://security.feishu.cn/link/safety?target=https%3A%2F%2Fmedium.com%2Fmicrosoft-mobile-engineering%2Fandroid-accessibility-resolving-common-talkback-issues-3c45076bcdf6&scene=ccm&logParams=%7B%22location%22%3A%22ccm_default%22%7D&lang=en-US)

[介绍 API -CSDN](https://blog.csdn.net/Jacinth40/article/details/119534083)


## 给 View 设置朗读文案

> 注意 朗读文案由 系统固定模式 + 内容 组成. 例如系统会自动给 Button 加上类型名(按钮), 给具备点击事件的 View 加上 (点按两次可激活) 等

朗读文案可以通过 xml 或者 api 设置


`<LinearLayout … android:contentDescription="我是要朗读的内容" >`
/

`view.contentDescription ="我是要朗读的内容"`

## 屏蔽 View 的辅助模式

> 日常使用场景往往把几个 View 的内容组合起来才有意义,这时需要屏蔽子 View 的辅助模式

```
<LinearLayout …
  android:contentDescription="@string/content_description_user_combined"
  <ImageView …
    android:importantForAccessibility="no"/>
  <TextView …
    android:importantForAccessibility="no" />
  <TextView …
    android:importantForAccessibility="no"/>
</LinearLayout>
```

## 设置 Action 信息
> 对于可点击 View, 系统在播报完内容后,会继续播报(点按两次可激活), 可用通过 API 修改相关内容

```
 ViewCompat.setAccessibilityDelegate(tvTest, object : AccessibilityDelegateCompat() {
    override fun onInitializeAccessibilityNodeInfo(v: View, info: AccessibilityNodeInfoCompat) {
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
```

