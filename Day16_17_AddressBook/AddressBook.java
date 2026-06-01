import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Manages a collection of contacts using the Java Collections Framework.
 * Uses ArrayList for storage, HashMap for fast name lookup, and HashSet to enforce unique phone numbers.
 */
public class AddressBook {
    
    // Ordered storage for all contacts
    private List<Contact> contactList;
    
    // Fast lookup using name as the key
    private HashMap<String, Contact> contactMap;
    
    // Prevents duplicate phone numbers
    private HashSet<String> phoneSet;

    /**
     * Initializes the AddressBook collections.
     */
    public AddressBook() {
        this.contactList = new ArrayList<>();
        this.contactMap = new HashMap<>();
        this.phoneSet = new HashSet<>();
    }

    /**
     * Adds a new contact to the address book.
     * @param contact The contact to be added
     * @return true if added successfully, false if duplicate phone number exists
     */
    public boolean addContact(Contact contact) {
        if (phoneSet.contains(contact.getPhone())) {
            System.out.println("❌ Error: Phone number " + contact.getPhone() + " already exists!");
            return false;
        }
        
        contactList.add(contact);
        contactMap.put(contact.getName().toLowerCase(), contact);
        phoneSet.add(contact.getPhone());
        System.out.println("✅ Contact added: " + contact.getName());
        return true;
    }

    /**
     * Searches for a contact by their exact name.
     * @param name The name to search for
     * @return The Contact if found, null otherwise
     */
    public Contact searchByName(String name) {
        return contactMap.get(name.toLowerCase());
    }

    /**
     * Deletes a contact from the address book by name.
     * @param name The name of the contact to delete
     * @return true if deleted, false if not found
     */
    public boolean deleteContact(String name) {
        Contact contact = searchByName(name);
        if (contact != null) {
            contactList.remove(contact);
            contactMap.remove(name.toLowerCase());
            phoneSet.remove(contact.getPhone());
            System.out.println("🗑️ Deleted contact: " + name);
            return true;
        }
        System.out.println("⚠️ Contact not found: " + name);
        return false;
    }

    /**
     * Displays all contacts, sorted alphabetically by name.
     */
    public void displaySortedByName() {
        if (contactList.isEmpty()) {
            System.out.println("Address Book is empty.");
            return;
        }
        
        // Creates a copy to avoid altering the original insertion order if we don't want to
        List<Contact> sortedList = new ArrayList<>(contactList);
        Collections.sort(sortedList);
        
        System.out.println("\n--- Address Book (Sorted by Name) ---");
        for (Contact c : sortedList) {
            System.out.println(c.toString());
        }
        System.out.println("-------------------------------------\n");
    }
}
