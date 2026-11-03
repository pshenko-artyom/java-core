package org.pshenko.javacore.basics;

import java.util.Scanner;

public class ContactManager {
    private static final int MAX_CAPACITY = 100;
    private static final int EXIT = 5;

    private static String[] names = new String[MAX_CAPACITY];
    private static String[] phones = new String[MAX_CAPACITY];

    private static int contactSize = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input;
        do {

            System.out.println("""
                ==============================
                1 - Добавить контакт
                2 - Просмотреть контакты
                3 - Найти контакт
                4 - Удалить контакт
                5 - Выйти
                ==============================""");

            input = readInt(scanner, "Выберите, что хотите сделать: ");
            switch (input) {
                case 1 -> {
                    String name = readNonEmptyString(scanner, "Введите имя пользователя: ");
                    String phone = readNonEmptyString(scanner, "Введите телефон: ");
                    addContact(name, phone);
                }
                case 2 -> getContacts();
                case 3 -> {
                    String name = readNonEmptyString(scanner, "Введите имя пользователя: ");
                    searchContactByName(name);
                }
                case 4 -> {
                    String name = readNonEmptyString(scanner, "Введите имя: ");
                    deleteContact(name);
                }
                case 5 -> System.out.println("Завершение программы...");
                default -> System.out.println("Некорректный ввод! Допустимы числа от 1 до 5");
            }
        } while (input != EXIT);
    }

    private static void addContact(String name, String phone) {
        if (contactSize >= MAX_CAPACITY) {
            System.out.println("К сожалению, добавить контакт нельзя: массив заполнен!\n");
            return;
        }

        names[contactSize] = name;
        phones[contactSize] = phone;
        contactSize++;

        System.out.println("Контакт успешно добавлен!\n");
    }

    private static void getContacts() {
        if (isContactListEmpty()) {
            System.out.println("Список контактов пуст!\n");
            return;
        }
        System.out.println("Список добавленных контактов: ");
        for (int i = 0; i < contactSize; i++) {
            int counter = i + 1;
            System.out.println(counter + ". " + names[i] + " - " + phones[i]);
        }
        System.out.println();
    }

    private static void searchContactByName(String name) {
        if (isContactListEmpty()) {
            System.out.println("Список контактов пуст!\n");
            return;
        }
        for (int i = 0; i < contactSize; i++) {
            if (names[i].equalsIgnoreCase(name)) {
                System.out.println("Телефон " + names[i] + ": " + phones[i] + "\n");
                return;
            }
        }
        System.out.println("Контакт не найден!\n");
    }

    private static void deleteContact(String name) {
        if (isContactListEmpty()) {
            System.out.println("Список контактов пуст!\n");
            return;
        }
        for (int i = 0; i < contactSize; i++) {
            if (names[i].equalsIgnoreCase(name)) {

                for (int j = i; j < contactSize - 1; j++) {
                    names[j] = names[j + 1];
                    phones[j] = phones[j + 1];
                }

                contactSize--;
                names[contactSize] = null;
                phones[contactSize] = null;

                System.out.println("Контакт успешно удален!\n");
                return;
            }
        }
        System.out.println("Контакт не найден!\n");
    }

    private static boolean isContactListEmpty() {
        return contactSize == 0;
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Введите целое число!");
            }
        }
    }

    private static String readNonEmptyString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Поле не может быть пустым!");
        }
    }
}

