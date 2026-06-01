/**
 * Bus.java
 *
 * Day 13 - OOP with Java
 * Concepts: Inheritance, Abstract method override,
 *           Subclass-specific fields, instanceof use case
 *
 * Bus is a concrete subclass of Vehicle.
 * It represents a diesel-powered passenger bus in the fleet.
 *
 * Bus has an additional field 'passengers' which is not present
 * in any other Vehicle subclass. This is what makes instanceof
 * + downcasting necessary — to access getPassengers(), the
 * caller must first check instanceof Bus and then cast.
 *
 * Fuel cost formula:
 *   litres = km / BUS_MILEAGE (fixed at 5 kmpl for a heavy bus)
 *   cost   = litres * DIESEL_PRICE_PER_LITRE
 */
public class Bus extends Vehicle {

    /*
     * Diesel price per litre — constant for all Bus objects.
     * Diesel is slightly cheaper than petrol.
     */
    private static final double DIESEL_PRICE_PER_LITRE = 92.0;

    /*
     * Fixed mileage for heavy buses — 5 km per litre of diesel.
     * Buses consume significantly more fuel than cars or bikes.
     */
    private static final double BUS_MILEAGE = 5.0;

    /*
     * passengers — number of passengers this bus can carry.
     * This field is specific to Bus — not present in Vehicle, Car, or Bike.
     * This is why instanceof + downcast is needed to access it.
     */
    private int passengers;

    /**
     * Constructor — Bus(String regNumber, String model, int passengers)
     *
     * Calls super(regNumber, model) to initialize inherited Vehicle fields.
     * Sets the bus-specific passenger capacity.
     *
     * @param regNumber  Vehicle registration number
     * @param model      Bus model name (e.g., "Volvo AC Bus")
     * @param passengers Passenger capacity of this bus
     */
    public Bus(String regNumber, String model, int passengers) {
        super(regNumber, model);
        this.passengers = passengers;
    }

    /**
     * getPassengers()
     *
     * Returns the passenger capacity of this bus.
     * This method exists ONLY in Bus — not in Vehicle or other subclasses.
     * To call this, the caller must downcast a Vehicle reference to Bus.
     *
     * @return Passenger capacity as an int
     */
    public int getPassengers() {
        return passengers;
    }

    /**
     * fuelCost(double km)
     *
     * Overrides the abstract method from Vehicle.
     * Calculates diesel cost for the given distance.
     *
     * Formula:
     *   litres = km / BUS_MILEAGE (5 kmpl)
     *   cost   = litres * DIESEL_PRICE_PER_LITRE (Rs. 92 per litre)
     *
     * @param km Distance to travel in kilometres
     * @return   Total diesel cost in rupees
     */
    @Override
    public double fuelCost(double km) {
        double litres = km / BUS_MILEAGE;
        return litres * DIESEL_PRICE_PER_LITRE;
    }

    /**
     * vehicleType()
     *
     * Overrides the abstract method from Vehicle.
     * Returns the label "Bus" for this vehicle type.
     *
     * @return "Bus" as a String
     */
    @Override
    public String vehicleType() {
        return "Bus";
    }
}
