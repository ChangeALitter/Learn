package com.changw.learn.basic.java.pattern.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibTwoInterceptor implements MethodInterceptor {
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before two interceptor");
        methodProxy.invokeSuper(o, objects);
        System.out.println("after two interceptor");
        return o;
    }
}
