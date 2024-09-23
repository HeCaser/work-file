package com.example.xqtest.javatest;

import android.net.Uri;

import java.util.Locale;

/**
 * @author: hepan
 * @date: 2023/1/12
 * @desc: 测试
 */
public class JavaTest {

    public static void main(String[] args) {

        methodB();

//        String path = uri.getPath();
//        System.out.println("hepan path "+path);
    }

    static void methodA() {
        return;
    }

    static void methodB() {
        methodA();
        System.out.println("hepan");
    }
}
