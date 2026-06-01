/**
 * FleetReport.java
 *
 * Day 13 - OOP with Java
 * Concepts: Polymorphism in action, instanceof operator,
 *           Safe downcasting, Dynamic method dispatch,
 *           Static utility methods
 *
 * This utility class handles all reporting and display for
 * the transport fleet simulation.
 *
 * It works exclusively with the abstract type Vehicle[].
 * It does not need to know the concrete types at compile time —
 * Java resolves the correct fuelCost() and vehicleType()
 * implementations at runtime through dynamic method dispatch.
 *
 * Key concepts demonstrated:
 *   - Polymorphic loop — same call, different behavior per type
 *   - instanceof    — safe type check before casting
 *   - Downcasting   — (Bus) cast to access getPassengers()
 *   - Total cost    — accumulating via polymorphic fuelCost() calls
 */
public class FleetReport {

    /**
     * printHeader(double km)
     *
     * Prints the report header with the distance being calculated for.
     *
     * @param km Distance in kilometres for this report
     */
    public static void printHeader(double km) {
        System.out.println("============================================================");
        System.out.println("     TRANSPORT FLEET — FUEL COST REPORT (" + km + " km)     ");
        System.out.println("============================================================");
        System.out.printf("  %-13s | %-18s | %-18s | %6s | %12s%n",
                "Type", "Model", "Reg Number", "KMs", "Fuel Cost");
        System.out.println("  -------------|------------------|------------------|--------|------------");
    }

    /**
     * printAllVehicles(Vehicle[] fleet, double km)
     *
     * Iterates over the Vehicle array and prints each vehicle's cost report.
     * Calls printCostReport(km) on each — this is a polymorphic call.
     *
     * Java dispatches to the correct subclass implementation of
     * fuelCost() at runtime — this is Dynamic Method Dispatch.
     *
     * @param fleet Array of Vehicle references (can hold Car, Bus, Bike, ElectricCar)
     * @param km    Distance in kilometres to calculate cost for
     */
    public static void printAllVehicles(Vehicle[] fleet, double km) {
        for (Vehicle v : fleet) {
            v.printCostReport(km); // dynamic dispatch — correct fuelCost() called automatically
        }
    }

    /**
     * printTotalCost(Vehicle[] fleet, double km)
     *
     * Calculates and prints the total fuel cost for the entire fleet.
     * Accumulates fuelCost(km) across all vehicles using a polymorphic loop.
     *
     * @param fleet Array of Vehicle references
     * @param km    Distance in kilometres
     */
    public static void printTotalCost(Vehicle[] fleet, double km) {
        double total = 0;
        for (Vehicle v : fleet) {
            total += v.fuelCost(km); // polymorphic call
        }
        System.out.println("  --------------------------------------------------------");
        System.out.printf("  Total Fleet Fuel Cost for %.0f km : Rs. %.2f%n", km, total);
        System.out.println("============================================================");
    }

    /**
     * printInstanceOfDemo(Vehicle[] fleet)
     *
     * Demonstrates instanceof operator and safe downcasting.
     *
     * For Bus objects — uses instanceof Bus to identify them, then
     * downcasts to (Bus) to access getPassengers() which only Bus has.
     *
     * For ElectricCar objects — uses instanceof ElectricCar to identify them,
     * then downcasts to (ElectricCar) to access getKmPerUnit().
     *
     * Rule: ALWAYS check instanceof before downcasting.
     *       Without the check, casting the wrong type throws ClassCastException.
     *
     * @param fleet Array of Vehicle references to inspect
     */
    public static void printInstanceOfDemo(Vehicle[] fleet) {
        System.out.println("\n=== instanceof + DOWNCAST DEMO ===");
        for (Vehicle v : fleet) {

            /*
             * Check if this Vehicle is actually a Bus at runtime.
             * Only then downcast to Bus and call getPassengers().
             * Without instanceof check, this cast would crash for Car/Bike.
             */
            if (v instanceof Bus) {
                Bus b = (Bus) v; // safe downcast — we verified type first
                System.out.println("  Bus found     : " + b.getModel()
                        + " | Passenger capacity: " + b.getPassengers());
            }

            /*
             * Check if this Vehicle is actually an ElectricCar.
             * Only EVs have getKmPerUnit() — Cars/Bikes/Buses don't.
             */
            if (v instanceof ElectricCar) {
                ElectricCar e = (ElectricCar) v; // safe downcast
                System.out.println("  EV found      : " + e.getModel()
                        + " | Efficiency: " + e.getKmPerUnit() + " km/kWh"
                        + " | Zero emissions");
            }
        }
    }

    /**
     * printCheapestVehicle(Vehicle[] fleet, double km)
     *
     * Finds and prints the vehicle with the lowest fuel cost
     * for the given distance. Uses a polymorphic loop to compare.
     *
     * @param fleet Array of Vehicle references
     * @param km    Distance in kilometres
     */
    public static void printCheapestVehicle(Vehicle[] fleet, double km) {
        Vehicle cheapest = fleet[0];
        for (Vehicle v : fleet) {
            if (v.fuelCost(km) < cheapest.fuelCost(km)) {
                cheapest = v;
            }
        }
        System.out.println("\n=== MOST FUEL-EFFICIENT VEHICLE ===");
        System.out.printf("  %-13s | %-18s | Rs. %.2f for %.0f km%n",
                cheapest.vehicleType(), cheapest.getModel(),
                cheapest.fuelCost(km), km);
    }
}
