/**
 * Interface defining the contract for items that can be borrowed from the library.
 */
public interface Borrowable {
    boolean borrowItem(Member member);
    boolean returnItem(Member member);
}
