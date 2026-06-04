/**
 * Finds the maximum average of any contiguous subarray of length k.
 * Uses a fixed-size sliding window approach.
 */
public class MaxAverageSubarray {
    public static double findMaxAverage(int[] readings, int k) {
        if (readings.length < k || k <= 0) {
            return 0.0;
        }
        
        long currentWindowSum = 0;
        for (int i = 0; i < k; i++) {
            currentWindowSum += readings[i];
        }
        
        long maxWindowSum = currentWindowSum;
        for (int i = k; i < readings.length; i++) {
            currentWindowSum += readings[i] - readings[i - k];
            maxWindowSum = Math.max(maxWindowSum, currentWindowSum);
        }
        
        return (double) maxWindowSum / k;
    }
}
