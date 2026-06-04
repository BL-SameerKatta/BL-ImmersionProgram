/**
 * Represents a physical hardcopy book.
 * Demonstrates Inheritance from Book.
 */
public class PhysicalBook extends Book {
    private String shelfLocation;
    private int weightGrams;

    public PhysicalBook(String isbn, String title, String author, String shelfLocation, int weightGrams) {
        super(isbn, title, author);
        this.shelfLocation = shelfLocation;
        this.weightGrams = weightGrams;
    }

    @Override
    public void displayBookDetails() {
        System.out.println("[Physical Book] " + getTitle() + " | Location: " + shelfLocation + " | Status: " + (isAvailable() ? "Available" : "Borrowed"));
    }

    @Override
    public boolean borrowItem(Member member) {
        if (isAvailable()) {
            setAvailable(false);
            System.out.println(member.getName() + " borrowed physical book: " + getTitle());
            return true;
        }
        System.out.println("Sorry, " + getTitle() + " is currently unavailable.");
        return false;
    }

    @Override
    public boolean returnItem(Member member) {
        if (!isAvailable()) {
            setAvailable(true);
            System.out.println(member.getName() + " returned physical book: " + getTitle());
            return true;
        }
        return false;
    }

    @Override
    public boolean reserveItem(Member member) {
        System.out.println(member.getName() + " reserved physical book: " + getTitle() + " for pickup at " + shelfLocation);
        return true;
    }

    @Override
    public boolean cancelReservation(Member member) {
        System.out.println(member.getName() + " cancelled reservation for physical book: " + getTitle());
        return true;
    }
}
