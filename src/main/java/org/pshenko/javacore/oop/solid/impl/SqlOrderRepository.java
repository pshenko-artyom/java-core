package org.pshenko.javacore.oop.solid.impl;

import org.pshenko.javacore.oop.solid.OrderRepository;

import java.math.BigDecimal;

public class SqlOrderRepository implements OrderRepository {
    @Override
    public void save(String orderId, BigDecimal total) {
        System.out.println("Заказ " + orderId + " сохранен в базу данных. Стоимость: " + total);
    }
}
