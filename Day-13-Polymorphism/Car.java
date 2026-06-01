/**
 * Car.java
 *
 * Day 13 - OOP with Java
 * Concepts: Inheritance, Abstract method override, Encapsulation
 *
 * Car is a concrete subclass of Vehicle.
 * It represents a petrol-powered car in the fleet.
 *
 * Fuel cost formula:
 *   litres consumed = km / mileage
 *   cost = litres * PETROL_PRICE_PER_LITRE
 *
 * Where mileage is km per litre — higher mileage = more fuel efficient.
 */
public class Car extends Vehicle {

    /*
     * Petrol price per litre — constant for all Car objects.
     * 'static final' means shared across all instances and never changes.
     */
    private static final double PETROL_PRICE_PER_LITRE = 102.0;

    /*
     * mileage — km per litre for this specific car model.
     * Different car models have different mileage values.
     * e.g., Honda City = 18 kmpl, Maruti Swift = 22 kmpl
     */
    private double mileage;

    /**
     * Constructor — Car(String regNumber, String model, double mileage)
     *
     * Calls super(regNumber, model) to initialize inherited Vehicle fields.
     * Sets the car-specific mileage value.
     *
     * @param regNumber Vehicle registration number
     * @param model     Car model name (e.g., "Honda City")
     * @param mileage   Fuel efficiency in km per litre
     */
    public Car(String regNumber, String model, double mileage) {
        super(regNumber, model);
        this.mileage = mileage;
    }

    /**
     * getMileage()
     *
     * Returns the fuel efficiency of this car in km per litre.
     *
     * @return Mileage as a double
     */
    public double getMileage() {
        return mileage;
    }

    /**
     * fuelCost(double km)
     *
     * Overrides the abstract method from Vehicle.
     * Calculates petrol cost for the given distance.
     *
     * Formula:
     *   litres = km / mileage
     *   cost   = litres * PETROL_PRICE_PER_LITRE (Rs. 102 per litre)
     *
     * @param km Distance to travel in kilometres
     * @return   Total petrol cost in rupees
     */
    @Override
    public double fuelCost(double km) {
        double litres = km / mileage;
        return litres * PETROL_PRICE_PER_LITRE;
    }

    /**
     * vehicleType()
     *
     * Overrides the abstract method from Vehicle.
     * Returns the label "Car" for this vehicle type.
     *
     * @return "Car" as a String
     */
    @Override
    public String vehicleType() {
        return "Car";
    }
}
