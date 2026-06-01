/**
 * UniversityReport.java
 *
 * Day 12 - OOP with Java
 * Concepts: Polymorphic array, instanceof check, object casting,
 *           static utility methods, IS-A relationship in action
 *
 * This utility class handles all display and reporting for
 * the university simulation.
 *
 * It works with Person[] — a polymorphic array that can hold
 * Person, Student, and GradStudent objects together.
 *
 * Key demonstrations here:
 *   - Polymorphic toString() — each object prints its own version
 *   - instanceof — checking the actual type at runtime
 *   - Downcasting — (Student) cast to access Student-specific methods
 *   - IS-A checks — GradStudent is also a Student is also a Person
 */
public class UniversityReport {

    /**
     * printHeader()
     *
     * Prints the top header for the university simulation output.
     */
    public static void printHeader() {
        System.out.println("============================================================");
        System.out.println("           BRIDGETECH UNIVERSITY — MEMBER REGISTRY          ");
        System.out.println("============================================================");
    }

    /**
     * printAllMembers(Person[] members)
     *
     * Prints all members using their overridden toString() methods.
     * The array type is Person[], but each element can be a
     * Person, Student, or GradStudent.
     *
     * Java automatically calls the correct toString() for each object
     * at runtime — this is runtime polymorphism.
     *
     * @param members Array of Person references (can hold any subtype)
     */
    public static void printAllMembers(Person[] members) {
        System.out.println("\n--- All Members (Polymorphic toString) ---");
        for (Person p : members) {
            System.out.println("  " + p); // calls overridden toString() of actual type
        }
    }

    /**
     * printIsARelationship(GradStudent g)
     *
     * Demonstrates the IS-A relationship chain using instanceof.
     * A GradStudent IS-A Student, IS-A Person, and IS-A Object.
     *
     * @param g The GradStudent object to check
     */
    public static void printIsARelationship(GradStudent g) {
        System.out.println("\n--- IS-A Relationship Chain ---");
        System.out.println("  g instanceof GradStudent : " + (g instanceof GradStudent));
        System.out.println("  g instanceof Student     : " + (g instanceof Student));
        System.out.println("  g instanceof Person      : " + (g instanceof Person));
        System.out.println("  g instanceof Object      : " + (g instanceof Object));
    }

    /**
     * printStudentsOnly(Person[] members)
     *
     * Iterates over a Person[] array and prints only Student objects.
     * Uses instanceof to check type before downcasting.
     *
     * This is the safe way to downcast — always check with instanceof first.
     * Casting without checking can throw ClassCastException at runtime.
     *
     * @param members Array of Person references to filter
     */
    public static void printStudentsOnly(Person[] members) {
        System.out.println("\n--- Students Only (instanceof + downcast) ---");
        for (Person p : members) {
            if (p instanceof Student) {
                Student s = (Student) p;   // safe downcast — we verified with instanceof
                System.out.printf("  %-15s | ID: %-6s | Major: %-20s | GPA: %.2f%n",
                        s.getName(), s.getStudentId(), s.getMajor(), s.getGpa());
            }
        }
    }

    /**
     * printGradStudentsOnly(Person[] members)
     *
     * Iterates over a Person[] array and prints only GradStudent objects.
     * Uses instanceof GradStudent to filter, then downcasts to access
     * graduate-specific fields like thesis and advisor.
     *
     * @param members Array of Person references to filter
     */
    public static void printGradStudentsOnly(Person[] members) {
        System.out.println("\n--- Graduate Students Only ---");
        for (Person p : members) {
            if (p instanceof GradStudent) {
                GradStudent g = (GradStudent) p;
                System.out.printf("  %-15s | %s | Advisor: %-12s | Thesis: %s%n",
                        g.getName(), g.getDegreeLevel(), g.getAdvisor(), g.getThesis());
            }
        }
    }

    /**
     * printFinalIdNote()
     *
     * Prints a note explaining the final keyword behavior on studentId.
     */
    public static void printFinalIdNote() {
        System.out.println("\n--- Final Field Demo ---");
        System.out.println("  studentId is declared 'final' in Student.");
        System.out.println("  It is assigned once in the constructor.");
        System.out.println("  No setStudentId() method exists — it cannot be changed.");
        System.out.println("  Attempting reassignment would cause a compile-time error.");
    }
}
