package com.jianchen.classloader;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 测试数据封装类
 *
 * @author: jian.cai@qunar.com
 * @Date: 15/4/8 Time: 下午6:20
 */
public class DataHolder {
    private static AtomicLong count = new AtomicLong();

    {
        count.incrementAndGet();
        System.out.println("current count value is " + count);
    }
}
