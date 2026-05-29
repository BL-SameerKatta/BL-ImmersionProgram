import java.util.Arrays;

public class ArraysPractice {

    public static void main(String[] args) {

        int[] stock = {45, 20, 60, 20, 90, 15, 60};

        findMinMax(stock);
        findTotalStock(stock);

        int target = 90;
        int index = linearSearch(stock, target);

        if (index != -1)
            System.out.println("Item found at index: " + index);
        else
            System.out.println("Item not found");

        findDuplicates(stock);

        int[] rotated = rotateArray(stock, 2);
        System.out.println("Rotated Array: " + Arrays.toString(rotated));

        int[][] shelf = {
                {1, 2, 3},
                {4, 5, 6}
        };

        transposeMatrix(shelf);
    }

    public static void findMinMax(int[] arr) {
        int min = arr[0];
        int max = arr[0];

        for (int num : arr) {
            if (num < min) min = num;
            if (num > max) max = num;
        }

        System.out.println("Minimum Stock: " + min);
        System.out.println("Maximum Stock: " + max);
    }

    public static void findTotalStock(int[] arr) {
        int sum = 0;
        for (int num : arr) sum += num;
        System.out.println("Total Stock: " + sum);
    }

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    public static void findDuplicates(int[] arr) {
        System.out.println("Duplicate Elements:");
        for (int i = 0; i < arr.length; i++) {
            boolean alreadyPrinted = false;

            for (int k = 0; k < i; k++) {
                if (arr[i] == arr[k]) {
                    alreadyPrinted = true;
                    break;
                }
            }

            if (alreadyPrinted) continue;

            int count = 1;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) count++;
            }

            if (count > 1)
                System.out.println(arr[i] + " appears " + count + " times");
        }
    }

    public static int[] rotateArray(int[] arr, int k) {
        int n = arr.length;
        int[] rotated = new int[n];

        for (int i = 0; i < n; i++) {
            rotated[(i + k) % n] = arr[i];
        }
        return rotated;
    }

    public static void transposeMatrix(int[][] matrix) {
        System.out.println("Transpose Matrix:");

        for (int col = 0; col < matrix[0].length; col++) {
            for (int row = 0; row < matrix.length; row++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }
}
