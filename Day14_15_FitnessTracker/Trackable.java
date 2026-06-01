/**
 * Interface representing a trackable entity.
 * Demonstrates the use of abstract methods and default methods in interfaces.
 */
public interface Trackable {
    /**
     * Logs the activity data.
     * @param activityDetails Details of the activity performed
     */
    void logActivity(String activityDetails);

    /**
     * Resets the tracked data to its initial state.
     * This is a default method provided by the interface.
     */
    default void resetData() {
        System.out.println("Trackable data has been reset to default values.");
    }
}
