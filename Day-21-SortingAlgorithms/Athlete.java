/**
 * Athlete.java
 *
 * Day 21 - DSA: Sorting Algorithms
 * Concepts: Data modeling, Encapsulation, toString() override
 *
 * Athlete represents a participant in the sports meet.
 * Each athlete has a name, event, and score.
 *
 * This class is used as the data unit across all sorting algorithms.
 * The sorting algorithms will sort Athlete objects by their score field.
 *
 * What this demonstrates:
 *   - Clean data modeling — one class per real-world entity
 *   - Encapsulation — all fields private, accessed via getters
 *   - toString() override — meaningful output during sorting traces
 */
public class Athlete {

    /*
     * Private instance fields.
     * name  : full name of the athlete
     * event : sport/event they are competing in (e.g., "100m Sprint")
     * score : performance score — higher is better
     */
    private String name;
    private String event;
    private int    score;

    /**
     * Constructor — Athlete(String name, String event, int score)
     *
     * Initializes all fields for this athlete.
     *
     * @param name  Athlete's full name
     * @param event Sport or event name
     * @param score Performance score (integer)
     */
    public Athlete(String name, String event, int score) {
        this.name  = name;
        this.event = event;
        this.score = score;
    }

    /**
     * getName() — Returns the athlete's name.
     * @return Name as a String
     */
    public String getName()  { return name; }

    /**
     * getEvent() — Returns the event this athlete competes in.
     * @return Event name as a String
     */
    public String getEvent() { return event; }

    /**
     * getScore() — Returns the athlete's score.
     * @return Score as an int
     */
    public int getScore()    { return score; }

    /**
     * setScore(int score) — Updates the athlete's score.
     *
     * @param score New score value
     */
    public void setScore(int score) { this.score = score; }

    /**
     * toString()
     *
     * Returns a compact formatted string for this athlete.
     * Used during sorting trace output to show array state.
     *
     * @return Formatted string with name and score
     */
    @Override
    public String toString() {
        return String.format("%-12s(%3d)", name, score);
    }
}
