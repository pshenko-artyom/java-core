package org.pshenko.javacore.oop.solid;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderProcessor {
    private final OrderRepository repository;
    private final NotificationService notificationService;

    public OrderProcessor(OrderRepository repository, NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    public void processOrder(String orderId, BigDecimal amount, String email) {
        BigDecimal total = calculateTotal(amount);
        String message = "Ваш заказ " + orderId + " готов!";

        repository.save(orderId, total);
        notificationService.sendNotification(email, message);
    }

    private BigDecimal calculateTotal(BigDecimal amount) {
        BigDecimal total = new BigDecimal("1.20");
        return amount.multiply(total).setScale(2, RoundingMode.HALF_UP);
    }
}
