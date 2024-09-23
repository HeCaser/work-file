package com.example.xqtest.function;

import jregex.Pattern;
import jregex.Replacer;

/**
 * @author: hepan
 * @date: 2023/7/26
 * @desc: 正则测试
 */
public class RegexFunction {

    public static void testGroup() {
        Pattern linkPattern = new Pattern("<a[^<]+>网页链接");
        // ---- 包裹的代表匹配的分组 ,第 0 组
        Replacer linkReplacerDefault = linkPattern.replacer("--$0--");
        Replacer linkReplacer = linkPattern.replacer("<img src=\"http://danjuan.imedao.com/o2017031489125807869.png\" style=\"vertical-align: middle;\"  height=\"16\" />$0");

        String content = "施罗德亚洲高息股债：关于施罗德亚洲高息股债基金更新内地销售文件的公告 <a href=\"http://static.cninfo.com.cn/finalpage/2023-03-13/1216107167.PDF\" title=\"http://static.cninfo.com.cn/finalpage/2023-03-13/1216107167.PDF\" target=\"_blank\" class=\"status-link\">网页链接</a>";
        System.out.println("hepan 正则匹配部分 = " + linkReplacerDefault.replace(content));
        System.out.println("hepan res = " + linkReplacer.replace(content));
    }

//    Returns a replacer of a pattern by specified perl-like expression. Such replacer will substitute all occurences of a pattern by an evaluated expression ("$&" and "$0" will substitute by the whole match, "$1" will substitute by group#1, etc). Example:
//    String text="The quick brown fox jumped over the lazy dog";
//    Pattern word=new Pattern("\\w+");
//  System.out.println(word.replacer("[$&]").replace(text));
//    //prints "[The] [quick] [brown] [fox] [jumped] [over] [the] [lazy] [dog]"
//    Pattern swap=new Pattern("(fox|dog)(.*?)(fox|dog)");
//  System.out.println(swap.replacer("$3$2$1").replace(text));
//    //prints "The quick brown dog jumped over the lazy fox"
//    Pattern scramble=new Pattern("(\\w+)(.*?)(\\w+)");
//  System.out.println(scramble.replacer("$3$2$1").replace(text));
//    //prints "quick The fox brown over jumped lazy the dog"
//
//    Params:
//    expr – a perl-like expression, the "$&" and "${&}" standing for whole match, the "$N" and "${N}" standing for group#N, and "${Foo}" standing for named group Foo.
//    See Also:
//    Replacer

    public static void regexGroupMatchDemo() {

//        demo1();
        demo2();

    }

    private static void demo3() {
        String text = "The quick brown fox jumped over the lazy dog";

        Pattern noMatch = new Pattern("(fox|dog)(aaa.*?)(fox|dog)"); // noMatch 无法整体匹配 text (text 中没有满足第二组的内容) ,因此会原样输出
        System.out.println(noMatch.replacer("[$1]").replace(text)); // The quick brown fox jumped over the lazy dog
        System.out.println(noMatch.replacer("[$2]").replace(text)); // The quick brown fox jumped over the lazy dog
        System.out.println(noMatch.replacer("[$3]").replace(text)); // The quick brown fox jumped over the lazy dog
    }

    private static void demo2() {
        String text = "The quick brown fox jumped over the lazy dog";

        // swap 匹配的内容是 text 中部分内容, 分为三部分 (fox)( jumped over the lazy )(dog), 取值时下标从 1 开始
        Pattern swap = new Pattern("(fox|dog)(.*?)(fox|dog)");
        System.out.println(swap.replacer("[$1]").replace(text)); // The quick brown [fox], 前面的 The quick brown 不属于匹配内容, 因此会原样保留, 同理若末尾有不匹配的字符串,也会原样保留
        System.out.println(swap.replacer("[$2]").replace(text)); // The quick brown [ jumped over the lazy ]
        System.out.println(swap.replacer("[$3]").replace(text)); // The quick brown [dog]
        System.out.println(swap.replacer("$3$2$1").replace(text)); // The quick brown dog jumped over the lazy fox  交换了第三组和第一组的值

    }

    private static void demo1() {
        String text = "The quick brown fox jumped over the lazy dog";

        // text 中每一个单词都会被匹配
        Pattern word = new Pattern("\\w+");
        // 作用: 给每一个匹配的单词加上[], 其中 $& 或者 $0 代表所有满足条件的匹配,
        System.out.println(word.replacer("[$&]").replace(text));// [The] [quick] [brown] [fox] [jumped] [over] [the] [lazy] [dog]
        System.out.println(word.replacer("[$0]").replace(text)); // $& 与 $0 等价

    }
}
