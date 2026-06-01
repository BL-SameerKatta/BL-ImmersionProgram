
abstract class Vehicle {
    protected String vehicleNumber;
    protected double distanceTravelled;

    public Vehicle(String vehicleNumber, double distanceTravelled) {
        this.vehicleNumber = vehicleNumber;
        this.distanceTravelled = distanceTravelled;
    }

    public abstract double calculateFuelCost();

    public void display() {
        System.out.println(getClass().getSimpleName() + " | " +
                vehicleNumber + " | Cost: " + calculateFuelCost());
    }
}

class Car extends Vehicle {
    public Car(String no, double d) { super(no, d); }
    public double calculateFuelCost() { return distanceTravelled * 8; }
}

class Bus extends Vehicle {
    public Bus(String no, double d) { super(no, d); }
    public double calculateFuelCost() { return distanceTravelled * 15; }
}

class Bike extends Vehicle {
    public Bike(String no, double d) { super(no, d); }
    public double calculateFuelCost() { return distanceTravelled * 3; }
}

class ElectricCar extends Vehicle {
    public ElectricCar(String no, double d) { super(no, d); }
    public double calculateFuelCost() { return distanceTravelled * 2; }
}

public class TransportFleetManagement {
    public static void main(String[] args) {
        Vehicle[] fleet = {
                new Car("CAR101", 100),
                new Bus("BUS101", 100),
                new Bike("BIKE101", 100),
                new ElectricCar("EV101", 100)
        };

        for (Vehicle vehicle : fleet) {
            vehicle.display();
            if (vehicle instanceof ElectricCar) {
                System.out.println("Electric Vehicle Detected");
            }
        }
    }
}
