package org.pshenko.javacore.oop.inheritance;

import java.math.BigDecimal;

public class Manager extends Person {
    private BigDecimal salary;

    public Manager(String name, int age, BigDecimal salary) {
        super(name, age);
        this.salary = salary;
    }

    @Override
    public void sayHello() {
        System.out.println("Привет! Я менеджер по имени " + getName() + ". Моя зарплата - " + salary);
    }
}
