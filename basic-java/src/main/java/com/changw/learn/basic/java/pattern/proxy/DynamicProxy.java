package com.changw.learn.basic.java.pattern.proxy;

import com.google.common.reflect.AbstractInvocationHandler;
import com.google.common.reflect.Reflection;

import java.lang.reflect.Method;

/**
 * 动态代理模式练习
 */
public class DynamicProxy extends AbstractInvocationHandler {

    private LearnSomething something;

    public DynamicProxy(LearnSomething learnSomething) {
          this.something = learnSomething;
    }

    public Object getObject() {
      return Reflection.newProxy(LearnSomething.class, this);
    }

    @Override
    protected Object handleInvocation(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("before method");
        method.invoke(something, objects);
        System.out.println("after method");
        return o;
    }
}
