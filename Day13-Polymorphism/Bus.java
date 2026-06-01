public class Bus extends Vehicle {

    public Bus(String vehicleNumber, String model, double distanceTravelled) {
        super(vehicleNumber, model, distanceTravelled);
    }

    @Override
    public double calculateFuelCost() {
        return getDistanceTravelled() * 15;
    }
}