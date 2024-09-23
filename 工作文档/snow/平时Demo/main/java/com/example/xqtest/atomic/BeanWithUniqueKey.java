package com.example.xqtest.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: hepan
 * @date: 2022/8/18
 * @desc:
 */
public class BeanWithUniqueKey {

    private static final AtomicInteger sInstanceKeyCounter = new AtomicInteger(1);

    // 保证每个 BeanWithUniqueKey 有唯一的 key
    private final int mInstanceKey = sInstanceKeyCounter.getAndIncrement();

    private String threadName;

    public BeanWithUniqueKey(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public String toString() {
        return "BeanWithUniqueKey{" +
                "mInstanceKey=" + mInstanceKey +
                ", threadName='" + threadName + '\'' +
                '}';
    }
}
