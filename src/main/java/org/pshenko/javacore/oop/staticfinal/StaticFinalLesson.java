package org.pshenko.javacore.oop.staticfinal;

public class StaticFinalLesson {
    public static void main(String[] args) {
        System.out.println(Counter.MAX_LIMIT);
        for (int i = 0; i < 20; i++) {
            Counter.increment();
        }
    }
}
