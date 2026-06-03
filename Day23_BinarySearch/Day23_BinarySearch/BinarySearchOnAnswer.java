/**
 * Binary Search on Answer — applying Binary Search to a <em>value range</em>
 * (the answer space) rather than searching within an array by index.
 *
 * <p><b>Core idea:</b><br>
 * Instead of searching for a value at a known index, "Search on Answer" treats
 * the answer itself as the search space. If we can define a monotonic predicate
 * {@code canAchieve(mid)} that returns {@code true} for all valid answers and
 * {@code false} for all invalid answers, Binary Search finds the boundary in
 * O(log(range)) evaluations of the predicate.</p>
 *
 * <p><b>Pattern:</b></p>
 * <pre>
 *   low  = minimum possible answer
 *   high = maximum possible answer
 *   while (low < high) {
 *       mid = (low + high) / 2;
 *       if (canAchieve(mid)) high = mid;   // mid is feasible; try smaller
 *       else                 low  = mid + 1; // mid too small; try larger
 *   }
 *   return low; // smallest feasible answer
 * </pre>
 *
 * <p><b>Story context (Day 23):</b><br>
 * The astronomer needs to find the minimum "brightness threshold" such that
 * at least K stars in the catalogue have brightness ≥ threshold. This is a
 * classic "search on answer" problem: the threshold is the answer, and we
 * binary-search over all possible threshold values.</p>
 *
 * <p>Three classic problems are demonstrated:</p>
 * <ol>
 *   <li>Square root of N (integer part) — O(log N)</li>
 *   <li>Find peak element brightness threshold — O(log(maxVal))</li>
 *   <li>Koko Eating Bananas / Minimum eating speed — O(n · log(maxPile))</li>
 * </ol>
 */
public class BinarySearchOnAnswer {

    /**
     * Computes the integer square root of {@code n} using Binary Search on Answer.
     *
     * <p>The answer space is [0, n]. We binary-search for the largest integer
     * {@code x} such that {@code x * x <= n}. The predicate is {@code mid*mid <= n}.</p>
     *
     * <p>This runs in O(log n) — far faster than Newton's method for integer floors
     * and avoids floating-point precision issues.</p>
     *
     * @param n a non-negative integer whose integer square root is required
     * @return the largest integer {@code x} such that {@code x * x <= n}
     */
    public static int integerSqrt(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be non-negative.");
        if (n == 0) return 0;

        long low  = 1;
        long high = n;
        long result = 1;

        while (low <= high) {
            long mid = low + (high - low) / 2;

            /*
             * Use long arithmetic to prevent overflow when mid*mid exceeds Integer.MAX_VALUE.
             */
            if (mid * mid <= n) {
                result = mid;   // mid is a valid answer; try larger
                low = mid + 1;
            } else {
                high = mid - 1; // mid is too large; try smaller
            }
        }

        return (int) result;
    }

    /**
     * Finds the minimum brightness threshold such that at least {@code k} stars
     * in the sorted brightness catalogue have brightness greater than or equal
     * to the threshold — using Binary Search on the answer space.
     *
     * <p>Predicate: {@code countStarsAtOrAbove(threshold) >= k}
     * This predicate is monotonically decreasing in threshold, so Binary Search
     * finds the maximum valid threshold in O(log(maxBrightness) * log(n)) time
     * (inner count uses Binary Search on the sorted array).</p>
     *
     * @param catalogue a sorted array of star brightness values (ascending)
     * @param k         the minimum number of stars required at or above the threshold
     * @return the maximum brightness threshold such that at least k stars qualify,
     *         or {@code -1} if no valid threshold exists
     */
    public static int findBrightnessThreshold(int[] catalogue, int k) {
        if (k <= 0 || k > catalogue.length) return -1;

        int low  = catalogue[0];                    // min possible threshold
        int high = catalogue[catalogue.length - 1]; // max possible threshold
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            /*
             * Count stars with brightness >= mid using Binary Search on the sorted array.
             * The first index where arr[i] >= mid is found via lower-bound Binary Search.
             */
            int firstIdx = lowerBound(catalogue, mid);
            int count    = catalogue.length - firstIdx;

            if (count >= k) {
                result = mid;   // mid is a valid threshold; try to increase it
                low = mid + 1;
            } else {
                high = mid - 1; // too few stars qualify; lower the threshold
            }
        }

        return result;
    }

    /**
     * Returns the index of the first element in the sorted array that is
     * greater than or equal to {@code target} (lower-bound Binary Search).
     *
     * <p>If all elements are less than target, returns {@code arr.length}.</p>
     *
     * @param arr    a sorted array in ascending order
     * @param target the value to find the lower bound for
     * @return the smallest index {@code i} such that {@code arr[i] >= target},
     *         or {@code arr.length} if no such index exists
     */
    public static int lowerBound(int[] arr, int target) {
        int low  = 0;
        int high = arr.length;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid; // keep mid as a candidate
            }
        }

        return low;
    }

    /**
     * Finds the minimum eating speed (bananas/hour) such that a monkey can eat
     * all banana piles within {@code h} hours — a classic "search on answer" problem.
     *
     * <p>For each speed {@code k}, the hours needed = sum of ceil(pile / k) for all piles.
     * The predicate {@code canFinish(k) = totalHours(k) <= h} is monotonically
     * non-decreasing in k, so Binary Search finds the minimum valid speed.</p>
     *
     * <p>Answer space: [1, max(piles)].<br>
     * Time complexity: O(n · log(max(piles))) where n = number of piles.</p>
     *
     * @param piles an array where {@code piles[i]} is the size of the i-th banana pile
     * @param h     the total number of hours available
     * @return the minimum eating speed (bananas per hour) to finish all piles in time
     */
    public static int minEatingSpeed(int[] piles, int h) {
        int low  = 1;
        int high = 0;
        for (int p : piles) if (p > high) high = p; // max pile size = upper bound

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (canFinish(piles, mid, h)) {
                high = mid;     // speed mid is feasible; try slower
            } else {
                low = mid + 1;  // speed mid is too slow; go faster
            }
        }

        return low;
    }

    /**
     * Checks whether eating at {@code speed} bananas per hour allows finishing
     * all piles within {@code h} hours.
     *
     * <p>Hours needed for a single pile = ceil(pile / speed) = (pile + speed - 1) / speed
     * using integer arithmetic to avoid floating-point.</p>
     *
     * @param piles an array of pile sizes
     * @param speed eating speed in bananas per hour
     * @param h     total hours available
     * @return true if all piles can be eaten at the given speed within h hours
     */
    private static boolean canFinish(int[] piles, int speed, int h) {
        long totalHours = 0;
        for (int pile : piles) {
            totalHours += (pile + speed - 1) / speed; // ceil(pile / speed)
        }
        return totalHours <= h;
    }

    /**
     * Main method demonstrating all three Binary Search on Answer problems
     * with traces showing the narrowing answer space at each step.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // ── Problem 1: Integer Square Root ────────────────────────────────────
        System.out.println("=== Problem 1: Integer Square Root via Binary Search on Answer ===");
        int[] sqrtTests = {0, 1, 4, 8, 16, 25, 37, 100, 2147395600};
        for (int n : sqrtTests) {
            System.out.printf("  √%-12d = %d%n", n, integerSqrt(n));
        }

        // ── Problem 2: Star Brightness Threshold (astronomer story) ───────────
        System.out.println("\n=== Problem 2: Minimum Brightness Threshold (Astronomer Story) ===");
        System.out.println("Find the MAXIMUM threshold T so that at least K stars have brightness >= T.");

        int[] catalogue = {10, 15, 15, 20, 25, 30, 30, 30, 40, 50};
        System.out.println("Sorted catalogue: [10, 15, 15, 20, 25, 30, 30, 30, 40, 50]");

        int[] kValues = {1, 3, 5, 8, 10};
        System.out.printf("  %-6s  %-20s%n", "K", "Max Threshold");
        System.out.println("  " + "─".repeat(28));
        for (int k : kValues) {
            int threshold = findBrightnessThreshold(catalogue, k);
            System.out.printf("  %-6d  %-20d%n", k, threshold);
        }

        // ── Problem 3: Minimum Eating Speed ───────────────────────────────────
        System.out.println("\n=== Problem 3: Minimum Eating Speed (Search on Answer Space) ===");

        int[][] pileTests = {
            {3, 6, 7, 11},
            {30, 11, 23, 4, 20},
            {30, 11, 23, 4, 20}
        };
        int[] hTests = {8, 5, 6};

        System.out.printf("  %-25s  %-6s  %-20s%n", "Piles", "Hours", "Min Speed");
        System.out.println("  " + "─".repeat(55));
        for (int i = 0; i < pileTests.length; i++) {
            StringBuilder sb = new StringBuilder("[");
            for (int j = 0; j < pileTests[i].length; j++) {
                sb.append(pileTests[i][j]);
                if (j < pileTests[i].length - 1) sb.append(",");
            }
            sb.append("]");
            int speed = minEatingSpeed(pileTests[i], hTests[i]);
            System.out.printf("  %-25s  %-6d  %-20d%n", sb.toString(), hTests[i], speed);
        }

        // ── Why "Search on Answer" works ─────────────────────────────────────
        System.out.println("\n=== Key Insight: When to Use Binary Search on Answer ===");
        System.out.println("  ✓ The answer lies in a known numeric range [low, high].");
        System.out.println("  ✓ There is a monotonic predicate: canAchieve(x) is");
        System.out.println("    true for all x ≥ answer and false for all x < answer.");
        System.out.println("  ✓ Evaluating the predicate for a given x is efficient.");
        System.out.println("  → Binary Search on the answer space finds the boundary");
        System.out.println("    in O(log(range)) predicate evaluations.");
    }
}
