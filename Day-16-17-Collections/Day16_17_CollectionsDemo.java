import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day16_17_CollectionsDemo {

    public static void main(String[] args) {

        List<String> technologies = new ArrayList<>();
        technologies.add("Java");
        technologies.add("Spring Boot");
        technologies.add("Hibernate");

        System.out.println("Technology List:");
        for (String tech : technologies) {
            System.out.println(tech);
        }

        Map<Integer, String> students = new HashMap<>();
        students.put(101, "Sameer");
        students.put(102, "Rahul");

        System.out.println("\nStudent Map:");
        students.forEach((id, name) ->
                System.out.println(id + " -> " + name));
    }
}