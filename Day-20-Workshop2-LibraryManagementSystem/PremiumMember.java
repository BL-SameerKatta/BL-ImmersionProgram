/**
 * PremiumMember.java
 *
 * Day 20 - Workshop 2: OOP Design Challenge — Library Management System
 * Concepts: Inheritance, Multiple interface implementation,
 *           Method overriding, Extended borrow limit
 *
 * PremiumMember extends Member and additionally implements Reservable.
 * This demonstrates multiple interface implementation —
 * PremiumMember IS-A Member (inheritance) AND IS Borrowable AND IS Reservable.
 *
 * Differences from regular Member:
 *   - Borrow limit: 6 books (vs 3 for regular)
 *   - Can reserve books that are currently unavailable
 *   - Has a subscription fee and membership tier
 *
 * Why this design works:
 *   - Member handles all Borrowable logic (inherited by PremiumMember)
 *   - PremiumMember adds Reservable on top without changing Member
 *   - getBorrowLimit() is overridden to return 6 instead of 3
 */
public class PremiumMember extends Member implements Reservable {

    /*
     * Extended borrow limit for premium members.
     */
    private static final int PREMIUM_BORROW_LIMIT = 6;

    /*
     * PremiumMember-specific fields.
     * subscriptionFee  : annual fee paid by premium member
     * membershipTier   : "Gold" or "Platinum"
     * reservedBooks    : books currently reserved (max 3)
     * reserveCount     : how many books are currently reserved
     */
    private double subscriptionFee;
    private String membershipTier;
    private Book[] reservedBooks;
    private int    reserveCount;

    /**
     * Constructor — PremiumMember(String memberId, String name, String email,
     *                              double subscriptionFee, String membershipTier)
     *
     * Calls super(memberId, name, email) to initialize Member fields.
     * Sets premium-specific fields.
     * reservedBooks array initialized with size 3.
     *
     * @param memberId        Unique member ID (e.g., "P2001")
     * @param name            Full name of the member
     * @param email           Email address
     * @param subscriptionFee Annual subscription fee paid
     * @param membershipTier  "Gold" or "Platinum"
     */
    public PremiumMember(String memberId, String name, String email,
                         double subscriptionFee, String membershipTier) {
        super(memberId, name, email);
        this.subscriptionFee = subscriptionFee;
        this.membershipTier  = membershipTier;
        this.reservedBooks   = new Book[3];
        this.reserveCount    = 0;
    }

    /**
     * getSubscriptionFee() — Returns the annual subscription fee.
     * @return Subscription fee as a double
     */
    public double getSubscriptionFee() { return subscriptionFee; }

    /**
     * getMembershipTier() — Returns the membership tier.
     * @return "Gold" or "Platinum" as a String
     */
    public String getMembershipTier()  { return membershipTier; }

    /**
     * getBorrowLimit()
     *
     * Overrides Member's getBorrowLimit().
     * Premium members can borrow up to 6 books simultaneously.
     *
     * @return 6 (premium borrow limit)
     */
    @Override
    public int getBorrowLimit() {
        return PREMIUM_BORROW_LIMIT;
    }

    /**
     * reserveBook(Book book)
     *
     * Implements the Reservable interface method.
     * Places a hold on a book that is currently unavailable.
     * Only allowed if the book is actually unavailable (otherwise just borrow it).
     * Maximum 3 reservations allowed at a time.
     *
     * @param book The Book to reserve
     * @return true if reservation succeeded, false otherwise
     */
    @Override
    public boolean reserveBook(Book book) {
        if (book.isAvailable()) {
            System.out.println("  [INFO] \"" + book.getTitle()
                    + "\" is available — borrow it directly instead of reserving.");
            return false;
        }
        if (reserveCount >= 3) {
            System.out.println("  [LIMIT] " + getName()
                    + " has reached the reservation limit of 3.");
            return false;
        }
        // Check if already reserved
        for (int i = 0; i < reserveCount; i++) {
            if (reservedBooks[i] == book) {
                System.out.println("  [DUPLICATE] \"" + book.getTitle()
                        + "\" is already reserved by " + getName());
                return false;
            }
        }
        reservedBooks[reserveCount++] = book;
        System.out.println("  [RESERVED] " + getName() + " reserved \""
                + book.getTitle() + "\" — will be notified when available.");
        return true;
    }

    /**
     * cancelReservation(Book book)
     *
     * Implements the Reservable interface method.
     * Removes an existing reservation for the given book.
     *
     * @param book The Book whose reservation to cancel
     * @return true if cancellation succeeded, false if no reservation found
     */
    @Override
    public boolean cancelReservation(Book book) {
        for (int i = 0; i < reserveCount; i++) {
            if (reservedBooks[i] == book) {
                for (int j = i; j < reserveCount - 1; j++) {
                    reservedBooks[j] = reservedBooks[j + 1];
                }
                reservedBooks[--reserveCount] = null;
                System.out.println("  [CANCELLED] Reservation for \""
                        + book.getTitle() + "\" cancelled for " + getName());
                return true;
            }
        }
        System.out.println("  [ERROR] No reservation found for \""
                + book.getTitle() + "\" under " + getName());
        return false;
    }

    /**
     * getReservedBooks()
     *
     * Implements the Reservable interface method.
     * Returns a trimmed copy of the reservedBooks array.
     *
     * @return Array of currently reserved Book objects
     */
    @Override
    public Book[] getReservedBooks() {
        Book[] result = new Book[reserveCount];
        for (int i = 0; i < reserveCount; i++) result[i] = reservedBooks[i];
        return result;
    }

    /**
     * getMemberType()
     *
     * Overrides Member's getMemberType().
     * Returns the premium tier as the member type label.
     *
     * @return Membership tier + " Member" (e.g., "Gold Member")
     */
    @Override
    public String getMemberType() {
        return membershipTier + " Member";
    }

    /**
     * toString()
     *
     * Overrides Member's toString() to include premium details.
     *
     * @return Formatted string with all premium member details
     */
    @Override
    public String toString() {
        return super.toString() + String.format(
                " | Fee: Rs.%.0f | Reserved: %d", subscriptionFee, reserveCount);
    }
}
