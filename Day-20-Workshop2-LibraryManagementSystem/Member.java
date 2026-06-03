/**
 * Member.java
 *
 * Day 20 - Workshop 2: OOP Design Challenge — Library Management System
 * Concepts: Class implementing Interface, Encapsulation,
 *           Borrow limit enforcement, toString() override
 *
 * Member represents a regular library member.
 * It implements the Borrowable interface — must provide borrowBook(),
 * returnBook(), getBorrowedBooks(), and getBorrowLimit().
 *
 * Regular Member limits:
 *   - Can borrow up to 3 books at a time
 *   - Cannot reserve books (that's PremiumMember only)
 *
 * Hierarchy:
 *   Member (implements Borrowable)
 *     └── PremiumMember (implements Borrowable + Reservable)
 */
public class Member implements Borrowable {

    /*
     * Standard borrow limit for regular members.
     * Declared as a constant — same for all regular Member objects.
     */
    private static final int BORROW_LIMIT = 3;

    /*
     * Instance fields.
     * memberId     : unique identifier (e.g., "M1001")
     * name         : full name of the member
     * email        : contact email
     * borrowedBooks: array of books currently borrowed (max BORROW_LIMIT)
     * borrowCount  : how many books are currently borrowed
     */
    private String memberId;
    private String name;
    private String email;
    private Book[] borrowedBooks;
    private int    borrowCount;

    /**
     * Constructor — Member(String memberId, String name, String email)
     *
     * Initializes member fields.
     * borrowedBooks array size = BORROW_LIMIT (3 for regular members).
     * borrowCount starts at 0 — no books borrowed at creation.
     *
     * @param memberId Unique member ID (e.g., "M1001")
     * @param name     Full name of the member
     * @param email    Email address of the member
     */
    public Member(String memberId, String name, String email) {
        this.memberId     = memberId;
        this.name         = name;
        this.email        = email;
        this.borrowedBooks = new Book[BORROW_LIMIT];
        this.borrowCount  = 0;
    }

    /**
     * getMemberId() — Returns the member's unique ID.
     * @return Member ID as a String
     */
    public String getMemberId() { return memberId; }

    /**
     * getName() — Returns the member's full name.
     * @return Name as a String
     */
    public String getName()     { return name; }

    /**
     * getEmail() — Returns the member's email address.
     * @return Email as a String
     */
    public String getEmail()    { return email; }

    /**
     * getBorrowCount() — Returns how many books are currently borrowed.
     * @return Borrow count as an int
     */
    public int getBorrowCount() { return borrowCount; }

    /**
     * borrowBook(Book book)
     *
     * Implements the Borrowable interface method.
     * Allows this member to borrow a book if:
     *   1. Member has not reached the borrow limit
     *   2. The book is currently available
     *
     * If both conditions are met:
     *   - Adds book to borrowedBooks array
     *   - Increments borrowCount
     *   - Marks book as unavailable (for PhysicalBook, decrements copies)
     *
     * @param book The Book to borrow
     * @return true if borrow succeeded, false otherwise
     */
    @Override
    public boolean borrowBook(Book book) {
        if (borrowCount >= BORROW_LIMIT) {
            System.out.println("  [LIMIT] " + name + " has reached the borrow limit of "
                    + BORROW_LIMIT + " books.");
            return false;
        }
        if (!book.isAvailable()) {
            System.out.println("  [UNAVAILABLE] \"" + book.getTitle() + "\" is not available.");
            return false;
        }

        // Handle copy decrement for physical books
        if (book instanceof PhysicalBook) {
            ((PhysicalBook) book).borrowCopy();
        } else if (book instanceof EBook) {
            ((EBook) book).borrowEBook();
        }

        borrowedBooks[borrowCount++] = book;
        System.out.println("  [BORROWED] " + name + " borrowed \"" + book.getTitle() + "\"");
        return true;
    }

    /**
     * returnBook(Book book)
     *
     * Implements the Borrowable interface method.
     * Allows this member to return a previously borrowed book.
     * Searches the borrowedBooks array for the book, removes it,
     * and updates the book's availability.
     *
     * @param book The Book to return
     * @return true if return succeeded, false if book wasn't borrowed by this member
     */
    @Override
    public boolean returnBook(Book book) {
        for (int i = 0; i < borrowCount; i++) {
            if (borrowedBooks[i] == book) {
                // Shift remaining books left to fill the gap
                for (int j = i; j < borrowCount - 1; j++) {
                    borrowedBooks[j] = borrowedBooks[j + 1];
                }
                borrowedBooks[--borrowCount] = null;

                // Restore availability
                if (book instanceof PhysicalBook) {
                    ((PhysicalBook) book).returnCopy();
                } else if (book instanceof EBook) {
                    ((EBook) book).returnEBook();
                }

                System.out.println("  [RETURNED] " + name + " returned \"" + book.getTitle() + "\"");
                return true;
            }
        }
        System.out.println("  [ERROR] \"" + book.getTitle() + "\" was not borrowed by " + name);
        return false;
    }

    /**
     * getBorrowedBooks()
     *
     * Implements the Borrowable interface method.
     * Returns a copy of the borrowedBooks array trimmed to borrowCount.
     *
     * @return Array of currently borrowed Book objects
     */
    @Override
    public Book[] getBorrowedBooks() {
        Book[] result = new Book[borrowCount];
        for (int i = 0; i < borrowCount; i++) result[i] = borrowedBooks[i];
        return result;
    }

    /**
     * getBorrowLimit()
     *
     * Implements the Borrowable interface method.
     * Returns the maximum number of books a regular member can borrow.
     *
     * @return Borrow limit (3 for regular Member)
     */
    @Override
    public int getBorrowLimit() {
        return BORROW_LIMIT;
    }

    /**
     * getMemberType()
     *
     * Returns the type label for this member.
     * Overridden by PremiumMember to return "Premium Member".
     *
     * @return "Regular Member" as a String
     */
    public String getMemberType() {
        return "Regular Member";
    }

    /**
     * toString()
     *
     * Overrides Object.toString().
     * Returns a formatted summary of this member.
     *
     * @return Formatted string with member details
     */
    @Override
    public String toString() {
        return String.format("%-15s | %-6s | %-20s | %-16s | Books: %d/%d",
                getMemberType(), memberId, name, email, borrowCount, getBorrowLimit());
    }
}
