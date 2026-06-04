import java.util.ArrayList;
import java.util.List;

/**
 * Generates all permutations of a secret code using Backtracking.
 */
public class SecretCodePermutations {
    public static List<List<Integer>> generatePermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackPermutations(result, new ArrayList<>(), nums);
        return result;
    }

    private static void backtrackPermutations(List<List<Integer>> result, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (tempList.contains(nums[i])) continue;
                
                tempList.add(nums[i]);
                backtrackPermutations(result, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
