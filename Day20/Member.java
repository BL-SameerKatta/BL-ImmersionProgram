import java.util.ArrayList;
import java.util.List;

/**
 * Represents a standard library member.
 * Demonstrates Encapsulation (private fields, public getters).
 */
public class Member {
    private String memberId;
    private String name;
    protected List<Book> borrowedBooks;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    /**
     * Max books a regular member can borrow.
     */
    public int getBorrowLimit() {
        return 3; 
    }

    public boolean borrowBook(Book book) {
        if (borrowedBooks.size() >= getBorrowLimit()) {
            System.out.println(name + " has reached their borrowing limit of " + getBorrowLimit() + " books.");
            return false;
        }
        
        if (book.borrowItem(this)) {
            borrowedBooks.add(book);
            return true;
        }
        return false;
    }

    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.returnItem(this);
        } else {
            System.out.println(name + " does not have this book borrowed.");
        }
    }

    public void viewBorrowedBooks() {
        System.out.println("\n--- Books borrowed by " + name + " ---");
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books currently borrowed.");
        } else {
            for (Book b : borrowedBooks) {
                System.out.println("- " + b.getTitle());
            }
        }
    }
}
