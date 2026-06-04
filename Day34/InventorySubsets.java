import java.util.ArrayList;
import java.util.List;

/**
 * Generates all subsets of an inventory using Backtracking.
 */
public class InventorySubsets {
    public static List<List<Integer>> generateSubsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackSubsets(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private static void backtrackSubsets(List<List<Integer>> result, List<Integer> tempList, int[] nums, int start) {
        result.add(new ArrayList<>(tempList));
        
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrackSubsets(result, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}
