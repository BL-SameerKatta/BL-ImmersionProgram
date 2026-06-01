
import java.util.*;

class Pair<T,U> {
    private T first;
    private U second;

    Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public String toString() {
        return "(" + first + "," + second + ")";
    }
}

class GenericStack<T> {
    private List<T> data = new ArrayList<>();

    void push(T value) { data.add(value); }
    T pop() { return data.remove(data.size()-1); }
    T peek() { return data.get(data.size()-1); }
}

class Repository<T> {
    private List<T> records = new ArrayList<>();

    void save(T record) { records.add(record); }
    List<T> findAll() { return records; }
}

public class AdvancedGenericsSystem {

    public static <T extends Comparable<T>> T findMax(T[] arr) {
        T max = arr[0];
        for (T value : arr) {
            if (value.compareTo(max) > 0) {
                max = value;
            }
        }
        return max;
    }

    public static void printList(List<?> list) {
        for (Object obj : list) {
            System.out.println(obj);
        }
    }

    public static void main(String[] args) {
        Pair<String,Integer> pair = new Pair<>("Sameer",101);
        System.out.println(pair);

        GenericStack<String> stack = new GenericStack<>();
        stack.push("Java");
        stack.push("Spring");
        System.out.println(stack.peek());

        Integer[] arr = {10,50,70,20};
        System.out.println(findMax(arr));

        Repository<String> repo = new Repository<>();
        repo.save("Record1");
        repo.save("Record2");

        printList(repo.findAll());
    }
}
