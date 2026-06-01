import java.util.ArrayList;
import java.util.List;

public class GenericStack<T> {

    private List<T> stack = new ArrayList<>();

    public void push(T value) {
        stack.add(value);
    }

    public T pop() {
        return stack.remove(stack.size() - 1);
    }

    public T peek() {
        return stack.get(stack.size() - 1);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}