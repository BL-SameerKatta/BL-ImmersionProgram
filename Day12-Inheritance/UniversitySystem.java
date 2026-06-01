public class UniversitySystem {

    public static void main(String[] args) {

        GradStudent student = new GradStudent(
                "Sameer",
                24,
                101,
                8.9,
                "AI Based Learning Platform"
        );

        System.out.println("===== UNIVERSITY REPORT =====");
        System.out.println(student);

        System.out.println("GradStudent IS-A Student : "
                + (student instanceof Student));

        System.out.println("Student IS-A Person : "
                + (student instanceof Person));
    }
}