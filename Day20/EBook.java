/**
 * Represents a digital E-Book.
 * Demonstrates Inheritance from Book.
 */
public class EBook extends Book {
    private double fileSizeMB;
    private String downloadFormat;

    public EBook(String isbn, String title, String author, double fileSizeMB, String downloadFormat) {
        super(isbn, title, author);
        this.fileSizeMB = fileSizeMB;
        this.downloadFormat = downloadFormat;
    }

    @Override
    public void displayBookDetails() {
        System.out.println("[E-Book] " + getTitle() + " | Format: " + downloadFormat + " (" + fileSizeMB + "MB)");
    }

    @Override
    public boolean borrowItem(Member member) {
        // EBooks are always available for download, infinite copies
        System.out.println(member.getName() + " downloaded E-Book: " + getTitle());
        return true; 
    }

    @Override
    public boolean returnItem(Member member) {
        System.out.println(member.getName() + " deleted local copy of E-Book: " + getTitle());
        return true;
    }

    @Override
    public boolean reserveItem(Member member) {
        System.out.println("E-Books do not need to be reserved! You can download it directly.");
        return false;
    }

    @Override
    public boolean cancelReservation(Member member) {
        return false;
    }
}
