/**
 * TransportSimulation.java
 *
 * Day 13 - OOP with Java
 * Concepts: Runtime Polymorphism, instanceof, Object Casting,
 *           Dynamic Method Dispatch, Open/Closed Principle
 *
 * This is the main driver class for the Day 13 simulation.
 *
 * Fleet hierarchy:
 *   Vehicle  <──  Car
 *   Vehicle  <──  Bus
 *   Vehicle  <──  Bike
 *   Vehicle  <──  ElectricCar  (added WITHOUT changing any existing code)
 *
 * What this demonstrates:
 *   1. Runtime Polymorphism — fuelCost() behaves differently per vehicle type
 *      even when called through a Vehicle reference
 *   2. Dynamic Method Dispatch — Java decides at runtime which
 *      fuelCost() to call based on the actual object type
 *   3. instanceof — safe type checking before downcasting
 *   4. Object Casting — downcasting Vehicle to Bus/ElectricCar
 *      to access subclass-specific methods
 *   5. Open/Closed Principle — ElectricCar was added without
 *      touching Vehicle, Car, Bus, or Bike
 *
 * How to run:
 *   javac Vehicle.java Car.java Bus.java Bike.java ElectricCar.java FleetReport.java TransportSimulation.java
 *   java TransportSimulation
 */
public class TransportSimulation {

    /**
     * main(String[] args)
     *
     * Entry point of the program.
     * Creates a mixed fleet of Car, Bus, Bike, and ElectricCar objects.
     * Stores them in a Vehicle[] array and runs all demos.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {

        /*
         * ── CREATING FLEET OBJECTS ──
         *
         * Each object is a concrete subclass of Vehicle.
         * We cannot do 'new Vehicle(...)' — Vehicle is abstract.
         *
         * Each constructor chains up to Vehicle via super(regNumber, model).
         */
        Car car1 = new Car("MH01AB1234", "Honda City",    18.0);
        Car car2 = new Car("MH02CD5678", "Maruti Swift",  22.0);
        Car car3 = new Car("MH03EF0000", "Toyota Innova", 14.0);

        Bus bus1 = new Bus("MH04GH9012", "Volvo AC Bus",   50);
        Bus bus2 = new Bus("MH05IJ3456", "Tata Starbus",   40);

        Bike bike1 = new Bike("MH06KL7890", "Royal Enfield");
        Bike bike2 = new Bike("MH07MN1111", "Honda Activa");

        /*
         * ElectricCar added as a new type WITHOUT changing any existing class.
         * This is the Open/Closed Principle:
         *   - Vehicle, Car, Bus, Bike are CLOSED for modification
         *   - The system is OPEN for extension via new subclasses
         */
        ElectricCar ev1 = new ElectricCar("MH08OP2222", "Tata Nexon EV", 6.5);
        ElectricCar ev2 = new ElectricCar("MH09QR3333", "MG ZS EV",      5.8);

        /*
         * Vehicle[] — polymorphic array.
         * Holds all 4 concrete types under a single Vehicle reference.
         * All existing code works seamlessly with the new ElectricCar type.
         */
        Vehicle[] fleet = {car1, car2, car3, bus1, bus2, bike1, bike2, ev1, ev2};

        double km = 150.0;

        /*
         * ── POLYMORPHIC COST REPORT ──
         * printAllVehicles loops through Vehicle[] and calls printCostReport(km).
         * Each call internally calls fuelCost(km) — the correct subclass version runs.
         * This is Dynamic Method Dispatch.
         */
        FleetReport.printHeader(km);
        FleetReport.printAllVehicles(fleet, km);
        FleetReport.printTotalCost(fleet, km);

        /*
         * ── instanceof + DOWNCAST DEMO ──
         * Identifies Bus and ElectricCar objects in the mixed array.
         * Safely downcasts to access subclass-specific methods.
         */
        FleetReport.printInstanceOfDemo(fleet);

        /*
         * ── CHEAPEST VEHICLE ──
         * Finds the most fuel-efficient vehicle using polymorphic comparison.
         */
        FleetReport.printCheapestVehicle(fleet, km);

        /*
         * ── OPEN/CLOSED PRINCIPLE SUMMARY ──
         * Describing what changed and what didn't when ElectricCar was added.
         */
        System.out.println("\n=== OPEN/CLOSED PRINCIPLE DEMO ===");
        System.out.println("  Files changed when ElectricCar was added : NONE");
        System.out.println("  Files added                              : ElectricCar.java only");
        System.out.println("  Vehicle.java  → untouched");
        System.out.println("  Car.java      → untouched");
        System.out.println("  Bus.java      → untouched");
        System.out.println("  Bike.java     → untouched");
        System.out.println("  FleetReport.java   → untouched");
        System.out.println("  >> System extended without modifying existing code.");

        /*
         * ── DYNAMIC METHOD DISPATCH DEMO ──
         * Same method name fuelCost() called on Vehicle reference.
         * Different subclass code runs for each object.
         */
        System.out.println("\n=== DYNAMIC METHOD DISPATCH DEMO ===");
        System.out.println("  Calling fuelCost(100) via Vehicle reference on each object:");

        Vehicle[] sample = {car1, bus1, bike1, ev1};
        for (Vehicle v : sample) {
            System.out.printf("  Vehicle ref → actual type: %-13s | fuelCost(100): Rs. %.2f%n",
                    v.getClass().getSimpleName(), v.fuelCost(100));
        }
        System.out.println("  >> Java chose the correct fuelCost() for each object at RUNTIME.");
    }
}
