package org.pshenko.javacore.oop.library.model.impl;

import org.pshenko.javacore.oop.library.model.Publication;
import org.pshenko.javacore.oop.library.model.PublicationType;

import java.util.Objects;

public class Newspaper extends Publication {
    private String publicationDay;

    public Newspaper(String title, String author, int year, String publicationDay) {
        super(title, author, year);
        this.publicationDay = publicationDay;
    }

    @Override
    public String getType() {
        return PublicationType.NEWSPAPER.getDescription();
    }

    public String getPublicationDay() {
        return publicationDay;
    }

    public void setPublicationDay(String publicationDay) {
        this.publicationDay = publicationDay;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Newspaper newspaper = (Newspaper) o;
        return Objects.equals(publicationDay, newspaper.publicationDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), publicationDay);
    }

    @Override
    public void printDetails() {
        System.out.println(super.toString() + ". День публикации: " + publicationDay);
    }
}
