import java.util.Arrays;

/**
 * Binary Search variants for finding the <em>first</em> and <em>last occurrence</em>
 * of a target value in a sorted array that may contain duplicate elements.
 *
 * <p><b>Why standard Binary Search isn't enough:</b><br>
 * Standard Binary Search stops at any occurrence of the target. If the array
 * has duplicates (e.g., many stars with identical brightness), we often need
 * the exact range {@code [firstIndex, lastIndex]} occupied by that value.
 * Each boundary requires a separate O(log n) binary search.</p>
 *
 * <p><b>Algorithms:</b></p>
 * <ul>
 *   <li><b>First occurrence</b>: when {@code arr[mid] == target}, record {@code mid}
 *       as a candidate and continue searching LEFT ({@code high = mid - 1}) to
 *       see if the target appears even earlier.</li>
 *   <li><b>Last occurrence</b>: when {@code arr[mid] == target}, record {@code mid}
 *       as a candidate and continue searching RIGHT ({@code low = mid + 1}) to
 *       see if the target appears even later.</li>
 * </ul>
 *
 * <p><b>Complexity:</b> O(log n) time, O(1) space for each boundary search.</p>
 *
 * <p><b>Story context (Day 23):</b><br>
 * The astronomer's catalogue contains repeated brightness values (multiple stars
 * at the same brightness level). Given a brightness, find the index of the
 * first and last star at that brightness level to determine the count of such stars.</p>
 *
 * <p>Corresponds to LC #34 — Find First and Last Position of Element in Sorted Array.</p>
 */
public class BinarySearchOccurrence {

    /**
     * Finds the index of the <em>first (leftmost)</em> occurrence of {@code target}
     * in the sorted array {@code arr}.
     *
     * <p>When a match is found at {@code mid}, the result is saved and the search
     * continues in the left half to find an earlier occurrence.</p>
     *
     * @param arr    a sorted array of integers in ascending order (may have duplicates)
     * @param target the value whose first occurrence is to be found
     * @return the index of the first occurrence of {@code target},
     *         or {@code -1} if the target is not present
     */
    public static int findFirst(int[] arr, int target) {
        int low    = 0;
        int high   = arr.length - 1;
        int result = -1; // -1 means "not found yet"

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                result = mid;   // record this match as a candidate
                high = mid - 1; // keep searching LEFT for an earlier occurrence
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    /**
     * Finds the index of the <em>last (rightmost)</em> occurrence of {@code target}
     * in the sorted array {@code arr}.
     *
     * <p>When a match is found at {@code mid}, the result is saved and the search
     * continues in the right half to find a later occurrence.</p>
     *
     * @param arr    a sorted array of integers in ascending order (may have duplicates)
     * @param target the value whose last occurrence is to be found
     * @return the index of the last occurrence of {@code target},
     *         or {@code -1} if the target is not present
     */
    public static int findLast(int[] arr, int target) {
        int low    = 0;
        int high   = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                result = mid;   // record this match as a candidate
                low = mid + 1;  // keep searching RIGHT for a later occurrence
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    /**
     * Returns a two-element array {@code [firstIndex, lastIndex]} representing
     * the range of indices where {@code target} appears in the sorted array.
     *
     * <p>If the target is not present, returns {@code [-1, -1]}.</p>
     *
     * <p>The count of occurrences is {@code lastIndex - firstIndex + 1}.</p>
     *
     * @param arr    a sorted array of integers (may contain duplicates)
     * @param target the value whose occurrence range is to be found
     * @return int array of length 2: {@code [firstIndex, lastIndex]},
     *         or {@code [-1, -1]} if not found
     */
    public static int[] findRange(int[] arr, int target) {
        return new int[]{findFirst(arr, target), findLast(arr, target)};
    }

    /**
     * Prints a step-by-step trace of the first-occurrence search, showing how
     * the algorithm continues searching left even after finding a match.
     *
     * @param arr    a sorted array (may contain duplicates)
     * @param target the target to search for
     */
    public static void traceFirstOccurrence(int[] arr, int target) {
        int low    = 0;
        int high   = arr.length - 1;
        int result = -1;
        int step   = 1;

        System.out.println("  Finding FIRST occurrence of " + target);
        System.out.printf("  %-5s %-5s %-5s %-5s %-10s %-15s%n",
                "Step", "Low", "High", "Mid", "arr[Mid]", "Action");
        System.out.println("  " + "─".repeat(50));

        while (low <= high) {
            int mid = low + (high - low) / 2;
            String action;

            if (arr[mid] == target) {
                result = mid;
                high   = mid - 1;
                action = "Match! Search LEFT";
            } else if (arr[mid] < target) {
                low    = mid + 1;
                action = "Go RIGHT";
            } else {
                high   = mid - 1;
                action = "Go LEFT";
            }

            System.out.printf("  %-5d %-5d %-5d %-5d %-10d %-15s%n",
                    step++, low, high, mid, arr[mid], action);
        }

        System.out.println("  → First occurrence at index: " + result);
    }

    /**
     * Main method demonstrating first/last occurrence search on the astronomer's
     * repeated-brightness story, with traces and count calculations.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // ── Story Problem: repeated brightness values ─────────────────────────
        System.out.println("=== Story Problem: First & Last Occurrence of Repeated Brightness ===");
        System.out.println("Catalogue has many stars at the same brightness level.");

        int[] catalogue = {
            10, 12, 12, 15, 15, 15, 15, 18, 18, 20,
            20, 20, 23, 25, 25, 27, 30, 30, 30, 30
        };
        System.out.println("Catalogue: " + Arrays.toString(catalogue));

        int targetBrightness = 15;
        int[] range = findRange(catalogue, targetBrightness);
        System.out.println("\nSearching for brightness = " + targetBrightness);
        System.out.println("First occurrence : index " + range[0]
                + "  (value = " + catalogue[range[0]] + ")");
        System.out.println("Last  occurrence : index " + range[1]
                + "  (value = " + catalogue[range[1]] + ")");
        System.out.println("Count of stars at brightness " + targetBrightness
                + " : " + (range[1] - range[0] + 1));

        // ── Multiple targets on the same catalogue ─────────────────────────────
        System.out.println("\n=== Occurrence Range for All Distinct Values ===");
        int[] targets = {10, 12, 15, 18, 20, 23, 25, 27, 30, 99};
        System.out.printf("  %-10s  %-8s  %-8s  %-8s%n",
                "Target", "First", "Last", "Count");
        System.out.println("  " + "─".repeat(40));
        for (int t : targets) {
            int[] r = findRange(catalogue, t);
            String count = (r[0] == -1) ? "not found"
                    : String.valueOf(r[1] - r[0] + 1);
            System.out.printf("  %-10d  %-8s  %-8s  %-8s%n",
                    t,
                    r[0] == -1 ? "-" : String.valueOf(r[0]),
                    r[1] == -1 ? "-" : String.valueOf(r[1]),
                    count);
        }

        // ── Step-by-step trace for first occurrence ────────────────────────────
        System.out.println("\n=== Trace: First Occurrence of 15 in catalogue ===");
        traceFirstOccurrence(catalogue, 15);

        // ── Classic LeetCode-style test cases ─────────────────────────────────
        System.out.println("\n=== Classic Test Cases ===");
        int[][] tests = {
            {5, 7, 7, 8, 8, 10},
            {5, 7, 7, 8, 8, 10},
            {}
        };
        int[] tgts = {8, 6, 0};
        for (int i = 0; i < tests.length; i++) {
            System.out.print("Array: " + Arrays.toString(tests[i])
                    + "  target = " + tgts[i] + "  →  range = ");
            if (tests[i].length == 0) {
                System.out.println("[-1, -1]  (empty array)");
            } else {
                System.out.println(Arrays.toString(findRange(tests[i], tgts[i])));
            }
        }
    }
}
