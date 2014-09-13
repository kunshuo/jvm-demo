package com.jianchen.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args：-XX:PermSize=10M -XX:MaxPermSize=10M
 *
 * @author: jian.cai@qunar.com
 * @Date: 14-9-13 Time: 下午2:42
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        //使用List保持着常量池引用，避免Full GC回收常量池行为
        List<String> list = new ArrayList<String>();

        //10M的permSize在integer范围内足够产生OOM了
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }

    /**
     * 程序执行大概几十秒后报如下错误：
     * Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
     at java.lang.String.intern(Native Method)
     at com.jianchen.memory.RuntimeConstantPoolOOM.main(RuntimeConstantPoolOOM.java:21)
     at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
     at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
     at java.lang.reflect.Method.invoke(Method.java:597)
     at com.intellij.rt.execution.application.AppMain.main(AppMain.java:120)
     */
}
