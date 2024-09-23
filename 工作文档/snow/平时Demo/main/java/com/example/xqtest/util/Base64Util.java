package com.example.xqtest.util;

import android.util.Base64;


/**
 * @author: hepan
 * @date: 2024/1/19
 * @desc:
 */
public class Base64Util {

    /**
     * base 64 编码.
     * 按照每 6 个 bit 作为一个 base64 单位. 因此三个字节(8*3=24 bit)转换为 4 个 base64 单位
     */
    public static void test(){

        byte[] src = new byte[3];
        src[0] = 0b00000100;
        src[1] = 0b00000001;
        src[2] = 0b00000001;
        //字母 A-Z、 a-z, 数字 0-9、符号 +、/  按顺序转换
        // 转换为 base64: 000001(B) 000000(A) 000100(E) 000001(B)
        String res =  Base64.encodeToString(src, Base64.NO_WRAP);
        System.out.println("hepan base64 结果 = "+res); // BAEB

    }
}
