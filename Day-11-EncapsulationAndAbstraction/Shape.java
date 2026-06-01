/**
 * Shape.java
 *
 * Day 11 - OOP with Java
 * Concepts: Abstraction, Abstract Class, Abstract Methods,
 *           Encapsulation, getters/setters, access modifiers
 *
 * This is the abstract base class for all shapes.
 * It cannot be instantiated directly — only its subclasses can.
 *
 * What this demonstrates:
 *   - 'abstract' keyword on class — prevents direct object creation
 *   - 'abstract' keyword on methods — forces subclasses to provide implementation
 *   - Encapsulation — color field is private, accessed via getter/setter
 *   - A concrete method (printInfo) shared by all subclasses
 *   - Template pattern — printInfo() calls abstract methods area() and perimeter()
 */
public abstract class Shape {

    /*
     * Private instance field — encapsulated.
     * No subclass or external class can access this directly.
     * Must use getColor() and setColor() to read or modify it.
     */
    private String color;

    /**
     * Constructor — Shape(String color)
     *
     * Called by every subclass constructor via super(color).
     * Sets the color of the shape at the time of creation.
     *
     * @param color The color of the shape (e.g., "Red", "Blue")
     */
    public Shape(String color) {
        this.color = color;
    }

    /**
     * getColor()
     *
     * Getter for the private color field.
     * Provides read access to the color from outside the class.
     *
     * @return The color of the shape as a String
     */
    public String getColor() {
        return color;
    }

    /**
     * setColor(String color)
     *
     * Setter for the private color field.
     * Provides controlled write access to the color.
     * Validation can be added here if needed in future.
     *
     * @param color The new color to set for the shape
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * area()
     *
     * Abstract method — no body here.
     * Every concrete subclass MUST override this method
     * and provide its own formula for calculating area.
     *
     * @return Area of the shape as a double
     */
    public abstract double area();

    /**
     * perimeter()
     *
     * Abstract method — no body here.
     * Every concrete subclass MUST override this method
     * and provide its own formula for calculating perimeter.
     *
     * @return Perimeter of the shape as a double
     */
    public abstract double perimeter();

    /**
     * printInfo()
     *
     * Concrete method — has a body, shared by all subclasses.
     * Prints a formatted one-line summary of the shape.
     *
     * Uses getClass().getSimpleName() to dynamically get the subclass name
     * (e.g., "Circle", "Rectangle") at runtime.
     *
     * Calls abstract methods area() and perimeter() —
     * this is the Template Method pattern in action.
     */
    public void printInfo() {
        System.out.printf("  %-12s | Color: %-8s | Area: %9.2f | Perimeter: %8.2f%n",
                getClass().getSimpleName(), color, area(), perimeter());
    }
}
