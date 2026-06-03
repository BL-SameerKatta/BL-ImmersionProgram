/**
 * InsertionSort.java
 *
 * Day 21 - DSA: Sorting Algorithms
 * Concepts: Insertion Sort, Key element, Shifting vs swapping,
 *           Best/Worst case, Adaptive behavior, Stability
 *
 * Insertion Sort builds the sorted portion one element at a time.
 * It picks each element (called the "key") and inserts it into
 * its correct position within the already-sorted left portion.
 *
 * Think of it like sorting playing cards in your hand:
 *   - Left portion = cards already sorted in hand
 *   - Right portion = cards yet to be picked up
 *   - Each new card is inserted into the correct position
 *
 * Key insight: Insertion Sort SHIFTS elements rather than swapping.
 * Elements in the sorted portion are shifted right to make room
 * for the key. This is more efficient than swapping when nearly sorted.
 *
 * Time Complexity:
 *   Best Case  : O(n)     — already sorted, zero shifts (only comparisons)
 *   Average    : O(n^2)   — random order
 *   Worst Case : O(n^2)   — reverse sorted (maximum shifts)
 *
 * Space Complexity: O(1) — in-place
 *
 * Stability: STABLE — equal elements are never moved past each other
 *            because we stop shifting when arr[j] <= key
 *
 * When to use:
 *   - Small arrays (n < 50)
 *   - Nearly sorted arrays (best practical choice for these)
 *   - Online sorting (can sort as elements arrive one by one)
 *   - Used as a sub-routine in hybrid sorts (TimSort, IntroSort)
 */
public class InsertionSort {

    /*
     * shiftCount — counts total shifts (not swaps).
     * Insertion Sort shifts elements, it doesn't strictly "swap".
     * This is more relevant metric than swapCount for this algorithm.
     */
    private int shiftCount;

    /**
     * Constructor — InsertionSort()
     *
     * Initializes shiftCount to 0.
     */
    public InsertionSort() {
        this.shiftCount = 0;
    }

    /**
     * sort(int[] arr)
     *
     * Sorts an integer array in ascending order using Insertion Sort.
     *
     * Algorithm:
     *   Start from index 1 (index 0 is trivially sorted by itself).
     *   For each element arr[i] (the "key"):
     *     1. Save arr[i] as key
     *     2. Compare key with elements to its left (j = i-1 down to 0)
     *     3. Shift each element one position right while arr[j] > key
     *     4. Place key at the gap created by shifting
     *
     * @param arr The integer array to sort (modified in-place)
     */
    public void sort(int[] arr) {
        shiftCount = 0;
        int n = arr.length;

        for (int i = 1; i < n; i++) {

            /*
             * 'key' is the element we are currently trying to place.
             * We save it before shifting so we don't lose it.
             */
            int key = arr[i];
            int j   = i - 1;

            /*
             * Shift elements of the sorted portion (arr[0..i-1])
             * that are greater than key one position to the right.
             * This opens a gap for the key to be inserted.
             */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j]; // shift right
                shiftCount++;
                j--;
            }

            /*
             * Place the key at the correct position.
             * j+1 is where the gap ends after all shifts.
             */
            arr[j + 1] = key;
        }
    }

    /**
     * sortWithTrace(int[] arr)
     *
     * Same as sort() but prints the array state after each key insertion.
     * Shows how the sorted portion grows from left to right.
     *
     * @param arr The integer array to sort with trace output
     */
    public void sortWithTrace(int[] arr) {
        shiftCount = 0;
        int n = arr.length;
        System.out.println("  Initial  : " + arrayToString(arr));

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j   = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                shiftCount++;
                j--;
            }
            arr[j + 1] = key;

            System.out.printf("  Insert key=%2d at pos %d: %s%n",
                    key, (j + 1), arrayToString(arr));
        }
    }

    /**
     * sortAthletes(Athlete[] athletes)
     *
     * Sorts an array of Athlete objects by score in descending order.
     * Uses the same insertion logic — picks each athlete as "key"
     * and inserts them at the right position in the sorted left portion.
     * Descending: shift while sorted athlete's score is LESS than key score.
     *
     * @param athletes Array of Athlete objects to sort by score descending
     */
    public void sortAthletes(Athlete[] athletes) {
        shiftCount = 0;
        int n = athletes.length;

        for (int i = 1; i < n; i++) {
            Athlete key = athletes[i];
            int j       = i - 1;

            /*
             * Descending: shift athletes left whose score is LESS than key.
             * Higher scores come first.
             */
            while (j >= 0 && athletes[j].getScore() < key.getScore()) {
                athletes[j + 1] = athletes[j];
                shiftCount++;
                j--;
            }
            athletes[j + 1] = key;
        }
    }

    /**
     * getShiftCount()
     *
     * Returns total number of element shifts performed.
     * Zero shifts = best case (already sorted).
     * Maximum shifts = worst case (reverse sorted).
     *
     * @return Total shifts as an int
     */
    public int getShiftCount() {
        return shiftCount;
    }

    /**
     * arrayToString(int[] arr)
     *
     * Helper — converts int array to readable string for trace output.
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
