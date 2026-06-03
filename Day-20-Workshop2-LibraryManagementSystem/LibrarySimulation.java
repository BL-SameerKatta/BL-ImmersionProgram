/**
 * LibrarySimulation.java
 *
 * Day 20 - Workshop 2: OOP Design Challenge — Library Management System
 * Concepts: All 4 OOP Pillars applied together
 *           Encapsulation, Abstraction, Inheritance, Polymorphism
 *           ArrayList, HashMap, Collections.sort(), Search & Filter
 *
 * This is the main driver class for the Day 20 Workshop simulation.
 *
 * Full system overview:
 *   Book (abstract)
 *     ├── PhysicalBook
 *     └── EBook
 *   Member (implements Borrowable)
 *     └── PremiumMember (implements Borrowable + Reservable)
 *   Library (manages ArrayList + HashMap storage)
 *   Borrowable (interface — borrow/return contract)
 *   Reservable (interface — reserve/cancel contract)
 *
 * What this simulation demonstrates:
 *   1. Abstract class usage — Book is abstract, only PhysicalBook/EBook created
 *   2. Interface implementation — Member implements Borrowable,
 *      PremiumMember implements Borrowable + Reservable
 *   3. Inheritance — PremiumMember extends Member
 *   4. Polymorphism — Book[] array, printInfo() resolves at runtime
 *   5. ArrayList — ordered book/member storage + sorting
 *   6. HashMap — fast O(1) lookup by ISBN and member ID
 *   7. Collections.sort() — sorting books by title and year
 *   8. Search & Filter — by title, author, ISBN, availability
 *
 * How to run:
 *   javac Book.java PhysicalBook.java EBook.java Borrowable.java Reservable.java
 *         Member.java PremiumMember.java Library.java LibrarySimulation.java
 *   java LibrarySimulation
 */
import java.util.ArrayList;

public class LibrarySimulation {

    /**
     * main(String[] args)
     *
     * Entry point of the program.
     * Creates a Library, adds books and members,
     * runs borrow/return/reserve operations,
     * and demonstrates search, filter, and sort.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {

        /*
         * ── SETUP: Create the Library ──
         */
        Library lib = new Library("BridgeLabz Central Library");

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║   BRIDGELABZ CENTRAL LIBRARY MANAGEMENT SYSTEM  ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        /*
         * ── ADD BOOKS ──
         * Mixing PhysicalBook and EBook objects.
         * Both stored as Book references in the library.
         * This is abstraction — Library doesn't care about the subtype.
         */
        System.out.println("\n--- Adding Books to Library ---");

        PhysicalBook pb1 = new PhysicalBook("ISBN001", "Clean Code",
                "Robert C. Martin", 2008, "A1-01", 3);
        PhysicalBook pb2 = new PhysicalBook("ISBN002", "The Pragmatic Programmer",
                "Andrew Hunt", 1999, "A1-02", 2);
        PhysicalBook pb3 = new PhysicalBook("ISBN003", "Introduction to Algorithms",
                "Thomas Cormen", 2009, "B2-05", 4);
        PhysicalBook pb4 = new PhysicalBook("ISBN004", "Design Patterns",
                "Gang of Four", 1994, "B2-06", 2);
        PhysicalBook pb5 = new PhysicalBook("ISBN005", "Head First Java",
                "Kathy Sierra", 2005, "C3-10", 5);

        EBook eb1 = new EBook("ISBN006", "Effective Java",
                "Joshua Bloch", 2018, "PDF", 8.5, "https://lib.bl.com/ej", 10);
        EBook eb2 = new EBook("ISBN007", "Java Concurrency in Practice",
                "Brian Goetz", 2006, "EPUB", 5.2, "https://lib.bl.com/jcp", 5);
        EBook eb3 = new EBook("ISBN008", "Spring in Action",
                "Craig Walls", 2022, "PDF", 12.0, "https://lib.bl.com/sia", 8);

        lib.addBook(pb1); lib.addBook(pb2); lib.addBook(pb3);
        lib.addBook(pb4); lib.addBook(pb5);
        lib.addBook(eb1); lib.addBook(eb2); lib.addBook(eb3);

        /*
         * ── REGISTER MEMBERS ──
         * Mix of regular Member and PremiumMember.
         * Both stored as Member references in the library.
         */
        System.out.println("\n--- Registering Members ---");

        Member m1 = new Member("M1001", "Ravi Kumar",  "ravi@mail.com");
        Member m2 = new Member("M1002", "Ankit Verma", "ankit@mail.com");
        Member m3 = new Member("M1003", "Sneha Joshi", "sneha@mail.com");

        PremiumMember pm1 = new PremiumMember("P2001", "Priya Shah",
                "priya@mail.com", 2000.0, "Gold");
        PremiumMember pm2 = new PremiumMember("P2002", "Rohan Das",
                "rohan@mail.com", 3500.0, "Platinum");

        lib.registerMember(m1); lib.registerMember(m2); lib.registerMember(m3);
        lib.registerMember(pm1); lib.registerMember(pm2);

        /*
         * ── DISPLAY CATALOG AND MEMBERS ──
         */
        lib.printAllBooks();
        lib.printAllMembers();

        /*
         * ── BORROW OPERATIONS ──
         * Regular and premium members borrow books.
         * Each borrow reduces available copies for PhysicalBook.
         */
        System.out.println("\n--- Borrow Operations ---");
        m1.borrowBook(pb1);  // Ravi borrows Clean Code
        m1.borrowBook(eb1);  // Ravi borrows Effective Java (ebook)
        m1.borrowBook(pb3);  // Ravi borrows Algorithms
        m1.borrowBook(pb5);  // Should FAIL — Ravi hit limit of 3

        pm1.borrowBook(pb2); // Priya borrows Pragmatic Programmer
        pm1.borrowBook(eb2); // Priya borrows Java Concurrency
        pm1.borrowBook(pb4); // Priya borrows Design Patterns
        pm1.borrowBook(eb3); // Priya borrows Spring in Action
        pm2.borrowBook(pb1); // Rohan borrows Clean Code (copy 2)
        pm2.borrowBook(pb1); // Rohan borrows Clean Code (copy 3)

        /*
         * ── RESERVE OPERATIONS ──
         * PremiumMember can reserve books that are fully borrowed.
         */
        System.out.println("\n--- Reserve Operations (PremiumMember only) ---");

        // Borrow all copies of pb4 first to make it unavailable
        m2.borrowBook(pb4);  // Ankit borrows Design Patterns (last copy — now unavailable)

        pm2.reserveBook(pb4);  // Rohan reserves Design Patterns (unavailable)
        pm1.reserveBook(pb4);  // Priya also tries to reserve (already reserved pb4 above)
        pm2.reserveBook(pb1);  // Rohan tries to reserve Clean Code (still available — info shown)

        // Regular member cannot reserve — method doesn't exist on Member
        System.out.println("  Regular Member m1 cannot call reserveBook() — not in Borrowable interface.");

        /*
         * ── RETURN OPERATIONS ──
         */
        System.out.println("\n--- Return Operations ---");
        m1.returnBook(pb1);   // Ravi returns Clean Code
        m2.returnBook(pb4);   // Ankit returns Design Patterns
        pm1.returnBook(eb1);  // Priya returns Effective Java
        m1.returnBook(pb5);   // Ravi never borrowed pb5 — error shown

        /*
         * ── CANCEL RESERVATION ──
         */
        System.out.println("\n--- Cancel Reservation ---");
        pm2.cancelReservation(pb4);         // Rohan cancels Design Patterns reservation
        pm2.cancelReservation(eb3);         // Rohan tries to cancel eb3 — no reservation found

        /*
         * ── VIEW BORROWED BOOKS PER MEMBER ──
         */
        System.out.println("\n--- Current Borrowed Books Per Member ---");
        lib.printMemberBorrowedBooks("M1001");
        lib.printMemberBorrowedBooks("P2001");
        lib.printMemberBorrowedBooks("P2002");

        /*
         * ── SEARCH BY ISBN ──
         */
        System.out.println("\n--- Search by ISBN ---");
        Book found = lib.searchByIsbn("ISBN003");
        System.out.println("  Search ISBN003 : " + (found != null ? found.getTitle() : "Not found"));
        Book notFound = lib.searchByIsbn("ISBN999");
        System.out.println("  Search ISBN999 : " + (notFound != null ? notFound.getTitle() : "Not found"));

        /*
         * ── SEARCH BY TITLE ──
         */
        System.out.println("\n--- Search by Title Keyword ---");
        ArrayList<Book> titleResults = lib.searchByTitle("java");
        System.out.println("  Books matching 'java':");
        for (Book b : titleResults) System.out.println("    - " + b.getTitle() + " (" + b.getBookType() + ")");

        /*
         * ── SEARCH BY AUTHOR ──
         */
        System.out.println("\n--- Search by Author ---");
        ArrayList<Book> authorResults = lib.searchByAuthor("martin");
        System.out.println("  Books by 'martin':");
        for (Book b : authorResults) System.out.println("    - " + b.getTitle());

        /*
         * ── FILTER AVAILABLE BOOKS ──
         */
        System.out.println("\n--- Available Books Only ---");
        ArrayList<Book> available = lib.filterAvailableBooks();
        System.out.println("  " + available.size() + " available book(s):");
        for (Book b : available) System.out.println("    - " + b.getTitle() + " (" + b.getDetails() + ")");

        /*
         * ── SORT BY TITLE ──
         */
        System.out.println("\n--- All Books Sorted by Title ---");
        ArrayList<Book> byTitle = lib.sortBooksByTitle();
        for (Book b : byTitle)
            System.out.printf("  %-30s | %4d%n", b.getTitle(), b.getYear());

        /*
         * ── SORT BY YEAR ──
         */
        System.out.println("\n--- All Books Sorted by Year (Newest First) ---");
        ArrayList<Book> byYear = lib.sortBooksByYear();
        for (Book b : byYear)
            System.out.printf("  %4d | %-30s | %s%n", b.getYear(), b.getTitle(), b.getAuthor());

        /*
         * ── OOP PILLARS SUMMARY ──
         */
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║              OOP PILLARS APPLIED                ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║ Encapsulation : All fields private, via getters ║");
        System.out.println("║ Abstraction   : Book abstract, Library uses Book ║");
        System.out.println("║ Inheritance   : PremiumMember extends Member    ║");
        System.out.println("║ Polymorphism  : printInfo() resolves at runtime  ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
    }
}
