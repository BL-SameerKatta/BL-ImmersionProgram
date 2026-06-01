import java.util.List;

/**
 * Utility class demonstrating generic methods, bounded type parameters, and wildcards.
 */
public class Utility {

    /**
     * A generic method with a bounded type parameter.
     * It finds the maximum element in an array of objects that implement Comparable.
     *
     * @param arr The array of elements
     * @param <T> The type of the elements, bounded by Comparable
     * @return the maximum element in the array
     */
    public static <T extends Comparable<T>> T findMax(T[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        
        T max = arr[0];
        for (T item : arr) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }

    /**
     * A utility method demonstrating the use of a wildcard (?).
     * It can accept a List of ANY type and print its contents.
     *
     * @param list The list to be printed
     */
    public static void printList(List<?> list) {
        System.out.println("Printing List Elements:");
        for (Object item : list) {
            System.out.println(" - " + item);
        }
    }
}
