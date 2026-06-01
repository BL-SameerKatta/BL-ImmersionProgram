
import java.util.*;

class Contact {
    String name;
    String phone;
    String email;

    Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String toString() {
        return name + " | " + phone + " | " + email;
    }
}

public class AddressBookManagementSystem {

    static List<Contact> contacts = new ArrayList<>();
    static Map<String, Contact> contactMap = new HashMap<>();
    static Set<String> phoneNumbers = new HashSet<>();

    static void addContact(Contact contact) {
        if (phoneNumbers.add(contact.phone)) {
            contacts.add(contact);
            contactMap.put(contact.name, contact);
        }
    }

    static void displayContacts() {
        contacts.stream()
                .sorted(Comparator.comparing(c -> c.name))
                .forEach(System.out::println);
    }

    static void searchContact(String name) {
        System.out.println(contactMap.get(name));
    }

    static void deleteContact(String name) {
        Contact c = contactMap.remove(name);
        if (c != null) {
            contacts.remove(c);
            phoneNumbers.remove(c.phone);
        }
    }

    public static void main(String[] args) {
        addContact(new Contact("Sameer","111","sameer@gmail.com"));
        addContact(new Contact("Rahul","222","rahul@gmail.com"));
        addContact(new Contact("Priya","333","priya@gmail.com"));

        displayContacts();
        searchContact("Rahul");
        deleteContact("Rahul");

        System.out.println("\nAfter Delete:");
        displayContacts();
    }
}
