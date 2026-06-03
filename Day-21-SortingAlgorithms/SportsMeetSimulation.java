/**
 * SportsMeetSimulation.java
 *
 * Day 21 - DSA: Sorting Algorithms
 * Concepts: Bubble Sort, Selection Sort, Insertion Sort,
 *           Swap counting, Stability, Best/Worst case analysis
 *
 * This is the main driver class for the Day 21 simulation.
 *
 * Story: A sports meet has 50 athletes with scores.
 * We sort them using Bubble Sort, Selection Sort, and Insertion Sort.
 * We count total swaps, check for best case (already sorted),
 * find the top 3 medalists, and trace pass-by-pass changes.
 *
 * What this simulation demonstrates:
 *   1. Bubble Sort   — adjacent comparison + swap, early termination
 *   2. Selection Sort — find minimum each pass, fewest writes
 *   3. Insertion Sort — shift-based, best for nearly sorted
 *   4. Swap counting  — compare algorithm efficiency
 *   5. Best case      — already sorted array = 0 swaps (Bubble + Insertion)
 *   6. Worst case     — reverse sorted = maximum swaps
 *   7. Pass-by-pass trace on [64, 25, 12, 22, 11]
 *   8. Top-3 medalists after sorting
 *
 * How to run:
 *   javac Athlete.java BubbleSort.java SelectionSort.java InsertionSort.java
 *         SortingComparator.java SportsMeetSimulation.java
 *   java SportsMeetSimulation
 */
public class SportsMeetSimulation {

    /**
     * main(String[] args)
     *
     * Entry point of the program.
     * Creates 10 athletes, runs all sorting algorithms,
     * and demonstrates all sorting concepts from Day 21.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║     SPORTS MEET — SCORE SORTER           ║");
        System.out.println("╚══════════════════════════════════════════╝");

        /*
         * ── CREATE 10 ATHLETES ──
         * Each has a name, event, and score.
         * Scores are intentionally unsorted to demonstrate sorting.
         */
        Athlete[] athletes = {
            new Athlete("Ravi",    "100m Sprint",   78),
            new Athlete("Priya",   "Long Jump",     92),
            new Athlete("Ankit",   "High Jump",     65),
            new Athlete("Sneha",   "Shot Put",      88),
            new Athlete("Rohan",   "400m Run",      71),
            new Athlete("Kavya",   "Javelin",       95),
            new Athlete("Arjun",   "Discus Throw",  83),
            new Athlete("Meera",   "Triple Jump",   60),
            new Athlete("Kiran",   "800m Run",      77),
            new Athlete("Divya",   "Pole Vault",    90)
        };

        SortingComparator.printAthletes("ATHLETES BEFORE SORTING", athletes);

        /*
         * ── BUBBLE SORT ──
         * Sort a clone so original stays intact for next algorithm.
         * Count swaps, find medalists.
         */
        System.out.println("\n══════════════════════════════════════════");
        System.out.println("  BUBBLE SORT (Descending by Score)");
        System.out.println("══════════════════════════════════════════");

        BubbleSort bubbleSort     = new BubbleSort();
        Athlete[]  bubbleSorted   = SortingComparator.cloneAthletes(athletes);
        bubbleSort.sortAthletes(bubbleSorted);

        SortingComparator.printAthletes("AFTER BUBBLE SORT", bubbleSorted);
        SortingComparator.printTop3Medalists(bubbleSorted);
        System.out.println("\n  Total Swaps (Bubble Sort) : " + bubbleSort.getSwapCount());

        /*
         * ── SELECTION SORT ──
         */
        System.out.println("\n══════════════════════════════════════════");
        System.out.println("  SELECTION SORT (Descending by Score)");
        System.out.println("══════════════════════════════════════════");

        SelectionSort selectionSort   = new SelectionSort();
        Athlete[]     selectionSorted = SortingComparator.cloneAthletes(athletes);
        selectionSort.sortAthletes(selectionSorted);

        SortingComparator.printAthletes("AFTER SELECTION SORT", selectionSorted);
        SortingComparator.printTop3Medalists(selectionSorted);
        System.out.println("\n  Total Swaps (Selection Sort) : " + selectionSort.getSwapCount());

        /*
         * ── INSERTION SORT ──
         */
        System.out.println("\n══════════════════════════════════════════");
        System.out.println("  INSERTION SORT (Descending by Score)");
        System.out.println("══════════════════════════════════════════");

        InsertionSort insertionSort   = new InsertionSort();
        Athlete[]     insertionSorted = SortingComparator.cloneAthletes(athletes);
        insertionSort.sortAthletes(insertionSorted);

        SortingComparator.printAthletes("AFTER INSERTION SORT", insertionSorted);
        SortingComparator.printTop3Medalists(insertionSorted);
        System.out.println("\n  Total Shifts (Insertion Sort) : " + insertionSort.getShiftCount());

        /*
         * ── PASS-BY-PASS TRACE ON [64, 25, 12, 22, 11] ──
         * Classic textbook example — trace every step of Bubble Sort.
         */
        System.out.println("\n══════════════════════════════════════════");
        System.out.println("  PASS-BY-PASS TRACE: Bubble Sort");
        System.out.println("  Array: [64, 25, 12, 22, 11]");
        System.out.println("══════════════════════════════════════════");

        int[] traceArray = {64, 25, 12, 22, 11};
        BubbleSort tracer = new BubbleSort();
        tracer.sortWithTrace(traceArray);
        System.out.println("  Total swaps: " + tracer.getSwapCount());

        /*
         * ── PASS-BY-PASS TRACE: INSERTION SORT ──
         */
        System.out.println("\n══════════════════════════════════════════");
        System.out.println("  PASS-BY-PASS TRACE: Insertion Sort");
        System.out.println("  Array: [64, 25, 12, 22, 11]");
        System.out.println("══════════════════════════════════════════");

        int[] traceArray2 = {64, 25, 12, 22, 11};
        InsertionSort tracer2 = new InsertionSort();
        tracer2.sortWithTrace(traceArray2);
        System.out.println("  Total shifts: " + tracer2.getShiftCount());

        /*
         * ── BEST CASE DEMO ──
         * Already sorted array — Bubble Sort should detect 0 swaps and stop early.
         * Insertion Sort should also do 0 shifts.
         */
        System.out.println("\n══════════════════════════════════════════");
        System.out.println("  BEST CASE: Already Sorted Array");
        System.out.println("  Array: [11, 12, 22, 25, 64]");
        System.out.println("══════════════════════════════════════════");

        int[] bestCase   = {11, 12, 22, 25, 64};
        BubbleSort    bsBest = new BubbleSort();
        InsertionSort isBest = new InsertionSort();

        System.out.println("\n  Bubble Sort on sorted array:");
        System.out.println("  isSorted check: " + bsBest.isSorted(bestCase));
        bsBest.sortWithTrace(SortingComparator.cloneArray(bestCase));
        System.out.println("  Swaps (should be 0): " + bsBest.getSwapCount());

        System.out.println("\n  Insertion Sort on sorted array:");
        isBest.sort(SortingComparator.cloneArray(bestCase));
        System.out.println("  Shifts (should be 0): " + isBest.getShiftCount());

        /*
         * ── WORST CASE DEMO ──
         * Reverse sorted array — maximum swaps for all algorithms.
         */
        System.out.println("\n══════════════════════════════════════════");
        System.out.println("  WORST CASE: Reverse Sorted Array");
        System.out.println("  Array: [64, 25, 22, 12, 11]");
        System.out.println("══════════════════════════════════════════");

        int[] worstCase = {64, 25, 22, 12, 11};
        BubbleSort    bsWorst = new BubbleSort();
        SelectionSort ssWorst = new SelectionSort();
        InsertionSort isWorst = new InsertionSort();

        bsWorst.sort(SortingComparator.cloneArray(worstCase));
        ssWorst.sort(SortingComparator.cloneArray(worstCase));
        isWorst.sort(SortingComparator.cloneArray(worstCase));

        System.out.println("  Bubble Sort    swaps  : " + bsWorst.getSwapCount());
        System.out.println("  Selection Sort swaps  : " + ssWorst.getSwapCount());
        System.out.println("  Insertion Sort shifts : " + isWorst.getShiftCount());

        /*
         * ── ALGORITHM COMPARISON ON RANDOM ARRAY ──
         */
        System.out.println("\n══════════════════════════════════════════");
        System.out.println("  COMPARISON ON RANDOM ARRAY [64,25,12,22,11]");
        System.out.println("══════════════════════════════════════════");
        int[] randomArr = {64, 25, 12, 22, 11};
        SortingComparator.compareAllThree(randomArr);

        /*
         * ── COMPLEXITY SUMMARY ──
         */
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║         SORTING ALGORITHM COMPLEXITY SUMMARY             ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║ Algorithm       Best      Average   Worst    Stable      ║");
        System.out.println("║ --------------- --------- --------- -------- ----------- ║");
        System.out.println("║ Bubble Sort     O(n)      O(n^2)    O(n^2)   YES         ║");
        System.out.println("║ Selection Sort  O(n^2)    O(n^2)    O(n^2)   NO          ║");
        System.out.println("║ Insertion Sort  O(n)      O(n^2)    O(n^2)   YES         ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }
}
