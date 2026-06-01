/**
 * Triangle.java
 *
 * Day 11 - OOP with Java
 * Concepts: Inheritance, Abstract method override, Heron's formula
 *
 * Triangle is a concrete subclass of Shape.
 * It provides its own implementation of area() and perimeter().
 *
 * Area calculation uses Heron's Formula:
 *   s = (a + b + c) / 2         (semi-perimeter)
 *   area = sqrt(s(s-a)(s-b)(s-c))
 *
 * This is a more complex formula than Circle or Rectangle,
 * showing how each subclass can have completely different logic
 * behind the same abstract method signatures.
 */
public class Triangle extends Shape {

    /*
     * Private instance fields representing the three sides.
     * a, b, c are the lengths of side 1, side 2, and side 3.
     */
    private double a;
    private double b;
    private double c;

    /**
     * Constructor — Triangle(double a, double b, double c, String color)
     *
     * Calls super(color) to initialize the inherited color field.
     * Stores all three side lengths.
     *
     * @param a     Length of side 1
     * @param b     Length of side 2
     * @param c     Length of side 3
     * @param color The color of the triangle
     */
    public Triangle(double a, double b, double c, String color) {
        super(color);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * getSideA() / getSideB() / getSideC()
     *
     * Getters for each side length.
     * Private fields are read via these public getters.
     *
     * @return Length of the respective side as a double
     */
    public double getSideA() { return a; }
    public double getSideB() { return b; }
    public double getSideC() { return c; }

    /**
     * area()
     *
     * Overrides the abstract method from Shape.
     * Calculates area using Heron's Formula:
     *   Step 1: Compute semi-perimeter s = (a + b + c) / 2
     *   Step 2: Area = sqrt(s * (s-a) * (s-b) * (s-c))
     *
     * @return Area of the triangle as a double
     */
    @Override
    public double area() {
        double s = (a + b + c) / 2.0;                       // semi-perimeter
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));  // Heron's formula
    }

    /**
     * perimeter()
     *
     * Overrides the abstract method from Shape.
     * Calculates perimeter as the sum of all three sides: a + b + c
     *
     * @return Perimeter of the triangle as a double
     */
    @Override
    public double perimeter() {
        return a + b + c;
    }
}
