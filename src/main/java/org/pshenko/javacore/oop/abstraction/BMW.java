package org.pshenko.javacore.oop.abstraction;

public class BMW implements CarInterface {
    @Override
    public void drive() {
        System.out.println("BMW едет!");
    }

    @Override
    public void stop() {
        System.out.println("BMW тормозит!");
    }
}
