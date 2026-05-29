
public class MathWizard {

    public static void main(String[] args) {

        System.out.println("Prime: " + isPrime(17));
        System.out.println("Factorial: " + factorial(5));
        System.out.println("Factorial Double: " + factorial(5.0));
        System.out.println("Fibonacci: " + fibonacci(10));
        System.out.println("GCD: " + gcd(24, 36));
        System.out.println("LCM: " + lcm(24, 36));
        System.out.println("Power: " + power(2, 5));
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static long factorial(int n) {
        long fact = 1;
        for (int i = 1; i <= n; i++) fact *= i;
        return fact;
    }

    public static double factorial(double n) {
        double fact = 1;
        for (int i = 1; i <= (int)n; i++) fact *= i;
        return fact;
    }

    public static int fibonacci(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    public static long power(int base, int exp) {
        long result = 1;
        for (int i = 0; i < exp; i++) result *= base;
        return result;
    }
}
