import java.util.PriorityQueue;

/**
 * Finds the top K highest stock prices using a Min-Heap.
 */
public class TopKStocks {
    public static int[] findTopK(int[] prices, int k) {
        if (k <= 0 || prices == null || prices.length == 0) return new int[0];
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int price : prices) {
            minHeap.offer(price);
            if (minHeap.size() > k) {
                minHeap.poll(); 
            }
        }
        
        int[] topK = new int[minHeap.size()];
        for (int i = topK.length - 1; i >= 0; i--) {
            topK[i] = minHeap.poll();
        }
        return topK;
    }
}
