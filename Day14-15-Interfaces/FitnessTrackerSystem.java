public class FitnessTrackerSystem {

    public static void main(String[] args) {

        FitnessDevice device =
                new FitnessDevice("Sameer", 8500, 450.75);

        device.logActivity();
        device.generateReport();
        device.sendNotification();
        device.resetActivityData();
    }
}