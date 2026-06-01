/**
 * Rectangle.java
 *
 * Day 11 - OOP with Java
 * Concepts: Encapsulation with validation, getters/setters, Abstract method override
 *
 * Rectangle is a concrete subclass of Shape.
 * It provides its own implementation of area() and perimeter()
 * using standard rectangle formulas.
 *
 * Key design decision:
 *   - length and width are private but NOT final — they can be changed.
 *   - Setters include validation: negative or zero values are rejected.
 *   - This shows encapsulation with controlled write access.
 *
 * Contrast with Circle:
 *   - Circle's radius is final (immutable) — no setter.
 *   - Rectangle's length/width are mutable — setters with validation.
 */
public class Rectangle extends Shape {

    /*
     * Private mutable instance fields.
     * Not final — can be changed after construction.
     * But only through validated setters — not directly.
     */
    private double length;
    private double width;

    /**
     * Constructor — Rectangle(double length, double width, String color)
     *
     * Calls super(color) to initialize the inherited color field.
     * Sets length and width for this rectangle.
     *
     * @param length The length of the rectangle (must be positive)
     * @param width  The width of the rectangle (must be positive)
     * @param color  The color of the rectangle
     */
    public Rectangle(double length, double width, String color) {
        super(color);
        this.length = length;
        this.width  = width;
    }

    /**
     * getLength()
     *
     * Getter — returns the current length of the rectangle.
     *
     * @return Length as a double
     */
    public double getLength() {
        return length;
    }

    /**
     * getWidth()
     *
     * Getter — returns the current width of the rectangle.
     *
     * @return Width as a double
     */
    public double getWidth() {
        return width;
    }

    /**
     * setLength(double length)
     *
     * Setter with validation — updates the length only if the new value is positive.
     * Negative or zero values are silently ignored to protect data integrity.
     * This is encapsulation in action — controlled write access.
     *
     * @param length The new length value (must be > 0 to take effect)
     */
    public void setLength(double length) {
        if (length > 0) {
            this.length = length;
        } else {
            System.out.println("  [INVALID] Length must be positive. Value rejected: " + length);
        }
    }

    /**
     * setWidth(double width)
     *
     * Setter with validation — updates the width only if the new value is positive.
     * Negative or zero values are silently ignored to protect data integrity.
     *
     * @param width The new width value (must be > 0 to take effect)
     */
    public void setWidth(double width) {
        if (width > 0) {
            this.width = width;
        } else {
            System.out.println("  [INVALID] Width must be positive. Value rejected: " + width);
        }
    }

    /**
     * area()
     *
     * Overrides the abstract method from Shape.
     * Calculates area using the formula: length × width
     *
     * @return Area of the rectangle as a double
     */
    @Override
    public double area() {
        return length * width;
    }

    /**
     * perimeter()
     *
     * Overrides the abstract method from Shape.
     * Calculates perimeter using the formula: 2 × (length + width)
     *
     * @return Perimeter of the rectangle as a double
     */
    @Override
    public double perimeter() {
        return 2 * (length + width);
    }
}
