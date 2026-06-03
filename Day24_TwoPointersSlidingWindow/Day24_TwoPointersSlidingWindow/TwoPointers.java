import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Two Pointers technique applied to pair-sum and triplet-sum problems.
 *
 * <p><b>What is the Two Pointers technique?</b><br>
 * Two Pointers uses two index variables that move toward each other (or in the
 * same direction) through a sorted array, allowing certain O(n²) brute-force
 * problems to be solved in O(n) or O(n²) with reduced constant factors and
 * no extra space.</p>
 *
 * <p><b>Pair Sum pattern (sorted array):</b></p>
 * <pre>
 *   left = 0, right = n-1
 *   while left &lt; right:
 *       sum = arr[left] + arr[right]
 *       if sum == target → found pair, move both inward
 *       if sum  &lt; target → need larger sum, move left right
 *       if sum  &gt; target → need smaller sum, move right left
 * </pre>
 *
 * <p><b>Triplets (3Sum) pattern:</b><br>
 * Fix one element, then apply the pair-sum two-pointer on the remaining
 * sorted sub-array. Runs in O(n²) — optimal for this problem.</p>
 *
 * <p><b>Story context (Day 24):</b><br>
 * A river station records hourly water levels. Find all unique pairs of
 * stations whose pollution indices sum to a given target, and find all unique
 * triplets of stations whose pollution indices sum to zero.</p>
 */
public class TwoPointers {

    /**
     * Finds all unique pairs in a sorted array whose values sum to {@code target}.
     *
     * <p>Uses the classic two-pointer approach on a sorted array: start with
     * the outermost elements and move inward based on whether the current sum
     * is too small or too large. Duplicate pairs are skipped automatically.</p>
     *
     * @param arr    a sorted integer array (ascending order)
     * @param target the required pair sum
     * @return a list of int arrays, each of length 2, representing a valid pair
     */
    public static List<int[]> pairSum(int[] arr, int target) {
        List<int[]> result = new ArrayList<>();
        int left  = 0;
        int right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == target) {
                result.add(new int[]{arr[left], arr[right]});

                /*
                 * Skip duplicate values on the left and right to avoid
                 * reporting the same pair more than once.
                 */
                while (left < right && arr[left]  == arr[left  + 1]) left++;
                while (left < right && arr[right] == arr[right - 1]) right--;

                left++;
                right--;

            } else if (sum < target) {
                left++;   // sum too small; increase the left pointer
            } else {
                right--;  // sum too large; decrease the right pointer
            }
        }

        return result;
    }

    /**
     * Finds all unique triplets in the array whose values sum to zero (3Sum).
     *
     * <p>Algorithm:</p>
     * <ol>
     *   <li>Sort the array — required for the two-pointer inner scan.</li>
     *   <li>Iterate index {@code i} from 0 to n-3 as the "fixed" first element.</li>
     *   <li>Apply {@link #pairSum(int[], int)} logic on {@code arr[i+1..n-1]}
     *       looking for pairs that sum to {@code -arr[i]}.</li>
     *   <li>Skip duplicate values of {@code arr[i]} to avoid duplicate triplets.</li>
     * </ol>
     *
     * <p>Time complexity: O(n²) — optimal for 3Sum.
     * Space complexity: O(1) auxiliary (output list not counted).</p>
     *
     * @param arr an integer array (need not be sorted — sorting done internally)
     * @return a list of int arrays, each of length 3, representing a valid triplet
     */
    public static List<int[]> threeSum(int[] arr) {
        List<int[]> result = new ArrayList<>();
        Arrays.sort(arr); // sort first — required for two-pointer approach
        int n = arr.length;

        for (int i = 0; i < n - 2; i++) {
            /*
             * Skip duplicate values for the fixed element to avoid duplicate triplets.
             */
            if (i > 0 && arr[i] == arr[i - 1]) continue;

            /*
             * Early exit: if the smallest possible triplet (arr[i] + smallest two)
             * is already positive, no valid triplet can exist for this or later i.
             */
            if (arr[i] > 0) break;

            int left  = i + 1;
            int right = n - 1;
            int target = -arr[i]; // we need left + right == -arr[i]

            while (left < right) {
                int sum = arr[left] + arr[right];

                if (sum == target) {
                    result.add(new int[]{arr[i], arr[left], arr[right]});

                    // Skip duplicates for left and right pointers
                    while (left < right && arr[left]  == arr[left  + 1]) left++;
                    while (left < right && arr[right] == arr[right - 1]) right--;

                    left++;
                    right--;

                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }

    /**
     * Prints a step-by-step trace of the two-pointer pair-sum algorithm,
     * showing pointer positions and decisions at each step.
     *
     * @param arr    a sorted array to trace on
     * @param target the target pair sum
     */
    public static void tracePairSum(int[] arr, int target) {
        int left  = 0;
        int right = arr.length - 1;
        int step  = 1;

        System.out.println("  Array : " + Arrays.toString(arr));
        System.out.println("  Target: " + target);
        System.out.printf("  %-5s %-6s %-6s %-12s %-12s %-18s%n",
                "Step", "Left", "Right", "arr[L]", "arr[R]", "Decision");
        System.out.println("  " + "─".repeat(62));

        while (left < right) {
            int sum = arr[left] + arr[right];
            String decision;

            if (sum == target) {
                decision = "PAIR FOUND ✓";
                System.out.printf("  %-5d %-6d %-6d %-12d %-12d %-18s%n",
                        step++, left, right, arr[left], arr[right], decision);
                while (left < right && arr[left]  == arr[left  + 1]) left++;
                while (left < right && arr[right] == arr[right - 1]) right--;
                left++; right--;
            } else if (sum < target) {
                decision = "Sum " + sum + " < " + target + " → L++";
                System.out.printf("  %-5d %-6d %-6d %-12d %-12d %-18s%n",
                        step++, left, right, arr[left], arr[right], decision);
                left++;
            } else {
                decision = "Sum " + sum + " > " + target + " → R--";
                System.out.printf("  %-5d %-6d %-6d %-12d %-12d %-18s%n",
                        step++, left, right, arr[left], arr[right], decision);
                right--;
            }
        }
    }

    /**
     * Main method demonstrating pair-sum and 3Sum using the river-station
     * pollution story, with traces and edge-case handling.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // ── Story Problem: pair-sum on pollution indices ──────────────────────
        System.out.println("=== Story Problem: Pair Sum — River Station Pollution Indices ===");
        System.out.println("Find all unique pairs of stations whose pollution indices sum to target.");

        int[] pollutionLevels = {-4, -1, -1, 0, 1, 2, 3, 5, 7, 8};
        System.out.println("Pollution indices (sorted): " + Arrays.toString(pollutionLevels));

        int target = 6;
        List<int[]> pairs = pairSum(pollutionLevels, target);
        System.out.println("Target sum = " + target);
        System.out.println("Pairs found: " + pairs.size());
        for (int[] p : pairs) {
            System.out.println("  " + Arrays.toString(p) + "  →  " + p[0] + " + " + p[1] + " = " + target);
        }

        // ── Trace ────────────────────────────────────────────────────────────
        System.out.println("\n=== Step-by-Step Trace: Pair Sum ===");
        tracePairSum(new int[]{1, 2, 3, 4, 6, 8, 9}, 10);

        // ── 3Sum: unique triplets summing to zero ─────────────────────────────
        System.out.println("\n=== Story Problem: 3Sum — Triplets of Stations with Zero Net Index ===");
        System.out.println("Find all unique triplets of stations whose pollution indices sum to zero.");

        int[] stations = {-4, -2, -1, -1, 0, 1, 2, 3, 4};
        System.out.println("Station indices: " + Arrays.toString(stations));
        List<int[]> triplets = threeSum(stations);
        System.out.println("Triplets found: " + triplets.size());
        for (int[] t : triplets) {
            System.out.println("  " + Arrays.toString(t)
                    + "  →  " + t[0] + " + " + t[1] + " + " + t[2] + " = 0");
        }

        // ── Classic LeetCode test cases ───────────────────────────────────────
        System.out.println("\n=== Classic 3Sum Test Cases ===");
        int[][] tests = {
            {-1, 0, 1, 2, -1, -4},
            {0, 0, 0},
            {1, 2, -2, -1},
            {}
        };
        for (int[] test : tests) {
            List<int[]> res = threeSum(Arrays.copyOf(test, test.length));
            System.out.print("Input: " + Arrays.toString(test) + "  →  Triplets: ");
            if (res.isEmpty()) {
                System.out.println("[]");
            } else {
                for (int[] t : res) System.out.print(Arrays.toString(t) + " ");
                System.out.println();
            }
        }
    }
}
