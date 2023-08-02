# Android App 获取设备码

## 利用系统提供的 API 
> 目前最优选择.可能出错(获取不到),刷机后会变化

> 获取后不建议直接使用, 可以做适当的编码(Hex Base64), 添加固定前缀例如 1 开头 + 机型信息等.

```
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
}
```

