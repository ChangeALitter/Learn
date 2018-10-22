package com.changw.learn.basic.java.pattern.proxy;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

public class CglibCustomFilter implements CallbackFilter {
    public int accept(Method method) {
        String name = method.getName();
        if (name.endsWith("play")) {
            return 1;
        } else if (name.endsWith("learnJava")) {
            return 2;
        } else {
            return 0;
        }
    }
}
