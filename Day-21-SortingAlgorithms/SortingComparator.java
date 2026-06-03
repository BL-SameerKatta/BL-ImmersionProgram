/**
 * SortingComparator.java
 *
 * Day 21 - DSA: Sorting Algorithms
 * Concepts: Algorithm comparison, Swap/shift counting,
 *           Best case vs worst case demonstration,
 *           Static utility methods
 *
 * This utility class provides helper methods to:
 *   - Print formatted athlete arrays before/after sorting
 *   - Compare performance of all 3 sorting algorithms side by side
 *   - Demonstrate best case vs worst case behavior
 *   - Clone arrays so each algorithm gets the same unsorted input
 *
 * It acts as the "report generator" for the sorting simulation.
 */
public class SortingComparator {

    /**
     * printAthletes(String label, Athlete[] athletes)
     *
     * Prints a labeled list of athletes with rank, name, and score.
     * Used to show array state before and after sorting.
     *
     * @param label    Header label for this list (e.g., "Before Sorting")
     * @param athletes Array of Athlete objects to display
     */
    public static void printAthletes(String label, Athlete[] athletes) {
        System.out.println("\n  === " + label + " ===");
        for (int i = 0; i < athletes.length; i++) {
            System.out.printf("  %2d. %-15s | Event: %-15s | Score: %d%n",
                    (i + 1),
                    athletes[i].getName(),
                    athletes[i].getEvent(),
                    athletes[i].getScore());
        }
    }

    /**
     * printTop3Medalists(Athlete[] sortedAthletes)
     *
     * Prints the top 3 athletes after sorting in descending order.
     * Assumes the array is already sorted with highest score at index 0.
     *
     * @param sortedAthletes Sorted array (descending by score)
     */
    public static void printTop3Medalists(Athlete[] sortedAthletes) {
        System.out.println("\n  === TOP 3 MEDALISTS ===");
        String[] medals = {"GOLD   ", "SILVER ", "BRONZE "};
        for (int i = 0; i < 3 && i < sortedAthletes.length; i++) {
            System.out.printf("  %s | %-15s | Score: %d%n",
                    medals[i],
                    sortedAthletes[i].getName(),
                    sortedAthletes[i].getScore());
        }
    }

    /**
     * cloneAthletes(Athlete[] original)
     *
     * Creates a shallow clone of the athletes array.
     * Used to give each sorting algorithm a fresh unsorted copy.
     * Without cloning, the first sort would change the array
     * and subsequent sorts would get an already-sorted input.
     *
     * @param original The original unsorted array
     * @return A new array with the same Athlete references
     */
    public static Athlete[] cloneAthletes(Athlete[] original) {
        Athlete[] clone = new Athlete[original.length];
        for (int i = 0; i < original.length; i++) clone[i] = original[i];
        return clone;
    }

    /**
     * cloneArray(int[] original)
     *
     * Creates a copy of an int array.
     * Used to give each sort algorithm the same unsorted int input.
     *
     * @param original The original array
     * @return A new array with the same values
     */
    public static int[] cloneArray(int[] original) {
        int[] copy = new int[original.length];
        for (int i = 0; i < original.length; i++) copy[i] = original[i];
        return copy;
    }

    /**
     * compareAllThree(int[] arr)
     *
     * Runs Bubble Sort, Selection Sort, and Insertion Sort
     * on identical copies of the input array.
     * Prints swap/shift counts for each to compare performance.
     *
     * @param arr The unsorted input array to compare on
     */
    public static void compareAllThree(int[] arr) {
        BubbleSort    bubble    = new BubbleSort();
        SelectionSort selection = new SelectionSort();
        InsertionSort insertion = new InsertionSort();

        int[] b = cloneArray(arr);
        int[] s = cloneArray(arr);
        int[] i = cloneArray(arr);

        bubble.sort(b);
        selection.sort(s);
        insertion.sort(i);

        System.out.println("\n  === ALGORITHM COMPARISON ===");
        System.out.printf("  %-20s | Swaps/Shifts: %d%n", "Bubble Sort",    bubble.getSwapCount());
        System.out.printf("  %-20s | Swaps/Shifts: %d%n", "Selection Sort", selection.getSwapCount());
        System.out.printf("  %-20s | Swaps/Shifts: %d%n", "Insertion Sort", insertion.getShiftCount());
    }

    /**
     * printIntArray(String label, int[] arr)
     *
     * Helper — prints a labeled int array on one line.
     *
     * @param label Header label
     * @param arr   The int array to print
     */
    public static void printIntArray(String label, int[] arr) {
        System.out.print("  " + label + ": [ ");
        for (int v : arr) System.out.print(v + " ");
        System.out.println("]");
    }
}
