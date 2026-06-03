import java.util.Arrays;

/**
 * Counting Sort implementation for bounded integer categories.
 *
 * <p><b>How Counting Sort works:</b><br>
 * Instead of comparing elements, Counting Sort counts how many times each
 * distinct value appears. It then uses a cumulative (prefix-sum) count array
 * to calculate the correct output index for each element and places elements
 * directly into their sorted positions.</p>
 *
 * <pre>
 *  Input  : [4, 2, 2, 8, 3, 3, 1]   (values in range 1..8)
 *  Count  : [0, 1, 2, 2, 1, 0, 0, 0, 1]  (index = value)
 *  Prefix : [0, 1, 3, 5, 6, 6, 6, 6, 7]  (cumulative sum)
 *  Output : [1, 2, 2, 3, 3, 4, 8]
 * </pre>
 *
 * <p><b>Complexity:</b></p>
 * <ul>
 *   <li>Time  — O(n + k) : n = number of elements, k = range of values (max − min + 1).</li>
 *   <li>Space — O(n + k) : count array of size k + output array of size n.</li>
 *   <li>No comparisons are made — breaks the O(n log n) comparison-sort lower bound.</li>
 * </ul>
 *
 * <p><b>When to use:</b> Counting Sort is ideal when k is small relative to n.
 * For genre codes 1–20 and 1 000 000 books, k = 20 while n = 1 000 000, giving
 * effectively O(n) performance.</p>
 *
 * <p><b>Stability:</b> The prefix-sum implementation below is <em>stable</em> —
 * equal elements appear in the same relative order as in the input, because the
 * output array is filled by iterating the input from right to left.</p>
 *
 * <p><b>Story context (Day 22):</b><br>
 * A library sorts 1M books by genre code (bounded categories 1–20).
 * Counting Sort handles this in O(n + 20) = O(n) time, far faster than any
 * comparison-based sort. Empirical runtime is measured for n = 100 / 1000 / 10 000.</p>
 */
public class CountingSort {

    /**
     * Sorts the given array of non-negative integers using Counting Sort.
     *
     * <p>The range of values is automatically determined from the array's minimum
     * and maximum values. The sorted result is returned as a new array; the
     * original array is not modified.</p>
     *
     * @param arr the array to sort; must contain non-negative integers
     * @return a new array containing the same elements in sorted ascending order
     * @throws IllegalArgumentException if the array is null or empty
     */
    public static int[] sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty.");
        }

        /*
         * Step 1: Find the minimum and maximum values to determine the count-array range.
         * Using min allows the count array to start at 'min' instead of 0,
         * saving memory when all values are large (e.g., years like 1800–2025).
         */
        int min = arr[0], max = arr[0];
        for (int v : arr) {
            if (v < min) min = v;
            if (v > max) max = v;
        }

        int range = max - min + 1;

        /*
         * Step 2: Count occurrences of each value.
         * count[v - min] holds how many times value v appears.
         */
        int[] count = new int[range];
        for (int v : arr) {
            count[v - min]++;
        }

        /*
         * Step 3: Build the prefix-sum (cumulative) count array.
         * After this step, count[i] holds the number of elements with value ≤ (i + min).
         * This gives the correct starting output index for each value group.
         */
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        /*
         * Step 4: Place elements into the output array from right to left.
         * Iterating right-to-left ensures stability: among equal elements,
         * the one appearing last in the input is placed last in the output.
         */
        int[] output = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int pos = count[arr[i] - min] - 1;
            output[pos] = arr[i];
            count[arr[i] - min]--;
        }

        return output;
    }

    /**
     * Prints the count array and prefix-sum array for a small input to make
     * the internal mechanics visible step by step.
     *
     * @param arr the array whose counting mechanics are to be traced
     */
    public static void trace(int[] arr) {
        int min = arr[0], max = arr[0];
        for (int v : arr) {
            if (v < min) min = v;
            if (v > max) max = v;
        }
        int range = max - min + 1;

        // Count
        int[] count = new int[range];
        for (int v : arr) count[v - min]++;
        System.out.println("  Count array  (index = value - " + min + "): " + Arrays.toString(count));

        // Prefix sum
        int[] prefix = Arrays.copyOf(count, range);
        for (int i = 1; i < range; i++) prefix[i] += prefix[i - 1];
        System.out.println("  Prefix array (cumulative)              : " + Arrays.toString(prefix));

        // Sorted output
        System.out.println("  Sorted output                          : " + Arrays.toString(sort(arr)));
    }

    /**
     * Main method demonstrating Counting Sort on book genre codes (the Day 22 story),
     * a step-by-step trace, stability verification, and empirical runtime comparison.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // ── Story Problem: genre codes 1–20 ──────────────────────────────────
        System.out.println("=== Story Problem: Counting Sort — Book Genre Codes (1–20) ===");
        int[] genreCodes = new int[50];
        for (int i = 0; i < 50; i++) genreCodes[i] = (int)(Math.random() * 20) + 1;

        System.out.println("Before (first 20): " + Arrays.toString(Arrays.copyOf(genreCodes, 20)) + " ...");
        int[] sortedGenres = sort(genreCodes);
        System.out.println("After  (first 20): " + Arrays.toString(Arrays.copyOf(sortedGenres, 20)) + " ...");
        System.out.println("Range: 1–20, so count array size k = 20 (constant regardless of n).");

        // ── Step-by-step trace on small array ────────────────────────────────
        System.out.println("\n=== Step-by-Step Trace on [4, 2, 2, 8, 3, 3, 1] ===");
        System.out.println("  Input: [4, 2, 2, 8, 3, 3, 1]");
        trace(new int[]{4, 2, 2, 8, 3, 3, 1});

        // ── Stability verification ────────────────────────────────────────────
        System.out.println("\n=== Stability Verification ===");
        System.out.println("Counting Sort IS stable (right-to-left output fill preserves order).");
        System.out.println("Equal values maintain their original relative input order in the output.");

        // ── Empirical runtime on sizes 100, 1000, 10000 ───────────────────────
        System.out.println("\n=== Empirical Runtime: Counting Sort (genre codes 1–20) ===");
        for (int size : new int[]{100, 1000, 10000}) {
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) arr[i] = (int)(Math.random() * 20) + 1;
            long start = System.nanoTime();
            sort(arr);
            long elapsed = System.nanoTime() - start;
            System.out.printf("  n = %6d  →  time = %7d µs  (O(n + 20) = effectively O(n))%n",
                    size, elapsed / 1000);
        }

        // ── Why Counting Sort wins here over Merge/Quick ─────────────────────
        System.out.println("\n=== Why Counting Sort beats comparison sorts for genre codes ===");
        System.out.println("  Merge Sort  : O(n log n) comparisons");
        System.out.println("  Quick Sort  : O(n log n) avg comparisons");
        System.out.println("  Counting Sort: O(n + k) where k=20 → O(n)  ← winner for bounded categories");
        System.out.println("  For 1 000 000 books: ~20M ops vs ~1M ops. Counting Sort is ~20x fewer ops.");
    }
}
