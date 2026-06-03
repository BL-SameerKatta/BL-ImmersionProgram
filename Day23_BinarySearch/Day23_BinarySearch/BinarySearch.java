/**
 * Standard Binary Search on a sorted integer array.
 *
 * <p><b>How Binary Search works:</b><br>
 * Binary Search works on a <em>sorted</em> array by repeatedly halving the
 * search space. At each step it compares the target with the middle element:</p>
 * <ul>
 *   <li>If the middle element equals the target → found, return its index.</li>
 *   <li>If the target is smaller → discard the right half, search left.</li>
 *   <li>If the target is larger  → discard the left half, search right.</li>
 * </ul>
 *
 * <p>Each comparison eliminates half the remaining elements, giving
 * O(log n) time — far superior to O(n) linear search.</p>
 *
 * <p><b>Complexity:</b></p>
 * <ul>
 *   <li>Best case  — O(1)     : target is the middle element on the first check.</li>
 *   <li>Worst case — O(log n) : target is at the boundary or not present.</li>
 *   <li>Space      — O(1)     : iterative version uses no extra memory.</li>
 * </ul>
 *
 * <p><b>Story context (Day 23):</b><br>
 * An astronomer has 10 million sorted star-brightness entries. Finding a specific
 * brightness value with linear search would take up to 10M comparisons; Binary
 * Search finds it in at most log₂(10 000 000) ≈ 24 comparisons.</p>
 */
public class BinarySearch {

    /**
     * Searches for {@code target} in the sorted array {@code arr} using
     * iterative Binary Search.
     *
     * <p>The array must be sorted in <em>ascending</em> order. If the target
     * appears multiple times, the index of <em>any</em> matching element is
     * returned (not necessarily the first or last occurrence — see
     * {@link BinarySearchOccurrence} for that).</p>
     *
     * @param arr    a sorted array of integers (ascending order)
     * @param target the value to search for
     * @return the index of {@code target} in {@code arr}, or {@code -1} if not found
     */
    public static int search(int[] arr, int target) {
        int low  = 0;
        int high = arr.length - 1;

        while (low <= high) {
            /*
             * Compute mid without integer overflow.
             * Using (low + high) / 2 can overflow when both are large;
             * low + (high - low) / 2 is always safe.
             */
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return mid;                  // target found at index mid
            } else if (arr[mid] < target) {
                low = mid + 1;               // target is in the right half
            } else {
                high = mid - 1;              // target is in the left half
            }
        }

        return -1; // target not present in the array
    }

    /**
     * Searches for {@code target} and prints a step-by-step trace showing
     * the low, mid, and high pointers at each iteration.
     *
     * <p>Useful for visualising how Binary Search narrows the search window
     * and for counting the exact number of iterations required.</p>
     *
     * @param arr    a sorted array of integers (ascending order)
     * @param target the value to search for
     * @return the index of {@code target}, or {@code -1} if not found
     */
    public static int searchWithTrace(int[] arr, int target) {
        int low  = 0;
        int high = arr.length - 1;
        int step = 1;

        System.out.println("  Searching for target = " + target
                + " in array of size " + arr.length);
        System.out.printf("  %-6s %-6s %-6s %-8s %-10s%n",
                "Step", "Low", "High", "Mid", "arr[Mid]");
        System.out.println("  " + "─".repeat(42));

        while (low <= high) {
            int mid = low + (high - low) / 2;

            System.out.printf("  %-6d %-6d %-6d %-8d %-10d%n",
                    step++, low, high, mid, arr[mid]);

            if (arr[mid] == target) {
                System.out.println("  → Found at index " + mid
                        + " in " + (step - 1) + " step(s).");
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("  → Target NOT found after " + (step - 1) + " step(s).");
        return -1;
    }

    /**
     * Recursive implementation of Binary Search.
     *
     * <p>Functionally identical to the iterative version; included to demonstrate
     * that the algorithm maps naturally to recursion. Each call reduces the
     * problem to exactly one half of the current sub-array.</p>
     *
     * <p>Note: the iterative version is preferred in practice because it avoids
     * O(log n) call-stack depth overhead.</p>
     *
     * @param arr    a sorted array of integers
     * @param target the value to search for
     * @param low    the starting index of the current search range (inclusive)
     * @param high   the ending index of the current search range (inclusive)
     * @return the index of {@code target}, or {@code -1} if not found
     */
    public static int searchRecursive(int[] arr, int target, int low, int high) {
        /*
         * Base case: search space is exhausted — target is not in the array.
         */
        if (low > high) return -1;

        int mid = low + (high - low) / 2;

        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return searchRecursive(arr, target, mid + 1, high); // search right half
        } else {
            return searchRecursive(arr, target, low, mid - 1);  // search left half
        }
    }

    /**
     * Main method demonstrating standard Binary Search on the astronomer's
     * star-brightness catalogue, with step-by-step traces and a comparison
     * of Binary Search vs linear search iteration counts.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // ── Story Problem: 10M sorted star-brightness entries ────────────────
        System.out.println("=== Story Problem: Binary Search on Star-Brightness Catalogue ===");
        System.out.println("Astronomer has 10,000,000 sorted brightness entries.");
        System.out.printf("Binary Search finds any entry in at most ⌈log₂(10,000,000)⌉ = %d comparisons.%n",
                (int) Math.ceil(Math.log(10_000_000) / Math.log(2)));

        /*
         * Simulate a sorted catalogue of 10M brightness values (0 to 9,999,999).
         * In a real scenario this would be loaded from a file or database.
         */
        int catalogueSize = 10_000_000;
        int targetBrightness = 7_654_321;

        long startLinear = System.nanoTime();
        int linearResult = -1;
        for (int i = 0; i < catalogueSize; i++) {
            if (i == targetBrightness) { linearResult = i; break; }
        }
        long linearTime = (System.nanoTime() - startLinear) / 1000;

        /*
         * For a perfectly uniform sorted array arr[i] = i, Binary Search on
         * value v finds it at index v in O(log n) steps.
         */
        long startBinary = System.nanoTime();
        int low = 0, high = catalogueSize - 1, binaryResult = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mid == targetBrightness) { binaryResult = mid; break; }
            else if (mid < targetBrightness) low = mid + 1;
            else high = mid - 1;
        }
        long binaryTime = (System.nanoTime() - startBinary) / 1000;

        System.out.println("\nTarget brightness : " + targetBrightness);
        System.out.println("Linear Search     : found at index " + linearResult
                + "  |  time = " + linearTime + " µs");
        System.out.println("Binary Search     : found at index " + binaryResult
                + "  |  time = " + binaryTime + " µs");

        // ── Step-by-step trace on small sorted array ─────────────────────────
        System.out.println("\n=== Step-by-Step Trace on Small Sorted Array ===");
        int[] smallArr = {2, 5, 8, 12, 16, 23, 38, 45, 67, 90};
        System.out.println("Array: [2, 5, 8, 12, 16, 23, 38, 45, 67, 90]");

        System.out.println("\n-- Search for 23 (present) --");
        searchWithTrace(smallArr, 23);

        System.out.println("\n-- Search for 1 (not present) --");
        searchWithTrace(smallArr, 1);

        System.out.println("\n-- Search for 90 (last element) --");
        searchWithTrace(smallArr, 90);

        // ── Recursive variant ─────────────────────────────────────────────────
        System.out.println("\n=== Recursive Binary Search ===");
        int[] sorted = {3, 7, 15, 22, 31, 40, 55, 68, 74, 99};
        System.out.println("Array: [3, 7, 15, 22, 31, 40, 55, 68, 74, 99]");
        int idx = searchRecursive(sorted, 55, 0, sorted.length - 1);
        System.out.println("Recursive search for 55 → index " + idx);
        idx = searchRecursive(sorted, 50, 0, sorted.length - 1);
        System.out.println("Recursive search for 50 → index " + idx + " (not found)");

        // ── Log₂(n) iteration counts for various sizes ────────────────────────
        System.out.println("\n=== Max Iterations (⌈log₂ n⌉) for Array Sizes ===");
        for (int n : new int[]{10, 100, 1_000, 10_000, 1_000_000, 10_000_000}) {
            System.out.printf("  n = %10d  →  max iterations = %d%n",
                    n, (int) Math.ceil(Math.log(n) / Math.log(2)));
        }
    }
}
