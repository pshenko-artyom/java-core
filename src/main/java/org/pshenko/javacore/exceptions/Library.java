package org.pshenko.javacore.exceptions;

import java.util.Scanner;

public class Library {
    private static final int MAX_CAPACITY = 100;
    private static final int EXIT = 0;
    private static final Book[] books = new Book[MAX_CAPACITY];
    private static int booksCount = 0;

    public static void main(String[] args) {
        System.out.println("""
            ==============================
            1 - Добавить книгу
            2 - Взять книгу
            3 - Вернуть книгу
            4 - Получить список книг
            0 - Выйти
            ==============================""");

        Scanner scanner = new Scanner(System.in);
        int input;
        do {
            input = readInt(scanner, "Выберите, что хотите сделать: ");
            switch (input) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    takeBook(scanner);
                    break;
                case 3:
                    returnBook(scanner);
                    break;
                case 4:
                    getAllBooks();
                    break;
                case 0:
                    System.out.println("Завершение программы...");
                    break;
                default:
                    System.out.println("Некорректный ввод! Допустимы числа от 0 до 4");
            }
        } while (input != EXIT);
    }

    private static void addBook(Scanner scanner) {
        String title = readNonEmptyString(scanner, "Введите название: ");
        String author = readNonEmptyString(scanner, "Введите автора: ");
        int availableCopies = readInt(scanner, "Введите количество книг: ");
        if (availableCopies <= 0) {
            throw new IllegalArgumentException("Количество книг должно быть больше нуля!");
        }
        if (booksCount >= MAX_CAPACITY) {
            throw new IllegalStateException("Нельзя добавить новую книгу: массив заполнен");
        }
        Book book = new Book(title, author, availableCopies);
        books[booksCount] = book;
        booksCount++;
    }

    private static void takeBook(Scanner scanner) {
        if (booksCount == 0) {
            System.out.println("Библиотека пуста.");
        }
        String title = readNonEmptyString(scanner, "Введите название: ");
        for (int i = 0; i < booksCount; i++) {
            if (books[i].getTitle().equals(title) && books[i].getAvailableCopies() > 0) {
                books[i].setAvailableCopies(books[i].getAvailableCopies() - 1);
                System.out.println("Книга \"%s\" успешно взята!".formatted(title));
                return;
            }
        }
    }

    private static void returnBook(Scanner scanner) {
        if (booksCount == 0) {
            System.out.println("Библиотека пуста.");
        }
        String title = readNonEmptyString(scanner, "Введите название: ");
        for (int i = 0; i < booksCount; i++) {
            if (books[i].getTitle().equals(title) && books[i].getAvailableCopies() > 0) {
                books[i].setAvailableCopies(books[i].getAvailableCopies() + 1);
                System.out.println("Книга \"%s\" успешно возвращена!".formatted(title));
                return;
            }
        }
    }

    private static void getAllBooks() {
        if (booksCount == 0) {
            System.out.println("Библиотека пуста.");
            return;
        }
        System.out.println("Всего книг: " + booksCount);
        for (Book book : books) {
            System.out.println(book);
        }
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
