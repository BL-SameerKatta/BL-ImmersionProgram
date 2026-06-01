public abstract class Shape {

    public abstract double area();
    public abstract double perimeter();

    public void displayShapeInfo() {
        System.out.println("Shape Type : " + getClass().getSimpleName());
        System.out.println("Area       : " + area());
        System.out.println("Perimeter  : " + perimeter());
        System.out.println("--------------------------------");
    }
}