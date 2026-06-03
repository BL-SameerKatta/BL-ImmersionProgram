import java.util.Arrays;

/**
 * Merge Sort implementation using the divide-and-conquer paradigm.
 *
 * <p><b>How Merge Sort works:</b><br>
 * The array is recursively split into two halves until each sub-array has a
 * single element (trivially sorted). Then the sub-arrays are merged back
 * together in sorted order. The key work happens in the merge step, not the
 * split step.</p>
 *
 * <pre>
 *  Divide:  [38, 27, 43, 3]  →  [38, 27]  [43, 3]
 *           [38, 27]          →  [38]      [27]
 *           [43,  3]          →  [43]       [3]
 *
 *  Conquer: [38] + [27]       →  [27, 38]
 *           [43] +  [3]       →  [3,  43]
 *           [27, 38] + [3, 43]→  [3, 27, 38, 43]
 * </pre>
 *
 * <p><b>Complexity:</b></p>
 * <ul>
 *   <li>Best / Average / Worst — O(n log n) : always divides evenly.</li>
 *   <li>Space — O(n) : requires auxiliary arrays during the merge step.</li>
 * </ul>
 *
 * <p><b>Stability:</b> Merge Sort is <em>stable</em> — equal elements from the
 * left sub-array are always placed before equal elements from the right
 * sub-array, preserving their original relative order.</p>
 *
 * <p><b>Story context (Day 22):</b><br>
 * A library sorts 1 million books by year. This class demonstrates the O(n log n)
 * merge step, shows each merge level, and measures empirical runtime on arrays
 * of size 100, 1000, and 10 000.</p>
 */
public class MergeSort {

    /** Counts total comparisons made during the sort; reset before each sort call. */
    private static long comparisonCount = 0;

    /**
     * Public entry point — sorts the given array in ascending order using Merge Sort
     * and returns the total number of element comparisons performed.
     *
     * @param arr the array to sort; modified in-place
     * @return total comparisons made across all merge operations
     */
    public static long sort(int[] arr) {
        comparisonCount = 0;
        mergeSort(arr, 0, arr.length - 1);
        return comparisonCount;
    }

    /**
     * Recursively divides the sub-array {@code arr[left..right]} into halves,
     * sorts each half, then merges them back together.
     *
     * <p>This is the "divide" step of divide-and-conquer. The actual work
     * (comparisons and writes) happens inside {@link #merge(int[], int, int, int)}.</p>
     *
     * @param arr   the array being sorted
     * @param left  the starting index of the sub-array (inclusive)
     * @param right the ending index of the sub-array (inclusive)
     */
    private static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            /*
             * Base case: a sub-array of size 0 or 1 is already sorted.
             */
            return;
        }

        /*
         * Find the midpoint without integer overflow (safe for large indices).
         */
        int mid = left + (right - left) / 2;

        mergeSort(arr, left, mid);       // sort the left half
        mergeSort(arr, mid + 1, right);  // sort the right half
        merge(arr, left, mid, right);    // merge the two sorted halves
    }

    /**
     * Merges two adjacent sorted sub-arrays into a single sorted sub-array.
     *
     * <p>The two sub-arrays are {@code arr[left..mid]} and {@code arr[mid+1..right]}.
     * A temporary array is used to hold the merged result, which is then
     * copied back into the original array.</p>
     *
     * <p>This step runs in O(n) time where n = right - left + 1.</p>
     *
     * @param arr   the array containing both sub-arrays
     * @param left  start index of the left sub-array
     * @param mid   end index of the left sub-array; {@code mid+1} is the start of right
     * @param right end index of the right sub-array
     */
    private static void merge(int[] arr, int left, int mid, int right) {
        int leftSize  = mid - left + 1;
        int rightSize = right - mid;

        /*
         * Copy both sub-arrays into temporary arrays to allow safe in-place merging.
         */
        int[] leftArr  = Arrays.copyOfRange(arr, left, mid + 1);
        int[] rightArr = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0;         // pointer for leftArr
        int j = 0;         // pointer for rightArr
        int k = left;      // pointer for the merged position in arr

        /*
         * Compare elements from both halves and place the smaller one next.
         * Using <= (not <) ensures stability: left-side equals come first.
         */
        while (i < leftSize && j < rightSize) {
            comparisonCount++;
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        /*
         * Copy any remaining elements from the left half (no comparisons needed).
         */
        while (i < leftSize) {
            arr[k++] = leftArr[i++];
        }

        /*
         * Copy any remaining elements from the right half (no comparisons needed).
         */
        while (j < rightSize) {
            arr[k++] = rightArr[j++];
        }
    }

    /**
     * Prints a step-by-step trace of Merge Sort showing each recursive split
     * and each merge operation on a small array.
     *
     * @param arr   the current sub-array being processed (passed by value for display)
     * @param depth the current recursion depth (used for indentation)
     * @param label a label such as "Left" or "Right" describing this sub-array
     */
    public static void traceMergeSort(int[] arr, int depth, String label) {
        String indent = "  ".repeat(depth);
        if (arr.length <= 1) {
            System.out.println(indent + label + ": " + Arrays.toString(arr) + " (base case)");
            return;
        }

        System.out.println(indent + label + ": " + Arrays.toString(arr) + " → splitting");
        int mid = arr.length / 2;
        int[] left  = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        traceMergeSort(left,  depth + 1, "Left ");
        traceMergeSort(right, depth + 1, "Right");

        // Simulate merge for display
        int[] merged = new int[arr.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            merged[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        }
        while (i < left.length)  merged[k++] = left[i++];
        while (j < right.length) merged[k++] = right[j++];

        System.out.println(indent + "Merged → " + Arrays.toString(merged));
    }

    /**
     * Main method demonstrating Merge Sort on the library-books story problem,
     * a divide-and-conquer trace, and empirical runtime on n = 100 / 1000 / 10 000.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // ── Story Problem: library sorts books ───────────────────────────────
        System.out.println("=== Story Problem: Merge Sort — Library Book Years ===");
        int[] books = {1995, 1870, 2003, 1945, 2021, 1780, 2015, 1900, 1999, 2010,
                       1855, 1967, 2008, 1923, 1984, 2018, 1776, 2000, 1912, 1850};

        System.out.println("Before: " + Arrays.toString(books));
        long comparisons = sort(books);
        System.out.println("After : " + Arrays.toString(books));
        System.out.println("Comparisons made: " + comparisons + " (O(n log n) merge step)");

        // ── Divide-and-conquer trace on small array ───────────────────────────
        System.out.println("\n=== Divide-and-Conquer Trace on [38, 27, 43, 3, 9, 82, 10] ===");
        traceMergeSort(new int[]{38, 27, 43, 3, 9, 82, 10}, 0, "Root ");

        // ── Empirical runtime on sizes 100, 1000, 10000 ───────────────────────
        System.out.println("\n=== Empirical Runtime Comparison (random arrays) ===");
        for (int size : new int[]{100, 1000, 10000}) {
            int[] arr = generateRandom(size);
            long start = System.nanoTime();
            sort(arr);
            long elapsed = System.nanoTime() - start;
            System.out.printf("  n = %6d  →  time = %7d µs  comparisons = %d%n",
                    size, elapsed / 1000, comparisonCount);
        }
    }

    /**
     * Generates an array of the given size filled with random integers in [0, 10000).
     *
     * @param size the number of elements
     * @return a new random integer array
     */
    static int[] generateRandom(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
        return arr;
    }
}
