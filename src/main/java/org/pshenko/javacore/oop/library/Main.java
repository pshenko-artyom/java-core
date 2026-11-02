package org.pshenko.javacore.oop.library;

import org.pshenko.javacore.oop.library.model.Library;
import org.pshenko.javacore.oop.library.model.Publication;
import org.pshenko.javacore.oop.library.model.impl.Book;
import org.pshenko.javacore.oop.library.model.impl.Magazine;
import org.pshenko.javacore.oop.library.model.impl.Newspaper;

import java.util.Scanner;


public class Main {
    private static final int EXIT = 0;

    public static void main(String[] args) {

        System.out.println("""
            ==============================
            1 - Добавить новую публикацию
            2 - Вывести список всех публикаций
            3 - Поиск публикации по автору
            4 - Вывести общее количество публикаций
            5 - Удалить публикацию по автору
            0 - Выход
            ==============================""");

        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        int input;

        do {
            input = readInt(scanner, "Выберите, что хотите сделать: ");
            switch (input) {
                case 1:
                    addPublication(library, scanner);
                    break;
                case 2:
                    library.listPublications();
                    break;
                case 3:
                    searchByAuthor(library, scanner);
                    break;
                case 4:
                    System.out.println("Общее количество публикаций: " + Publication.getPublicationCount());
                    break;
                case 5:
                    deleteByAuthor(library, scanner);
                    break;
                case 0:
                    System.out.println("Завершение программы...");
                    break;
                default:
                    System.out.println("Допустимые значения - от 0 до 5");
                    break;
            }
        } while (input != EXIT);
    }

    private static void addPublication(Library library, Scanner scanner) {
        int input = 0;
        do {
            System.out.println("""
                ==============================
                1 - Книга
                2 - Журнал
                3 - Газета
                ===============================""");

            System.out.print("Выберите тип публикации: ");
            try {
                input = Integer.parseInt(scanner.nextLine().trim());
                if (input < 1 || input > 3) {
                    System.out.println("\nНекорректный ввод. Выберите от 1 до 3.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Допустимы только числа\n");
            }

        } while (input < 1 || input > 3);

        String title = readNonEmptyString(scanner, "Введите название: ");
        String author = readNonEmptyString(scanner, "Введите автора: ");
        int year = readInt(scanner, "Введите год: ");

        switch (input) {
            case 1:
                String isbn = readNonEmptyString(scanner, "Введите ISBN: ");
                Publication book = new Book(title, author, year, isbn);
                library.addPublication(book);
                break;
            case 2:
                int issueNumber = readInt(scanner, "Введите номер выпуска: ");
                Publication magazine = new Magazine(title, author, year, issueNumber);
                library.addPublication(magazine);
                break;
            case 3:
                String publicationDay = readNonEmptyString(scanner, "Введите день публикации: ");
                Publication newsPaper = new Newspaper(title, author, year, publicationDay);
                library.addPublication(newsPaper);
                break;
        }
    }

    private static void searchByAuthor(Library library, Scanner scanner) {
        String author = readNonEmptyString(scanner, "Введите автора для поиска: ");
        library.searchByAuthor(author);
    }

    private static void deleteByAuthor(Library library, Scanner scanner) {
        String author = readNonEmptyString(scanner, "Введите автора для удаления: ");
        library.deleteByAuthor(author);
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
