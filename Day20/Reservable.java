/**
 * Interface defining the contract for items that can be reserved.
 */
public interface Reservable {
    boolean reserveItem(Member member);
    boolean cancelReservation(Member member);
}
