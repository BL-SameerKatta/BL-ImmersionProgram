/**
 * Circle.java
 *
 * Day 11 - OOP with Java
 * Concepts: Inheritance, Encapsulation, Immutability, Abstract method override
 *
 * Circle is a concrete subclass of Shape.
 * It provides its own implementation of area() and perimeter()
 * using the standard circle formulas.
 *
 * Key design decision:
 *   - radius is declared as 'final' — once set in the constructor,
 *     it can NEVER be changed. There is intentionally NO setRadius() method.
 *   - This enforces immutability on the radius field.
 *   - Only getRadius() is provided (read-only access).
 */
public class Circle extends Shape {

    /*
     * 'final' instance field — immutable after construction.
     * Declaring it 'private' hides it from outside.
     * Declaring it 'final' prevents any reassignment.
     * No setter method is provided — enforces immutability.
     */
    private final double radius;

    /**
     * Constructor — Circle(double radius, String color)
     *
     * Calls super(color) to invoke the Shape constructor
     * and initialize the inherited color field.
     * Sets the final radius — this is the only place it can be assigned.
     *
     * @param radius The radius of the circle (must be positive)
     * @param color  The color of the circle
     */
    public Circle(double radius, String color) {
        super(color);           // call Shape constructor
        this.radius = radius;   // final field — set once, never changed
    }

    /**
     * getRadius()
     *
     * Getter for the immutable radius field.
     * Read-only access — no corresponding setter exists by design.
     *
     * @return The radius of the circle as a double
     */
    public double getRadius() {
        return radius;
    }

    /**
     * area()
     *
     * Overrides the abstract method from Shape.
     * Calculates area using the formula: π × r²
     *
     * @return Area of the circle as a double
     */
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    /**
     * perimeter()
     *
     * Overrides the abstract method from Shape.
     * Calculates circumference using the formula: 2 × π × r
     *
     * @return Circumference (perimeter) of the circle as a double
     */
    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }
}
