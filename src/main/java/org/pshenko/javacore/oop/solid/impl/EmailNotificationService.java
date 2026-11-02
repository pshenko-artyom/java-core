package org.pshenko.javacore.oop.solid.impl;

import org.pshenko.javacore.oop.solid.NotificationService;

public class EmailNotificationService implements NotificationService {
    @Override
    public void sendNotification(String email, String message) {
        System.out.println("Письмо отправлено пользователю " + email + ". Сообщение: " + message);
    }
}
