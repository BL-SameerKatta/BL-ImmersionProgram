import java.util.Arrays;
import java.util.List;

/**
 * Main class to demonstrate the Generics scenarios.
 */
public class Main {
    public static void main(String[] args) {
        
        System.out.println("--- 1. Generic Pair ---");
        Pair<String, Integer> studentScore = new Pair<>("Alice", 95);
        System.out.println("Created Pair: " + studentScore);
        System.out.println("Name: " + studentScore.getFirst() + ", Score: " + studentScore.getSecond());

        System.out.println("\n--- 2. Generic Stack ---");
        GenericStack<Double> stack = new GenericStack<>();
        stack.push(10.5);
        stack.push(20.0);
        stack.push(30.2);
        System.out.println("Popped from stack: " + stack.pop());
        System.out.println("Top of stack is now: " + stack.peek());

        System.out.println("\n--- 3. Generic Method with Bounded Type ---");
        Integer[] intArr = { 5, 9, 1, 4, 15, 3 };
        Integer maxInt = Utility.findMax(intArr);
        System.out.println("Array: " + Arrays.toString(intArr));
        System.out.println("Maximum integer: " + maxInt);

        String[] strArr = { "Apple", "Zebra", "Monkey", "Banana" };
        String maxStr = Utility.findMax(strArr);
        System.out.println("Array: " + Arrays.toString(strArr));
        System.out.println("Maximum string (alphabetically): " + maxStr);

        System.out.println("\n--- 4. Generic Repository ---");
        Repository<String> nameRepo = new Repository<>();
        nameRepo.add("Alice");
        nameRepo.add("Bob");
        nameRepo.add("Charlie");
        System.out.println("Repository contents: " + nameRepo.getAll());

        System.out.println("\n--- 5. Wildcard Print Method ---");
        List<String> names = Arrays.asList("Dave", "Eve", "Frank");
        List<Integer> numbers = Arrays.asList(100, 200, 300);
        
        System.out.println("Passing List<String> to List<?>:");
        Utility.printList(names);
        
        System.out.println("Passing List<Integer> to List<?>:");
        Utility.printList(numbers);
    }
}
