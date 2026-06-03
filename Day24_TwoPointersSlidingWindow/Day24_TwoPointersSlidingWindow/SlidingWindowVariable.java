import java.util.HashMap;
import java.util.Map;

/**
 * Variable-Size Sliding Window technique for problems where the window
 * expands and contracts dynamically based on a constraint.
 *
 * <p><b>How the variable window differs from fixed:</b><br>
 * The fixed window always has exactly k elements. The variable window grows
 * by moving the right pointer forward and shrinks by moving the left pointer
 * forward whenever the current window violates a condition. The goal is typically
 * to find the longest or shortest window satisfying the condition.</p>
 *
 * <p><b>Pattern (variable window):</b></p>
 * <pre>
 *   left = 0
 *   for right = 0 to n-1:
 *       expand window: include arr[right]
 *       while (window violates condition):
 *           shrink window: exclude arr[left], left++
 *       update answer with current valid window size
 * </pre>
 *
 * <p><b>Story context (Day 24):</b><br>
 * A river station records hourly water levels. Find the longest contiguous
 * period where levels stayed within a given range (max − min ≤ limit).
 * Also: find the longest period of strictly increasing levels (no repeats).</p>
 *
 * <p>Two classic problems are implemented:</p>
 * <ol>
 *   <li>Longest subarray where (max − min) ≤ limit — O(n) with two deques
 *       (simplified here with O(n²) max/min for clarity; see note inside).</li>
 *   <li>Longest substring without repeating characters — O(n) with a HashMap.</li>
 * </ol>
 */
public class SlidingWindowVariable {

    /**
     * Finds the length of the longest contiguous subarray where
     * {@code max(window) - min(window) <= limit}.
     *
     * <p>The window expands by moving {@code right} forward. When the range
     * condition is violated, {@code left} advances until the condition holds again.</p>
     *
     * <p>For each window check, max and min are recomputed in O(window size).
     * Overall: O(n²) in the worst case — acceptable for clarity and moderate n.
     * For O(n) performance on large inputs, use two monotonic deques (one for
     * max tracking, one for min tracking) — see the inline comment below.</p>
     *
     * @param levels the array of water level readings
     * @param limit  the maximum allowed difference between max and min in a window
     * @return the length of the longest valid window
     */
    public static int longestWithinRange(int[] levels, int limit) {
        int left      = 0;
        int maxLength = 0;
        int bestLeft  = 0;

        for (int right = 0; right < levels.length; right++) {
            /*
             * Shrink the window from the left until max - min <= limit.
             * For O(n) performance, replace the inner max/min scan with
             * two ArrayDeques maintaining a monotonic max-deque and min-deque.
             */
            while (windowMax(levels, left, right) - windowMin(levels, left, right) > limit) {
                left++;
            }

            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                bestLeft  = left;
            }
        }

        System.out.println("  Best window: indices [" + bestLeft + ", " + (bestLeft + maxLength - 1) + "]"
                + "  length = " + maxLength);
        return maxLength;
    }

    /**
     * Returns the maximum value in {@code arr[left..right]}.
     *
     * @param arr   the source array
     * @param left  start index (inclusive)
     * @param right end index (inclusive)
     * @return the maximum element in the sub-range
     */
    private static int windowMax(int[] arr, int left, int right) {
        int max = arr[left];
        for (int i = left + 1; i <= right; i++) if (arr[i] > max) max = arr[i];
        return max;
    }

    /**
     * Returns the minimum value in {@code arr[left..right]}.
     *
     * @param arr   the source array
     * @param left  start index (inclusive)
     * @param right end index (inclusive)
     * @return the minimum element in the sub-range
     */
    private static int windowMin(int[] arr, int left, int right) {
        int min = arr[left];
        for (int i = left + 1; i <= right; i++) if (arr[i] < min) min = arr[i];
        return min;
    }

    /**
     * Finds the length of the longest substring without any repeating characters.
     *
     * <p>A HashMap maps each character to its most recent index. When a duplicate
     * is found within the current window, the left pointer jumps past the previous
     * occurrence (if it is within the current window), effectively removing the
     * duplicate without iterating element by element.</p>
     *
     * <p>Time complexity: O(n) — each character is visited at most twice.
     * Space complexity: O(min(n, charset)) — for the HashMap.</p>
     *
     * <p>Corresponds to LC #3 — Longest Substring Without Repeating Characters.</p>
     *
     * @param s the input string
     * @return the length of the longest substring with all unique characters
     */
    public static int longestUniqueSubstring(String s) {
        /*
         * Map each character to the index AFTER its most recent occurrence.
         * This allows left to jump directly past the duplicate.
         */
        Map<Character, Integer> lastSeen = new HashMap<>();
        int left      = 0;
        int maxLength = 0;
        int bestLeft  = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            if (lastSeen.containsKey(c) && lastSeen.get(c) >= left) {
                /*
                 * Duplicate found within the current window.
                 * Move left past the previous occurrence.
                 */
                left = lastSeen.get(c) + 1;
            }

            lastSeen.put(c, right);

            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                bestLeft  = left;
            }
        }

        System.out.println("  Best window: \"" + s.substring(bestLeft, bestLeft + maxLength) + "\""
                + "  at indices [" + bestLeft + ", " + (bestLeft + maxLength - 1) + "]");
        return maxLength;
    }

    /**
     * Finds the maximum sum of any subarray whose sum does not exceed {@code limit}.
     *
     * <p>Uses the variable window: expand right until sum exceeds limit, then
     * shrink from left. Works correctly only on non-negative arrays because
     * removing left elements always decreases the window sum.</p>
     *
     * @param arr   an array of non-negative integers
     * @param limit the maximum allowed subarray sum
     * @return the maximum subarray sum that is ≤ limit
     */
    public static int maxSumWithinLimit(int[] arr, int limit) {
        int left   = 0;
        int sum    = 0;
        int maxSum = 0;

        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];

            /*
             * Shrink window from left while sum exceeds the limit.
             * Safe because all values are non-negative: removing left elements
             * strictly decreases the sum.
             */
            while (sum > limit && left <= right) {
                sum -= arr[left++];
            }

            if (sum > maxSum) maxSum = sum;
        }

        return maxSum;
    }

    /**
     * Prints a step-by-step trace of the variable sliding window for
     * the longest-within-range problem.
     *
     * @param levels the array of water levels
     * @param limit  the max allowed (max − min) in any window
     */
    public static void traceWindow(int[] levels, int limit) {
        int left = 0;
        int maxLength = 0;

        System.out.println("  Levels: [" + java.util.Arrays.toString(levels) + "]  limit = " + limit);
        System.out.printf("  %-5s %-5s %-5s %-8s %-8s %-8s %-10s%n",
                "Right", "Left", "Size", "Min", "Max", "Range", "Valid?");
        System.out.println("  " + "─".repeat(55));

        for (int right = 0; right < levels.length; right++) {
            while (windowMax(levels, left, right) - windowMin(levels, left, right) > limit) {
                left++;
            }
            int size  = right - left + 1;
            int wMax  = windowMax(levels, left, right);
            int wMin  = windowMin(levels, left, right);
            int range = wMax - wMin;
            boolean valid = range <= limit;
            if (size > maxLength) maxLength = size;

            System.out.printf("  %-5d %-5d %-5d %-8d %-8d %-8d %-10s%n",
                    right, left, size, wMin, wMax, range, valid ? "YES" : "NO (shrink)");
        }
        System.out.println("  Longest valid window length: " + maxLength);
    }

    /**
     * Main method demonstrating variable sliding window on the river-station
     * water level story and the longest unique substring problem.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // ── Story Problem: longest period within a water level range ──────────
        System.out.println("=== Story Problem: Longest Period Within Water Level Range ===");
        System.out.println("Find the longest contiguous hours where max - min <= limit.");

        int[] waterLevels = {8, 2, 4, 7, 4, 2, 3, 1, 5, 9};
        System.out.println("Hourly water levels: " + java.util.Arrays.toString(waterLevels));

        for (int limit : new int[]{2, 4, 6}) {
            System.out.println("\nLimit = " + limit + ":");
            int len = longestWithinRange(waterLevels, limit);
            System.out.println("  Longest valid period: " + len + " hours");
        }

        // ── Step-by-step trace ────────────────────────────────────────────────
        System.out.println("\n=== Step-by-Step Trace (limit = 3) ===");
        traceWindow(new int[]{4, 2, 2, 7, 5, 1, 5, 4}, 3);

        // ── Max sum within limit (non-negative arrays) ────────────────────────
        System.out.println("\n=== Max Subarray Sum Within Limit ===");
        int[] readings = {3, 1, 4, 1, 5, 9, 2, 6};
        System.out.println("Readings: " + java.util.Arrays.toString(readings));
        for (int lim : new int[]{10, 15, 20}) {
            System.out.println("  Limit " + lim + "  →  max sum = " + maxSumWithinLimit(readings, lim));
        }

        // ── Longest substring without repeating characters ────────────────────
        System.out.println("\n=== Longest Substring Without Repeating Characters (LC #3) ===");
        String[] inputs = {"abcabcbb", "bbbbb", "pwwkew", "dvdf", ""};
        for (String s : inputs) {
            System.out.print("Input: \"" + s + "\"  →  length = ");
            System.out.println(longestUniqueSubstring(s));
        }
    }
}
