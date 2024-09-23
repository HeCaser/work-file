package com.example.xqtest.util;



import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 复用工具类, 字 snow 下沉而来
 *
 * 用于转换 999.7773 为中文读法
 */
public class NumberToChineseUtil {

    public static String MM_DD = "MM-dd";
    public static String MM_DD_CHINESE = "MM月dd日";
    private static final Pattern AMOUNT_PATTERN = Pattern.compile("^(0|[1-9]\\d{0,11})\\.?(\\d){0,2}$"); // 不考虑分隔符的正确性
    private static final char[] RMB_NUMS = "零一二三四五六七八九".toCharArray();
    private static final String[] UNITS = {"元", "角", "分"};
    private static final String[] U1 = {"", "十", "百", "千"};
    private static final String[] U2 = {"", "万", "亿"};
    private static final String[] StartUnit = {"正", "负"};

    /**
     *  读法, 开头正负号, 特殊情况兼容, 末尾 元.
     *  eg: 186,3295(华为 会按照电话号读) 转化为 一百八十六万...
     */
    public static String convert(String amount)  {

        try {
            // 验证金额正确性
            if (amount.equals("0.00")) {
                return "零点零零";
            }

            StringBuilder result = new StringBuilder();
            if (amount.startsWith("+")) {
                result.append(StartUnit[0]);
                amount = amount.replace("+", "");
            } else if (amount.startsWith("-")) {
                result.append(StartUnit[1]);
                amount = amount.replace("-", "");
            }
            // 去掉分隔符
            amount = amount.replace(",", "");

            Matcher matcher = AMOUNT_PATTERN.matcher(amount);
            if (!matcher.find()) {
                return amount;
            }

            String[] split = amount.split("\\.");

            if (split.length == 1) {
                // 只有整数部分
                result.append(integer2rmb(split[0]));
            }else{
                result.append(integer2rmb(split[0]));
                result.append(fraction2rmb(split[1]));
            }

            result.append(UNITS[0]);

            return result.toString();
        } catch (Exception e) {
           return amount;
        }
    }
     public static String integer2rmb(String integer) {
         if (TextUtils.isEmpty(integer)) {
             return "";
         }
         if (Float.parseFloat(integer) == 0.0f) {
             return "零";
         }
        StringBuilder buffer = new StringBuilder();
        // 从个位数开始转换
        int i, j;
        for (i = integer.length() - 1, j = 0; i >= 0; i--, j++) {
            char n = integer.charAt(i);
            if (n == '0') {
                // 当 n 是 0 且 n 的右边一位不是 0 时，插入[零]
                if (i < integer.length() - 1 && integer.charAt(i + 1) != '0') {
                    buffer.append(RMB_NUMS[0]);
                }
                // 插入[万]或者[亿]
                if (j % 4 == 0) {
                    if (i > 0 && integer.charAt(i - 1) != '0' || i > 1 && integer.charAt(i - 2) != '0'
                            || i > 2 && integer.charAt(i - 3) != '0') {
                        buffer.append(U2[j / 4]);
                    }
                }
            } else {
                if (j % 4 == 0) {
                    buffer.append(U2[j / 4]); // 插入[万]或者[亿]
                }
                buffer.append(U1[j % 4]); // 插入[拾]、[佰]或[仟]
                buffer.append(RMB_NUMS[n - '0']); // 插入数字
            }
        }
        return buffer.reverse().toString();
    }

    // 将金额小数部分转换为中文大写
    private static String fraction2rmb(String fraction) {
        if (TextUtils.isEmpty(fraction)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("点");
        sb.append(RMB_NUMS[fraction.charAt(0) - '0']);
        if (fraction.length() > 1) {
            sb.append(RMB_NUMS[fraction.charAt(1) - '0']);
        }
        return sb.toString();
    }

    public static String convertDate(String dateStr) {
        DateFormat dateFormat = new SimpleDateFormat(MM_DD);
        try {
            Date date = dateFormat.parse(dateStr);
            DateFormat fmt = new SimpleDateFormat(MM_DD_CHINESE);
            return fmt.format(date);
        } catch (ParseException e) {
            return dateStr;
        }
    }
}