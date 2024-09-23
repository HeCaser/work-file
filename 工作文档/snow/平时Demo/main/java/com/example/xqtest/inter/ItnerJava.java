package com.example.xqtest.inter;

import androidx.annotation.NonNull;

/**
 * @author: hepan
 * @date: 2022/10/11
 * @desc:
 */
public class ItnerJava {
    ISomeKInterface a = new ISomeKInterface() {
        @Override
        public int getFlag() {
            // equal as  super.flag in kotlin
//            int superFlag = ItnerJava.super.getClass();
            return 2;

        }

        @Override
        public void onProc(@NonNull String data) {
        }
    };
}
