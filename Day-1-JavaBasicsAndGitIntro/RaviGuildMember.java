/**
 * Day 1 - Core Programming
 * Concepts: Data Types, Variables, Type Casting, Operators
 *
 * Problem: Ravi just joined a coding guild. Store his name, age, rank, salary,
 * and membership fee using correct data types. Compute his annual bonus (12% of
 * salary), cast it to int, and print a formatted welcome card.
 */
public class RaviGuildMember {

    public static void main(String[] args) {

        String name          = "Ravi";
        int    age           = 25;
        String rank          = "Junior Coder";
        double salary        = 50000.00;
        float  membershipFee = 1500.50f;

        double bonusExact = salary * 0.12;
        int    bonusInt   = (int) bonusExact;

        System.out.println("=========================================");
        System.out.println("       WELCOME TO THE CODING GUILD       ");
        System.out.println("=========================================");
        System.out.printf("  Name           : %s%n",       name);
        System.out.printf("  Age            : %d years%n", age);
        System.out.printf("  Rank           : %s%n",       rank);
        System.out.printf("  Salary         : Rs. %.2f%n", salary);
        System.out.printf("  Membership Fee : Rs. %.2f%n", membershipFee);
        System.out.println("-----------------------------------------");
        System.out.printf("  Annual Bonus   : Rs. %.2f%n", bonusExact);
        System.out.printf("  Bonus (int)    : Rs. %d  (type cast applied)%n", bonusInt);
        System.out.println("=========================================");
        System.out.println("  Welcome aboard, " + name + "! Happy Coding!");
        System.out.println("=========================================");
    }
}
