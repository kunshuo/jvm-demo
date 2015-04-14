package com.jianchen.classloader;

/**
 * @author: jian.cai@qunar.com
 * @Date: 15/4/14 Time: 下午6:53
 */
public class LoadedVersusInitialized {
    private static void loadWithoutInit(ClassLoader loader)
            throws Exception {
        System.out.println("Class.forName(\"com.jianchen.classloader.Foo\", false, currentLoader) invoked");
        //指定initialize=false的话,加载类之后并不进行类对象初始化
        Class<?> clazz = Class.forName("com.jianchen.classloader.Foo", false, loader);
        System.out.println("class Foo loaded but not initialized");
        clazz.newInstance();//此处实例化对象,间接触发进行类对象的初始化
        System.out.println("class Foo initialized");
    }

    private static void loadWithInit()
            throws Exception {
        System.out.println("Class.forName(\"com.jianchen.classloader.Bar\") invoked");
        //等同于显式进行类对象的初始化
        Class<?> clazz = Class.forName("com.jianchen.classloader.Bar"); // Class.forName("Bar", true, currentLoader);
        System.out.println("class Bar initialized");
    }

    private static void loadInterfaceWithInit()
            throws Exception {
        System.out.println("Class.forName(\"com.jianchen.classloader.IBaz\") invoked");
        Class<?> clazz = Class.forName("com.jianchen.classloader.IBaz"); // Class.forName("IBaz", true, currentLoader);
        System.out.println("interface IBaz initialized");
    }

    public static int dummyValue() {
        System.out.println("dummyValue() invoked from IBaz.<clinit>()");
        return 42;
    }

    public static void main(String[] args) throws Exception {
        ClassLoader currentLoader = LoadedVersusInitialized.class.getClassLoader();
        loadWithoutInit(currentLoader);
        System.out.println();
        loadWithInit();
        System.out.println();
        loadInterfaceWithInit();
    }
}

class Foo {
    static {
        System.out.println("Foo.<clinit>() invoked");
    }
}

class Bar {
    static {
        System.out.println("Bar.<clinit>() invoked");
    }
}

interface IBaz {
    int value = LoadedVersusInitialized.dummyValue();
}

/*
java -verbose com.jianchen.classloader.LoadedVersusInitialized

Class.forName("com.jianchen.classloader.Foo", false, currentLoader) invoked
class Foo loaded but not initialized
Foo.<clinit>() invoked
class Foo initialized

Class.forName("com.jianchen.classloader.Bar") invoked
Bar.<clinit>() invoked
class Bar initialized

Class.forName("com.jianchen.classloader.IBaz") invoked
dummyValue() invoked from IBaz.<clinit>()
interface IBaz initialized
*/

