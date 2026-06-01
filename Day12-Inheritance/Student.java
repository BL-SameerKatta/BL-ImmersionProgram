public class Student extends Person {

    private final int studentId;
    private double gpa;

    public Student(String name,int age,int studentId,double gpa) {
        super(name, age);
        this.studentId = studentId;
        this.gpa = gpa;
    }

    public int getStudentId() { return studentId; }
    public double getGpa() { return gpa; }

    public void setGpa(double gpa) {
        if(gpa >= 0 && gpa <= 10) this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{name='" + getName() + "', age=" + getAge() +
                ", studentId=" + studentId + ", gpa=" + gpa + "}";
    }
}