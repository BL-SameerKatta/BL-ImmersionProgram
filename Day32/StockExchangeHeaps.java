import java.util.PriorityQueue;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Stock Exchange Heaps System
 * Ranks companies by market cap using Heaps.
 * Includes building a Max-Heap from scratch, finding top-K stocks using PriorityQueue,
 * and an implementation of Heap Sort.
 */
public class StockExchangeHeaps {

    /**
     * Builds a Max-Heap from an unsorted array of market caps from scratch.
     * Modifies the array in-place. O(N) time complexity.
     * 
     * @param arr the array of market caps
     */
    public static void buildMaxHeap(int[] arr) {
        int n = arr.length;
        // Start from the last non-leaf node and heapify down
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapifyDown(arr, n, i);
        }
    }

    /**
     * Helper function to maintain the Max-Heap property.
     * Pushes a smaller value down the tree.
     * 
     * @param arr the array
     * @param n the size of the heap
     * @param i the index of the node to heapify
     */
    private static void heapifyDown(int[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        // If left child is larger than root
        if (leftChild < n && arr[leftChild] > arr[largest]) {
            largest = leftChild;
        }

        // If right child is larger than largest so far
        if (rightChild < n && arr[rightChild] > arr[largest]) {
            largest = rightChild;
        }

        // If largest is not root
        if (largest != i) {
            // Swap arr[i] and arr[largest]
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapifyDown(arr, n, largest);
        }
    }

    /**
     * Sorts an array using the Heap Sort algorithm.
     * O(N log N) time complexity.
     * 
     * @param arr the array to sort
     */
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Step 1: Build a Max-Heap
        buildMaxHeap(arr);

        // Step 2: Extract elements from the heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root (maximum element) to the end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Call max heapify on the reduced heap
            heapifyDown(arr, i, 0);
        }
    }

    /**
     * Finds the Top-K performing stocks using Java's built-in PriorityQueue.
     * We use a Min-Heap of size K to efficiently keep track of the K largest elements.
     * 
     * @param marketCaps array of market caps
     * @param k the number of top stocks to find
     * @return a list of the top K market caps
     */
    public static List<Integer> findTopKStocks(int[] marketCaps, int k) {
        // PriorityQueue is a Min-Heap by default in Java
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int cap : marketCaps) {
            minHeap.offer(cap);
            
            // If the heap grows larger than K, remove the smallest element.
            // This ensures only the K largest elements remain in the heap.
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Extract elements into a list
        List<Integer> topK = new ArrayList<>(minHeap);
        // Sort descending for better readability
        topK.sort(Collections.reverseOrder());
        return topK;
    }
}
