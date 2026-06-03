import java.util.Arrays;

/**
 * Quick Sort implementation with two partitioning strategies:
 * <ol>
 *   <li><b>Lomuto partition</b> — uses the last element as pivot; simple and
 *       easy to understand; O(n²) worst case on already-sorted input.</li>
 *   <li><b>3-Way (Dutch National Flag) partition</b> — handles duplicate keys
 *       efficiently by partitioning into three regions: less-than, equal-to,
 *       and greater-than the pivot.</li>
 * </ol>
 *
 * <p><b>How Quick Sort works:</b><br>
 * A pivot element is chosen; elements smaller than the pivot are moved to its
 * left and elements greater are moved to its right (partitioning). The pivot
 * is now at its final sorted position. The process is then applied recursively
 * to the left and right sub-arrays.</p>
 *
 * <p><b>Complexity:</b></p>
 * <ul>
 *   <li>Best / Average — O(n log n) : pivot splits array roughly in half.</li>
 *   <li>Worst          — O(n²)      : pivot is always the smallest or largest element
 *                                     (e.g., already-sorted array with last-element pivot).</li>
 *   <li>Space          — O(log n)   : recursive call stack depth.</li>
 * </ul>
 *
 * <p><b>Stability:</b> Quick Sort is <em>NOT stable</em> — the swap step in
 * Lomuto partition can move equal elements past each other.</p>
 *
 * <p><b>Story context (Day 22):</b><br>
 * A library sorts 1M books by genre code (1–20). Quick Sort with Lomuto partition
 * is demonstrated on a general array; 3-way pivot is shown on an array with many
 * duplicate genre codes to illustrate why 3-way partitioning is superior for
 * bounded-category data.</p>
 */
public class QuickSort {

    /** Counts comparisons made during the Lomuto sort. */
    private static long lomutoComparisons = 0;

    /** Counts comparisons made during the 3-way sort. */
    private static long threeWayComparisons = 0;

    // =========================================================================
    // LOMUTO PARTITION SCHEME
    // =========================================================================

    /**
     * Public entry point for Quick Sort using the Lomuto partition scheme.
     *
     * <p>Lomuto always uses the <em>last element</em> of the sub-array as the pivot.
     * After partitioning, the pivot is placed at its final sorted index, and
     * Quick Sort is applied recursively to the sub-arrays on either side.</p>
     *
     * @param arr the array to sort; modified in-place
     * @return total comparisons made
     */
    public static long sortLomuto(int[] arr) {
        lomutoComparisons = 0;
        quickSortLomuto(arr, 0, arr.length - 1);
        return lomutoComparisons;
    }

    /**
     * Recursively sorts the sub-array {@code arr[low..high]} using Lomuto partition.
     *
     * @param arr  the array being sorted
     * @param low  the starting index of the sub-array (inclusive)
     * @param high the ending index of the sub-array (inclusive)
     */
    private static void quickSortLomuto(int[] arr, int low, int high) {
        if (low >= high) return; // base case: 0 or 1 elements

        int pivotIndex = partitionLomuto(arr, low, high);

        quickSortLomuto(arr, low, pivotIndex - 1);  // sort left of pivot
        quickSortLomuto(arr, pivotIndex + 1, high); // sort right of pivot
    }

    /**
     * Partitions the sub-array {@code arr[low..high]} using the Lomuto scheme.
     *
     * <p>Algorithm:</p>
     * <ol>
     *   <li>Choose {@code arr[high]} as the pivot.</li>
     *   <li>Maintain a boundary index {@code i} starting at {@code low - 1}.</li>
     *   <li>For each element from {@code low} to {@code high-1}: if it is ≤ pivot,
     *       increment {@code i} and swap {@code arr[i]} with {@code arr[j]}.</li>
     *   <li>Place the pivot at {@code arr[i+1]} by swapping with {@code arr[high]}.</li>
     * </ol>
     *
     * @param arr  the array to partition
     * @param low  start index
     * @param high end index (pivot is arr[high])
     * @return the final index of the pivot element
     */
    private static int partitionLomuto(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1; // boundary of elements ≤ pivot

        for (int j = low; j < high; j++) {
            lomutoComparisons++;
            if (arr[j] <= pivot) {
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        /*
         * Place the pivot in its correct sorted position by swapping with arr[i+1].
         */
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // =========================================================================
    // 3-WAY (DUTCH NATIONAL FLAG) PARTITION
    // =========================================================================

    /**
     * Public entry point for Quick Sort using 3-way (Dutch National Flag) partitioning.
     *
     * <p>3-way partitioning divides the array into three regions around the pivot:</p>
     * <ul>
     *   <li>{@code arr[low..lt-1]}  — elements strictly less than pivot</li>
     *   <li>{@code arr[lt..gt]}     — elements equal to pivot</li>
     *   <li>{@code arr[gt+1..high]} — elements strictly greater than pivot</li>
     * </ul>
     * <p>All equal-to-pivot elements are placed in their final positions in a
     * single pass, making this O(n) for arrays of all identical elements.</p>
     *
     * @param arr the array to sort; modified in-place
     * @return total comparisons made
     */
    public static long sortThreeWay(int[] arr) {
        threeWayComparisons = 0;
        quickSort3Way(arr, 0, arr.length - 1);
        return threeWayComparisons;
    }

    /**
     * Recursively sorts {@code arr[low..high]} using 3-way partitioning.
     *
     * @param arr  the array being sorted
     * @param low  starting index of the sub-array
     * @param high ending index of the sub-array
     */
    private static void quickSort3Way(int[] arr, int low, int high) {
        if (low >= high) return;

        /*
         * lt  : elements arr[low..lt-1]  are < pivot
         * gt  : elements arr[gt+1..high] are > pivot
         * i   : current element under examination
         */
        int pivot = arr[low];
        int lt = low;
        int gt = high;
        int i  = low + 1;

        while (i <= gt) {
            threeWayComparisons++;
            if (arr[i] < pivot) {
                swap(arr, lt++, i++); // move to less-than region
            } else if (arr[i] > pivot) {
                swap(arr, i, gt--);   // move to greater-than region (don't advance i)
            } else {
                i++;                  // already in equal-to region
            }
        }

        /*
         * Recursively sort only the less-than and greater-than regions.
         * The equal-to region [lt..gt] is already in its final position.
         */
        quickSort3Way(arr, low, lt - 1);
        quickSort3Way(arr, gt + 1, high);
    }

    /**
     * Swaps elements at indices {@code a} and {@code b} in the given array.
     *
     * @param arr the array in which to swap
     * @param a   index of the first element
     * @param b   index of the second element
     */
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * Prints a trace showing the pivot choice and partition result for each
     * recursive call on a small array.
     *
     * @param arr   the sub-array being partitioned (copy for display)
     * @param depth recursion depth for indentation
     */
    public static void traceLomuto(int[] arr, int depth) {
        if (arr.length <= 1) return;
        String indent = "  ".repeat(depth);
        int pivot = arr[arr.length - 1];
        System.out.println(indent + "Array: " + Arrays.toString(arr) + "  pivot=" + pivot);

        // Simulate partition for display
        int[] copy = Arrays.copyOf(arr, arr.length);
        int i = -1;
        for (int j = 0; j < copy.length - 1; j++) {
            if (copy[j] <= pivot) {
                i++;
                int t = copy[i]; copy[i] = copy[j]; copy[j] = t;
            }
        }
        int t = copy[i + 1]; copy[i + 1] = copy[copy.length - 1]; copy[copy.length - 1] = t;
        int pi = i + 1;

        System.out.println(indent + "After : " + Arrays.toString(copy) + "  pivot at index " + pi);

        if (pi > 0)
            traceLomuto(Arrays.copyOfRange(copy, 0, pi), depth + 1);
        if (pi < copy.length - 1)
            traceLomuto(Arrays.copyOfRange(copy, pi + 1, copy.length), depth + 1);
    }

    /**
     * Main method demonstrating Lomuto Quick Sort on book publication years,
     * 3-way Quick Sort on genre codes with duplicates, a recursion trace,
     * and empirical runtime on sizes 100 / 1000 / 10 000.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // ── Lomuto on book years ─────────────────────────────────────────────
        System.out.println("=== Quick Sort (Lomuto) — Library Book Publication Years ===");
        int[] years = {1995, 1870, 2003, 1945, 2021, 1780, 2015, 1900, 1999, 2010,
                       1855, 1967, 2008, 1923, 1984, 2018, 1776, 2000, 1912, 1850};
        System.out.println("Before: " + Arrays.toString(years));
        long cmp = sortLomuto(years);
        System.out.println("After : " + Arrays.toString(years));
        System.out.println("Comparisons: " + cmp);

        // ── Recursion trace on small array ───────────────────────────────────
        System.out.println("\n=== Lomuto Partition Trace on [10, 80, 30, 90, 40, 50, 70] ===");
        traceLomuto(new int[]{10, 80, 30, 90, 40, 50, 70}, 0);

        // ── 3-Way on genre codes (many duplicates) ────────────────────────────
        System.out.println("\n=== Quick Sort (3-Way) — Genre Codes 1–20 with Duplicates ===");
        int[] genres = new int[30];
        for (int i = 0; i < 30; i++) genres[i] = (int)(Math.random() * 20) + 1;
        System.out.println("Before: " + Arrays.toString(genres));
        long cmp3 = sortThreeWay(genres);
        System.out.println("After : " + Arrays.toString(genres));
        System.out.println("Comparisons (3-way): " + cmp3 + "  ← fewer than Lomuto on duplicates");

        // ── Lomuto vs 3-Way on all-same array (worst case for Lomuto) ─────────
        System.out.println("\n=== Duplicate Stress: 20 copies of value 5 ===");
        int[] allSame = new int[20];
        Arrays.fill(allSame, 5);
        long lomutoCmp = sortLomuto(Arrays.copyOf(allSame, allSame.length));
        long threewayCmp = sortThreeWay(Arrays.copyOf(allSame, allSame.length));
        System.out.println("Lomuto comparisons : " + lomutoCmp);
        System.out.println("3-Way  comparisons : " + threewayCmp + "  (O(n) — all equal handled in one pass)");

        // ── Empirical runtime on sizes 100, 1000, 10000 ───────────────────────
        System.out.println("\n=== Empirical Runtime: Lomuto Quick Sort ===");
        for (int size : new int[]{100, 1000, 10000}) {
            int[] arr = MergeSort.generateRandom(size);
            long start = System.nanoTime();
            sortLomuto(arr);
            long elapsed = System.nanoTime() - start;
            System.out.printf("  n = %6d  →  time = %7d µs%n", size, elapsed / 1000);
        }
    }
}
