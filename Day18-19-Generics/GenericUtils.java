import java.util.List;

public class GenericUtils {

    public static <T extends Comparable<T>> T findMax(T[] array) {

        T max = array[0];

        for (T item : array) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }

        return max;
    }

    public static void printList(List<?> list) {
        for (Object item : list) {
            System.out.println(item);
        }
    }
}