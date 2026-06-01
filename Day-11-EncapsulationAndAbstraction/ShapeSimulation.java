/**
 * ShapeSimulation.java
 *
 * Day 11 - OOP with Java
 * Concepts: Abstract class usage, Encapsulation demo, Immutability demo,
 *           Polymorphic array of Shape objects
 *
 * This is the main driver class for the Day 11 simulation.
 * It creates multiple shape objects, stores them in a Shape[] array,
 * and demonstrates abstraction, encapsulation, and immutability.
 *
 * What this demonstrates:
 *   - You CANNOT do: new Shape("Red") — Shape is abstract
 *   - You CAN do   : new Circle(7, "Red") — Circle is concrete
 *   - A Shape[] array can hold Circle, Rectangle, Triangle objects together
 *   - area() and perimeter() resolve to the correct subclass at runtime
 *   - Circle radius is immutable — no setter exists
 *   - Rectangle length/width are mutable but validated through setters
 *
 * How to run:
 *   javac Shape.java Circle.java Rectangle.java Triangle.java ShapeReport.java ShapeSimulation.java
 *   java ShapeSimulation
 */
public class ShapeSimulation {

    /**
     * main(String[] args)
     *
     * Entry point of the program.
     * Creates 6 shape objects (2 of each type),
     * stores them in a Shape array, and runs the full demo.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {

        /*
         * Creating concrete shape objects.
         * Note: 'new Shape(...)' would be a compile error — Shape is abstract.
         * We create Circle, Rectangle, Triangle objects and store them
         * in Shape references — this is polymorphism.
         */
        Circle    c1 = new Circle(7.0,  "Red");
        Circle    c2 = new Circle(3.5,  "Blue");
        Rectangle r1 = new Rectangle(10.0, 5.0, "Green");
        Rectangle r2 = new Rectangle(8.0,  8.0, "Yellow");
        Triangle  t1 = new Triangle(3.0, 4.0, 5.0,  "Purple");
        Triangle  t2 = new Triangle(6.0, 8.0, 10.0, "Orange");

        /*
         * Storing all shapes in a Shape array.
         * The array type is Shape (abstract), but holds concrete objects.
         * This is the power of abstraction — one array for all types.
         */
        Shape[] shapes = {c1, c2, r1, r2, t1, t2};

        /*
         * Print the full shape report using ShapeReport utility.
         * ShapeReport only knows about Shape — not Circle or Rectangle.
         * Java resolves the correct area() / perimeter() at runtime.
         */
        ShapeReport.printHeader();
        ShapeReport.printAllShapes(shapes);
        ShapeReport.printTotalArea(shapes);
        ShapeReport.printFooter();

        /*
         * ── IMMUTABILITY DEMO ──
         * Circle radius is declared 'final' with no setter.
         * The only way to interact with radius is through getRadius().
         * Attempting to call setRadius() would be a compile error.
         */
        System.out.println("\n=== IMMUTABILITY DEMO (Circle) ===");
        System.out.println("  Circle c1 radius : " + c1.getRadius());
        System.out.println("  Attempting c1.setRadius(99) ...");
        System.out.println("  >> No setRadius() method exists — radius is final!");
        System.out.println("  Radius remains    : " + c1.getRadius() + " (unchanged)");

        /*
         * ── ENCAPSULATION DEMO ──
         * Rectangle allows changes to length and width via validated setters.
         * Negative values are rejected — the setter protects the field.
         */
        System.out.println("\n=== ENCAPSULATION DEMO (Rectangle) ===");
        System.out.printf("  Before | Length: %.1f  Width: %.1f  Area: %.2f%n",
                r1.getLength(), r1.getWidth(), r1.area());

        r1.setLength(-5);   // invalid — should be rejected
        r1.setWidth(8.0);   // valid   — should be accepted

        System.out.printf("  After  | Length: %.1f  Width: %.1f  Area: %.2f%n",
                r1.getLength(), r1.getWidth(), r1.area());

        /*
         * ── COLOR SETTER DEMO ──
         * The color field in Shape is private.
         * It can only be changed via the public setColor() method.
         */
        System.out.println("\n=== SETTER DEMO (Shape color) ===");
        System.out.println("  t1 color before setColor : " + t1.getColor());
        t1.setColor("Magenta");
        System.out.println("  t1 color after setColor  : " + t1.getColor());

        /*
         * ── ABSTRACT ARRAY DEMO ──
         * Showing that the same Shape reference can point to different subclasses.
         * getClass().getSimpleName() reveals the actual runtime type.
         */
        System.out.println("\n=== ABSTRACT ARRAY DEMO ===");
        for (Shape s : shapes) {
            System.out.printf("  Shape ref points to: %-12s | Area: %.2f%n",
                    s.getClass().getSimpleName(), s.area());
        }
    }
}
