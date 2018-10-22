package com.changw.learn.basic.java.pattern.proxy;

public class ProxyServerTest {

    public static void main(String[] args) {
        LearnSomething test = new LearnSomething() {
            @Override
            public void learnJava(String knowledge) {
                System.out.println(knowledge);
            }

            @Override
            public void play(String balls) {
                System.out.println(balls);

            }
        };
        DynamicProxy dynamicProxy = new DynamicProxy(test);
        LearnSomething learnSomething = (LearnSomething) dynamicProxy.getObject();
        learnSomething.learnJava("java");
        learnSomething.play("football");
        System.out.println(learnSomething.toString());
    }
}
