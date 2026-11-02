package org.pshenko.javacore.oop.encapsulation;

public class EncapsulationLesson {
    public static void main(String[] args) {
        Car car = new Car();
        car.setModel("BMW");
        car.setColor("black");
        System.out.println(car.getModel());
        System.out.println(car.getColor());
    }
}
