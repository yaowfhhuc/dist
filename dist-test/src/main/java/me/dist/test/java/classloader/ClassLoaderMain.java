package me.dist.test.java.classloader;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;


/**
 * @author yaowf
 * @createDate 2018-05-09 14:38
 */
public class ClassLoaderMain {

    public static void main(String[] args) throws  Exception{

        URLClassLoader fscl1 = new URLClassLoader(new URL[]{new URL("file://d://")});
        URLClassLoader fscl2 = new URLClassLoader(new URL[]{new URL("file://c://")});
        String className = "me.dist.test.java.classloader.Sample";
        try {
            Class<?> class1 = fscl1.loadClass(className);
            Object obj1 = class1.newInstance();
            Class<?> class2 = fscl2.loadClass(className);
            Object obj2 = class2.newInstance();
            Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class);
            setSampleMethod.invoke(obj1,obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
