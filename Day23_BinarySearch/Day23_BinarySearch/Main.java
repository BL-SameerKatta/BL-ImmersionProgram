import java.util.Arrays;

/**
 * Main driver class for Day 23: Binary Search Variants.
 *
 * <p>Demonstrates all four Binary Search concepts covered on Day 23 using the
 * <b>astronomer's 10M star-brightness catalogue</b> as the unifying story:</p>
 *
 * <ol>
 *   <li>{@link BinarySearch}           — standard O(log n) search, iterative + recursive,
 *                                        step trace, linear vs binary comparison.</li>
 *   <li>{@link BinarySearchRotated}    — search in a telescope-glitch-rotated catalogue,
 *                                        find minimum element (rotation point).</li>
 *   <li>{@link BinarySearchOccurrence} — first and last occurrence of a repeated
 *                                        brightness value; count of duplicates.</li>
 *   <li>{@link BinarySearchOnAnswer}   — search on answer space: brightness threshold,
 *                                        integer square root, minimum eating speed.</li>
 * </ol>
 *
 * <p><b>Complexity summary:</b></p>
 * <pre>
 *  Variant                   | Time        | Space
 *  ──────────────────────────|─────────────|──────
 *  Standard Binary Search    | O(log n)    | O(1)
 *  Rotated Array Search      | O(log n)    | O(1)
 *  First / Last Occurrence   | O(log n)    | O(1)   (two separate binary searches)
 *  Binary Search on Answer   | O(log R·f)  | O(1)   R = answer range, f = predicate cost
 * </pre>
 */
public class Main {

    /**
     * Entry point — runs all four Day 23 sections in sequence.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║   DAY 23 — Binary Search: All Variants                      ║");
        System.out.println("║   Story: Astronomer's 10M star-brightness catalogue          ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        // ── SECTION 1: Standard Binary Search ────────────────────────────────
        System.out.println("┌──────────────────────────────────────────────┐");
        System.out.println("│  SECTION 1 — Standard Binary Search          │");
        System.out.println("└──────────────────────────────────────────────┘");

        int[] sortedCatalogue = {
            102, 245, 310, 487, 563, 629, 714, 800, 912, 1050,
            1203, 1389, 1512, 1678, 1790, 1923, 2100, 2345, 2589, 2800
        };
        System.out.println("Sorted catalogue (20 entries): " + Arrays.toString(sortedCatalogue));

        System.out.println("\n-- Search for 1512 (present) --");
        BinarySearch.searchWithTrace(sortedCatalogue, 1512);

        System.out.println("\n-- Search for 999 (not present) --");
        BinarySearch.searchWithTrace(sortedCatalogue, 999);

        System.out.println("\n-- Recursive search for 2800 (last element) --");
        int idx = BinarySearch.searchRecursive(sortedCatalogue, 2800, 0, sortedCatalogue.length - 1);
        System.out.println("  Recursive result → index " + idx);

        System.out.println("\n-- Max iterations for large catalogue sizes --");
        for (int n : new int[]{100, 10_000, 1_000_000, 10_000_000}) {
            System.out.printf("  n = %10d  →  max %d comparisons (vs %d linear)%n",
                    n, (int) Math.ceil(Math.log(n) / Math.log(2)), n);
        }

        // ── SECTION 2: Rotated Array Search ──────────────────────────────────
        System.out.println("\n┌──────────────────────────────────────────────┐");
        System.out.println("│  SECTION 2 — Rotated Array (Telescope Glitch)│");
        System.out.println("└──────────────────────────────────────────────┘");

        int[] rotated = {1678, 1790, 1923, 2100, 2345, 2589, 2800, 102, 245, 310, 487, 563, 629, 714};
        System.out.println("Rotated catalogue: " + Arrays.toString(rotated));

        System.out.println("\n-- Search for 563 in rotated array --");
        BinarySearchRotated.searchRotatedWithTrace(rotated, 563);

        System.out.println("\n-- Search for 2800 (largest, before rotation point) --");
        BinarySearchRotated.searchRotatedWithTrace(rotated, 2800);

        System.out.println("\n-- Search for 500 (not present) --");
        BinarySearchRotated.searchRotatedWithTrace(rotated, 500);

        int minVal = BinarySearchRotated.findMinimum(rotated);
        System.out.println("\nMinimum brightness (rotation point): " + minVal);

        // ── SECTION 3: First and Last Occurrence ─────────────────────────────
        System.out.println("\n┌──────────────────────────────────────────────┐");
        System.out.println("│  SECTION 3 — First & Last Occurrence         │");
        System.out.println("└──────────────────────────────────────────────┘");

        int[] duplicateCatalogue = {
            100, 150, 150, 200, 200, 200, 250, 300, 300, 300,
            300, 350, 400, 400, 450, 500, 500, 500, 550, 600
        };
        System.out.println("Catalogue with duplicates: " + Arrays.toString(duplicateCatalogue));

        int targetBrightness = 300;
        int[] range = BinarySearchOccurrence.findRange(duplicateCatalogue, targetBrightness);
        System.out.println("\nBrightness = " + targetBrightness);
        System.out.println("  First occurrence : index " + range[0]);
        System.out.println("  Last  occurrence : index " + range[1]);
        System.out.println("  Stars at this brightness: " + (range[1] - range[0] + 1));

        System.out.println("\nTrace of first-occurrence search for 300:");
        BinarySearchOccurrence.traceFirstOccurrence(duplicateCatalogue, targetBrightness);

        System.out.println("\nFull occurrence table:");
        int[] uniqueVals = {100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 999};
        System.out.printf("  %-10s  %-8s  %-8s  %-8s%n", "Brightness", "First", "Last", "Count");
        System.out.println("  " + "─".repeat(38));
        for (int v : uniqueVals) {
            int[] r = BinarySearchOccurrence.findRange(duplicateCatalogue, v);
            String count = r[0] == -1 ? "not found" : String.valueOf(r[1] - r[0] + 1);
            System.out.printf("  %-10d  %-8s  %-8s  %-8s%n",
                    v,
                    r[0] == -1 ? "-" : String.valueOf(r[0]),
                    r[1] == -1 ? "-" : String.valueOf(r[1]),
                    count);
        }

        // ── SECTION 4: Binary Search on Answer ───────────────────────────────
        System.out.println("\n┌──────────────────────────────────────────────┐");
        System.out.println("│  SECTION 4 — Binary Search on Answer Space   │");
        System.out.println("└──────────────────────────────────────────────┘");

        System.out.println("\n-- Integer square root (O(log n), no floating-point) --");
        for (int n : new int[]{9, 25, 37, 144, 10000}) {
            System.out.printf("  sqrt(%5d) = %d%n", n, BinarySearchOnAnswer.integerSqrt(n));
        }

        System.out.println("\n-- Brightness threshold: max T so that >= K stars qualify --");
        int[] bCatalogue = {10, 15, 15, 20, 25, 30, 30, 30, 40, 50};
        System.out.println("Catalogue: " + Arrays.toString(bCatalogue));
        for (int k : new int[]{1, 3, 5, 8, 10}) {
            System.out.printf("  K = %-3d  →  max threshold = %d%n",
                    k, BinarySearchOnAnswer.findBrightnessThreshold(bCatalogue, k));
        }

        System.out.println("\n-- Minimum eating speed (Koko's bananas) --");
        System.out.printf("  Piles = [3,6,7,11], H = 8  →  min speed = %d%n",
                BinarySearchOnAnswer.minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
        System.out.printf("  Piles = [30,11,23,4,20], H = 5  →  min speed = %d%n",
                BinarySearchOnAnswer.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));

        // ── Summary ───────────────────────────────────────────────────────────
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                  DAY 23 — SUMMARY                           ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  Standard B.S.   → O(log n), sorted array, any occurrence  ║");
        System.out.println("║  Rotated B.S.    → O(log n), identify sorted half per step ║");
        System.out.println("║  First/Last Occ. → O(log n), continue past match to edge   ║");
        System.out.println("║  Search on Ans.  → O(log R · f), monotonic predicate       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println("\n── All Day 23 demos complete ──");
    }
}
