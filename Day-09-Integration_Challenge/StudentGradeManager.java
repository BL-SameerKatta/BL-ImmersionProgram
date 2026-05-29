/**
 * Day 9 - WORKSHOP 1: Core Programming Integration Challenge
 * Concepts: Arrays, Methods, Exception Handling, File I/O, Sorting, Search
 *
 * Problem: Build a command-line Student Grade Manager.
 * - Read student data from file (name, marks in 5 subjects)
 * - Compute grade using nested conditions
 * - Store in arrays, search by name
 * - Sort by total marks (bubble sort)
 * - Write report to output file
 */
import java.io.*;
import java.util.*;

public class StudentGradeManager {

    static final String INPUT_FILE  = "students.txt";
    static final String OUTPUT_FILE = "grade_report.txt";
    static final int    MAX         = 50;

    static String getGrade(double avg) {
        if (avg >= 90) return "A+";
        if (avg >= 80) return "A";
        if (avg >= 70) return "B";
        if (avg >= 60) return "C";
        if (avg >= 50) return "D";
        return "F";
    }

    static int computeTotal(int[] marks) {
        int total = 0;
        for (int m : marks) total += m;
        return total;
    }

    static void bubbleSort(String[] names, int[][] marks, int[] totals, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (totals[j] < totals[j + 1]) {
                    int tmp = totals[j]; totals[j] = totals[j+1]; totals[j+1] = tmp;
                    String tName = names[j]; names[j] = names[j+1]; names[j+1] = tName;
                    int[] tMarks = marks[j]; marks[j] = marks[j+1]; marks[j+1] = tMarks;
                }
            }
        }
    }

    static int searchByName(String[] names, int n, String target) {
        for (int i = 0; i < n; i++)
            if (names[i].equalsIgnoreCase(target)) return i;
        return -1;
    }

    static void createSampleInput() throws IOException {
        try (FileWriter fw = new FileWriter(INPUT_FILE)) {
            fw.write("Ravi,85,90,78,92,88\n");
            fw.write("Priya,76,88,95,82,79\n");
            fw.write("Ankit,55,60,72,58,65\n");
            fw.write("Sneha,91,93,89,97,94\n");
            fw.write("Rohan,40,55,48,62,50\n");
            fw.write("Kavya,88,92,85,90,87\n");
            fw.write("Arjun,72,68,75,80,70\n");
        }
        System.out.println("  Input file created: " + INPUT_FILE);
    }

    static int readStudents(String[] names, int[][] marks) throws IOException {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            while ((line = br.readLine()) != null && count < MAX) {
                String[] parts = line.split(",");
                names[count] = parts[0].trim();
                marks[count] = new int[5];
                for (int i = 0; i < 5; i++)
                    marks[count][i] = Integer.parseInt(parts[i + 1].trim());
                count++;
            }
        }
        return count;
    }

    static void writeReport(String[] names, int[][] marks, int[] totals, int n)
            throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(OUTPUT_FILE))) {
            pw.println("=======================================================");
            pw.println("             STUDENT GRADE REPORT — 2025-26            ");
            pw.println("=======================================================");
            pw.printf("%-8s %5s %5s %5s %5s %5s | %5s | %6s | %5s%n",
                    "Name", "S1", "S2", "S3", "S4", "S5", "Total", "Avg", "Grade");
            pw.println("-------------------------------------------------------");
            for (int i = 0; i < n; i++) {
                double avg = totals[i] / 5.0;
                pw.printf("%-8s %5d %5d %5d %5d %5d | %5d | %6.2f | %5s%n",
                        names[i],
                        marks[i][0], marks[i][1], marks[i][2], marks[i][3], marks[i][4],
                        totals[i], avg, getGrade(avg));
            }
            pw.println("=======================================================");
            pw.println("Sorted by total marks (descending)");
        }
        System.out.println("  Report written to: " + OUTPUT_FILE);
    }

    public static void main(String[] args) {

        String[] names  = new String[MAX];
        int[][]  marks  = new int[MAX][5];
        int[]    totals = new int[MAX];

        try {
            createSampleInput();

            int n = readStudents(names, marks);
            for (int i = 0; i < n; i++)
                totals[i] = computeTotal(marks[i]);

            System.out.println("\n--- Before Sorting ---");
            for (int i = 0; i < n; i++)
                System.out.printf("  %-8s Total: %d  Grade: %s%n",
                        names[i], totals[i], getGrade(totals[i] / 5.0));

            bubbleSort(names, marks, totals, n);

            System.out.println("\n--- After Sorting (descending) ---");
            for (int i = 0; i < n; i++)
                System.out.printf("  Rank %d: %-8s Total: %d  Grade: %s%n",
                        i + 1, names[i], totals[i], getGrade(totals[i] / 5.0));

            writeReport(names, marks, totals, n);

            System.out.println("\n--- Search by Name ---");
            String[] searches = {"Priya", "Rohan", "Zara"};
            for (String s : searches) {
                int idx = searchByName(names, n, s);
                if (idx != -1)
                    System.out.printf("  Found: %-8s Total: %d  Grade: %s%n",
                            names[idx], totals[idx], getGrade(totals[idx] / 5.0));
                else
                    System.out.println("  Not found: " + s);
            }

        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found — " + e.getMessage());
        } catch (IOException e) {
            System.out.println("ERROR: I/O failure — " + e.getMessage());
        }
    }
}
