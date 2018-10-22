package com.changw.learn.basic.java.pattern;

public interface MySubject {
    void notify(MyEvent event);
    void register(MyObserver observer);
    void unRegister(MyObserver observer);
}
