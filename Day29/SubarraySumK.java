import java.util.HashMap;
import java.util.Map;

/**
 * Finds if there is a contiguous subarray that sums up to target K.
 * Uses the Prefix Sum and HashMap technique for O(n) performance.
 */
public class SubarraySumK {
    public static boolean hasSubarrayWithSum(int[] arr, int k) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        int currentSum = 0;

        // Base case: to handle subarrays starting from index 0
        prefixSumMap.put(0, -1);

        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];

            if (prefixSumMap.containsKey(currentSum - k)) {
                return true; 
            }
            prefixSumMap.put(currentSum, i);
        }

        return false; 
    }
}
