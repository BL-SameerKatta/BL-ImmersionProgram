/**
 * Vehicle.java
 *
 * Day 13 - OOP with Java
 * Concepts: Abstract Class, Abstract Methods, Encapsulation,
 *           Runtime Polymorphism, Dynamic Method Dispatch
 *
 * Vehicle is the abstract base class for all transport types
 * in the fleet management system.
 *
 * It cannot be instantiated directly — only concrete subclasses
 * (Car, Bus, Bike, ElectricCar) can be created.
 *
 * What this class demonstrates:
 *   - 'abstract' class — cannot do 'new Vehicle(...)'
 *   - 'abstract' methods fuelCost() and vehicleType() — subclasses MUST override
 *   - Encapsulation — regNumber and model are private
 *   - A concrete method printCostReport() shared by all subclasses
 *   - Template method pattern — printCostReport() calls abstract fuelCost()
 *     which resolves to the correct subclass implementation at runtime
 */
public abstract class Vehicle {

    /*
     * Private instance fields — common to every vehicle.
     * regNumber : registration plate (e.g., "MH01AB1234")
     * model     : vehicle model name (e.g., "Honda City")
     *
     * Private — must use getters to read from subclasses or outside.
     */
    private String regNumber;
    private String model;

    /**
     * Constructor — Vehicle(String regNumber, String model)
     *
     * Called by every subclass constructor via super(regNumber, model).
     * Initializes the two common fields for every vehicle.
     *
     * @param regNumber Vehicle registration number
     * @param model     Vehicle model name
     */
    public Vehicle(String regNumber, String model) {
        this.regNumber = regNumber;
        this.model     = model;
    }

    /**
     * getRegNumber()
     *
     * Returns the vehicle registration number.
     *
     * @return Registration number as a String
     */
    public String getRegNumber() {
        return regNumber;
    }

    /**
     * getModel()
     *
     * Returns the vehicle model name.
     *
     * @return Model name as a String
     */
    public String getModel() {
        return model;
    }

    /**
     * fuelCost(double km)
     *
     * Abstract method — no implementation here.
     * Each subclass MUST provide its own fuel cost calculation.
     *
     * Different vehicle types calculate cost differently:
     *   - Car    : based on petrol mileage (km/litre * petrol price)
     *   - Bus    : based on diesel consumption
     *   - Bike   : best mileage, petrol price
     *   - ElectricCar : based on battery units (kWh * electricity price)
     *
     * @param km  Distance travelled in kilometres
     * @return    Total fuel/energy cost in rupees
     */
    public abstract double fuelCost(double km);

    /**
     * vehicleType()
     *
     * Abstract method — returns a label for the vehicle type.
     * Each subclass returns its own type string
     * (e.g., "Car", "Bus", "Bike", "ElectricCar").
     *
     * @return Vehicle type label as a String
     */
    public abstract String vehicleType();

    /**
     * printCostReport(double km)
     *
     * Concrete method shared by all subclasses.
     * Prints a formatted one-line cost report for this vehicle.
     *
     * Calls vehicleType() and fuelCost(km) — both abstract methods.
     * Java resolves these to the correct subclass implementation at runtime.
     * This is Dynamic Method Dispatch in action.
     *
     * @param km Distance in kilometres to calculate cost for
     */
    public void printCostReport(double km) {
        System.out.printf(
            "  %-13s | %-18s | Reg: %-14s | %6.0f km | Rs. %9.2f%n",
            vehicleType(), model, regNumber, km, fuelCost(km)
        );
    }
}
