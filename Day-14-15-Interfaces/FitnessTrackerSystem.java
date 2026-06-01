
interface Trackable {
    void logActivity();
    default void resetData() {
        System.out.println("Activity data reset.");
    }
}

interface Reportable {
    void generateReport();
}

interface Notifiable {
    void sendAlert();
}

class FitnessDevice implements Trackable, Reportable, Notifiable {

    public void logActivity() {
        System.out.println("Steps, Calories and Distance tracked.");
    }

    public void generateReport() {
        System.out.println("Weekly fitness report generated.");
    }

    public void sendAlert() {
        System.out.println("Time to drink water.");
    }
}

public class FitnessTrackerSystem {
    public static void main(String[] args) {
        FitnessDevice device = new FitnessDevice();
        device.logActivity();
        device.generateReport();
        device.sendAlert();
        device.resetData();
    }
}
