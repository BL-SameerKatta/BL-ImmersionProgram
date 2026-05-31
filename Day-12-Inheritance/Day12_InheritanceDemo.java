class Person {
    protected String name;

    Person(String name) {
        this.name = name;
    }
}

class Student extends Person {
    private int rollNo;

    Student(String name, int rollNo) {
        super(name);
        this.rollNo = rollNo;
    }

    public void display() {
        System.out.println("Name : " + name);
        System.out.println("Roll : " + rollNo);
    }
}

public class Day12_InheritanceDemo {
    public static void main(String[] args) {
        Student student = new Student("Sameer", 101);
        student.display();
    }
}