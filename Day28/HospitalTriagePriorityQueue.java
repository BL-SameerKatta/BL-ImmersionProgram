import java.util.PriorityQueue;

/**
 * Simulates a hospital triage system using a Priority Queue (Min-Heap).
 */
public class HospitalTriagePriorityQueue {
    private PriorityQueue<Patient> triageQueue = new PriorityQueue<>();

    public void enqueuePatient(String name, int severity) {
        triageQueue.offer(new Patient(name, severity));
    }

    public Patient treatNextPatient() {
        return triageQueue.poll();
    }
}
