package org.pshenko.javacore.collections;

import org.pshenko.javacore.collections.exception.ContactAlreadyExistsException;
import org.pshenko.javacore.collections.exception.ContactNotFoundException;
import org.pshenko.javacore.collections.exception.GroupNotFoundException;
import org.pshenko.javacore.collections.service.ContactBook;

import java.util.Scanner;

import static org.pshenko.javacore.collections.util.Util.readInt;
import static org.pshenko.javacore.collections.util.Util.readNonEmptyString;

public class Main {
    private static final int EXIT = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input;
        ContactBook contactBook = new ContactBook();
        do {
            System.out.println("""
                ==============================
                1 - Добавить контакт
                2 - Удалить контакт
                3 - Посмотреть все контакты
                4 - Найти контакт
                5 - Посмотреть контакты по группе
                0 - Выйти
                ==============================""");
            input = readInt(scanner, "Выберите операцию: ");
            switch (input) {
                case 1 -> {
                    String name = readNonEmptyString(scanner, "Введите имя: ");
                    String phone = readNonEmptyString(scanner, "Введите телефон: ");
                    String email = readNonEmptyString(scanner, "Введите email: ");
                    String group = readNonEmptyString(scanner, "Введите группу: ");

                    try {
                        contactBook.addContact(name, phone, email, group);
                    } catch (ContactAlreadyExistsException e) {
                        System.out.println("Ошибка при добавлении контакта: " + e.getMessage());
                    }
                }
                case 2 -> {
                    String name = readNonEmptyString(scanner, "Введите имя: ");
                    try {
                        contactBook.deleteContact(name);
                    } catch (ContactNotFoundException e) {
                        System.out.println("Ошибка при удалении контакта: " + e.getMessage());
                    }
                }
                case 3 -> contactBook.getContacts();
                case 4 -> {
                    String name = readNonEmptyString(scanner, "Введите имя: ");
                    contactBook.searchContactByName(name);
                }
                case 5 -> {
                    String group = readNonEmptyString(scanner, "Введите группу: ");
                    try {
                        contactBook.getContactsByGroup(group);
                    } catch (GroupNotFoundException e) {
                        System.out.println("Ошибка при просмотре контактов по группе: " + e.getMessage());
                    }
                }
                case 0 -> System.out.println("Завершение программы...");
                default -> System.out.println("Неверная команда!");
            }
        } while (input != EXIT);
    }

}
