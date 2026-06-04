import java.util.Stack;

/**
 * Calculates the Stock Span for each day.
 */
public class StockSpan {
    public static int[] calculate(int[] prices) {
        int n = prices.length;
        int[] spans = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) {
                stack.pop();
            }
            
            if (stack.isEmpty()) {
                spans[i] = i + 1;
            } else {
                spans[i] = i - stack.peek();
            }
            
            stack.push(i);
        }
        
        return spans;
    }
}
