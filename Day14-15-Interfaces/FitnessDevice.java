public class FitnessDevice implements Trackable, Reportable, Notifiable {

    private String userName;
    private int steps;
    private double caloriesBurned;

    public FitnessDevice(String userName, int steps, double caloriesBurned) {
        this.userName = userName;
        this.steps = steps;
        this.caloriesBurned = caloriesBurned;
    }

    public String getUserName() { return userName; }
    public int getSteps() { return steps; }
    public double getCaloriesBurned() { return caloriesBurned; }

    @Override
    public void logActivity() {
        System.out.println("User: " + userName);
        System.out.println("Steps: " + steps);
        System.out.println("Calories: " + caloriesBurned);
    }

    @Override
    public void generateReport() {
        System.out.println("Fitness Report Generated");
    }

    @Override
    public void sendNotification() {
        System.out.println("Stay hydrated and keep moving!");
    }
}