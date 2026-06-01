/**
 * Bike.java
 *
 * Day 13 - OOP with Java
 * Concepts: Inheritance, Abstract method override,
 *           Simple concrete subclass
 *
 * Bike is a concrete subclass of Vehicle.
 * It represents a petrol-powered two-wheeler in the fleet.
 *
 * Bikes have the best fuel efficiency — 45 km per litre.
 * No additional fields beyond what Vehicle provides.
 *
 * Fuel cost formula:
 *   litres = km / BIKE_MILEAGE (45 kmpl)
 *   cost   = litres * PETROL_PRICE_PER_LITRE
 */
public class Bike extends Vehicle {

    /*
     * Petrol price per litre — same rate as Car.
     * Both use petrol, so the price constant is the same.
     */
    private static final double PETROL_PRICE_PER_LITRE = 102.0;

    /*
     * Bikes have significantly better mileage than cars.
     * 45 kmpl is a typical average for Indian two-wheelers.
     */
    private static final double BIKE_MILEAGE = 45.0;

    /**
     * Constructor — Bike(String regNumber, String model)
     *
     * Calls super(regNumber, model) to initialize inherited Vehicle fields.
     * No additional fields — Bike only needs reg number and model.
     *
     * @param regNumber Vehicle registration number
     * @param model     Bike model name (e.g., "Royal Enfield", "Honda Activa")
     */
    public Bike(String regNumber, String model) {
        super(regNumber, model);
    }

    /**
     * fuelCost(double km)
     *
     * Overrides the abstract method from Vehicle.
     * Calculates petrol cost for the given distance.
     *
     * Formula:
     *   litres = km / BIKE_MILEAGE (45 kmpl)
     *   cost   = litres * PETROL_PRICE_PER_LITRE (Rs. 102 per litre)
     *
     * Bikes are the cheapest per km — best mileage in the fleet.
     *
     * @param km Distance to travel in kilometres
     * @return   Total petrol cost in rupees
     */
    @Override
    public double fuelCost(double km) {
        double litres = km / BIKE_MILEAGE;
        return litres * PETROL_PRICE_PER_LITRE;
    }

    /**
     * vehicleType()
     *
     * Overrides the abstract method from Vehicle.
     * Returns the label "Bike" for this vehicle type.
     *
     * @return "Bike" as a String
     */
    @Override
    public String vehicleType() {
        return "Bike";
    }
}
