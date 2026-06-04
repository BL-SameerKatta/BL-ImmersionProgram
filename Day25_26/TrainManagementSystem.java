/**
 * Train Management System
 * Models a train as a Singly Linked List of coaches.
 * Supports adding, removing, reversing, cycle detection, finding the middle, and merging.
 */
public class TrainManagementSystem {

    private TrainCoach head;

    public void addCoach(int coachId) {
        TrainCoach newCoach = new TrainCoach(coachId);
        if (head == null) {
            head = newCoach;
            return;
        }
        TrainCoach current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newCoach;
    }

    public void removeCoach(int coachId) {
        if (head == null) return;

        if (head.coachId == coachId) {
            head = head.next;
            return;
        }

        TrainCoach current = head;
        while (current.next != null && current.next.coachId != coachId) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    public void reverseTrain() {
        TrainCoach previous = null;
        TrainCoach current = head;
        TrainCoach next = null;

        while (current != null) {
            next = current.next;     
            current.next = previous; 
            previous = current;      
            current = next;          
        }
        head = previous; 
    }

    public boolean hasCircularRoute() {
        if (head == null) return false;
        
        TrainCoach slow = head;
        TrainCoach fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                return true; 
            }
        }
        return false;
    }

    public int findMiddleCoach() {
        if (head == null) return -1;
        
        TrainCoach slow = head;
        TrainCoach fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.coachId;
    }

    public static TrainCoach mergeSortedSchedules(TrainCoach t1, TrainCoach t2) {
        TrainCoach dummyHead = new TrainCoach(0);
        TrainCoach current = dummyHead;

        while (t1 != null && t2 != null) {
            if (t1.coachId <= t2.coachId) {
                current.next = t1;
                t1 = t1.next;
            } else {
                current.next = t2;
                t2 = t2.next;
            }
            current = current.next;
        }

        if (t1 != null) {
            current.next = t1;
        } else if (t2 != null) {
            current.next = t2;
        }

        return dummyHead.next;
    }
}
