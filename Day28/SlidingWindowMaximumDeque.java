import java.util.Deque;
import java.util.LinkedList;

/**
 * Finds the Sliding Window Maximum for patient vitals using a monotonic Deque.
 */
public class SlidingWindowMaximumDeque {
    public static int[] findMax(int[] vitals, int k) {
        if (vitals == null || vitals.length == 0 || k <= 0) return new int[0];
        
        int n = vitals.length;
        int[] result = new int[n - k + 1];
        int resultIndex = 0;
        
        Deque<Integer> deque = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            
            while (!deque.isEmpty() && vitals[deque.peekLast()] < vitals[i]) {
                deque.pollLast();
            }
            
            deque.offerLast(i);
            
            if (i >= k - 1) {
                result[resultIndex++] = vitals[deque.peekFirst()];
            }
        }
        
        return result;
    }
}
