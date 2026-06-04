/**
 * Demonstrates Counting Sort for bounded categories (e.g., genre codes 1-20).
 */
public class CountingSort {
    public static void sort(int[] arr, int maxVal) {
        int[] count = new int[maxVal + 1];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
        int index = 0;
        for (int i = 0; i <= maxVal; i++) {
            while (count[i] > 0) {
                arr[index++] = i;
                count[i]--;
            }
        }
    }
}
