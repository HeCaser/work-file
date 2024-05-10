
本文对 Java 的 String 类的 hashCode 记录

1. String hashCode() 实现

```java
  public int hashCode() {
        int h = 0;
        final int len = length();
        if (h == 0 && len > 0) {
            for (int i = 0; i < len; i++) {
                // 新值 = 前值 * 31 + 当前字符 ascii 值
                h = 31 * h + charAt(i);
            }
        }
        return h;
    }
```

2. 具备相同 hashCode 的字符串

`aa` 的 hashCode = 31*97 + 97 = 3104

`bB` 的 hashCode = 31*98 + 66 = 3104


3. 总结

在 `hashCode` 方法中的乘数 31 已经可以避免大部分字符串产生 hash 冲突(因为 a-z 正好是 31 个), 但是仍然无法全部避免. 

