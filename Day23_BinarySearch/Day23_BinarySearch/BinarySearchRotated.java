/**
 * Binary Search variants for <em>rotated</em> sorted arrays.
 *
 * <p>A rotated sorted array is a sorted array that has been "rotated" at some
 * unknown pivot index. For example:</p>
 * <pre>
 *   Original  : [1, 2, 3, 4, 5, 6, 7]
 *   Rotated   : [4, 5, 6, 7, 1, 2, 3]   (rotated at index 3)
 * </pre>
 *
 * <p><b>Story context (Day 23):</b><br>
 * A telescope glitch scrambled (rotated) the astronomer's sorted brightness
 * catalogue. The astronomer must:</p>
 * <ol>
 *   <li>Search for a target brightness in the rotated catalogue — O(log n).</li>
 *   <li>Find the minimum brightness value (the rotation point) — O(log n).</li>
 * </ol>
 *
 * <p><b>Key insight:</b><br>
 * Even in a rotated array, at least one of the two halves around {@code mid}
 * is always sorted. By checking which half is sorted and whether the target
 * lies within that sorted half, Binary Search can still eliminate half the
 * search space at each step.</p>
 *
 * <p><b>Complexity:</b> O(log n) time, O(1) space for all methods in this class.</p>
 */
public class BinarySearchRotated {

    /**
     * Searches for {@code target} in a rotated sorted array with no duplicate values.
     *
     * <p>Algorithm at each step:</p>
     * <ol>
     *   <li>Compute {@code mid}.</li>
     *   <li>If {@code arr[mid] == target}, return {@code mid}.</li>
     *   <li>Determine which half is sorted:
     *       <ul>
     *         <li>If {@code arr[low] <= arr[mid]}: the left half is sorted.</li>
     *         <li>Otherwise: the right half is sorted.</li>
     *       </ul>
     *   </li>
     *   <li>Check if the target lies within the sorted half; if yes, search there;
     *       otherwise search the other half.</li>
     * </ol>
     *
     * @param arr    a rotated sorted array with distinct elements
     * @param target the value to search for
     * @return the index of {@code target}, or {@code -1} if not found
     */
    public static int searchRotated(int[] arr, int target) {
        int low  = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) return mid;

            /*
             * Determine which half is guaranteed to be sorted.
             * If arr[low] <= arr[mid], elements from low..mid are in sorted order.
             */
            if (arr[low] <= arr[mid]) {
                // Left half [low..mid] is sorted
                if (target >= arr[low] && target < arr[mid]) {
                    high = mid - 1; // target is within the sorted left half
                } else {
                    low = mid + 1;  // target must be in the right half
                }
            } else {
                // Right half [mid..high] is sorted
                if (target > arr[mid] && target <= arr[high]) {
                    low = mid + 1;  // target is within the sorted right half
                } else {
                    high = mid - 1; // target must be in the left half
                }
            }
        }

        return -1; // target not found
    }

    /**
     * Searches for {@code target} in a rotated sorted array and prints a
     * step-by-step trace showing which half is identified as sorted at each step.
     *
     * @param arr    a rotated sorted array with distinct elements
     * @param target the value to search for
     * @return the index of {@code target}, or {@code -1} if not found
     */
    public static int searchRotatedWithTrace(int[] arr, int target) {
        int low  = 0;
        int high = arr.length - 1;
        int step = 1;

        System.out.println("  Target = " + target);
        System.out.printf("  %-5s %-5s %-5s %-5s %-10s %-20s%n",
                "Step", "Low", "High", "Mid", "arr[Mid]", "Sorted half");
        System.out.println("  " + "─".repeat(55));

        while (low <= high) {
            int mid = low + (high - low) / 2;
            String sortedHalf;

            if (arr[mid] == target) {
                System.out.printf("  %-5d %-5d %-5d %-5d %-10d %-20s%n",
                        step, low, high, mid, arr[mid], "FOUND");
                System.out.println("  → Found at index " + mid);
                return mid;
            }

            if (arr[low] <= arr[mid]) {
                sortedHalf = "Left [" + low + ".." + mid + "]";
                if (target >= arr[low] && target < arr[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                sortedHalf = "Right [" + mid + ".." + high + "]";
                if (target > arr[mid] && target <= arr[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            System.out.printf("  %-5d %-5d %-5d %-5d %-10d %-20s%n",
                    step++, low, high, mid, arr[mid], sortedHalf);
        }

        System.out.println("  → Target NOT found.");
        return -1;
    }

    /**
     * Finds the minimum element in a rotated sorted array (no duplicates).
     *
     * <p>The minimum element is at the rotation point — the only place where
     * {@code arr[mid] > arr[mid+1]}. Binary Search locates this point in O(log n):</p>
     * <ul>
     *   <li>If {@code arr[mid] > arr[high]}: the rotation is in the right half;
     *       move {@code low} to {@code mid + 1}.</li>
     *   <li>Otherwise: the minimum is in the left half (including {@code mid});
     *       move {@code high} to {@code mid}.</li>
     * </ul>
     *
     * <p>This corresponds to LC #153 — Find Minimum in Rotated Sorted Array.</p>
     *
     * @param arr a rotated sorted array with distinct elements; must not be empty
     * @return the minimum value in the array
     */
    public static int findMinimum(int[] arr) {
        int low  = 0;
        int high = arr.length - 1;

        /*
         * If the array is not rotated (already sorted), the minimum is arr[low].
         */
        while (low < high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] > arr[high]) {
                /*
                 * The right half is "broken" (wraps around the rotation point).
                 * The minimum must be to the right of mid.
                 */
                low = mid + 1;
            } else {
                /*
                 * The right half is properly sorted; minimum is in [low..mid].
                 * Keep mid as a candidate by not doing mid-1.
                 */
                high = mid;
            }
        }

        return arr[low]; // low == high == index of minimum
    }

    /**
     * Main method demonstrating Binary Search on rotated arrays using the
     * astronomer's telescope-glitch story, with step traces and minimum finding.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // ── Story Problem: rotated brightness catalogue ───────────────────────
        System.out.println("=== Story Problem: Search in Rotated Star-Brightness Catalogue ===");
        System.out.println("Telescope glitch rotated the sorted catalogue at an unknown pivot.");

        int[] rotatedCatalogue = {55, 60, 67, 72, 80, 88, 91, 95, 10, 18, 23, 33, 41, 49};
        System.out.println("Rotated catalogue: [55, 60, 67, 72, 80, 88, 91, 95, 10, 18, 23, 33, 41, 49]");

        int target = 23;
        int idx = searchRotated(rotatedCatalogue, target);
        System.out.println("Search for brightness " + target + " → index " + idx
                + " (value = " + (idx >= 0 ? rotatedCatalogue[idx] : "not found") + ")");

        int minBrightness = findMinimum(rotatedCatalogue);
        System.out.println("Minimum brightness (rotation point): " + minBrightness);

        // ── Step-by-step trace ────────────────────────────────────────────────
        System.out.println("\n=== Step-by-Step Trace: Search in [4, 5, 6, 7, 0, 1, 2] ===");
        int[] classic = {4, 5, 6, 7, 0, 1, 2};

        System.out.println("\n-- Search for 0 --");
        searchRotatedWithTrace(classic, 0);

        System.out.println("\n-- Search for 4 (first element) --");
        searchRotatedWithTrace(classic, 4);

        System.out.println("\n-- Search for 3 (not present) --");
        searchRotatedWithTrace(classic, 3);

        // ── Finding minimum in various rotated arrays ─────────────────────────
        System.out.println("\n=== Finding Minimum in Rotated Arrays ===");
        int[][] testArrays = {
            {3, 4, 5, 1, 2},
            {4, 5, 6, 7, 0, 1, 2},
            {11, 13, 15, 17},       // not rotated — minimum is first element
            {2, 1},
            {1}
        };

        for (int[] arr : testArrays) {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < arr.length; i++) {
                sb.append(arr[i]);
                if (i < arr.length - 1) sb.append(", ");
            }
            sb.append("]");
            System.out.printf("  %-30s  →  minimum = %d%n", sb.toString(), findMinimum(arr));
        }
    }
}
