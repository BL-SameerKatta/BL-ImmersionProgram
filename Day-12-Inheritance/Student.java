/**
 * Student.java
 *
 * Day 12 - OOP with Java
 * Concepts: Inheritance (extends), super() constructor call,
 *           final keyword, method overriding, IS-A relationship
 *
 * Student extends Person — it IS-A Person plus more.
 * It inherits name, age, email from Person and adds:
 *   - studentId (final — immutable after assignment)
 *   - major
 *   - gpa
 *
 * Key concepts shown here:
 *   - 'extends Person' sets up the inheritance relationship
 *   - super(name, age, email) chains to Person's constructor
 *   - 'final' on studentId means it can never be changed after construction
 *   - @Override on toString() replaces Person's version with Student's version
 *   - getName(), getAge(), getEmail() are inherited from Person — not redefined
 */
public class Student extends Person {

    /*
     * 'final' field — assigned once in the constructor and never changed.
     * There is intentionally NO setStudentId() method.
     * Once a student ID is assigned, it is permanent.
     *
     * Private — accessible only within Student class.
     * Subclasses (GradStudent) must use getStudentId() to read it.
     */
    private final String studentId;

    /*
     * Non-final private fields — these can change
     * (e.g., a student can change major or GPA can update).
     */
    private String major;
    private double gpa;

    /**
     * Constructor — Student(String name, int age, String email,
     *                       String studentId, String major, double gpa)
     *
     * Uses super(name, age, email) to call Person's constructor first.
     * This is constructor chaining — Java requires the parent constructor
     * to run before the child constructor continues.
     *
     * After super() completes, we initialize Student-specific fields.
     *
     * @param name      Full name (passed up to Person)
     * @param age       Age in years (passed up to Person)
     * @param email     Email address (passed up to Person)
     * @param studentId Unique student ID — stored as final
     * @param major     Academic major (e.g., "Computer Science")
     * @param gpa       Grade Point Average (0.0 to 4.0)
     */
    public Student(String name, int age, String email,
                   String studentId, String major, double gpa) {
        super(name, age, email);    // must be the FIRST line — calls Person constructor
        this.studentId = studentId; // final — set once here, never again
        this.major     = major;
        this.gpa       = gpa;
    }

    /**
     * getStudentId()
     *
     * Returns the immutable student ID.
     * No corresponding setter — ID is final and cannot be changed.
     *
     * @return Student ID as a String
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * getMajor()
     *
     * Returns the student's current academic major.
     *
     * @return Major as a String
     */
    public String getMajor() {
        return major;
    }

    /**
     * setMajor(String major)
     *
     * Allows updating the student's major.
     * Major is not final — students can change their major.
     *
     * @param major New major to set
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * getGpa()
     *
     * Returns the student's current GPA.
     *
     * @return GPA as a double
     */
    public double getGpa() {
        return gpa;
    }

    /**
     * setGpa(double gpa)
     *
     * Allows updating the student's GPA.
     * Validates that GPA stays within the 0.0 to 4.0 range.
     *
     * @param gpa New GPA value (must be between 0.0 and 4.0)
     */
    public void setGpa(double gpa) {
        if (gpa >= 0.0 && gpa <= 4.0) {
            this.gpa = gpa;
        } else {
            System.out.println("  [INVALID] GPA must be between 0.0 and 4.0. Rejected: " + gpa);
        }
    }

    /**
     * toString()
     *
     * Overrides Person's toString() method.
     * Returns a Student-level string that includes all Person fields
     * (via getName(), getAge()) plus Student-specific fields.
     *
     * @return Formatted string representing the Student
     */
    @Override
    public String toString() {
        return String.format(
            "Student    [ Name: %-15s | Age: %2d | ID: %-6s | Major: %-20s | GPA: %.2f ]",
            getName(), getAge(), studentId, major, gpa
        );
    }
}
