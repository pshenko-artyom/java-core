package org.pshenko.javacore.oop.object;

public class ObjectLesson {
    public static void main(String[] args) {
        Person person1 = new Person("Artyom", 22);
        Person person2 = new Person("Anton", 20);
        System.out.println(person1);
        System.out.println(person2);
        System.out.println(person1.equals(person2));
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());
    }
}
