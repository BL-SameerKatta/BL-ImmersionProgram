import java.util.Arrays;
import java.util.List;

/**
 * Main driver class for Day 24: Two Pointers & Sliding Window.
 *
 * <p>Ties together all four concepts covered on Day 24 using the
 * <b>river station water levels story</b> as the unifying narrative:</p>
 *
 * <ol>
 *   <li>{@link TwoPointers#pairSum}       — pair of station indices summing to target.</li>
 *   <li>{@link TwoPointers#threeSum}      — unique triplets of stations summing to zero.</li>
 *   <li>{@link SlidingWindowFixed}        — max average water level over k hours.</li>
 *   <li>{@link SlidingWindowVariable}     — longest period where levels stayed within a range.</li>
 * </ol>
 *
 * <p><b>Technique summary:</b></p>
 * <pre>
 *  Technique               | Time     | Space  | Use when
 *  ────────────────────────|──────────|────────|──────────────────────────────────────
 *  Two Pointers (pair sum) | O(n)     | O(1)   | sorted array, find pair with property
 *  Two Pointers (3Sum)     | O(n²)    | O(1)   | sorted array, find triplet with property
 *  Sliding Window (fixed)  | O(n)     | O(1)   | subarray of EXACTLY k elements
 *  Sliding Window (variable)| O(n)   | O(1–k) | longest/shortest subarray with constraint
 * </pre>
 */
public class Main {

    /**
     * Entry point — runs all four Day 24 sections with the river-station story.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║   DAY 24 — Two Pointers & Sliding Window                    ║");
        System.out.println("║   Story: River Station Hourly Water / Pollution Readings     ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        // Shared datasets
        int[] pollutionSorted  = {-5, -3, -1, 0, 1, 2, 3, 4, 6, 8};
        int[] pollutionUnsorted = {-1, 0, 1, 2, -1, -4, 3, -2};
        int[] waterLevels      = {4, 2, 7, 3, 6, 9, 1, 5, 8, 3, 6, 2};

        // ── SECTION 1: Two Pointers — Pair Sum ───────────────────────────────
        System.out.println("┌──────────────────────────────────────────────────┐");
        System.out.println("│  SECTION 1 — Two Pointers: Pair Sum              │");
        System.out.println("└──────────────────────────────────────────────────┘");
        System.out.println("Sorted pollution indices: " + Arrays.toString(pollutionSorted));

        for (int target : new int[]{3, 7, 1}) {
            List<int[]> pairs = TwoPointers.pairSum(pollutionSorted, target);
            System.out.print("  Target = " + target + "  →  Pairs: ");
            if (pairs.isEmpty()) {
                System.out.println("none");
            } else {
                for (int[] p : pairs) System.out.print(Arrays.toString(p) + " ");
                System.out.println();
            }
        }

        System.out.println("\nStep-by-step trace for target = 7:");
        TwoPointers.tracePairSum(pollutionSorted, 7);

        // ── SECTION 2: Two Pointers — 3Sum ───────────────────────────────────
        System.out.println("\n┌──────────────────────────────────────────────────┐");
        System.out.println("│  SECTION 2 — Two Pointers: 3Sum (Triplets = 0)   │");
        System.out.println("└──────────────────────────────────────────────────┘");
        System.out.println("Pollution indices: " + Arrays.toString(pollutionUnsorted));

        List<int[]> triplets = TwoPointers.threeSum(Arrays.copyOf(pollutionUnsorted, pollutionUnsorted.length));
        System.out.println("Unique triplets summing to zero: " + triplets.size());
        for (int[] t : triplets) {
            System.out.println("  " + Arrays.toString(t)
                    + "  →  " + t[0] + " + " + t[1] + " + " + t[2] + " = 0");
        }

        // ── SECTION 3: Fixed Sliding Window ──────────────────────────────────
        System.out.println("\n┌──────────────────────────────────────────────────┐");
        System.out.println("│  SECTION 3 — Fixed Sliding Window (max avg k hrs)│");
        System.out.println("└──────────────────────────────────────────────────┘");
        System.out.println("Hourly water levels: " + Arrays.toString(waterLevels));

        for (int k : new int[]{3, 4, 5}) {
            int maxSum = SlidingWindowFixed.maxSumSubarray(Arrays.copyOf(waterLevels, waterLevels.length), k);
            System.out.printf("  k = %d  →  max sum = %d  max avg = %.2f%n",
                    k, maxSum, (double) maxSum / k);
        }

        System.out.println("\nDetailed trace (k = 3):");
        SlidingWindowFixed.traceWindow(new int[]{1, 4, 2, 10, 23, 3, 1, 0, 20}, 3);

        // ── SECTION 4: Variable Sliding Window ───────────────────────────────
        System.out.println("\n┌──────────────────────────────────────────────────┐");
        System.out.println("│  SECTION 4 — Variable Window (within range)       │");
        System.out.println("└──────────────────────────────────────────────────┘");
        System.out.println("Hourly water levels: " + Arrays.toString(waterLevels));

        for (int limit : new int[]{3, 5, 8}) {
            int len = SlidingWindowVariable.longestWithinRange(waterLevels, limit);
            System.out.println("  Limit = " + limit
                    + "  →  longest valid period = " + len + " hours");
        }

        System.out.println("\nDetailed trace (limit = 4):");
        SlidingWindowVariable.traceWindow(new int[]{8, 2, 4, 7, 4, 2, 3, 1, 5, 9}, 4);

        System.out.println("\nLongest substring without repeating characters:");
        String[] strs = {"abcabcbb", "pwwkew", "riverstation"};
        for (String s : strs) {
            System.out.print("  \"" + s + "\"  →  length = ");
            System.out.println(SlidingWindowVariable.longestUniqueSubstring(s));
        }

        // ── Final Summary ─────────────────────────────────────────────────────
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                     DAY 24 — SUMMARY                        ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  Two Pointers (pair)   O(n)   sorted array, one pass        ║");
        System.out.println("║  Two Pointers (3Sum)   O(n²)  fix one, two-pointer inside   ║");
        System.out.println("║  Fixed Window          O(n)   exactly k elements, slide     ║");
        System.out.println("║  Variable Window       O(n)   expand right, shrink left     ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println("\n── All Day 24 demos complete ──");
    }
}
