/**
 * Borrowable.java
 *
 * Day 20 - Workshop 2: OOP Design Challenge — Library Management System
 * Concepts: Interface, Contract definition, Multiple interface implementation
 *
 * Borrowable defines the contract for any library member who can borrow books.
 * Any class implementing this interface MUST provide these borrowing behaviors.
 *
 * Why an interface and not an abstract class?
 *   - Member already extends a class hierarchy (Member <- PremiumMember)
 *   - Using an interface allows adding borrowing capability without
 *     breaking the existing inheritance chain
 *   - Multiple interfaces can be implemented simultaneously
 *
 * Classes implementing Borrowable:
 *   - Member (regular borrowing with standard limits)
 *   - PremiumMember (extended borrowing with higher limits)
 */
public interface Borrowable {

    /**
     * borrowBook(Book book)
     *
     * Abstract method — implementing class must define borrowing logic.
     * Handles the process of issuing a book to this member.
     * Should check if the member can borrow more books and if book is available.
     *
     * @param book The Book object to borrow
     * @return true if borrowing was successful, false otherwise
     */
    boolean borrowBook(Book book);

    /**
     * returnBook(Book book)
     *
     * Abstract method — implementing class must define return logic.
     * Handles the process of returning a previously borrowed book.
     *
     * @param book The Book object to return
     * @return true if return was successful, false if book wasn't borrowed
     */
    boolean returnBook(Book book);

    /**
     * getBorrowedBooks()
     *
     * Abstract method — implementing class must return list of borrowed books.
     * Used to display what a member currently has borrowed.
     *
     * @return Array of Book objects currently borrowed by this member
     */
    Book[] getBorrowedBooks();

    /**
     * getBorrowLimit()
     *
     * Abstract method — returns the maximum number of books
     * this member type can borrow simultaneously.
     * Regular Member: 3 books, PremiumMember: 6 books.
     *
     * @return Maximum borrow limit as an int
     */
    int getBorrowLimit();
}
