package org.pshenko.javacore.oop.library.model.impl;

import org.pshenko.javacore.oop.library.model.Publication;
import org.pshenko.javacore.oop.library.model.PublicationType;

import java.util.Objects;

public class Magazine extends Publication {
    private int issueNumber;

    public Magazine(String title, String author, int year, int issueNumber) {
        super(title, author, year);
        this.issueNumber = issueNumber;
    }

    @Override
    public String getType() {
        return PublicationType.MAGAZINE.getDescription();
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Magazine magazine = (Magazine) o;
        return issueNumber == magazine.issueNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), issueNumber);
    }

    @Override
    public void printDetails() {
        System.out.println(super.toString() + ". Номер выпуска: " + issueNumber);
    }
}
