/**
 * BankSimulation.java
 *
 * Day 10 - OOP with Java
 * Concepts: Object creation, method calls, static vs instance usage,
 *           this keyword in action, access modifiers
 *
 * This is the main driver class for the Day 10 simulation.
 * It creates 3 BankAccount objects and runs 5 transactions each.
 *
 * What this demonstrates:
 *   - Creating objects using the 'new' keyword
 *   - Calling instance methods on objects (deposit, withdraw)
 *   - Calling static method on class (BankAccount.getTotalAccounts())
 *   - Overdraft protection in action
 *   - Using a separate utility class (TransactionLogger)
 *
 * How to run:
 *   javac BankAccount.java TransactionLogger.java BankSimulation.java
 *   java BankSimulation
 */
public class BankSimulation {

    /**
     * main(String[] args)
     *
     * Entry point of the program.
     * Creates 3 bank accounts for Ravi, Priya, and Ankit.
     * Runs 5 transactions on each account.
     * Prints individual statements and a final summary.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {

        TransactionLogger.printHeader();

        /*
         * Creating 3 BankAccount objects.
         * Each call to 'new BankAccount(...)' triggers the constructor,
         * increments totalAccounts, and assigns a unique account number.
         */
        BankAccount acc1 = new BankAccount("Ravi",  10000.00);
        BankAccount acc2 = new BankAccount("Priya", 25000.00);
        BankAccount acc3 = new BankAccount("Ankit",  5000.00);

        /*
         * Static method call — called on the class itself, not on any object.
         * BankAccount.getTotalAccounts() works because totalAccounts
         * is a static field updated in every constructor call.
         */
        System.out.println("\n  Total Accounts Opened So Far: "
                + BankAccount.getTotalAccounts());

        /* ── RAVI'S TRANSACTIONS ── */
        TransactionLogger.printSectionTitle("Ravi's Transactions");
        acc1.deposit(5000);       // valid deposit
        acc1.withdraw(3000);      // valid withdrawal
        acc1.deposit(2000);       // valid deposit
        acc1.withdraw(20000);     // OVERDRAFT — should be blocked
        acc1.withdraw(1000);      // valid withdrawal

        /* ── PRIYA'S TRANSACTIONS ── */
        TransactionLogger.printSectionTitle("Priya's Transactions");
        acc2.deposit(10000);      // valid deposit
        acc2.withdraw(8000);      // valid withdrawal
        acc2.deposit(3000);       // valid deposit
        acc2.withdraw(15000);     // valid withdrawal
        acc2.deposit(500);        // valid deposit

        /* ── ANKIT'S TRANSACTIONS ── */
        TransactionLogger.printSectionTitle("Ankit's Transactions");
        acc3.deposit(1000);       // valid deposit
        acc3.withdraw(4000);      // OVERDRAFT — should be blocked
        acc3.deposit(2000);       // valid deposit
        acc3.withdraw(500);       // valid withdrawal
        acc3.withdraw(3000);      // OVERDRAFT — should be blocked

        /*
         * Print full account statements for all 3 accounts.
         * Each statement shows the complete transaction log
         * and the final balance.
         */
        System.out.println("\n\n========== ACCOUNT STATEMENTS ==========");
        acc1.getStatement();
        acc2.getStatement();
        acc3.getStatement();

        /*
         * Print one-line summary for all accounts using
         * the TransactionLogger utility class.
         */
        System.out.println("\n========== FINAL SUMMARY ==========");
        TransactionLogger.printAccountSummary(acc1);
        TransactionLogger.printAccountSummary(acc2);
        TransactionLogger.printAccountSummary(acc3);

        TransactionLogger.printFooter(BankAccount.getTotalAccounts());
    }
}
