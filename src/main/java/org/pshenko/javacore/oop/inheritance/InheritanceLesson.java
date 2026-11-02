package org.pshenko.javacore.oop.inheritance;

import java.math.BigDecimal;

public class InheritanceLesson {
    public static void main(String[] args) {
        Person manager = new Manager("Artyom", 22, new BigDecimal("2000"));
        manager.sayHello();
    }
}
