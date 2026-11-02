package org.pshenko.javacore.oop.library.model;

import java.util.Objects;

public abstract class Publication implements Printable {
    private static int publicationCount = 0;
    private String title;
    private String author;
    private int year;

    public Publication(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        incrementPublicationCount();
    }

    public static int getPublicationCount() {
        return publicationCount;
    }

    public static void incrementPublicationCount() {
        publicationCount++;
    }

    public static void decrementPublicationCount() {
        if (publicationCount > 0) {
            publicationCount--;
        }
    }

    public abstract String getType();

    @Override
    public String toString() {
        return """
            Публикация "%s" автор "%s" год выпуска %d
            """.formatted(title, author, year).trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return year == that.year && Objects.equals(title, that.title) && Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
