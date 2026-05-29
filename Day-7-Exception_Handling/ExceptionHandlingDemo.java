
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

public class ExceptionHandlingDemo {

    public static void main(String[] args) {

        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Division by zero not allowed.");
        }

        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid array index.");
        }

        try {
            Integer.parseInt("ABC");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
        }

        try {
            withdraw(500, 1000);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void withdraw(double balance, double amount)
            throws InsufficientFundsException {

        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient balance.");
        }

        System.out.println("Withdrawal successful.");
    }
}
