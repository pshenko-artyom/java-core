package org.pshenko.javacore.oop.casting;

public class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Гав!");
    }

    void wagTail() {
        System.out.println("Виляю хвостом!");
    }
}
