public interface Trackable {

    void logActivity();

    default void resetActivityData() {
        System.out.println("Activity data has been reset.");
    }
}