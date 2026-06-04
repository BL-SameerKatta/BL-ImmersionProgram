/**
 * Demonstrates the Heap Sort algorithm.
 */
public class HeapSort {
    public static void sort(int[] arr) {
        int n = arr.length;

        MaxHeapBuilder.buildMaxHeap(arr);

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            MaxHeapBuilder.heapify(arr, i, 0);
        }
    }
}
