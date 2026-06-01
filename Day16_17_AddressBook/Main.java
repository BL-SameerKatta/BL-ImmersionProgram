/**
 * Main class to demonstrate the Address Book application operations.
 */
public class Main {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();

        System.out.println("--- 1. Adding Contacts ---");
        addressBook.addContact(new Contact("Alice Smith", "123-456-7890", "alice@example.com"));
        addressBook.addContact(new Contact("Charlie Brown", "555-123-4567", "charlie@example.com"));
        addressBook.addContact(new Contact("Bob Johnson", "987-654-3210", "bob@example.com"));
        
        // Attempting to add a duplicate phone number
        addressBook.addContact(new Contact("Duplicate Dave", "123-456-7890", "dave@example.com"));

        System.out.println("\n--- 2. Displaying Sorted Contacts ---");
        addressBook.displaySortedByName();

        System.out.println("--- 3. Searching for a Contact ---");
        String searchTarget = "Bob Johnson";
        Contact found = addressBook.searchByName(searchTarget);
        if (found != null) {
            System.out.println("Found: " + found.toString());
        } else {
            System.out.println("Not Found: " + searchTarget);
        }

        System.out.println("\n--- 4. Deleting a Contact ---");
        addressBook.deleteContact("Alice Smith");

        System.out.println("\n--- 5. Displaying Contacts After Deletion ---");
        addressBook.displaySortedByName();
    }
}
