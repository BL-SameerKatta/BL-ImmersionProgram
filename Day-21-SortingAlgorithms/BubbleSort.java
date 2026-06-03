/**
 * BubbleSort.java
 *
 * Day 21 - DSA: Sorting Algorithms
 * Concepts: Bubble Sort, Swap counting, Best/Worst case,
 *           Early termination optimization, Stability
 *
 * Bubble Sort repeatedly steps through the array, compares adjacent
 * elements, and swaps them if they are in the wrong order.
 * After each full pass, the largest unsorted element "bubbles up"
 * to its correct position at the end.
 *
 * Time Complexity:
 *   Best Case  : O(n)     — already sorted, zero swaps (with optimization)
 *   Average    : O(n^2)   — random order
 *   Worst Case : O(n^2)   — reverse sorted
 *
 * Space Complexity: O(1) — in-place, no extra array needed
 *
 * Stability: STABLE — equal elements keep their original relative order
 *            because we only swap when strictly less than (not equal)
 *
 * When to use:
 *   - Small arrays (n < 20)
 *   - When the array is nearly sorted
 *   - When simplicity of code matters more than performance
 */
public class BubbleSort {

    /*
     * swapCount — tracks total number of swaps performed.
     * Accessible after sorting to verify best/worst case behavior.
     */
    private int swapCount;

    /**
     * Constructor — BubbleSort()
     *
     * Initializes swapCount to 0.
     * A fresh BubbleSort instance should start with no swaps counted.
     */
    public BubbleSort() {
        this.swapCount = 0;
    }

    /**
     * sort(int[] arr)
     *
     * Sorts an integer array in ascending order using Bubble Sort.
     * Includes early termination: if no swaps happen in a full pass,
     * the array is already sorted and we stop immediately.
     *
     * Pass-by-pass trace:
     *   - After pass 1: largest element is at index n-1
     *   - After pass 2: 2nd largest is at index n-2
     *   - And so on...
     *
     * @param arr The integer array to sort (modified in-place)
     */
    public void sort(int[] arr) {
        swapCount = 0;
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swappedInThisPass = false;

            /*
             * Inner loop: compare adjacent pairs.
             * After each outer iteration i, the last i elements
             * are already sorted — no need to check them again.
             * So inner loop runs from 0 to n-i-2.
             */
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    int temp  = arr[j];
                    arr[j]    = arr[j + 1];
                    arr[j + 1] = temp;
                    swapCount++;
                    swappedInThisPass = true;
                }
            }

            /*
             * Early termination — best case optimization.
             * If no swap happened in this full pass,
             * the array is fully sorted. Break immediately.
             * This gives O(n) time for already-sorted arrays.
             */
            if (!swappedInThisPass) {
                System.out.println("    [EARLY STOP] No swaps in pass " + (i + 1)
                        + " — array already sorted!");
                break;
            }
        }
    }

    /**
     * sortWithTrace(int[] arr)
     *
     * Same as sort() but prints the array state after every pass.
     * Useful for visualizing how elements bubble up step by step.
     *
     * @param arr The integer array to sort with trace output
     */
    public void sortWithTrace(int[] arr) {
        swapCount = 0;
        int n = arr.length;
        System.out.println("  Initial  : " + arrayToString(arr));

        for (int i = 0; i < n - 1; i++) {
            boolean swappedInThisPass = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp   = arr[j];
                    arr[j]     = arr[j + 1];
                    arr[j + 1] = temp;
                    swapCount++;
                    swappedInThisPass = true;
                }
            }

            System.out.printf("  After pass %2d: %s%n", (i + 1), arrayToString(arr));

            if (!swappedInThisPass) {
                System.out.println("    [EARLY STOP] Already sorted after pass " + (i + 1));
                break;
            }
        }
    }

    /**
     * sortAthletes(Athlete[] athletes)
     *
     * Sorts an array of Athlete objects by score in descending order.
     * Higher score = better rank = comes first.
     * Swaps entire Athlete objects (not just scores).
     *
     * @param athletes Array of Athlete objects to sort by score (descending)
     */
    public void sortAthletes(Athlete[] athletes) {
        swapCount = 0;
        int n = athletes.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swappedInThisPass = false;

            for (int j = 0; j < n - i - 1; j++) {
                /*
                 * Descending: swap if current score is LESS than next score.
                 * Higher scores should come first (rank 1 = highest score).
                 */
                if (athletes[j].getScore() < athletes[j + 1].getScore()) {
                    Athlete temp      = athletes[j];
                    athletes[j]       = athletes[j + 1];
                    athletes[j + 1]   = temp;
                    swapCount++;
                    swappedInThisPass = true;
                }
            }

            if (!swappedInThisPass) break;
        }
    }

    /**
     * getSwapCount()
     *
     * Returns the total number of swaps performed in the last sort call.
     * Zero swaps = best case (already sorted).
     *
     * @return Total swaps as an int
     */
    public int getSwapCount() {
        return swapCount;
    }

    /**
     * isSorted(int[] arr)
     *
     * Checks whether the given array is already sorted in ascending order.
     * If true, Bubble Sort with early termination will do 0 swaps.
     *
     * @param arr The array to check
     * @return true if sorted ascending, false otherwise
     */
    public boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) return false;
        }
        return true;
    }

    /**
     * arrayToString(int[] arr)
     *
     * Helper — converts an int array to a readable string.
     * Used in trace output.
     *
     * @param arr The array to convert
     * @return String representation like "[ 11 12 22 25 64 ]"
     */
    public String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[ ");
        for (int v : arr) sb.append(v).append(" ");
        return sb.append("]").toString();
    }
}
