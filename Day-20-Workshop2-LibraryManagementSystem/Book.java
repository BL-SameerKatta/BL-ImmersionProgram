/**
 * Book.java
 *
 * Day 20 - Workshop 2: OOP Design Challenge — Library Management System
 * Concepts: Abstract Class, Encapsulation, Abstraction, toString() override
 *
 * Book is the abstract base class for all book types in the library.
 * It holds the common attributes shared by every book:
 *   - ISBN, title, author, year, and availability status.
 *
 * Cannot be instantiated directly — only PhysicalBook and EBook can.
 *
 * Hierarchy:
 *   Book (abstract)
 *     ├── PhysicalBook
 *     └── EBook
 *
 * What this demonstrates:
 *   - Abstract class with both abstract and concrete methods
 *   - Encapsulation — all fields private, accessed via getters/setters
 *   - Abstract method getBookType() — each subclass returns its own label
 *   - Abstract method getDetails() — each subclass formats differently
 *   - Concrete method printInfo() — shared by all subclasses
 */
public abstract class Book {

    /*
     * Private fields — common to all book types.
     * isbn        : unique identifier for the book
     * title       : title of the book
     * author      : author's full name
     * year        : year of publication
     * isAvailable : true if book can be borrowed, false if already issued
     */
    private String  isbn;
    private String  title;
    private String  author;
    private int     year;
    private boolean isAvailable;

    /**
     * Constructor — Book(String isbn, String title, String author, int year)
     *
     * Initializes all common fields for any book.
     * Sets isAvailable to true by default — new books start as available.
     * Called by PhysicalBook and EBook via super(...).
     *
     * @param isbn   Unique International Standard Book Number
     * @param title  Title of the book
     * @param author Author's full name
     * @param year   Year of publication
     */
    public Book(String isbn, String title, String author, int year) {
        this.isbn        = isbn;
        this.title       = title;
        this.author      = author;
        this.year        = year;
        this.isAvailable = true; // all books available when added to library
    }

    /**
     * getIsbn() — Returns the ISBN of this book.
     * @return ISBN as a String
     */
    public String getIsbn()   { return isbn; }

    /**
     * getTitle() — Returns the title of this book.
     * @return Title as a String
     */
    public String getTitle()  { return title; }

    /**
     * getAuthor() — Returns the author's name.
     * @return Author name as a String
     */
    public String getAuthor() { return author; }

    /**
     * getYear() — Returns the publication year.
     * @return Year as an int
     */
    public int getYear()      { return year; }

    /**
     * isAvailable()
     *
     * Returns whether this book is currently available for borrowing.
     * true  = available (not issued to anyone)
     * false = not available (currently borrowed by a member)
     *
     * @return Availability status as a boolean
     */
    public boolean isAvailable() { return isAvailable; }

    /**
     * setAvailable(boolean status)
     *
     * Updates the availability status of this book.
     * Called when a book is borrowed (false) or returned (true).
     *
     * @param status New availability status
     */
    public void setAvailable(boolean status) { this.isAvailable = status; }

    /**
     * getBookType()
     *
     * Abstract method — no body here.
     * Each subclass must return its own type label.
     * PhysicalBook returns "Physical Book", EBook returns "E-Book".
     *
     * @return Book type label as a String
     */
    public abstract String getBookType();

    /**
     * getDetails()
     *
     * Abstract method — no body here.
     * Each subclass formats its own detailed description.
     * PhysicalBook includes shelf/copies info, EBook includes file/size info.
     *
     * @return Detailed description string
     */
    public abstract String getDetails();

    /**
     * printInfo()
     *
     * Concrete method shared by all subclasses.
     * Prints a formatted one-line summary of the book.
     * Calls getBookType() and getDetails() — resolved at runtime.
     */
    public void printInfo() {
        System.out.printf("  [%-13s] %-30s | %-20s | %4d | %-12s | %s%n",
                getBookType(), title, author, year,
                isAvailable ? "AVAILABLE" : "BORROWED",
                getDetails());
    }

    /**
     * toString()
     *
     * Overrides Object.toString().
     * Returns a compact string representation of the book.
     *
     * @return Formatted string with ISBN, title, and availability
     */
    @Override
    public String toString() {
        return String.format("Book[isbn=%s, title=%s, available=%b]",
                isbn, title, isAvailable);
    }
}
