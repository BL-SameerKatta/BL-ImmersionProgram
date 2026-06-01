public class ElectricCar extends Vehicle {

    public ElectricCar(String vehicleNumber, String model, double distanceTravelled) {
        super(vehicleNumber, model, distanceTravelled);
    }

    @Override
    public double calculateFuelCost() {
        return getDistanceTravelled() * 2;
    }
}