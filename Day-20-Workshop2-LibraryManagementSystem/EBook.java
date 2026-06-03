/**
 * EBook.java
 *
 * Day 20 - Workshop 2: OOP Design Challenge — Library Management System
 * Concepts: Inheritance, Abstract method override, Encapsulation
 *
 * EBook is a concrete subclass of Book.
 * It represents a digital book that members can download or read online.
 *
 * Additional attributes beyond Book:
 *   - fileFormat     : format of the digital file (e.g., "PDF", "EPUB", "MOBI")
 *   - fileSizeMB     : file size in megabytes
 *   - downloadLink   : URL where the ebook can be accessed
 *   - maxSimultaneous: how many members can borrow/read at the same time
 *   - currentBorrowers: how many members currently have it borrowed
 *
 * Key design difference from PhysicalBook:
 *   - EBooks can be borrowed by multiple members simultaneously
 *   - No shelf location — it's digital
 *   - Never "runs out" unless simulatenous limit is hit
 */
public class EBook extends Book {

    /*
     * EBook-specific fields.
     * fileFormat        : "PDF", "EPUB", "MOBI" etc.
     * fileSizeMB        : size of the digital file in MB
     * downloadLink      : URL to access the ebook
     * maxSimultaneous   : max members who can borrow at the same time
     * currentBorrowers  : members currently borrowing this ebook
     */
    private String fileFormat;
    private double fileSizeMB;
    private String downloadLink;
    private int    maxSimultaneous;
    private int    currentBorrowers;

    /**
     * Constructor — EBook(String isbn, String title, String author, int year,
     *                      String fileFormat, double fileSizeMB,
     *                      String downloadLink, int maxSimultaneous)
     *
     * Calls super(isbn, title, author, year) to initialize Book fields.
     * Sets ebook-specific fields.
     * currentBorrowers starts at 0 — no one borrowing at creation.
     *
     * @param isbn             Unique book identifier
     * @param title            Title of the book
     * @param author           Author's name
     * @param year             Year of publication
     * @param fileFormat       Digital file format (e.g., "PDF")
     * @param fileSizeMB       File size in megabytes
     * @param downloadLink     URL to access the ebook
     * @param maxSimultaneous  Maximum simultaneous borrowers allowed
     */
    public EBook(String isbn, String title, String author, int year,
                 String fileFormat, double fileSizeMB,
                 String downloadLink, int maxSimultaneous) {
        super(isbn, title, author, year);
        this.fileFormat       = fileFormat;
        this.fileSizeMB       = fileSizeMB;
        this.downloadLink     = downloadLink;
        this.maxSimultaneous  = maxSimultaneous;
        this.currentBorrowers = 0;
    }

    /**
     * getFileFormat() — Returns the digital file format.
     * @return File format as a String (e.g., "PDF")
     */
    public String getFileFormat()      { return fileFormat; }

    /**
     * getFileSizeMB() — Returns the file size in MB.
     * @return File size as a double
     */
    public double getFileSizeMB()      { return fileSizeMB; }

    /**
     * getDownloadLink() — Returns the download/access URL.
     * @return Download link as a String
     */
    public String getDownloadLink()    { return downloadLink; }

    /**
     * getMaxSimultaneous() — Returns max simultaneous borrowers allowed.
     * @return Max simultaneous count as an int
     */
    public int getMaxSimultaneous()    { return maxSimultaneous; }

    /**
     * getCurrentBorrowers() — Returns how many members currently have this ebook.
     * @return Current borrower count as an int
     */
    public int getCurrentBorrowers()   { return currentBorrowers; }

    /**
     * borrowEBook()
     *
     * Allows a member to borrow this ebook.
     * Increments currentBorrowers if under the limit.
     * Sets isAvailable to false only when max simultaneous is reached.
     *
     * @return true if successfully borrowed, false if limit reached
     */
    public boolean borrowEBook() {
        if (currentBorrowers >= maxSimultaneous) return false;
        currentBorrowers++;
        if (currentBorrowers >= maxSimultaneous) setAvailable(false);
        return true;
    }

    /**
     * returnEBook()
     *
     * Allows a member to return this ebook.
     * Decrements currentBorrowers and sets availability back to true.
     */
    public void returnEBook() {
        if (currentBorrowers > 0) {
            currentBorrowers--;
            setAvailable(true);
        }
    }

    /**
     * getBookType()
     *
     * Overrides abstract method from Book.
     * Returns the type label for this book.
     *
     * @return "E-Book" as a String
     */
    @Override
    public String getBookType() {
        return "E-Book";
    }

    /**
     * getDetails()
     *
     * Overrides abstract method from Book.
     * Returns ebook-specific details: format, size, and borrower count.
     *
     * @return Details string with format and simultaneous borrow info
     */
    @Override
    public String getDetails() {
        return String.format("Format: %-5s | Size: %5.1f MB | Readers: %d/%d",
                fileFormat, fileSizeMB, currentBorrowers, maxSimultaneous);
    }
}
