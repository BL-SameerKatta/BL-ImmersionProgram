public class Bike extends Vehicle {

    public Bike(String vehicleNumber, String model, double distanceTravelled) {
        super(vehicleNumber, model, distanceTravelled);
    }

    @Override
    public double calculateFuelCost() {
        return getDistanceTravelled() * 3;
    }
}