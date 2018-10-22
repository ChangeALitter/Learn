package com.changw.learn.basic.java.pattern.proxy;

import com.google.common.reflect.Reflection;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.Factory;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CglibProxyTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibTarget.class);
        enhancer.setCallbackFilter(new CglibCustomFilter());
        enhancer.setCallbackTypes(new Class<?>[]{NoOp.class, CglibOneInterceptor.class, CglibTwoInterceptor.class});

        Constructor<?> ctor = enhancer.createClass().getDeclaredConstructor();
        ctor.setAccessible(true);
        Object instance = ctor.newInstance();

        Factory factory = (Factory)instance;
        factory.setCallbacks(new Callback[] {
                NoOp.INSTANCE,
                new CglibOneInterceptor(),
                new CglibTwoInterceptor()
        });

        CglibTarget test = (CglibTarget)instance;
        test.learnJava("spring");
        test.play("football");
        test.sayHello();
    }
}
