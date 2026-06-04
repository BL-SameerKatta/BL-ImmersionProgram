/**
 * Represents a Premium library member with higher privileges.
 * Demonstrates Inheritance and Polymorphism (overriding getBorrowLimit).
 */
public class PremiumMember extends Member {
    
    private double membershipFee;

    public PremiumMember(String memberId, String name, double membershipFee) {
        super(memberId, name);
        this.membershipFee = membershipFee;
    }

    /**
     * Premium members can borrow up to 10 books.
     * Demonstrates Polymorphism (Method Overriding).
     */
    @Override
    public int getBorrowLimit() {
        return 10; 
    }

    public void displayPremiumStatus() {
        System.out.println(getName() + " is a Premium Member (Fee Paid: $" + membershipFee + ")");
    }
}
