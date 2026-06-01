/**
 * A generic class representing a pair of values, potentially of different types.
 * Demonstrates the use of multiple generic type parameters.
 *
 * @param <T> The type of the first value
 * @param <U> The type of the second value
 */
public class Pair<T, U> {
    private T first;
    private U second;

    /**
     * Constructor for Pair.
     * @param first The first element
     * @param second The second element
     */
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Gets the first element.
     * @return the first element
     */
    public T getFirst() {
        return first;
    }

    /**
     * Sets the first element.
     * @param first the new first element
     */
    public void setFirst(T first) {
        this.first = first;
    }

    /**
     * Gets the second element.
     * @return the second element
     */
    public U getSecond() {
        return second;
    }

    /**
     * Sets the second element.
     * @param second the new second element
     */
    public void setSecond(U second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "Pair [first=" + first + ", second=" + second + "]";
    }
}
