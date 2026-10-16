package skytrack;

public class CPLStudent extends Student implements ProgressTracker {

    private static final double REQUIRED_HOURS = 200.0;

    public CPLStudent(String name, int age, String contactNumber, double flightHours, double theoryScore) {
        super(name, age, contactNumber, "CPL", flightHours, theoryScore);
    }

    @Override
    public double getRequiredHours() {
        return REQUIRED_HOURS;
    }

    @Override
    public double calculateCRI() {
        double flightPart = (getFlightHours() / getRequiredHours()) * 50;
        double theoryPart = (getTheoryScore() / 100) * 50;
        return flightPart + theoryPart;
    }

    @Override
    public String getReadinessStatus() {
        double cri = calculateCRI();

        if (cri < 40) {
            return "Not Ready";
        } else if (cri < 75) {
            return "In Progress";
        } else {
            return "Exam Ready";
        }
    }
}