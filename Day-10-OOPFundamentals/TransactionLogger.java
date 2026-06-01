/**
 * TransactionLogger.java
 *
 * Day 10 - OOP with Java
 * Concepts: Separate class responsibility, static utility methods,
 *           method design, access modifiers
 *
 * This class is a utility class responsible for printing
 * formatted transaction summaries and reports.
 *
 * It demonstrates:
 *   - Separation of concerns (printing logic separated from BankAccount)
 *   - Static utility methods (no object needed to call them)
 *   - Clean method design with single responsibility
 */
public class TransactionLogger {

    /**
     * printHeader()
     *
     * Prints the top header banner for the bank simulation report.
     * Called once at the beginning of the simulation.
     */
    public static void printHeader() {
        System.out.println("============================================");
        System.out.println("         BRIDGEBANK — TRANSACTION LOG       ");
        System.out.println("============================================");
    }

    /**
     * printSectionTitle(String title)
     *
     * Prints a formatted section title separator.
     * Used to visually separate each account's transaction block.
     *
     * @param title The section heading to display
     */
    public static void printSectionTitle(String title) {
        System.out.println("\n-- " + title + " --");
        System.out.println("--------------------------------------------");
    }

    /**
     * printAccountSummary(BankAccount account)
     *
     * Prints a one-line summary of an account's current state.
     * Shows holder name and final balance after all transactions.
     *
     * @param account The BankAccount object to summarize
     */
    public static void printAccountSummary(BankAccount account) {
        System.out.printf("  %-10s | Final Balance: Rs. %10.2f%n",
                account.getHolder(), account.getBalance());
    }

    /**
     * printFooter(int totalAccounts)
     *
     * Prints the closing footer with total accounts created.
     * Called at the end of the simulation.
     *
     * @param totalAccounts Total number of accounts opened
     */
    public static void printFooter(int totalAccounts) {
        System.out.println("\n============================================");
        System.out.println("  Total Accounts Opened : " + totalAccounts);
        System.out.println("  Simulation Complete.");
        System.out.println("============================================");
    }
}
