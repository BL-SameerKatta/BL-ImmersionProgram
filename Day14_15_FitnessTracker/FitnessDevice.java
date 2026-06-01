/**
 * FitnessDevice class demonstrates multiple interface implementation.
 * It implements Trackable, Reportable, and Notifiable interfaces.
 * This demonstrates a capability that Java interfaces provide, 
 * which cannot be done with classes (Java does not support multiple inheritance of classes).
 */
public class FitnessDevice implements Trackable, Reportable, Notifiable {
    
    private String deviceId;
    private String currentUser;
    private int totalSteps;

    /**
     * Constructor for FitnessDevice
     * @param deviceId The unique ID of the fitness device
     * @param currentUser The name of the user
     */
    public FitnessDevice(String deviceId, String currentUser) {
        this.deviceId = deviceId;
        this.currentUser = currentUser;
        this.totalSteps = 0;
    }

    /**
     * Gets the device ID.
     * @return the device ID
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * Sets the device ID.
     * @param deviceId the new device ID
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * Gets the current user.
     * @return the current user
     */
    public String getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the current user.
     * @param currentUser the new current user
     */
    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Gets the total steps.
     * @return the total steps
     */
    public int getTotalSteps() {
        return totalSteps;
    }

    /**
     * Sets the total steps.
     * @param totalSteps the new total steps
     */
    public void setTotalSteps(int totalSteps) {
        this.totalSteps = totalSteps;
    }

    /**
     * Implementation of the logActivity method from Trackable interface.
     */
    @Override
    public void logActivity(String activityDetails) {
        System.out.println("Logging activity for " + currentUser + " on device " + deviceId + ": " + activityDetails);
        // Simulate step increment based on activity
        this.totalSteps += 500; 
    }

    /**
     * Implementation of the generateReport method from Reportable interface.
     */
    @Override
    public void generateReport() {
        System.out.println("\n--- Fitness Report ---");
        System.out.println("User: " + currentUser);
        System.out.println("Device ID: " + deviceId);
        System.out.println("Total Steps: " + totalSteps);
        System.out.println("----------------------\n");
    }

    /**
     * Implementation of the sendAlert method from Notifiable interface.
     */
    @Override
    public void sendAlert(String message) {
        System.out.println("🔔 ALERT [" + deviceId + "]: " + message);
    }
}
