package org.pshenko.javacore.oop.solid;

import org.pshenko.javacore.oop.solid.impl.EmailNotificationService;
import org.pshenko.javacore.oop.solid.impl.SqlOrderRepository;

import java.math.BigDecimal;

public class SolidLesson {
    public static void main(String[] args) {
        OrderRepository repository = new SqlOrderRepository();
        NotificationService notificationService = new EmailNotificationService();

        OrderProcessor processor = new OrderProcessor(repository, notificationService);
        processor.processOrder("ORDER-12345", new BigDecimal("2145"), "artyom@gmail.com");
    }
}
