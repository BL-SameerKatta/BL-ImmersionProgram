/**
 * Utility to find and print the top 3 medalists from a sorted array of scores.
 */
public class TopMedalists {
    public static void printTop3(int[] sortedScores) {
        int n = sortedScores.length;
        System.out.println("\n--- Top 3 Medalists ---");
        if (n >= 1) System.out.println("Gold: " + sortedScores[n - 1]);
        if (n >= 2) System.out.println("Silver: " + sortedScores[n - 2]);
        if (n >= 3) System.out.println("Bronze: " + sortedScores[n - 3]);
    }
}
