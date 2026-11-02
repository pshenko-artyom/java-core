package org.pshenko.javacore.oop.abstraction;

public class AbstractionLesson {
    public static void main(String[] args) {
        PersonAbstract manager = new Manager();
        manager.sayHello();

        CarInterface bmw = new BMW();
        bmw.drive();
        bmw.stop();
    }
}
