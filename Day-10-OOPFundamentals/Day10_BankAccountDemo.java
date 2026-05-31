public class Day10_BankAccountDemo {

    static class BankAccount {
        private String accountNumber;
        private String accountHolder;
        private double balance;
        private static int totalAccounts = 0;

        public BankAccount(String accountNumber, String accountHolder, double balance) {
            this.accountNumber = accountNumber;
            this.accountHolder = accountHolder;
            this.balance = balance;
            totalAccounts++;
        }

        public void deposit(double amount) {
            balance += amount;
        }

        public void withdraw(double amount) {
            if (amount <= balance) {
                balance -= amount;
            }
        }

        public void displayDetails() {
            System.out.println("Account: " + accountNumber);
            System.out.println("Holder : " + accountHolder);
            System.out.println("Balance: " + balance);
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount("ACC101", "Sameer", 5000);
        account.deposit(1000);
        account.withdraw(500);
        account.displayDetails();
    }
}