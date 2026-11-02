package org.pshenko.javacore.oop.library.model.impl;

import org.pshenko.javacore.oop.library.model.Publication;
import org.pshenko.javacore.oop.library.model.PublicationType;

import java.util.Objects;

public class Book extends Publication {
    private String isbn;

    public Book(String title, String author, int year, String isbn) {
        super(title, author, year);
        this.isbn = isbn;
    }

    @Override
    public String getType() {
        return PublicationType.BOOK.getDescription();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isbn);
    }

    @Override
    public void printDetails() {
        System.out.println(super.toString() + ". Международный стандартный книжный номер: " + isbn);
    }
}
