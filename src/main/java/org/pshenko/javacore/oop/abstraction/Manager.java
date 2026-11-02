package org.pshenko.javacore.oop.abstraction;

public class Manager extends PersonAbstract {
    @Override
    void sayHello() {
        System.out.println("Привет. Это реализация метода от менеджера!");
    }
}
