/**
 * PhysicalBook.java
 *
 * Day 20 - Workshop 2: OOP Design Challenge — Library Management System
 * Concepts: Inheritance, Abstract method override, Encapsulation
 *
 * PhysicalBook is a concrete subclass of Book.
 * It represents a physical printed book that sits on a shelf.
 *
 * Additional attributes beyond Book:
 *   - shelfLocation : where the book is physically stored (e.g., "A3-12")
 *   - totalCopies   : total number of copies in the library
 *   - availableCopies: how many copies are currently available to borrow
 *
 * Key design:
 *   - availableCopies decrements when a copy is borrowed
 *   - availableCopies increments when a copy is returned
 *   - isAvailable (from Book) is false only when ALL copies are borrowed
 */
public class PhysicalBook extends Book {

    /*
     * PhysicalBook-specific fields.
     * shelfLocation   : aisle and shelf code (e.g., "B2-05")
     * totalCopies     : total printed copies owned by library
     * availableCopies : copies currently not borrowed
     */
    private String shelfLocation;
    private int    totalCopies;
    private int    availableCopies;

    /**
     * Constructor — PhysicalBook(String isbn, String title, String author,
     *                            int year, String shelfLocation, int totalCopies)
     *
     * Calls super(isbn, title, author, year) to initialize Book fields.
     * Sets physical-specific fields.
     * availableCopies starts equal to totalCopies — all copies available at creation.
     *
     * @param isbn          Unique book identifier
     * @param title         Title of the book
     * @param author        Author's name
     * @param year          Year of publication
     * @param shelfLocation Physical shelf code (e.g., "A3-12")
     * @param totalCopies   Total number of copies in library
     */
    public PhysicalBook(String isbn, String title, String author,
                        int year, String shelfLocation, int totalCopies) {
        super(isbn, title, author, year);
        this.shelfLocation   = shelfLocation;
        this.totalCopies     = totalCopies;
        this.availableCopies = totalCopies; // all copies available initially
    }

    /**
     * getShelfLocation() — Returns the shelf location code.
     * @return Shelf location as a String
     */
    public String getShelfLocation()   { return shelfLocation; }

    /**
     * getTotalCopies() — Returns total number of copies owned.
     * @return Total copies as an int
     */
    public int getTotalCopies()        { return totalCopies; }

    /**
     * getAvailableCopies() — Returns number of copies available to borrow.
     * @return Available copies as an int
     */
    public int getAvailableCopies()    { return availableCopies; }

    /**
     * borrowCopy()
     *
     * Reduces availableCopies by 1 when a member borrows this book.
     * Sets the Book's isAvailable to false only if no copies remain.
     *
     * @return true if a copy was successfully borrowed, false if none available
     */
    public boolean borrowCopy() {
        if (availableCopies <= 0) return false;
        availableCopies--;
        if (availableCopies == 0) setAvailable(false);
        return true;
    }

    /**
     * returnCopy()
     *
     * Increases availableCopies by 1 when a member returns this book.
     * Sets the Book's isAvailable back to true.
     */
    public void returnCopy() {
        if (availableCopies < totalCopies) {
            availableCopies++;
            setAvailable(true);
        }
    }

    /**
     * getBookType()
     *
     * Overrides abstract method from Book.
     * Returns the type label for this book.
     *
     * @return "Physical" as a String
     */
    @Override
    public String getBookType() {
        return "Physical";
    }

    /**
     * getDetails()
     *
     * Overrides abstract method from Book.
     * Returns physical-specific details: shelf location and copy count.
     *
     * @return Details string with shelf and copy information
     */
    @Override
    public String getDetails() {
        return String.format("Shelf: %-6s | Copies: %d/%d",
                shelfLocation, availableCopies, totalCopies);
    }
}
