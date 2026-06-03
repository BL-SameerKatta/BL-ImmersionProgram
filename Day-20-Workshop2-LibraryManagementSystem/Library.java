/**
 * Library.java
 *
 * Day 20 - Workshop 2: OOP Design Challenge — Library Management System
 * Concepts: ArrayList, HashMap, Collections.sort(), Search & Filter,
 *           OOP Pillars applied together
 *
 * Library is the central system class that manages all books and members.
 * It uses:
 *   - ArrayList<Book>          : ordered list of all books in the library
 *   - HashMap<String, Book>    : fast ISBN-based book lookup
 *   - HashMap<String, Member>  : fast member ID-based member lookup
 *   - ArrayList<Member>        : ordered list of all members
 *
 * All 4 OOP pillars applied here:
 *   - Encapsulation  : all storage fields are private
 *   - Abstraction    : works with Book references (doesn't need to know Physical/EBook)
 *   - Inheritance    : accepts both Member and PremiumMember objects
 *   - Polymorphism   : polymorphic method calls on Book and Member references
 */
import java.util.*;

public class Library {

    /*
     * Library name — identifies this library instance.
     */
    private String libraryName;

    /*
     * bookList    : ArrayList preserves insertion order of books
     * bookIndex   : HashMap for O(1) lookup by ISBN
     * memberList  : ArrayList preserves insertion order of members
     * memberIndex : HashMap for O(1) lookup by member ID
     */
    private ArrayList<Book>          bookList;
    private HashMap<String, Book>    bookIndex;
    private ArrayList<Member>        memberList;
    private HashMap<String, Member>  memberIndex;

    /**
     * Constructor — Library(String libraryName)
     *
     * Initializes all storage collections.
     *
     * @param libraryName Name of this library
     */
    public Library(String libraryName) {
        this.libraryName  = libraryName;
        this.bookList     = new ArrayList<>();
        this.bookIndex    = new HashMap<>();
        this.memberList   = new ArrayList<>();
        this.memberIndex  = new HashMap<>();
    }

    /**
     * addBook(Book book)
     *
     * Adds a new book to the library.
     * Stores in both bookList (for ordered display) and bookIndex (for fast lookup).
     * Rejects duplicate ISBNs.
     *
     * @param book The Book object to add
     */
    public void addBook(Book book) {
        if (bookIndex.containsKey(book.getIsbn())) {
            System.out.println("  [DUPLICATE] Book with ISBN " + book.getIsbn()
                    + " already exists.");
            return;
        }
        bookList.add(book);
        bookIndex.put(book.getIsbn(), book);
        System.out.println("  [ADDED] \"" + book.getTitle() + "\" ("
                + book.getBookType() + ") added to library.");
    }

    /**
     * registerMember(Member member)
     *
     * Registers a new member (regular or premium) with the library.
     * Stores in both memberList and memberIndex.
     *
     * @param member The Member (or PremiumMember) to register
     */
    public void registerMember(Member member) {
        if (memberIndex.containsKey(member.getMemberId())) {
            System.out.println("  [DUPLICATE] Member ID " + member.getMemberId()
                    + " already registered.");
            return;
        }
        memberList.add(member);
        memberIndex.put(member.getMemberId(), member);
        System.out.println("  [REGISTERED] " + member.getMemberType()
                + " : " + member.getName() + " (ID: " + member.getMemberId() + ")");
    }

    /**
     * searchByIsbn(String isbn)
     *
     * Searches for a book by its ISBN using HashMap — O(1) lookup.
     *
     * @param isbn The ISBN to search for
     * @return The Book if found, null otherwise
     */
    public Book searchByIsbn(String isbn) {
        return bookIndex.get(isbn);
    }

    /**
     * searchByTitle(String keyword)
     *
     * Searches for books whose title contains the given keyword.
     * Case-insensitive linear search through bookList.
     *
     * @param keyword Search keyword (partial title match)
     * @return ArrayList of matching Book objects
     */
    public ArrayList<Book> searchByTitle(String keyword) {
        ArrayList<Book> results = new ArrayList<>();
        for (Book b : bookList) {
            if (b.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(b);
            }
        }
        return results;
    }

    /**
     * searchByAuthor(String author)
     *
     * Searches for books by author name (partial, case-insensitive).
     *
     * @param author Author name keyword to search for
     * @return ArrayList of matching Book objects
     */
    public ArrayList<Book> searchByAuthor(String author) {
        ArrayList<Book> results = new ArrayList<>();
        for (Book b : bookList) {
            if (b.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                results.add(b);
            }
        }
        return results;
    }

    /**
     * filterAvailableBooks()
     *
     * Returns only books that are currently available to borrow.
     *
     * @return ArrayList of available Book objects
     */
    public ArrayList<Book> filterAvailableBooks() {
        ArrayList<Book> results = new ArrayList<>();
        for (Book b : bookList) {
            if (b.isAvailable()) results.add(b);
        }
        return results;
    }

    /**
     * sortBooksByTitle()
     *
     * Returns a sorted copy of the book list, sorted by title alphabetically.
     * Uses Collections.sort() with a lambda comparator.
     *
     * @return Sorted ArrayList of all books
     */
    public ArrayList<Book> sortBooksByTitle() {
        ArrayList<Book> sorted = new ArrayList<>(bookList);
        Collections.sort(sorted, (a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()));
        return sorted;
    }

    /**
     * sortBooksByYear()
     *
     * Returns a sorted copy of the book list, sorted by publication year descending.
     *
     * @return Sorted ArrayList of all books (newest first)
     */
    public ArrayList<Book> sortBooksByYear() {
        ArrayList<Book> sorted = new ArrayList<>(bookList);
        Collections.sort(sorted, (a, b) -> b.getYear() - a.getYear());
        return sorted;
    }

    /**
     * getMemberById(String memberId)
     *
     * Retrieves a member by their unique ID using HashMap — O(1) lookup.
     *
     * @param memberId The member ID to look up
     * @return The Member if found, null otherwise
     */
    public Member getMemberById(String memberId) {
        return memberIndex.get(memberId);
    }

    /**
     * printAllBooks()
     *
     * Prints the complete library catalog using polymorphic printInfo() calls.
     * Works for both PhysicalBook and EBook without type-checking.
     */
    public void printAllBooks() {
        System.out.println("\n=== " + libraryName + " — BOOK CATALOG ===");
        System.out.printf("  %-15s %-30s | %-20s | %4s | %-12s | Details%n",
                "[Type]", "Title", "Author", "Year", "Status");
        System.out.println("  " + "-".repeat(110));
        for (Book b : bookList) {
            b.printInfo(); // polymorphic call
        }
        System.out.println("  Total books: " + bookList.size());
    }

    /**
     * printAllMembers()
     *
     * Prints all registered members using polymorphic toString() calls.
     * Works for both Member and PremiumMember.
     */
    public void printAllMembers() {
        System.out.println("\n=== " + libraryName + " — MEMBER REGISTRY ===");
        for (Member m : memberList) {
            System.out.println("  " + m); // polymorphic toString()
        }
        System.out.println("  Total members: " + memberList.size());
    }

    /**
     * printMemberBorrowedBooks(String memberId)
     *
     * Prints all books currently borrowed by a specific member.
     *
     * @param memberId The ID of the member to check
     */
    public void printMemberBorrowedBooks(String memberId) {
        Member m = memberIndex.get(memberId);
        if (m == null) {
            System.out.println("  Member not found: " + memberId);
            return;
        }
        System.out.println("\n  Books borrowed by " + m.getName() + ":");
        Book[] borrowed = m.getBorrowedBooks();
        if (borrowed.length == 0) {
            System.out.println("    No books currently borrowed.");
        } else {
            for (Book b : borrowed) System.out.println("    - " + b.getTitle());
        }
    }

    /**
     * getLibraryName() — Returns the library name.
     * @return Library name as a String
     */
    public String getLibraryName() { return libraryName; }
}
