package org.pshenko.javacore.exceptions;

import org.pshenko.javacore.exceptions.exception.ItemNotFoundException;
import org.pshenko.javacore.exceptions.exception.NoAvailableCopiesException;
import org.pshenko.javacore.exceptions.service.Library;

import java.util.Scanner;

import static org.pshenko.javacore.exceptions.util.Util.readInt;
import static org.pshenko.javacore.exceptions.util.Util.readNonEmptyString;

public class Main {

    private static final int EXIT = 5;

    public static void main(String[] args) {
        System.out.println("""
            ==============================
            1 - Добавить книгу
            2 - Взять книгу
            3 - Вернуть книгу
            4 - Получить список книг
            5 - Выйти
            ==============================""");

        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        int input;
        do {
            input = readInt(scanner, "Выберите, что хотите сделать: ");
            switch (input) {
                case 1:
                    try {
                        String title = readNonEmptyString(scanner, "Введите название: ");
                        String author = readNonEmptyString(scanner, "Введите автора: ");
                        int copies;
                        do {
                            copies = readInt(scanner, "Введите количество книг: ");
                            if (copies <= 0) {
                                System.out.println("Количество книг должно быть больше нуля! Попробуйте снова.");
                            }
                        } while (copies <= 0);
                        library.addBook(title, author, copies);
                    } catch (IllegalStateException | IllegalArgumentException e) {
                        System.out.println("Ошибка при попытке добавить книгу: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        if (library.isEmpty()) {
                            System.out.println("Библиотека пуста!");
                            break;
                        }
                        String title = readNonEmptyString(scanner, "Введите название: ");
                        library.takeBook(title);
                    } catch (ItemNotFoundException | NoAvailableCopiesException e) {
                        System.out.println("Ошибка при попытке взять книгу: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        String title = readNonEmptyString(scanner, "Введите название: ");
                        library.returnBook(title);
                    } catch (ItemNotFoundException e) {
                        System.out.println("Ошибка при попытке вернуть книгу: " + e.getMessage());
                    }
                    break;
                case 4:
                    if (library.isEmpty()) {
                        System.out.println("Библиотека пуста!");
                        break;
                    }
                    library.getAllBooks();
                    break;
                case 5:
                    System.out.println("Завершение программы...");
                    break;
                default:
                    System.out.println("Некорректный ввод! Допустимы числа от 1 до 5");
            }
        } while (input != EXIT);
    }


}
