package org.pshenko.javacore.oop.casting;

public class CastingLesson {
    public static void main(String[] args) {
        int intVal = 1000;
        long longVal = intVal;
        System.out.println(longVal);

        double pi = 3.14159;
        int truncatedPi = (int) pi;
        System.out.println(truncatedPi);

        Animal myAnimal = new Dog();
        myAnimal.makeSound();
        // myAnimal.wagTail() - недоступен

        if (myAnimal instanceof Dog) {
            Dog myDog = (Dog) myAnimal;
            myDog.wagTail(); // доступен!
        }
    }
}
