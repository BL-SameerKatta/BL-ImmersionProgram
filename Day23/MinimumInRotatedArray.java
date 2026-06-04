/**
 * Finds the minimum element in a rotated sorted array.
 */
public class MinimumInRotatedArray {
    public static int findMinimum(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        if (arr[left] <= arr[right]) {
            return arr[left];
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (mid < right && arr[mid] > arr[mid + 1]) {
                return arr[mid + 1];
            }
            if (mid > left && arr[mid - 1] > arr[mid]) {
                return arr[mid];
            }

            if (arr[left] <= arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; 
    }
}
