package org.pshenko.javacore.oop.encapsulation;

public class Car {
    private String model;
    private String color;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (!model.isBlank()) {
            this.model = model;
        } else {
            System.out.println("Модель не может быть пустой!");
        }
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
