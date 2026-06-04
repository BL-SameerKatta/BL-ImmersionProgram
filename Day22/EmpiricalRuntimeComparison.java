import java.util.Random;

/**
 * Compares empirical runtimes of Merge Sort, Quick Sort, and Counting Sort.
 */
public class EmpiricalRuntimeComparison {
    public static void compareRuntimes(int[] sizes) {
        Random rand = new Random();
        System.out.println("--- Empirical Runtime Comparison ---");
        
        for (int size : sizes) {
            int[] data = new int[size];
            for (int i = 0; i < size; i++) {
                data[i] = rand.nextInt(20) + 1; 
            }
            
            int[] dataForMerge = data.clone();
            int[] dataForQuick = data.clone();
            int[] dataForCounting = data.clone();

            System.out.println("\nArray Size: " + size);

            long startTime = System.nanoTime();
            MergeSort.sort(dataForMerge, 0, dataForMerge.length - 1);
            long endTime = System.nanoTime();
            System.out.println("Merge Sort time:    " + (endTime - startTime) + " ns");

            startTime = System.nanoTime();
            QuickSort.sort(dataForQuick, 0, dataForQuick.length - 1);
            endTime = System.nanoTime();
            System.out.println("Quick Sort time:    " + (endTime - startTime) + " ns");

            startTime = System.nanoTime();
            CountingSort.sort(dataForCounting, 20);
            endTime = System.nanoTime();
            System.out.println("Counting Sort time: " + (endTime - startTime) + " ns");
        }
    }
}
