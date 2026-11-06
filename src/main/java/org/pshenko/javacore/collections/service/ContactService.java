package org.pshenko.javacore.collections.service;

import org.pshenko.javacore.collections.model.Contact;

import java.util.*;

public class ContactService {
    private final List<Contact> contacts = new ArrayList<>();
    private final Set<Contact> uniqueContacts = new HashSet<>();
    private final Map<String, List<Contact>> groups = new HashMap<>();

    public boolean addContact(String name, String phone, String email, String group) {
        Contact candidate = new Contact(name, phone, email, group);
        if (!uniqueContacts.contains(candidate)) {
            contacts.add(candidate);
            uniqueContacts.add(candidate);
            groups.computeIfAbsent(group, k -> new ArrayList<>()).add(candidate);
            return true;
        }
        return false;
    }

    public boolean deleteContact(Contact contact) {
        if (!uniqueContacts.contains(contact)) {
            return false;
        }

        contacts.remove(contact);
        uniqueContacts.remove(contact);

        String group = contact.getGroup();
        List<Contact> groupContacts = groups.get(group);
        if (groupContacts != null) {
            groupContacts.remove(contact);
            if (groupContacts.isEmpty()) {
                groups.remove(group);
            }
        }
        return true;
    }

    public List<Contact> getContacts() {
        return Collections.unmodifiableList(contacts);
    }

    public List<Contact> getContactsByGroup(String group) {
        if (groups.containsKey(group)) {
            return Collections.unmodifiableList(groups.get(group));
        }
        return List.of();
    }

    public List<Contact> getContactsByName(String name) {
        List<Contact> result = new ArrayList<>();
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.getName().equals(name)) {
                result.add(contact);
            }
        }
        return result;
    }
}
