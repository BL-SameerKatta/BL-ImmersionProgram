/**
 * ShapeReport.java
 *
 * Day 11 - OOP with Java
 * Concepts: Abstraction in action, polymorphism preview,
 *           static utility methods, Shape array traversal
 *
 * This utility class handles all report printing for the shape simulation.
 * It works with the abstract type Shape — it does not need to know
 * whether a shape is a Circle, Rectangle, or Triangle.
 *
 * This is abstraction in action:
 *   - ShapeReport only knows about Shape (the abstract type).
 *   - It calls area() and perimeter() on each shape.
 *   - Java automatically calls the correct subclass implementation at runtime.
 */
public class ShapeReport {

    /**
     * printHeader()
     *
     * Prints the column header for the shape report table.
     * Called once before iterating over the shapes array.
     */
    public static void printHeader() {
        System.out.println("=== SHAPE AREA & PERIMETER REPORT ===");
        System.out.println("  Shape        | Color    |      Area | Perimeter");
        System.out.println("  -------------|----------|-----------|----------");
    }

    /**
     * printAllShapes(Shape[] shapes)
     *
     * Iterates over an array of Shape objects and prints each one.
     * Uses polymorphism — calls printInfo() on each Shape reference,
     * which internally calls the correct area() and perimeter()
     * based on the actual subclass type.
     *
     * @param shapes Array of Shape objects (can be Circle, Rectangle, Triangle mix)
     */
    public static void printAllShapes(Shape[] shapes) {
        for (Shape s : shapes) {
            s.printInfo();   // polymorphic call — correct subclass method runs
        }
    }

    /**
     * printTotalArea(Shape[] shapes)
     *
     * Computes and prints the total combined area of all shapes.
     * Iterates the array, calling area() on each shape,
     * and accumulates the result.
     *
     * @param shapes Array of Shape objects to sum area across
     */
    public static void printTotalArea(Shape[] shapes) {
        double totalArea = 0;
        for (Shape s : shapes) {
            totalArea += s.area();
        }
        System.out.println("  ------------------------------------------");
        System.out.printf("  Total Combined Area : %.2f%n", totalArea);
    }

    /**
     * printFooter()
     *
     * Prints the closing line of the report.
     */
    public static void printFooter() {
        System.out.println("==========================================");
    }
}
