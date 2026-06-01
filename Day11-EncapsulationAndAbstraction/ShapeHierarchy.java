import java.util.*;

public class ShapeHierarchy {

    public static void main(String[] args) {

        List<Shape> shapes = new ArrayList<>();

        shapes.add(new Circle(5));
        shapes.add(new Rectangle(10, 4));
        shapes.add(new Triangle(3, 4, 5));

        System.out.println("========= SHAPE REPORT =========");

        for (Shape shape : shapes) {
            shape.displayShapeInfo();
        }

        System.out.println("Total Shapes : " + shapes.size());
    }
}