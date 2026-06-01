public class Car extends Vehicle {

    public Car(String vehicleNumber, String model, double distanceTravelled) {
        super(vehicleNumber, model, distanceTravelled);
    }

    @Override
    public double calculateFuelCost() {
        return getDistanceTravelled() * 8;
    }
}