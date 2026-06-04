/**
 * Demonstrates the Bubble Sort algorithm.
 * Includes tracking of total swaps and an early exit if the array is already sorted.
 */
public class BubbleSort {
    public static void sort(int[] scores) {
        int n = scores.length;
        int totalSwaps = 0;
        boolean swapped;
        System.out.println("--- Starting Bubble Sort ---");
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (scores[j] > scores[j + 1]) {
                    int temp = scores[j];
                    scores[j] = scores[j + 1];
                    scores[j + 1] = temp;
                    swapped = true;
                    totalSwaps++;
                }
            }
            if (!swapped) {
                System.out.println("Array is already sorted! Stopping early.");
                break;
            }
        }
        System.out.println("Total swaps made: " + totalSwaps);
    }
}
