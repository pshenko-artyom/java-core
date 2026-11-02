package org.pshenko.javacore.oop.library.model;


public class Library {
    private static final int MAX_PUBLICATIONS = 100;
    private final Publication[] publications;
    private int publicationCount = 0;

    public Library() {
        this.publications = new Publication[MAX_PUBLICATIONS];
    }

    public void addPublication(Publication publication) {
        if (publicationCount >= MAX_PUBLICATIONS) {
            System.out.println("К сожалению, добавить публикацию нельзя: массив заполнен");
            return;
        }
        publications[publicationCount] = publication;
        publicationCount++;
    }

    public void listPublications() {
        if (isLibraryEmpty()) {
            return;
        }
        System.out.println("Список публикаций: ");
        for (int i = 0; i < publicationCount; i++) {
            publications[i].printDetails();
        }
    }

    public void searchByAuthor(String author) {
        if (isLibraryEmpty()) {
            return;
        }
        boolean isFound = false;
        for (int i = 0; i < publicationCount; i++) {
            if (publications[i].getAuthor().equals(author)) {
                publications[i].printDetails();
                isFound = true;
            }
        }
        if (!isFound) {
            System.out.println("Публикаций с автором " + author + " не найдено.");
        }
    }

    public void deleteByAuthor(String author) {
        if (isLibraryEmpty()) {
            return;
        }
        for (int i = 0; i < publicationCount; i++) {
            if (publications[i].getAuthor().equals(author)) {

                for (int j = i; j < publicationCount - 1; j++) {
                    publications[j] = publications[j + 1];
                }

                publicationCount--;
                Publication.decrementPublicationCount();
                publications[publicationCount] = null;

                System.out.println("Публикация успешно удалена!");
                return;
            }
        }
        System.out.println("Публикация с автором \"" + author + "\" не найдена.");
    }

    private boolean isLibraryEmpty() {
        if (publicationCount == 0) {
            System.out.println("Нет публикаций.");
            return true;
        }
        return false;
    }
}
