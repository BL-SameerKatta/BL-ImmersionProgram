/**
 * SelectionSort.java
 *
 * Day 21 - DSA: Sorting Algorithms
 * Concepts: Selection Sort, Minimum finding, Swap counting,
 *           Best/Worst case, Stability analysis
 *
 * Selection Sort divides the array into two parts:
 *   - Sorted portion (left side, grows from index 0)
 *   - Unsorted portion (right side, shrinks each pass)
 *
 * In each pass, it finds the MINIMUM element in the unsorted portion
 * and swaps it into the correct position at the start of the unsorted part.
 *
 * Key insight: Selection Sort always does exactly n-1 swaps regardless
 * of input order — this makes it predictable but not adaptive.
 *
 * Time Complexity:
 *   Best Case  : O(n^2) — still scans entire unsorted portion each time
 *   Average    : O(n^2)
 *   Worst Case : O(n^2)
 *
 * Space Complexity: O(1) — in-place
 *
 * Stability: NOT STABLE — equal elements may change relative order
 *            because swapping can skip over equal elements
 *
 * When to use:
 *   - When minimizing the number of swaps is important
 *   - Small arrays where simplicity matters
 *   - When write operations are expensive (only n-1 writes guaranteed)
 */
public class SelectionSort {

    /*
     * swapCount — tracks total swaps performed.
     * For Selection Sort this is always exactly n-1 for a fully unsorted array.
     */
    private int swapCount;

    /**
     * Constructor — SelectionSort()
     *
     * Initializes swapCount to 0.
     */
    public SelectionSort() {
        this.swapCount = 0;
    }

    /**
     * sort(int[] arr)
     *
     * Sorts an integer array in ascending order using Selection Sort.
     *
     * Algorithm:
     *   For each position i from 0 to n-2:
     *     1. Assume arr[i] is the minimum
     *     2. Scan arr[i+1] to arr[n-1] to find actual minimum index
     *     3. If minimum is not already at position i, swap it there
     *     4. Position i is now sorted — move to i+1
     *
     * @param arr The integer array to sort (modified in-place)
     */
    public void sort(int[] arr) {
        swapCount = 0;
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {

            /*
             * Assume the current position has the minimum value.
             * We will scan the rest of the array to verify.
             */
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                /*
                 * If we find a smaller element, update minIndex.
                 * We are NOT swapping yet — just finding the minimum's position.
                 */
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            /*
             * Only swap if the minimum is not already at position i.
             * This avoids a useless swap and correctly counts actual swaps.
             */
            if (minIndex != i) {
                int temp      = arr[i];
                arr[i]        = arr[minIndex];
                arr[minIndex] = temp;
                swapCount++;
            }
        }
    }

    /**
     * sortWithTrace(int[] arr)
     *
     * Same as sort() but prints the array state after each pass.
     * Also prints which element was selected as minimum each time.
     *
     * @param arr The integer array to sort with trace output
     */
    public void sortWithTrace(int[] arr) {
        swapCount = 0;
        int n = arr.length;
        System.out.println("  Initial  : " + arrayToString(arr));

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) minIndex = j;
            }

            if (minIndex != i) {
                int temp      = arr[i];
                arr[i]        = arr[minIndex];
                arr[minIndex] = temp;
                swapCount++;
            }

            System.out.printf("  Pass %2d   : %s  (min=%d placed at index %d)%n",
                    (i + 1), arrayToString(arr), arr[i], i);
        }
    }

    /**
     * sortAthletes(Athlete[] athletes)
     *
     * Sorts an array of Athlete objects by score in descending order.
     * Finds the MAXIMUM score in the unsorted portion each pass
     * (since we want highest score first = descending order).
     *
     * @param athletes Array of Athlete objects to sort by score descending
     */
    public void sortAthletes(Athlete[] athletes) {
        swapCount = 0;
        int n = athletes.length;

        for (int i = 0; i < n - 1; i++) {
            /*
             * For descending order, find MAX index instead of min index.
             */
            int maxIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (athletes[j].getScore() > athletes[maxIndex].getScore()) {
                    maxIndex = j;
                }
            }

            if (maxIndex != i) {
                Athlete temp       = athletes[i];
                athletes[i]        = athletes[maxIndex];
                athletes[maxIndex] = temp;
                swapCount++;
            }
        }
    }

    /**
     * getSwapCount()
     *
     * Returns total number of swaps performed in the last sort call.
     * Selection Sort makes at most n-1 swaps — useful when writes are expensive.
     *
     * @return Total swaps as an int
     */
    public int getSwapCount() {
        return swapCount;
    }

    /**
     * arrayToString(int[] arr)
     *
     * Helper — converts int array to a readable string for trace output.
     *
     * @param arr The array to convert
     * @return String representation like "[ 11 12 22 25 64 ]"
     */
    public String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[ ");
        for (int v : arr) sb.append(v).append(" ");
        return sb.append("]").toString();
    }
}
