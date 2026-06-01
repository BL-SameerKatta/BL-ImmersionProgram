/**
 * BankAccount.java
 *
 * Day 10 - OOP with Java
 * Concepts: Class, Object, Constructor, this keyword,
 *           static vs instance fields, access modifiers
 *
 * This class represents a bank account with basic banking operations.
 * It demonstrates:
 *   - Private instance fields (encapsulation)
 *   - Static fields shared across all objects
 *   - Constructor with 'this' keyword
 *   - Public methods for deposit, withdraw, statement
 *   - Access modifiers: private, public, static
 */
public class BankAccount {

    /*
     * Static fields — belong to the class, not to any single object.
     * Shared across all BankAccount instances.
     * totalAccounts  : tracks how many accounts have been created
     * nextAccountNumber : auto-increments for each new account
     */
    private static int totalAccounts     = 0;
    private static int nextAccountNumber = 1001;

    /*
     * Instance fields — each object has its own copy.
     * accountNumber  : unique ID assigned at creation
     * holder         : name of the account owner
     * balance        : current balance (can change over time)
     * transactionLog : array storing history of all transactions
     * logCount       : tracks how many log entries exist
     */
    private int      accountNumber;
    private String   holder;
    private double   balance;
    private String[] transactionLog;
    private int      logCount;

    /**
     * Constructor — BankAccount(String holder, double initialDeposit)
     *
     * Called when a new account is created.
     * Uses 'this' keyword to distinguish instance fields from parameters.
     * Auto-assigns a unique account number and increments the static counter.
     *
     * @param holder         Name of the account holder
     * @param initialDeposit Opening balance amount
     */
    public BankAccount(String holder, double initialDeposit) {
        this.accountNumber  = nextAccountNumber++;  // auto-assign and increment
        this.holder         = holder;
        this.balance        = initialDeposit;
        this.transactionLog = new String[20];
        this.logCount       = 0;
        totalAccounts++;                             // update class-level counter
        log("Account opened with Rs." + initialDeposit);
    }

    /**
     * deposit(double amount)
     *
     * Adds the given amount to the current balance.
     * Validates that the amount is positive before processing.
     * Logs the transaction with updated balance.
     *
     * @param amount The amount to deposit (must be > 0)
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("  [ERROR] Invalid deposit amount: " + amount);
            return;
        }
        balance += amount;
        log("Deposited    Rs." + String.format("%.2f", amount)
                + "  | Balance: Rs." + String.format("%.2f", balance));
        System.out.println("  Deposited Rs." + amount
                + " into " + holder + "'s account.");
    }

    /**
     * withdraw(double amount)
     *
     * Deducts the given amount from the current balance.
     * Validates the amount is positive and sufficient balance exists.
     * Blocks the withdrawal and logs it if it would cause an overdraft.
     *
     * @param amount The amount to withdraw (must be > 0 and <= balance)
     */
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("  [ERROR] Invalid withdrawal amount: " + amount);
            return;
        }
        if (amount > balance) {
            System.out.println("  [BLOCKED] Overdraft attempt of Rs." + amount
                    + " | Available: Rs." + String.format("%.2f", balance));
            log("OVERDRAFT BLOCKED Rs." + String.format("%.2f", amount)
                    + " | Balance unchanged: Rs." + String.format("%.2f", balance));
            return;
        }
        balance -= amount;
        log("Withdrawn    Rs." + String.format("%.2f", amount)
                + "  | Balance: Rs." + String.format("%.2f", balance));
        System.out.println("  Withdrawn Rs." + amount
                + " from " + holder + "'s account.");
    }

    /**
     * getStatement()
     *
     * Prints a formatted account statement to the console.
     * Displays account number, holder name, current balance,
     * and the full transaction history.
     */
    public void getStatement() {
        System.out.println("\n  ============================================");
        System.out.println("              ACCOUNT STATEMENT               ");
        System.out.println("  ============================================");
        System.out.println("  Account No  : " + accountNumber);
        System.out.println("  Holder      : " + holder);
        System.out.printf ("  Balance     : Rs. %.2f%n", balance);
        System.out.println("  --------------------------------------------");
        System.out.println("  Transaction History:");
        for (int i = 0; i < logCount; i++)
            System.out.println("    " + (i + 1) + ". " + transactionLog[i]);
        System.out.println("  ============================================");
    }

    /**
     * log(String entry)
     *
     * Private helper method to record a transaction into the log array.
     * Only accessible within this class — not part of the public API.
     * Silently ignores new entries if the log array is full.
     *
     * @param entry Description of the transaction to record
     */
    private void log(String entry) {
        if (logCount < transactionLog.length)
            transactionLog[logCount++] = entry;
    }

    /**
     * getTotalAccounts()
     *
     * Static method — called on the class, not on any object.
     * Returns the total number of BankAccount objects created so far.
     *
     * @return Total number of accounts created
     */
    public static int getTotalAccounts() {
        return totalAccounts;
    }

    /**
     * getBalance()
     *
     * Returns the current balance of this account.
     *
     * @return Current balance as a double
     */
    public double getBalance() {
        return balance;
    }

    /**
     * getHolder()
     *
     * Returns the name of the account holder.
     *
     * @return Holder name as a String
     */
    public String getHolder() {
        return holder;
    }
}
