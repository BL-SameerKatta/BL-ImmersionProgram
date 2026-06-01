/**
 * ElectricCar.java
 *
 * Day 13 - OOP with Java
 * Concepts: Open/Closed Principle preview, Inheritance,
 *           Abstract method override, New type added without changing existing code
 *
 * ElectricCar is a concrete subclass of Vehicle.
 * It represents an electric vehicle (EV) in the fleet.
 *
 * This class demonstrates the Open/Closed Principle:
 *   - The Vehicle class is CLOSED for modification — we did not change it.
 *   - The system is OPEN for extension — we simply added a new subclass.
 *   - All existing code (FleetReport, TransportSimulation) still works
 *     without any changes, because ElectricCar extends Vehicle and
 *     provides its own fuelCost() and vehicleType() implementations.
 *
 * ElectricCar uses electricity instead of fuel:
 *   energy units = km / kmPerUnit  (km per kWh)
 *   cost = units * ELECTRICITY_PRICE_PER_UNIT
 */
public class ElectricCar extends Vehicle {

    /*
     * Electricity price per kWh (kilowatt-hour).
     * Significantly cheaper than petrol or diesel per km.
     */
    private static final double ELECTRICITY_PRICE_PER_UNIT = 8.5;

    /*
     * kmPerUnit — how many km this EV can travel on 1 kWh of charge.
     * Varies by model — e.g., Tata Nexon EV gets ~6.5 km per kWh.
     */
    private double kmPerUnit;

    /**
     * Constructor — ElectricCar(String regNumber, String model, double kmPerUnit)
     *
     * Calls super(regNumber, model) to initialize inherited Vehicle fields.
     * Sets the EV-specific energy efficiency in km per kWh.
     *
     * @param regNumber Vehicle registration number
     * @param model     EV model name (e.g., "Tata Nexon EV")
     * @param kmPerUnit Energy efficiency in km per kWh
     */
    public ElectricCar(String regNumber, String model, double kmPerUnit) {
        super(regNumber, model);
        this.kmPerUnit = kmPerUnit;
    }

    /**
     * getKmPerUnit()
     *
     * Returns the energy efficiency of this EV in km per kWh.
     *
     * @return Energy efficiency as a double
     */
    public double getKmPerUnit() {
        return kmPerUnit;
    }

    /**
     * fuelCost(double km)
     *
     * Overrides the abstract method from Vehicle.
     * Calculates electricity cost for the given distance.
     *
     * Formula:
     *   units = km / kmPerUnit         (kWh consumed)
     *   cost  = units * 8.5            (Rs. 8.5 per kWh)
     *
     * EVs are significantly cheaper to run per km vs petrol/diesel vehicles.
     *
     * @param km Distance to travel in kilometres
     * @return   Total electricity cost in rupees
     */
    @Override
    public double fuelCost(double km) {
        double units = km / kmPerUnit;
        return units * ELECTRICITY_PRICE_PER_UNIT;
    }

    /**
     * vehicleType()
     *
     * Overrides the abstract method from Vehicle.
     * Returns the label "ElectricCar" for this vehicle type.
     *
     * @return "ElectricCar" as a String
     */
    @Override
    public String vehicleType() {
        return "ElectricCar";
    }
}
