public class TransportFleetManagement {

    public static void main(String[] args) {

        Vehicle[] fleet = {
            new Car("CAR101","Swift",100),
            new Bus("BUS101","Volvo",150),
            new Bike("BIKE101","Pulsar",120),
            new ElectricCar("EV101","Tesla",200)
        };

        for (Vehicle vehicle : fleet) {
            System.out.println(vehicle.getClass().getSimpleName()
                    + " Cost = " + vehicle.calculateFuelCost());

            if (vehicle instanceof ElectricCar) {
                System.out.println("Electric Vehicle Detected");
            }
        }
    }
}