/**
 * Interface representing an entity that can send alerts or notifications.
 */
public interface Notifiable {
    /**
     * Sends an alert message to the user.
     * @param message The alert message to be sent
     */
    void sendAlert(String message);
}
