/**
 * GradStudent.java
 *
 * Day 12 - OOP with Java
 * Concepts: Multi-level Inheritance, Constructor chaining (3 levels),
 *           Method overriding at each level, IS-A relationship chain
 *
 * GradStudent extends Student which extends Person.
 * This is multi-level inheritance — 3 levels deep.
 *
 * IS-A chain:
 *   GradStudent  IS-A  Student
 *   GradStudent  IS-A  Person
 *   Student      IS-A  Person
 *
 * GradStudent adds graduate-specific attributes:
 *   - thesis    : title of the research thesis
 *   - advisor   : faculty advisor name
 *   - degreeLevel : "MS" or "PhD"
 *
 * Constructor chaining across 3 levels:
 *   GradStudent(...) -> super(...) -> Student(...) -> super(...) -> Person(...)
 *   Java walks UP the chain before coming back DOWN.
 */
public class GradStudent extends Student {

    /*
     * GradStudent-specific private fields.
     * These are NOT present in Person or Student.
     * Only GradStudent objects have these attributes.
     */
    private String thesis;
    private String advisor;
    private String degreeLevel; // "MS" or "PhD"

    /**
     * Constructor — GradStudent(String name, int age, String email,
     *                            String studentId, String major, double gpa,
     *                            String thesis, String advisor, String degreeLevel)
     *
     * Constructor chaining across 3 levels:
     *   Step 1: GradStudent calls super(name, age, email, studentId, major, gpa)
     *           → which is Student's constructor
     *   Step 2: Student calls super(name, age, email)
     *           → which is Person's constructor
     *   Step 3: Person initializes name, age, email
     *   Step 4: Control returns to Student — initializes studentId, major, gpa
     *   Step 5: Control returns to GradStudent — initializes thesis, advisor, degreeLevel
     *
     * @param name        Full name (chains up to Person)
     * @param age         Age in years (chains up to Person)
     * @param email       Email address (chains up to Person)
     * @param studentId   Unique student ID — final (chains up to Student)
     * @param major       Academic major (chains up to Student)
     * @param gpa         Grade Point Average (chains up to Student)
     * @param thesis      Title of the graduate research thesis
     * @param advisor     Name of the faculty advisor
     * @param degreeLevel Degree being pursued: "MS" or "PhD"
     */
    public GradStudent(String name, int age, String email,
                       String studentId, String major, double gpa,
                       String thesis, String advisor, String degreeLevel) {
        super(name, age, email, studentId, major, gpa); // chain to Student constructor
        this.thesis      = thesis;
        this.advisor     = advisor;
        this.degreeLevel = degreeLevel;
    }

    /**
     * getThesis()
     *
     * Returns the title of this graduate student's research thesis.
     *
     * @return Thesis title as a String
     */
    public String getThesis() {
        return thesis;
    }

    /**
     * setThesis(String thesis)
     *
     * Allows updating the thesis title.
     * Thesis titles can change as research evolves.
     *
     * @param thesis New thesis title
     */
    public void setThesis(String thesis) {
        this.thesis = thesis;
    }

    /**
     * getAdvisor()
     *
     * Returns the name of the faculty advisor for this student.
     *
     * @return Advisor name as a String
     */
    public String getAdvisor() {
        return advisor;
    }

    /**
     * getDegreeLevel()
     *
     * Returns the graduate degree level being pursued.
     * Expected values: "MS" or "PhD"
     *
     * @return Degree level as a String
     */
    public String getDegreeLevel() {
        return degreeLevel;
    }

    /**
     * toString()
     *
     * Overrides Student's toString() method (which overrode Person's).
     * Returns a GradStudent-level string including all inherited fields
     * plus graduate-specific fields.
     *
     * Uses getName(), getAge(), getStudentId(), getMajor(), getGpa()
     * — all inherited getters from Person and Student.
     *
     * @return Formatted string representing the GradStudent
     */
    @Override
    public String toString() {
        return String.format(
            "GradStudent[ Name: %-15s | Age: %2d | ID: %-6s | Major: %-18s | GPA: %.2f | %s | Advisor: %-12s | Thesis: %s ]",
            getName(), getAge(), getStudentId(), getMajor(), getGpa(),
            degreeLevel, advisor, thesis
        );
    }
}
