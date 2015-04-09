package com.jianchen.memory;

import org.junit.Test;

/**
 * @author: jian.cai@qunar.com
 * @Date: 15/4/9 Time: 上午11:05
 */
public class StringConstantTest {
    private static String a;
    private static String b;

    @Test
    public void testString1() {
        String s2 = "abc";
        //在堆中新创建"abc"对象，s3指向该对象,Java中，
        //只要使用new关键字来创建对象，则一定会（在堆区或栈区）创建一个新的对象。
        String s3 = new String("abc");
        //在池中创建对象"ab" 和 "c"，并且s4指向池中对象"abc"
        String s4 = "ab" + "c";//因为是常量,编译后的代码实际上等同于s4 = "abc";
        System.out.println(s2 == s4); //true
        System.out.println(s3 == s4); //false
    }

    /**
     * a+b ,实际编译后的执行代码是调用stringBuilder的append方法,
     * 内部如果发现是参数为空,则实际append的是"null"字符串
     */
    @Test
    public void testString2() {
        String ab = a + b;
        System.out.println(ab);//输出nullnull
    }

    /**
     * a+b ,实际编译后的执行代码是调用StringBuilder的append方法,
     * 最后会调用StringBuilder.toString方法.这个实际上返回的是新字符串了
     */
    @Test
    public void testString3() {
        String ab = "ab";//string pool中创建字符串ab
        String a = "a";
        String b = "b";
        String s1 = a + b;//实际编译后的代码是StringBuilder.append方法进行处理的
        System.out.println(s1 == ab);//输出false
    }


    @Test
    public void testString4() {
        String ab = "ab";
        System.out.println(("a" + "b") == ab); //输出true,代码编译后就等同于"ab"=="ab"
    }
}
