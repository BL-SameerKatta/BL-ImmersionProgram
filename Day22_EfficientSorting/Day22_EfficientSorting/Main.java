import java.util.Arrays;

/**
 * Main driver class for Day 22: Efficient Sorting Algorithms.
 *
 * <p>This class ties together all three efficient sorting algorithms covered on Day 22
 * and runs the complete <b>library-books story problem</b>:</p>
 * <ul>
 *   <li>{@link MergeSort}     — stable, O(n log n), divide-and-conquer</li>
 *   <li>{@link QuickSort}     — in-place, O(n log n) average, Lomuto + 3-way pivot</li>
 *   <li>{@link CountingSort}  — non-comparison, O(n + k), ideal for bounded categories</li>
 * </ul>
 *
 * <p><b>Story:</b> A library sorts 1 million books by year (Merge / Quick Sort)
 * and by genre code 1–20 (Counting Sort). Each algorithm is applied to the same
 * shuffled dataset. Empirical runtimes are measured at n = 100, 1 000, and 10 000.</p>
 *
 * <p><b>Algorithm comparison:</b></p>
 * <pre>
 *  Algorithm       | Time (best/avg/worst) | Space   | Stable | Notes
 *  ────────────────|───────────────────────|─────────|────────|──────────────────────────
 *  Merge Sort      | O(n log n) all cases  | O(n)    | YES    | Guaranteed performance
 *  Quick Sort      | O(n log n) / O(n²)    | O(log n)| NO     | Fastest in practice (avg)
 *  Counting Sort   | O(n + k)              | O(n+k)  | YES    | Only for bounded integers
 * </pre>
 */
public class Main {

    /**
     * Entry point — runs the library story problem, individual algorithm demos,
     * and the empirical runtime comparison table.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║   DAY 22 — Efficient Sorting: Merge, Quick, Counting Sort   ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println("Story: A library sorts 1 million books by year and by genre.\n");

        // Shared dataset: 20 book publication years (representative sample)
        int[] bookYears = {1995, 1870, 2003, 1945, 2021, 1780, 2015, 1900, 1999, 2010,
                           1855, 1967, 2008, 1923, 1984, 2018, 1776, 2000, 1912, 1850};

        // ── SECTION 1: Merge Sort ────────────────────────────────────────────
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│  SECTION 1 — Merge Sort (book years)     │");
        System.out.println("└──────────────────────────────────────────┘");
        int[] mergeArr = Arrays.copyOf(bookYears, bookYears.length);
        System.out.println("Before: " + Arrays.toString(mergeArr));
        long mergeCmp = MergeSort.sort(mergeArr);
        System.out.println("After : " + Arrays.toString(mergeArr));
        System.out.println("Comparisons: " + mergeCmp);
        System.out.println("Oldest book year: " + mergeArr[0] + " | Newest: " + mergeArr[mergeArr.length - 1]);

        System.out.println("\nDivide-and-conquer trace on [38, 27, 43, 3]:");
        MergeSort.traceMergeSort(new int[]{38, 27, 43, 3}, 0, "Root ");

        // ── SECTION 2: Quick Sort (Lomuto) ───────────────────────────────────
        System.out.println("\n┌──────────────────────────────────────────┐");
        System.out.println("│  SECTION 2 — Quick Sort Lomuto           │");
        System.out.println("└──────────────────────────────────────────┘");
        int[] quickArr = Arrays.copyOf(bookYears, bookYears.length);
        System.out.println("Before: " + Arrays.toString(quickArr));
        long quickCmp = QuickSort.sortLomuto(quickArr);
        System.out.println("After : " + Arrays.toString(quickArr));
        System.out.println("Comparisons: " + quickCmp);

        System.out.println("\nLomuto partition trace on [10, 80, 30, 90, 40, 50, 70]:");
        QuickSort.traceLomuto(new int[]{10, 80, 30, 90, 40, 50, 70}, 0);

        // ── SECTION 3: Quick Sort (3-Way) on genre codes ─────────────────────
        System.out.println("\n┌──────────────────────────────────────────┐");
        System.out.println("│  SECTION 3 — Quick Sort 3-Way (genres)  │");
        System.out.println("└──────────────────────────────────────────┘");
        int[] genres = new int[30];
        for (int i = 0; i < 30; i++) genres[i] = (int)(Math.random() * 20) + 1;
        System.out.println("Genre codes before: " + Arrays.toString(genres));
        long threeCmp = QuickSort.sortThreeWay(genres);
        System.out.println("Genre codes after : " + Arrays.toString(genres));
        System.out.println("3-Way comparisons : " + threeCmp + "  (handles duplicates efficiently)");

        // ── SECTION 4: Counting Sort on genre codes ───────────────────────────
        System.out.println("\n┌──────────────────────────────────────────┐");
        System.out.println("│  SECTION 4 — Counting Sort (genres 1–20)│");
        System.out.println("└──────────────────────────────────────────┘");
        int[] genreArr = new int[20];
        for (int i = 0; i < 20; i++) genreArr[i] = (int)(Math.random() * 20) + 1;
        System.out.println("Before: " + Arrays.toString(genreArr));
        int[] countSorted = CountingSort.sort(genreArr);
        System.out.println("After : " + Arrays.toString(countSorted));
        System.out.println("No comparisons — pure counting, O(n + 20) = O(n).");

        // ── SECTION 5: Empirical Runtime Comparison at n=100/1000/10000 ──────
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║   SECTION 5 — Empirical Runtime Comparison                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.printf("  %-14s  %-8s  %-12s  %-12s  %-14s%n",
                "n", "Units", "Merge Sort", "Quick Sort", "Counting Sort");
        System.out.println("  " + "─".repeat(66));

        for (int size : new int[]{100, 1000, 10000}) {
            /*
             * Generate three independent random arrays so each algorithm gets
             * the same unsorted data, making the comparison fair.
             */
            int[] base = MergeSort.generateRandom(size);
            int[] forMerge   = Arrays.copyOf(base, size);
            int[] forQuick   = Arrays.copyOf(base, size);

            // Counting Sort uses genre codes (bounded 1–20), not raw random values
            int[] forCounting = new int[size];
            for (int i = 0; i < size; i++) forCounting[i] = (base[i] % 20) + 1;

            long t1 = System.nanoTime();
            MergeSort.sort(forMerge);
            long mergeTime = (System.nanoTime() - t1) / 1000;

            long t2 = System.nanoTime();
            QuickSort.sortLomuto(forQuick);
            long quickTime = (System.nanoTime() - t2) / 1000;

            long t3 = System.nanoTime();
            CountingSort.sort(forCounting);
            long countTime = (System.nanoTime() - t3) / 1000;

            System.out.printf("  %-14d  %-8s  %-12d  %-12d  %-14d%n",
                    size, "µs", mergeTime, quickTime, countTime);
        }

        // ── Final algorithm decision guide ───────────────────────────────────
        System.out.println("\n── Algorithm Selection Guide ──");
        System.out.println("  Use Merge Sort    when: stability required, guaranteed O(n log n), large data.");
        System.out.println("  Use Quick Sort    when: in-place, fastest average case, memory is limited.");
        System.out.println("  Use Counting Sort when: values are integers in a small known range (genres, grades).");
        System.out.println("\n── All Day 22 demos complete ──");
    }
}
