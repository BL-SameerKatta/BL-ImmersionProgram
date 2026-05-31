abstract class Shape {
    abstract double area();
    abstract double perimeter();
}

class Circle extends Shape {
    private double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }

    public double perimeter() {
        return 2 * Math.PI * radius;
    }
}

public class Day11_ShapeDemo {
    public static void main(String[] args) {
        Shape shape = new Circle(5);
        System.out.println("Area = " + shape.area());
        System.out.println("Perimeter = " + shape.perimeter());
    }
}