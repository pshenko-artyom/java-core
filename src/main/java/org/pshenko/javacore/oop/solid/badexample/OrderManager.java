package org.pshenko.javacore.oop.solid.badexample;

public class OrderManager {

    public void processOrder(String orderId, double amount, String customerEmail) {
        double total = amount * 1.20;
        System.out.println("Заказ " + orderId + " обработан. Стоимость: " + total);
        System.out.println("INSERT INTO orders VALUES ('" + orderId + "', " + total + ")");
        System.out.println("Отправляем Email пользователю " + customerEmail + ": Ваш заказ: " + orderId + " готов!");
    }
}
