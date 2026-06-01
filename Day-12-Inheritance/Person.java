/**
 * Person.java
 *
 * Day 12 - OOP with Java
 * Concepts: Inheritance, Base Class, Encapsulation,
 *           Constructor, toString() override, Access Modifiers
 *
 * Person is the top-level base class in the university hierarchy.
 * It holds the most common attributes shared by all people
 * in the system — name, age, and email.
 *
 * Every class in this hierarchy ultimately extends Person:
 *   Person  <--  Student  <--  GradStudent
 *
 * What this demonstrates:
 *   - Private fields with public getters (encapsulation)
 *   - A constructor that initializes all fields
 *   - Overriding toString() to return a meaningful string representation
 *   - This class will be called via super() from Student's constructor
 */
public class Person {

    /*
     * Private instance fields.
     * These are accessible only within this class.
     * Subclasses (Student, GradStudent) must use getters to read them.
     */
    private String name;
    private int    age;
    private String email;

    /**
     * Constructor — Person(String name, int age, String email)
     *
     * Initializes all three fields for this Person object.
     * This constructor is called from Student via super(name, age, email).
     *
     * @param name  Full name of the person
     * @param age   Age of the person in years
     * @param email Email address of the person
     */
    public Person(String name, int age, String email) {
        this.name  = name;
        this.age   = age;
        this.email = email;
    }

    /**
     * getName()
     *
     * Returns the name of this person.
     * Used by subclasses since 'name' is private and cannot
     * be accessed directly from Student or GradStudent.
     *
     * @return Name as a String
     */
    public String getName() {
        return name;
    }

    /**
     * getAge()
     *
     * Returns the age of this person.
     *
     * @return Age as an int
     */
    public int getAge() {
        return age;
    }

    /**
     * getEmail()
     *
     * Returns the email address of this person.
     *
     * @return Email as a String
     */
    public String getEmail() {
        return email;
    }

    /**
     * toString()
     *
     * Overrides the default Object.toString() method.
     * Returns a human-readable string representation of this Person.
     *
     * Called automatically when a Person object is printed:
     *   System.out.println(person) -> calls this method internally.
     *
     * @return Formatted string with name, age, and email
     */
    @Override
    public String toString() {
        return String.format(
            "Person     [ Name: %-15s | Age: %2d | Email: %s ]",
            name, age, email
        );
    }
}
