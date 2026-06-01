
import java.util.*;

abstract class Shape {
    public abstract double area();
    public abstract double perimeter();
}

class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }

    public double perimeter() {
        return 2 * Math.PI * radius;
    }
}

class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double area() {
        return length * width;
    }

    public double perimeter() {
        return 2 * (length + width);
    }
}

class Triangle extends Shape {
    private double a,b,c;

    public Triangle(double a,double b,double c){
        this.a=a; this.b=b; this.c=c;
    }

    public double area(){
        double s=(a+b+c)/2;
        return Math.sqrt(s*(s-a)*(s-b)*(s-c));
    }

    public double perimeter(){
        return a+b+c;
    }
}

public class ShapeHierarchy {
    public static void main(String[] args) {

        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(5));
        shapes.add(new Rectangle(10,4));
        shapes.add(new Triangle(3,4,5));

        System.out.println("===== SHAPE REPORT =====");

        for(Shape shape : shapes){
            System.out.println("Type      : " + shape.getClass().getSimpleName());
            System.out.println("Area      : " + shape.area());
            System.out.println("Perimeter : " + shape.perimeter());
            System.out.println("------------------------");
        }
    }
}
