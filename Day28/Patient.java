/**
 * Represents a Patient in the hospital triage system.
 */
public class Patient implements Comparable<Patient> {
    public String name;
    public int severity; 

    public Patient(String name, int severity) {
        this.name = name;
        this.severity = severity;
    }

    @Override
    public int compareTo(Patient other) {
        return Integer.compare(this.severity, other.severity);
    }
    
    @Override
    public String toString() {
        return name + " (Severity: " + severity + ")";
    }
}
