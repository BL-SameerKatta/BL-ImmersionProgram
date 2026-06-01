/**
 * UniversitySimulation.java
 *
 * Day 12 - OOP with Java
 * Concepts: Multi-level Inheritance, Constructor chaining (3 levels),
 *           super keyword, final keyword, method overriding,
 *           IS-A relationship, polymorphic array
 *
 * This is the main driver class for the Day 12 simulation.
 *
 * University hierarchy:
 *   Person  <──  Student  <──  GradStudent
 *
 * What this file demonstrates:
 *   1. Creating objects at all 3 levels of the hierarchy
 *   2. Constructor chaining — each constructor calls super() to chain upward
 *   3. final studentId — set once, cannot change
 *   4. toString() overridden at each level — each prints its own version
 *   5. Person[] polymorphic array holds all 3 types
 *   6. instanceof + downcasting to access subclass-specific methods
 *   7. IS-A checks — GradStudent is also a Student and also a Person
 *
 * How to run:
 *   javac Person.java Student.java GradStudent.java UniversityReport.java UniversitySimulation.java
 *   java UniversitySimulation
 */
public class UniversitySimulation {

    /**
     * main(String[] args)
     *
     * Entry point of the program.
     * Creates Person, Student, and GradStudent objects.
     * Stores them in a Person[] array and runs all demos.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {

        UniversityReport.printHeader();

        /*
         * ── CREATING OBJECTS AT ALL 3 LEVELS ──
         *
         * Person — top of the hierarchy, just name/age/email.
         */
        Person p1 = new Person("Dr. Sharma",  48, "sharma@bridgetech.edu");
        Person p2 = new Person("Dr. Mehta",   52, "mehta@bridgetech.edu");

        /*
         * Student — extends Person.
         * Constructor chains: Student(...) -> super(...) -> Person(...)
         * studentId is final — cannot be changed after this line.
         */
        Student s1 = new Student("Ravi Kumar",  20, "ravi@uni.edu",
                                 "S1001", "Computer Science", 3.8);
        Student s2 = new Student("Priya Shah",  21, "priya@uni.edu",
                                 "S1002", "Electronics",      3.5);
        Student s3 = new Student("Ankit Verma", 19, "ankit@uni.edu",
                                 "S1003", "Mechanical",       3.2);

        /*
         * GradStudent — extends Student which extends Person.
         * Constructor chains across 3 levels:
         * GradStudent(...) -> super(...) -> Student(...) -> super(...) -> Person(...)
         */
        GradStudent g1 = new GradStudent(
                "Sneha Joshi", 25, "sneha@uni.edu",
                "G2001", "AI & ML", 3.95,
                "Deep Learning for Medical Imaging",
                "Dr. Sharma", "PhD");

        GradStudent g2 = new GradStudent(
                "Rohan Das",  24, "rohan@uni.edu",
                "G2002", "Data Science", 3.78,
                "Predictive Analytics in Supply Chain",
                "Dr. Mehta", "MS");

        /*
         * Polymorphic array — holds all types under Person reference.
         * This works because Student IS-A Person and GradStudent IS-A Person.
         */
        Person[] allMembers = {p1, p2, s1, s2, s3, g1, g2};

        /*
         * Print all members — each calls its own overridden toString().
         * p1 prints Person-level, s1 prints Student-level, g1 prints GradStudent-level.
         */
        UniversityReport.printAllMembers(allMembers);

        /*
         * IS-A relationship chain using instanceof on g1.
         */
        UniversityReport.printIsARelationship(g1);

        /*
         * Filter and print only Students from the mixed array.
         * Uses instanceof check before downcasting.
         */
        UniversityReport.printStudentsOnly(allMembers);

        /*
         * Filter and print only GradStudents from the mixed array.
         */
        UniversityReport.printGradStudentsOnly(allMembers);

        /*
         * ── FINAL FIELD DEMO ──
         * studentId is final — demonstrate that it cannot be changed.
         */
        System.out.println("\n=== FINAL FIELD DEMO ===");
        System.out.println("  s1 Student ID : " + s1.getStudentId());
        System.out.println("  Trying to call s1.setStudentId() ...");
        System.out.println("  >> No setStudentId() method exists — field is final!");
        System.out.println("  Student ID remains : " + s1.getStudentId());
        UniversityReport.printFinalIdNote();

        /*
         * ── CONSTRUCTOR CHAINING TRACE ──
         * Showing the order in which constructors execute for GradStudent.
         */
        System.out.println("\n=== CONSTRUCTOR CHAINING ORDER ===");
        System.out.println("  When 'new GradStudent(...)' is called:");
        System.out.println("  Step 1 → GradStudent constructor starts");
        System.out.println("  Step 2 → calls super(...) → Student constructor starts");
        System.out.println("  Step 3 → Student calls super(...) → Person constructor runs");
        System.out.println("  Step 4 → Person sets name, age, email");
        System.out.println("  Step 5 → control returns to Student → sets studentId, major, gpa");
        System.out.println("  Step 6 → control returns to GradStudent → sets thesis, advisor, degreeLevel");
        System.out.println("  Object is now fully constructed.");

        /*
         * ── toString() OVERRIDE CHAIN ──
         * Same object, different reference types — each prints its level.
         */
        System.out.println("\n=== toString() OVERRIDE AT EACH LEVEL ===");
        Person    pRef = g1;   // Person reference pointing to GradStudent object
        Student   sRef = g1;   // Student reference pointing to GradStudent object
        GradStudent gRef = g1; // GradStudent reference pointing to GradStudent object

        System.out.println("  Via Person ref    : " + pRef);
        System.out.println("  Via Student ref   : " + sRef);
        System.out.println("  Via GradStudent ref: " + gRef);
        System.out.println("  >> All 3 call GradStudent.toString() — Java uses the actual object type.");

        /*
         * ── GPA SETTER VALIDATION ──
         * setGpa() in Student validates the range 0.0 to 4.0.
         */
        System.out.println("\n=== SETTER VALIDATION DEMO ===");
        System.out.println("  s1 GPA before : " + s1.getGpa());
        s1.setGpa(3.9);           // valid
        System.out.println("  After setGpa(3.9)  : " + s1.getGpa());
        s1.setGpa(5.5);           // invalid — should be rejected
        System.out.println("  After setGpa(5.5)  : " + s1.getGpa() + " (unchanged)");
    }
}
