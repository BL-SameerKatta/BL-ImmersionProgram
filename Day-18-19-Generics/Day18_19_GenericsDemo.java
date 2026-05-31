class Box<T> {

    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}

public class Day18_19_GenericsDemo {

    public static <T extends Comparable<T>> T findMax(T a, T b) {
        return (a.compareTo(b) > 0) ? a : b;
    }

    public static void main(String[] args) {

        Box<String> box = new Box<>("Generics Example");
        System.out.println("Box Value: " + box.getValue());

        Integer max = findMax(10, 20);
        System.out.println("Maximum Value: " + max);
    }
}