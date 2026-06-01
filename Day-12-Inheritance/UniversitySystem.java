
class Person {
    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}

class Student extends Person {
    protected final int studentId;
    protected double gpa;

    public Student(String name, int age, int studentId, double gpa) {
        super(name, age);
        this.studentId = studentId;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{" + super.toString() +
                ", studentId=" + studentId +
                ", gpa=" + gpa + "}";
    }
}

class GradStudent extends Student {
    private String thesisTitle;

    public GradStudent(String name, int age, int studentId,
                       double gpa, String thesisTitle) {
        super(name, age, studentId, gpa);
        this.thesisTitle = thesisTitle;
    }

    @Override
    public String toString() {
        return "GradStudent{" + super.toString() +
                ", thesis='" + thesisTitle + "'}";
    }
}

public class UniversitySystem {
    public static void main(String[] args) {

        GradStudent student = new GradStudent(
                "Sameer",
                24,
                101,
                8.9,
                "AI Based Learning Platform"
        );

        System.out.println(student);

        System.out.println("GradStudent IS-A Student : "
                + (student instanceof Student));

        System.out.println("Student IS-A Person : "
                + (student instanceof Person));
    }
}
