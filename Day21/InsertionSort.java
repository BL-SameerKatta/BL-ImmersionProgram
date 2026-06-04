/**
 * Demonstrates the Insertion Sort algorithm.
 * Builds the sorted array one item at a time.
 */
public class InsertionSort {
    public static void sort(int[] scores) {
        int n = scores.length;
        System.out.println("\n--- Starting Insertion Sort ---");
        for (int i = 1; i < n; i++) {
            int currentScore = scores[i];
            int j = i - 1;
            while (j >= 0 && scores[j] > currentScore) {
                scores[j + 1] = scores[j];
                j = j - 1;
            }
            scores[j + 1] = currentScore;
        }
    }
}
