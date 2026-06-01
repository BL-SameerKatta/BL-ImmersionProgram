public class AdvancedGenericsSystem {

    public static void main(String[] args) {

        Pair<String, Integer> student =
                new Pair<>("Sameer", 101);

        System.out.println(student);

        GenericStack<String> stack =
                new GenericStack<>();

        stack.push("Java");
        stack.push("Spring");

        System.out.println("Top Element: " +
                stack.peek());

        Integer[] numbers =
                {10, 50, 90, 30, 20};

        System.out.println(
                "Maximum Number: " +
                        GenericUtils.findMax(numbers)
        );

        Repository<String> repository =
                new Repository<>();

        repository.save("Record-1");
        repository.save("Record-2");

        GenericUtils.printList(
                repository.findAll()
        );
    }
}