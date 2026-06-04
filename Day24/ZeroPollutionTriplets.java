import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Finds all unique triplets of pollution indices that sum up to zero.
 * Uses sorting and the Two Pointers technique.
 */
public class ZeroPollutionTriplets {
    public static List<List<Integer>> findTriplets(int[] pollutionIndices) {
        List<List<Integer>> triplets = new ArrayList<>();
        Arrays.sort(pollutionIndices);
        
        for (int i = 0; i < pollutionIndices.length - 2; i++) {
            if (i > 0 && pollutionIndices[i] == pollutionIndices[i - 1]) {
                continue;
            }
            
            int left = i + 1;
            int right = pollutionIndices.length - 1;
            int targetSum = -pollutionIndices[i]; 
            
            while (left < right) {
                int currentSum = pollutionIndices[left] + pollutionIndices[right];
                if (currentSum == targetSum) {
                    triplets.add(Arrays.asList(pollutionIndices[i], pollutionIndices[left], pollutionIndices[right]));
                    while (left < right && pollutionIndices[left] == pollutionIndices[left + 1]) left++;
                    while (left < right && pollutionIndices[right] == pollutionIndices[right - 1]) right--;
                    left++;
                    right--;
                } else if (currentSum < targetSum) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return triplets;
    }
}
