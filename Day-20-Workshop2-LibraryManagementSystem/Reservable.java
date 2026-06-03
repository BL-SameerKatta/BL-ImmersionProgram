/**
 * Reservable.java
 *
 * Day 20 - Workshop 2: OOP Design Challenge — Library Management System
 * Concepts: Interface, Contract definition, Multiple interface implementation
 *
 * Reservable defines the contract for any library member who can reserve books.
 * Reserving means placing a hold on a book that is currently borrowed by someone else.
 * When the book is returned, the reserved member gets priority to borrow it.
 *
 * Only PremiumMember implements both Borrowable AND Reservable.
 * Regular Member can only borrow — not reserve.
 *
 * This demonstrates the flexibility of interfaces:
 *   - PremiumMember implements Borrowable + Reservable
 *   - Member implements only Borrowable
 *   - Both extend the same parent class (Member)
 *   - Interface adds capability selectively
 */
public interface Reservable {

    /**
     * reserveBook(Book book)
     *
     * Abstract method — implementing class must define reservation logic.
     * Places a hold on a book that is currently unavailable.
     * The member will be notified when the book becomes available.
     *
     * @param book The Book object to reserve
     * @return true if reservation was successful, false otherwise
     */
    boolean reserveBook(Book book);

    /**
     * cancelReservation(Book book)
     *
     * Abstract method — implementing class must define cancellation logic.
     * Removes an existing reservation for this member on the given book.
     *
     * @param book The Book object whose reservation to cancel
     * @return true if cancellation was successful, false if no reservation existed
     */
    boolean cancelReservation(Book book);

    /**
     * getReservedBooks()
     *
     * Abstract method — returns list of books currently reserved by this member.
     *
     * @return Array of Book objects reserved by this member
     */
    Book[] getReservedBooks();
}
