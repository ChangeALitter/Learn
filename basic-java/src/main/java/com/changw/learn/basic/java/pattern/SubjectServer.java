package com.changw.learn.basic.java.pattern;

import com.changw.learn.basic.java.pattern.impl.FootballGameObserverImpl;
import com.changw.learn.basic.java.pattern.impl.FootballGameSubject;
import com.google.common.reflect.TypeResolver;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

public class SubjectServer {

    public static void main(String[] args) {
        var subject = new FootballGameSubject();
        var observer = new FootballGameObserverImpl("chang", subject);
        var event = new MyEvent(1,"man city");
        ((FootballGameObserverImpl) observer).register();
        subject.notify(event);
        var methodSet = TypeToken.of(FootballGameObserverImpl.class).getTypes().rawTypes();
        Iterator iterator = methodSet.iterator();
        while (iterator.hasNext()) {
            Class<?> currClass = (Class)iterator.next();
            System.out.println("Class:" + currClass.getName());
            Method[] methods = currClass.getDeclaredMethods();
            for (Method method : methods) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                 System.out.println("Method:" + method.getName() + " parameter:" + parameterTypes);
            }
            System.out.println("==================");
        }
    }
}
