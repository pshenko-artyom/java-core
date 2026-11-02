package org.pshenko.javacore.oop.library.model;

public enum PublicationType {
    BOOK("Книга"),
    MAGAZINE("Журнал"),
    NEWSPAPER("Газета");

    private final String description;

    PublicationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
