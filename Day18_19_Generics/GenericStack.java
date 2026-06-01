import java.util.ArrayList;
import java.util.List;

/**
 * A generic stack implementation using a list.
 * Demonstrates a generic class with a single type parameter.
 *
 * @param <T> The type of elements in the stack
 */
public class GenericStack<T> {
    
    private List<T> elements;

    /**
     * Constructor for GenericStack.
     */
    public GenericStack() {
        this.elements = new ArrayList<>();
    }

    /**
     * Pushes an element onto the top of the stack.
     * @param item the element to push
     */
    public void push(T item) {
        elements.add(item);
    }

    /**
     * Removes and returns the element at the top of the stack.
     * @return the removed element, or null if empty
     */
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        return elements.remove(elements.size() - 1);
    }

    /**
     * Returns the element at the top of the stack without removing it.
     * @return the top element, or null if empty
     */
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return elements.get(elements.size() - 1);
    }

    /**
     * Checks if the stack is empty.
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    /**
     * Gets the current size of the stack.
     * @return size
     */
    public int size() {
        return elements.size();
    }
}
