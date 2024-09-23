package com.example.xqtest;

import jregex.Pattern;
import jregex.Replacer;

/**
 * @author: hepan
 * @date: 2023/5/17
 * @desc:
 */
public class JavaTest {

    private static final String DANJUAN_APP_DOMAIN = "https://danjuanfunds.com";
    private static final Pattern stockTagPattern = new Pattern("<a\\shref=\"https?:\\/\\/xueqiu.com\\/[SP]\\/(?!CSI|F(\\d{6}))(.*?)\".*?>(.*?)<\\/a>");
    private static final Replacer stockTagReplacer = stockTagPattern.replacer("$3");

    private static final Pattern fundTagPattern = new Pattern("<a\\shref=\"https?:\\/\\/xueqiu.com\\/S\\/F(\\d+)\".*?>(.*?)<\\/a>");
    private static final Replacer fundTagReplacer = fundTagPattern.replacer("<a href=\"" + DANJUAN_APP_DOMAIN + "/fund/$1\">$2</a>");

    private static final Pattern planTagPattern = new Pattern("<a\\shref=\"https?:\\/\\/xueqiu.com\\/S\\/(CSI.*?)\".*?>(.*?)<\\/a>");
    private static final Replacer planTagReplacer = planTagPattern.replacer("<a href=\"" + DANJUAN_APP_DOMAIN + "/plan/$1\">$2</a>");

    private static final Pattern eventTagPattern = new Pattern("<a\\shref=\"https?:\\/\\/xueqiu.com\\/k\\?q=.*?\".*?>(.*?)<\\/a>");
    private static final Replacer eventTagReplacer = eventTagPattern.replacer("$1");


    private static final Pattern userTagPattern = new Pattern("<a href=\"https?:\\/\\/xueqiu.com\\/n\\/(.*?)\".*?>(@.*?)<\\/a>");
    private static final Replacer userTagReplacer = userTagPattern.replacer("<a href=\"" + DANJUAN_APP_DOMAIN + "/profile/$1\">$2</a>");

    private static final Pattern linkPattern = new Pattern("<a[^<]+>网页链接");
    private static final Replacer linkReplacer = linkPattern.replacer("<img src=\"http://danjuan.imedao.com/o2017031489125807869.png\" style=\"vertical-align: middle;\"  height=\"16\" />$0");

    public static void main(String[] args) {
        testRep();
    }


    public static void testRep() {

    }
}


