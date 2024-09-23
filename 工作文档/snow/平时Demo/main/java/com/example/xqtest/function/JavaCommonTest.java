package com.example.xqtest.function;

import android.os.SystemClock;

import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author: hepan
 * @date: 2022/9/6
 * @desc:
 */
public class JavaCommonTest {

    /**
     * 迭代器遍历 set
     */
    public void testSetIterator() {
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        set.add("he");
        set.add("pan");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println("hepan " + iterator.next());
        }
    }

    CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet();

    public void testCopyOnWriteArraySet() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                set.clear();
                for (int i = 0; i < 3; i++) {
                    SystemClock.sleep(1000);
                    set.add("" + i);
                    System.out.println("hepan add " + i);
                }
                printSet();
            }
        }).start();
    }

    private void printSet() {
        CopyOnWriteArraySet temp = set;
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            System.out.println("hepan print " + name);
        }
    }
}
