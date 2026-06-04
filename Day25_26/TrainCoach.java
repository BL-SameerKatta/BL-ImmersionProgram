/**
 * Node class representing a single Train Coach in a Linked List.
 */
public class TrainCoach {
    public int coachId;
    public TrainCoach next;

    public TrainCoach(int coachId) {
        this.coachId = coachId;
        this.next = null;
    }
}
