package org.pshenko.javacore.collections.service;

import org.pshenko.javacore.collections.exception.ContactAlreadyExistsException;
import org.pshenko.javacore.collections.exception.ContactNotFoundException;
import org.pshenko.javacore.collections.exception.GroupNotFoundException;
import org.pshenko.javacore.collections.model.Contact;

import java.util.*;

public class ContactBook {
    private static final List<Contact> contacts = new ArrayList<>();
    private static final Set<Contact> uniqueContacts = new HashSet<>();
    private static final Map<String, List<Contact>> groups = new HashMap<>();

    public void addContact(String name, String phone, String email, String group) throws ContactAlreadyExistsException {
        Contact candidate = new Contact(name, phone, email, group);
        if (!uniqueContacts.contains(candidate)) {
            contacts.add(candidate);
            uniqueContacts.add(candidate);
            groups.computeIfAbsent(group, k -> new ArrayList<>()).add(candidate);
        } else {
            throw new ContactAlreadyExistsException("Контакт уже существует в списке контактов");
        }
    }

    public void deleteContact(String name) throws ContactNotFoundException {
        if (uniqueContacts.contains(name)) {
            contacts.remove(name);
            uniqueContacts.remove(name);
            groups.computeIfAbsent(name, k -> new ArrayList<>()).remove(name);
        } else {
            throw new ContactNotFoundException("Не найдено контактов с именем \"%s\"".formatted(name));
        }
    }

    public void getContacts() {
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            System.out.println(contact);
        }
    }

    public void searchContactByName(String name) {
        Iterator<Contact> iterator = uniqueContacts.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.getName().equalsIgnoreCase(name)) {
                System.out.println(contact);
            }
        }
    }

    public void getContactsByGroup(String group) throws GroupNotFoundException {
        if (groups.containsKey(group)) {
            List<Contact> contactsByGroup = groups.get(group);
            Iterator<Contact> iterator = contactsByGroup.iterator();
            while (iterator.hasNext()) {
                Contact contact = iterator.next();
                System.out.println(contact);
            }
        } else {
            throw new GroupNotFoundException("Группа контактов \"%s\" не найдена".formatted(group));
        }
    }
}
