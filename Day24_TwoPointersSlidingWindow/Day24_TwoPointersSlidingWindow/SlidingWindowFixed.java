import java.util.Arrays;

/**
 * Fixed-Size Sliding Window technique for subarray problems with a constant window length k.
 *
 * <p><b>What is the Sliding Window technique?</b><br>
 * Instead of recomputing the sum (or other aggregate) of every subarray from scratch —
 * which would be O(n·k) — the sliding window maintains a running aggregate and
 * updates it in O(1) per step by:</p>
 * <ul>
 *   <li>Adding the new element entering the window on the right.</li>
 *   <li>Removing the old element leaving the window on the left.</li>
 * </ul>
 * <p>This reduces the overall complexity to O(n).</p>
 *
 * <p><b>Pattern (fixed window of size k):</b></p>
 * <pre>
 *   Initialise window sum using first k elements.
 *   Slide from index k to n-1:
 *       windowSum += arr[i]          // add incoming right element
 *       windowSum -= arr[i - k]      // remove outgoing left element
 *       update answer if windowSum is better
 * </pre>
 *
 * <p><b>Story context (Day 24):</b><br>
 * A river station records hourly water levels. Find the contiguous period of
 * exactly k hours where the average water level was highest (maximum average
 * subarray of length k).</p>
 */
public class SlidingWindowFixed {

    /**
     * Finds the maximum sum of any contiguous subarray of exactly {@code k} elements.
     *
     * <p>The window is initialised with the first k elements, then slid one step at
     * a time to the right. At each step the element leaving on the left is subtracted
     * and the element entering on the right is added. The maximum sum seen is returned.</p>
     *
     * @param arr the array of water levels or values; must have length ≥ k
     * @param k   the fixed window size (number of elements); must be ≥ 1
     * @return the maximum subarray sum over all windows of size k
     * @throws IllegalArgumentException if k &lt; 1 or k &gt; arr.length
     */
    public static int maxSumSubarray(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            throw new IllegalArgumentException("k must be between 1 and arr.length inclusive.");
        }

        /*
         * Step 1: Compute the sum of the first window [0, k-1].
         */
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        int maxSum      = windowSum;
        int maxStart    = 0; // start index of the best window found

        /*
         * Step 2: Slide the window from index k to n-1.
         * Each iteration shifts the window one position to the right.
         */
        for (int i = k; i < arr.length; i++) {
            windowSum += arr[i];         // element entering the window on the right
            windowSum -= arr[i - k];     // element leaving the window on the left

            if (windowSum > maxSum) {
                maxSum   = windowSum;
                maxStart = i - k + 1;    // start index of the current window
            }
        }

        System.out.println("  Best window : indices [" + maxStart + ", " + (maxStart + k - 1) + "]"
                + "  →  " + Arrays.toString(Arrays.copyOfRange(arr, maxStart, maxStart + k)));
        return maxSum;
    }

    /**
     * Finds the maximum average of any contiguous subarray of exactly {@code k} elements.
     *
     * <p>Internally computes the maximum sum and divides by k.
     * Corresponds to LC #643 — Maximum Average Subarray I.</p>
     *
     * @param arr the array of values; must have length ≥ k
     * @param k   the fixed window size
     * @return the maximum average as a double, rounded to 5 decimal places
     */
    public static double maxAverageSubarray(int[] arr, int k) {
        int windowSum = 0;
        for (int i = 0; i < k; i++) windowSum += arr[i];

        int maxSum = windowSum;

        for (int i = k; i < arr.length; i++) {
            windowSum += arr[i] - arr[i - k];
            if (windowSum > maxSum) maxSum = windowSum;
        }

        return (double) maxSum / k;
    }

    /**
     * Prints a step-by-step trace of the sliding window, showing the window
     * contents, sum, and whether a new maximum was found at each slide.
     *
     * @param arr the array to trace on
     * @param k   the fixed window size
     */
    public static void traceWindow(int[] arr, int k) {
        System.out.println("  Array : " + Arrays.toString(arr) + "  k = " + k);
        System.out.printf("  %-6s %-10s %-8s %-25s %-10s%n",
                "Step", "Window", "Sum", "Contents", "MaxSoFar");
        System.out.println("  " + "─".repeat(65));

        int windowSum = 0;
        for (int i = 0; i < k; i++) windowSum += arr[i];
        int maxSum = windowSum;

        System.out.printf("  %-6s [%d-%d]    %-8d %-25s %-10d%n",
                "Init", 0, k - 1, windowSum,
                Arrays.toString(Arrays.copyOfRange(arr, 0, k)), maxSum);

        for (int i = k; i < arr.length; i++) {
            windowSum += arr[i] - arr[i - k];
            if (windowSum > maxSum) maxSum = windowSum;

            System.out.printf("  %-6d [%d-%d]    %-8d %-25s %-10d%n",
                    i - k + 1, i - k + 1, i, windowSum,
                    Arrays.toString(Arrays.copyOfRange(arr, i - k + 1, i + 1)), maxSum);
        }

        System.out.println("  Maximum sum = " + maxSum
                + "  |  Maximum average = " + String.format("%.2f", (double) maxSum / k));
    }

    /**
     * Main method demonstrating fixed-size sliding window on the river-station
     * water level story, with traces and multiple window sizes.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // ── Story Problem: max average water level over k hours ───────────────
        System.out.println("=== Story Problem: Max Average Water Level Over k Hours ===");
        System.out.println("Find the k-hour period with the highest average water level.");

        int[] waterLevels = {4, 2, 7, 3, 6, 9, 1, 5, 8, 3, 6, 2};
        System.out.println("Hourly water levels: " + Arrays.toString(waterLevels));

        for (int k : new int[]{3, 4, 5}) {
            System.out.println("\nWindow size k = " + k + ":");
            int maxSum = maxSumSubarray(waterLevels, k);
            System.out.printf("  Max sum = %d  |  Max average = %.4f%n",
                    maxSum, (double) maxSum / k);
        }

        // ── Step-by-step trace ────────────────────────────────────────────────
        System.out.println("\n=== Step-by-Step Trace (k = 3) ===");
        traceWindow(new int[]{1, 4, 2, 10, 23, 3, 1, 0, 20}, 3);

        // ── Max average subarray (LeetCode #643 style) ────────────────────────
        System.out.println("\n=== Max Average Subarray (LC #643 style) ===");
        int[][] testArrays = {
            {1, 12, -5, -6, 50, 3},
            {5, 5, 5, 5, 5},
            {-1, -2, -3, -4}
        };
        int[] kVals = {4, 3, 2};
        for (int i = 0; i < testArrays.length; i++) {
            double avg = maxAverageSubarray(testArrays[i], kVals[i]);
            System.out.printf("  Array: %-30s  k=%d  →  max avg = %.5f%n",
                    Arrays.toString(testArrays[i]), kVals[i], avg);
        }

        // ── Brute force vs sliding window complexity note ─────────────────────
        System.out.println("\n=== Complexity Comparison ===");
        System.out.println("  Brute force (nested loops): O(n·k) — recomputes sum for each window");
        System.out.println("  Sliding Window             : O(n)  — one pass, O(1) update per step");
        System.out.println("  For n=1,000,000 and k=1000: brute = 1B ops vs sliding = 1M ops.");
    }
}
