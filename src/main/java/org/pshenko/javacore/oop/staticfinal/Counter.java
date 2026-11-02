package org.pshenko.javacore.oop.staticfinal;

public class Counter {
    public static final int MAX_LIMIT = 10;
    private static int counter;

    public static void increment() {
        if (counter < MAX_LIMIT) {
            counter++;
            System.out.println("Счетчик увеличен. Текущее значение: " + counter);
        } else {
            System.out.println("Достигнут лимит: " + MAX_LIMIT);
        }
    }
}
