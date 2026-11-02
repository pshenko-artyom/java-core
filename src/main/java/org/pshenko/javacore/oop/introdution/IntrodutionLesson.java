package org.pshenko.javacore.oop.introdution;

public class IntrodutionLesson {
    public static void main(String[] args) {
        Person person = new Person("Artyom", 22, "artyom@gmail.com");
        System.out.println(person.getName());
        System.out.println(person.getAge());
        System.out.println(person.getEmail());
    }
}
