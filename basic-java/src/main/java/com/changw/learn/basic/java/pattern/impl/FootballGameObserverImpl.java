package com.changw.learn.basic.java.pattern.impl;

import com.changw.learn.basic.java.pattern.MyEvent;
import com.changw.learn.basic.java.pattern.MyObserver;
import com.changw.learn.basic.java.pattern.MySubject;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FootballGameObserverImpl implements MyObserver {

    private String name;
    private MySubject subject;

    public void register() {
        subject.register(this);
    }

    public void unRegister() {
        subject.unRegister(this);
    }

    @Override
    public void doSomething(MyEvent appear) {
        System.out.print(name +" ready to watch:" + appear.getMsg());
    }
}
