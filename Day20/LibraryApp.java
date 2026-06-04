/**
 * Main application class to demonstrate the Library Management System.
 */
public class LibraryApp {
    public static void main(String[] args) {
        System.out.println("=== Starting Library Management System ===");

        Library library = new Library();

        // 1. Create Books (Demonstrating Polymorphism)
        Book book1 = new PhysicalBook("ISBN-001", "The Java Programming Language", "James Gosling", "Shelf A1", 800);
        Book book2 = new EBook("ISBN-002", "Effective Java", "Joshua Bloch", 5.4, "PDF");
        Book book3 = new PhysicalBook("ISBN-003", "Clean Code", "Robert C. Martin", "Shelf B2", 600);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // 2. Create Members
        Member alice = new Member("M001", "Alice Smith");
        PremiumMember bob = new PremiumMember("M002", "Bob Johnson", 99.99);

        library.registerMember(alice);
        library.registerMember(bob);

        // 3. Display Catalog (Sorted)
        library.displayAllBooksSortedByTitle();

        // 4. Borrowing Process
        System.out.println("\n--- Borrowing Actions ---");
        alice.borrowBook(book1); // Borrows physical book
        bob.borrowBook(book2);   // Downloads ebook

        // Try borrowing a book already taken
        bob.borrowBook(book1);   

        // 5. Reserving Process
        System.out.println("\n--- Reserving Actions ---");
        book1.reserveItem(bob);  
        book2.reserveItem(alice); // EBooks shouldn't need reserve

        // 6. View Member Status
        alice.viewBorrowedBooks();
        bob.viewBorrowedBooks();
    }
}
