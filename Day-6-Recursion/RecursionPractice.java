
public class RecursionPractice {

    public static void main(String[] args) {

        System.out.println("Factorial: " + factorial(5));
        System.out.println("Sum of Digits: " + sumOfDigits(1234));
        System.out.println("Reverse String: " + reverseString("BridgeLabz"));

        towerOfHanoi(3, 'A', 'C', 'B');
    }

    public static int factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }

    public static int sumOfDigits(int n) {
        if (n == 0) return 0;
        return n % 10 + sumOfDigits(n / 10);
    }

    public static String reverseString(String str) {
        if (str.isEmpty()) return str;
        return reverseString(str.substring(1)) + str.charAt(0);
    }

    public static void towerOfHanoi(int n, char source, char destination, char auxiliary) {
        if (n == 1) {
            System.out.println("Move disk 1 from " + source + " to " + destination);
            return;
        }

        towerOfHanoi(n - 1, source, auxiliary, destination);
        System.out.println("Move disk " + n + " from " + source + " to " + destination);
        towerOfHanoi(n - 1, auxiliary, destination, source);
    }
}
