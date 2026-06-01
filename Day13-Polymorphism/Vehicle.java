public abstract class Vehicle {

    private String vehicleNumber;
    private String model;
    private double distanceTravelled;

    public Vehicle(String vehicleNumber, String model, double distanceTravelled) {
        this.vehicleNumber = vehicleNumber;
        this.model = model;
        this.distanceTravelled = distanceTravelled;
    }

    public String getVehicleNumber() { return vehicleNumber; }
    public String getModel() { return model; }
    public double getDistanceTravelled() { return distanceTravelled; }

    public abstract double calculateFuelCost();
}