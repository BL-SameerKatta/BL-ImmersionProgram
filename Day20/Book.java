/**
 * Abstract class representing a generic Book in the library.
 * Demonstrates Abstraction.
 */
public abstract class Book implements Borrowable, Reservable {
    private String isbn;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return isAvailable; }

    protected void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    /**
     * Abstract method that must be implemented by concrete book types.
     * Demonstrates Polymorphism.
     */
    public abstract void displayBookDetails();

    @Override
    public String toString() {
        return title + " by " + author + " (ISBN: " + isbn + ")";
    }
}
