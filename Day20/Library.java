import java.util.*;

/**
 * Core Library System managing Books and Members.
 * Uses HashMap for O(1) lookups by ID and ArrayList for storage and sorting.
 */
public class Library {
    // HashMap for fast lookups by ISBN or MemberID
    private Map<String, Book> bookCatalog;
    private Map<String, Member> members;

    public Library() {
        bookCatalog = new HashMap<>();
        members = new HashMap<>();
    }

    public void addBook(Book book) {
        bookCatalog.put(book.getIsbn(), book);
        System.out.println("Added to catalog: " + book.getTitle());
    }

    public void registerMember(Member member) {
        members.put(member.getMemberId(), member);
        System.out.println("Registered member: " + member.getName());
    }

    public Member getMember(String memberId) {
        return members.get(memberId);
    }

    public Book getBook(String isbn) {
        return bookCatalog.get(isbn);
    }

    /**
     * Searches for books by a specific author.
     */
    public List<Book> searchByAuthor(String author) {
        List<Book> results = new ArrayList<>();
        for (Book book : bookCatalog.values()) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                results.add(book);
            }
        }
        return results;
    }

    /**
     * Displays all books in the catalog, sorted alphabetically by Title.
     */
    public void displayAllBooksSortedByTitle() {
        System.out.println("\n--- Library Catalog (Sorted by Title) ---");
        List<Book> sortedBooks = new ArrayList<>(bookCatalog.values());
        
        // Sorting using a lambda comparator
        sortedBooks.sort((b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()));
        
        for (Book book : sortedBooks) {
            book.displayBookDetails();
        }
    }
}
