/**
 * Main class to demonstrate the FitnessDevice functionality.
 */
public class Main {
    public static void main(String[] args) {
        // Instantiate the FitnessDevice
        FitnessDevice tracker = new FitnessDevice("FD-1001", "Alice");
        
        System.out.println("Starting Fitness Tracker Demonstration...");
        
        // 1. Using Trackable interface methods
        tracker.logActivity("Ran 2 miles");
        tracker.logActivity("Cycled for 30 minutes");
        
        // 2. Using Reportable interface methods
        tracker.generateReport();
        
        // 3. Using Notifiable interface methods
        tracker.sendAlert("Goal reached! You've been very active today.");
        
        // 4. Using the default method from Trackable interface
        // Notice we don't have to implement this in FitnessDevice, it comes for free
        tracker.resetData();
        
        // Resetting internal state to match the message
        tracker.setTotalSteps(0);
        
        // Generating report again to show data has been reset
        tracker.generateReport();
    }
}
