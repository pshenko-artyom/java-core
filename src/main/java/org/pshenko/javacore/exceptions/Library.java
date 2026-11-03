package org.pshenko.javacore.exceptions;

public class Library {
    private static final int MAX_CAPACITY = 100;
    private final Book[] books = new Book[MAX_CAPACITY];
    private int booksCount = 0;

    public void addBook(String title, String author, int copies) {
        if (copies <= 0) {
            throw new IllegalArgumentException("Количество книг должно быть больше нуля!");
        }
        if (booksCount >= MAX_CAPACITY) {
            throw new IllegalStateException("Массив заполнен. Нельзя добавить новую книгу!");
        }
        Book book = new Book(title, author, copies);
        books[booksCount] = book;
        booksCount++;
        System.out.printf("Книга \"%s\" успешно добавлена!%n", title);
    }

    public void takeBook(String title) throws NoAvailableCopiesException, ItemNotFoundException {
        for (int i = 0; i < booksCount; i++) {
            if (books[i].getTitle().equals(title)) {
                if (books[i].getAvailableCopies() == 0) {
                    throw new NoAvailableCopiesException("К сожалению, все копии книги \"%s\" разобраны!".formatted(title));
                }
                books[i].setAvailableCopies(books[i].getAvailableCopies() - 1);
                System.out.printf("Книга \"%s\" успешно взята!%n", title);
                return;
            }
        }
        throw new ItemNotFoundException("Книга \"%s\" не найдена!".formatted(title));
    }

    public void returnBook(String title) throws ItemNotFoundException {
        for (int i = 0; i < booksCount; i++) {
            if (books[i].getTitle().equals(title)) {
                books[i].setAvailableCopies(books[i].getAvailableCopies() + 1);
                System.out.printf("Книга \"%s\" успешно возвращена!%n", title);
                return;
            }
        }
        throw new ItemNotFoundException("Книга \"%s\" не найдена!".formatted(title));
    }

    public void getAllBooks() {
        System.out.println("Всего книг: " + booksCount);
        for (int i = 0; i < booksCount; i++) {
            System.out.println(books[i]);
        }
    }

    public boolean isEmpty() {
        return booksCount == 0;
    }
}
