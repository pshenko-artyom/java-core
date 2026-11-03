package org.pshenko.javacore.basics;

import java.util.Scanner;

public class ContactManager {
    private static final int MAX_CAPACITY = 100;
    private static final int EXIT = 0;

    private static final String[] names = new String[MAX_CAPACITY];
    private static final String[] phones = new String[MAX_CAPACITY];

    private static int contactSize = 0;

    public static void main(String[] args) {
        System.out.println("""
            ==============================
            1 - Добавить контакт
            2 - Просмотреть контакты
            3 - Найти контакт
            4 - Удалить контакт
            0 - Выйти
            ==============================""");

        Scanner scanner = new Scanner(System.in);
        int input;
        do {
            input = readInt(scanner, "Выберите, что хотите сделать: ");
            switch (input) {
                case 1:
                    addContact(scanner);
                    break;
                case 2:
                    getContacts();
                    break;
                case 3:
                    searchContactByName(scanner);
                    break;
                case 4:
                    deleteContact(scanner);
                    break;
                case 0:
                    System.out.println("Завершение программы...");
                    break;
                default:
                    System.out.println("Некорректный ввод! Допустимы числа от 0 до 4");
            }
        } while (input != EXIT);
    }

    private static void addContact(Scanner scanner) {
        if (contactSize >= MAX_CAPACITY) {
            System.out.println("К сожалению, добавить контакт нельзя: массив заполнен");
            return;
        }
        String name = readNonEmptyString(scanner, "Введите имя пользователя: ");
        String phone = readNonEmptyString(scanner, "Введите телефон: ");

        names[contactSize] = name;
        phones[contactSize] = phone;
        contactSize++;

        System.out.println("Контакт успешно добавлен!");
    }

    private static void getContacts() {
        if (isContactListEmpty()) {
            return;
        }
        System.out.println("Список добавленных контактов: ");
        for (int i = 0; i < contactSize; i++) {
            System.out.println(names[i] + " " + phones[i]);
        }
    }

    private static void searchContactByName(Scanner scanner) {
        if (isContactListEmpty()) {
            return;
        }
        String name = readNonEmptyString(scanner, "Введите имя контакта: ");
        for (int i = 0; i < contactSize; i++) {
            if (names[i].equals(name)) {
                System.out.println(names[i] + " " + phones[i]);
                return;
            }
        }
        System.out.println("Контакт не найден.");
    }

    private static void deleteContact(Scanner scanner) {
        if (isContactListEmpty()) {
            return;
        }
        String name = readNonEmptyString(scanner, "Введите имя для удаления: ");
        for (int i = 0; i < contactSize; i++) {
            if (names[i].equals(name)) {

                for (int j = i; j < contactSize - 1; j++) {
                    names[j] = names[j + 1];
                    phones[j] = phones[j + 1];
                }

                contactSize--;
                names[contactSize] = null;
                phones[contactSize] = null;

                System.out.println("Контакт успешно удален.");
                return;
            }
        }
        System.out.println("Контакт не найден.");
    }

    private static boolean isContactListEmpty() {
        if (contactSize == 0) {
            System.out.println("Список контактов пуст.");
            return true;
        }
        return false;
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите целое число.");
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
            System.out.println("Ошибка! Поле не может быть пустым.");
        }
    }
}

