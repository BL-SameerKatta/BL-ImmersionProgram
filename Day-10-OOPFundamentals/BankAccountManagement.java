
import java.util.*;

public class BankAccountManagement {

    static class Transaction {
        private String type;
        private double amount;

        public Transaction(String type, double amount) {
            this.type = type;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return type + " : Rs." + amount;
        }
    }

    static class BankAccount {
        private String accountNumber;
        private String holderName;
        private double balance;
        private List<Transaction> transactions;
        private static int totalAccounts = 0;

        public BankAccount(String accountNumber, String holderName, double balance) {
            this.accountNumber = accountNumber;
            this.holderName = holderName;
            this.balance = balance;
            this.transactions = new ArrayList<>();
            totalAccounts++;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                transactions.add(new Transaction("Deposit", amount));
            }
        }

        public void withdraw(double amount) {
            if (amount > balance) {
                System.out.println("Insufficient balance for " + holderName);
                return;
            }
            balance -= amount;
            transactions.add(new Transaction("Withdrawal", amount));
        }

        public void printStatement() {
            System.out.println("\n===== ACCOUNT STATEMENT =====");
            System.out.println("Account No : " + accountNumber);
            System.out.println("Holder     : " + holderName);
            System.out.println("Balance    : " + balance);

            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }

        public static int getTotalAccounts() {
            return totalAccounts;
        }
    }

    public static void main(String[] args) {
        BankAccount a1 = new BankAccount("A101", "Sameer", 5000);
        BankAccount a2 = new BankAccount("A102", "Rahul", 7000);
        BankAccount a3 = new BankAccount("A103", "Priya", 10000);

        a1.deposit(1000); a1.withdraw(500); a1.deposit(300); a1.withdraw(100); a1.deposit(250);
        a2.deposit(2000); a2.withdraw(1000); a2.deposit(400); a2.withdraw(250); a2.deposit(150);
        a3.deposit(3000); a3.withdraw(500); a3.deposit(700); a3.withdraw(900); a3.deposit(100);

        a1.printStatement();
        a2.printStatement();
        a3.printStatement();

        System.out.println("\nTotal Accounts Created = " + BankAccount.getTotalAccounts());
    }
}
