# 记录 Android View 相关知识点

# View 的 Context

**结论:**

1. View 的 Context 大部分情况是 Activity 类型
2. 可以利用 Application 创建 View
3. 持有不同 Context 的 View 可以作为兄弟节点

```
  val tv = TextView(applicationContext).apply {
        text = this.context.javaClass.name // Application
        textSize = 30f
    }
    val tv2 = TextView(this).apply {
        text = this.context.javaClass.name // Activity
        textSize = 30f
    }
    binding.llContainer.addView(tv)
    binding.llContainer.addView(tv2)
```

