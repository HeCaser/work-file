# 正则, 分组匹配替换

Demo 参考 Java 包的 Pattern.matcher()

```java
  private static void demo1() {
        String text = "The quick brown fox jumped over the lazy dog";

        // text 中每一个单词都会被匹配
        Pattern word = new Pattern("\\w+");
        // 作用: 给每一个匹配的单词加上[], 其中 $& 或者 $0 代表所有满足条件的匹配,
        System.out.println(word.replacer("[$&]").replace(text));// [The] [quick] [brown] [fox] [jumped] [over] [the] [lazy] [dog]
        System.out.println(word.replacer("[$0]").replace(text)); // $& 与 $0 等价

    }
```

```java
    private static void demo2() {
        String text = "The quick brown fox jumped over the lazy dog";

        // swap 匹配的内容是 text 中部分内容, 分为三部分 (fox)( jumped over the lazy )(dog), 取值时下标从 1 开始
        Pattern swap = new Pattern("(fox|dog)(.*?)(fox|dog)");
        System.out.println(swap.replacer("[$1]").replace(text)); // The quick brown [fox], 前面的 The quick brown 不属于匹配内容, 因此会原样保留, 同理若末尾有不匹配的字符串,也会原样保留
        System.out.println(swap.replacer("[$2]").replace(text)); // The quick brown [ jumped over the lazy ]
        System.out.println(swap.replacer("[$3]").replace(text)); // The quick brown [dog]
        System.out.println(swap.replacer("$3$2$1").replace(text)); // The quick brown dog jumped over the lazy fox  交换了第三组和第一组的值

    }

```

```java
// 匹配不上的情况
 private static void demo3() {
        String text = "The quick brown fox jumped over the lazy dog";

        Pattern noMatch = new Pattern("(fox|dog)(aaa.*?)(fox|dog)"); // noMatch 无法整体匹配 text (text 中没有满足第二组的内容) ,因此会原样输出
        System.out.println(noMatch.replacer("[$1]").replace(text)); // The quick brown fox jumped over the lazy dog
        System.out.println(noMatch.replacer("[$2]").replace(text)); // The quick brown fox jumped over the lazy dog
        System.out.println(noMatch.replacer("[$3]").replace(text)); // The quick brown fox jumped over the lazy dog
    }


```