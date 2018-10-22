package com.changw.learn.basic.java.pattern.impl;

import com.changw.learn.basic.java.pattern.MyEvent;
import com.changw.learn.basic.java.pattern.MyObserver;
import com.changw.learn.basic.java.pattern.MySubject;


import java.util.ArrayList;
import java.util.List;

public class FootballGameSubject implements MySubject {

    private  List<MyObserver> observers = new ArrayList<>();


    @Override
    public void notify(MyEvent event) {
       for (MyObserver myObserver : observers) {
           myObserver.doSomething(event);
       }
    }

    @Override
    public void register(MyObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unRegister(MyObserver observer) {
        if (!observers.isEmpty()) {
            observers.remove(observer);
        }
    }
}
