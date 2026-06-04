import java.util.Stack;
import java.util.Arrays;

/**
 * Computes the Next Greater Price for each day.
 * Uses a monotonic decreasing stack to find the next larger element in O(n) time.
 */
public class NextGreaterPrice {
    public static int[] findNext(int[] prices) {
        int n = prices.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        Arrays.fill(result, -1);
        
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && prices[i] > prices[stack.peek()]) {
                int index = stack.pop();
                result[index] = prices[i]; 
            }
            stack.push(i);
        }
        
        return result;
    }
}
