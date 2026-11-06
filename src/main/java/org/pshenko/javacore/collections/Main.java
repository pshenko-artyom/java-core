package org.pshenko.javacore.collections;

import org.pshenko.javacore.collections.model.Contact;
import org.pshenko.javacore.collections.service.ContactService;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static org.pshenko.javacore.collections.util.Util.*;

public class Main {
    private static final int EXIT = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ContactService contactService = new ContactService();
        seedMockData(contactService);

        int choice;
        do {
            printMenu();
            choice = readInt(scanner, "Выберите операцию: ");
            switch (choice) {
                case 1 -> addContact(scanner, contactService);
                case 2 -> deleteContact(scanner, contactService);
                case 3 -> getContacts(contactService);
                case 4 -> getContactByName(scanner, contactService);
                case 5 -> getContactsByGroup(scanner, contactService);
                case 0 -> System.out.println("Завершение программы...");
                default -> System.out.println("Введите целое число от 0 до 5!");
            }
        } while (choice != EXIT);
        scanner.close();
    }

    private static void addContact(Scanner scanner, ContactService contactService) {
        String name = readNonEmptyString(scanner, "Введите имя: ");
        String phone = readNonEmptyString(scanner, "Введите телефон: ");
        String email = readNonEmptyString(scanner, "Введите email: ");
        String group = readNonEmptyString(scanner, "Введите группу: ");
        if (contactService.addContact(name, phone, email, group)) {
            System.out.println("Контакт успешно добавлен!");
        } else {
            System.out.printf("Контакт с именем \"%s\" и телефоном \"%s\" существует", name, phone);
        }
    }

    private static void deleteContact(Scanner scanner, ContactService contactService) {
        String name = readNonEmptyString(scanner, "Введите имя: ");
        List<Contact> candidates = contactService.getContactsByName(name);
        Contact contactToDelete;

        if (candidates.isEmpty()) {
            System.out.printf("Контактов с именем \"%s\" не найдено%n", name);
            return;
        } else if (candidates.size() == 1) {
            contactToDelete = candidates.getFirst();
        } else {
            for (int i = 0; i < candidates.size(); i++) {
                System.out.println((i + 1) + ". " + candidates.get(i));
            }
            int index = readIntInRange(
                scanner,
                "Выберите контакт для удаления: ",
                1,
                candidates.size()
            );
            contactToDelete = candidates.get(index - 1);
        }

        if (contactService.deleteContact(contactToDelete)) {
            System.out.printf("Контакт \"%s\" успешно удален!%n", contactToDelete.getName());
        } else {
            System.out.printf("Контакт \"%s\" не найден!%n", contactToDelete.getName());
        }
    }

    private static void getContacts(ContactService contactService) {
        List<Contact> contacts = contactService.getContacts();
        if (contacts.isEmpty()) {
            System.out.println("Список контактов пуст!");
        } else {
            printContacts(contacts);
        }
    }

    private static void getContactByName(Scanner scanner, ContactService contactService) {
        String name = readNonEmptyString(scanner, "Введите имя: ");
        List<Contact> contactsByName = contactService.getContactsByName(name);
        if (contactsByName.isEmpty()) {
            System.out.printf("Контактов с именем \"%s\" не найдено!%n", name);
        } else {
            printContacts(contactsByName);
        }
    }

    private static void getContactsByGroup(Scanner scanner, ContactService contactService) {
        String group = readNonEmptyString(scanner, "Введите группу: ");
        List<Contact> contactsByGroup = contactService.getContactsByGroup(group);
        if (contactsByGroup.isEmpty()) {
            System.out.printf("Группа контактов \"%s\" не найдена%n", group);
        } else {
            printContacts(contactsByGroup);
        }
    }

    private static void printContacts(List<Contact> contacts) {
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            System.out.println(contact);
        }
    }

    private static void printMenu() {
        System.out.println("""
            
            ==============================
            1 - Добавить контакт
            2 - Удалить контакт
            3 - Посмотреть все контакты
            4 - Найти контакт
            5 - Посмотреть контакты по группе
            0 - Выйти
            ==============================
            """);
    }

    private static void seedMockData(ContactService contactService) {
        List<Contact> mock = List.of(
            new Contact("Иван", "+375-29-111-22-33", "ivan1@example.com", "Работа"),
            new Contact("Иван", "+375-29-222-33-44", "ivan2@example.com", "Семья"),
            new Contact("Иван", "+375-29-333-44-55", "ivan3@example.com", "Друзья"),
            new Contact("Анна", "+375-33-111-22-33", "anna1@example.com", "Работа"),
            new Contact("Анна", "+375-33-222-33-44", "anna2@example.com", "Семья"),
            new Contact("Анна", "+375-33-333-44-55", "anna3@example.com", "Друзья"),
            new Contact("Пётр", "+375-44-111-22-33", "petr1@example.com", "Работа"),
            new Contact("Пётр", "+375-44-222-33-44", "petr2@example.com", "Семья"),
            new Contact("Пётр", "+375-44-333-44-55", "petr3@example.com", "Друзья"),
            new Contact("Пётр", "+375-29-222-33-44", "petr@example.com", "Работа"),
            new Contact("Дмитрий", "+375-33-333-44-55", "dmitry@example.com", "Работа"),
            new Contact("Ольга", "+375-44-444-55-66", "olga@example.com", "Работа"),
            new Contact("Мария", "+375-33-666-77-88", "maria@example.com", "Семья"),
            new Contact("Сергей", "+375-29-888-99-00", "sergey@example.com", "Друзья"),
            new Contact("Елена", "+375-44-999-00-11", "elena@example.com", "Друзья"),
            new Contact("Николай", "+375-33-000-11-22", "nikolay@example.com", "Друзья")
        );

        Iterator<Contact> iterator = mock.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            contactService.addContact(
                contact.getName(), contact.getPhone(), contact.getEmail(), contact.getGroup()
            );
        }
    }
}
