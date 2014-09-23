package com.jianchen.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * 类加载器
 *
 * @author: jian.cai@qunar.com
 * @Date: 14-9-23 Time: 下午9:13
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object obj = myLoader.loadClass("com.jianchen.classloader.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass()); //输出class com.jianchen.classloader.ClassLoaderTest
        System.out.println(obj.getClass().getClassLoader().getClass());//输出class com.jianchen.classloader.ClassLoaderTest$1
        System.out.println(obj instanceof com.jianchen.classloader.ClassLoaderTest);//输出false
    }

    /**
     * 比较两个类是否“相等”，只有在这两个类是由同一个类加载器加载的前提下才有意义，
     * 否则，即使这两个类来源于同一个Class文件，被同一个虚拟机加载，只要加载它们的类加载器不同，
     * 那这两个类就必定不相等。
     */
}