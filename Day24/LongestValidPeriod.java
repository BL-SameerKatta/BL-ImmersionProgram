/**
 * Finds the longest contiguous period (subarray) where water levels stayed within a given range.
 * Uses a variable-size sliding window approach.
 */
public class LongestValidPeriod {
    public static int find(int[] levels, int minLevel, int maxLevel) {
        int maxLength = 0;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < levels.length; windowEnd++) {
            if (levels[windowEnd] < minLevel || levels[windowEnd] > maxLevel) {
                windowStart = windowEnd + 1;
            } else {
                int currentLength = windowEnd - windowStart + 1;
                maxLength = Math.max(maxLength, currentLength);
            }
        }
        return maxLength;
    }
}
