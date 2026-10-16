package skytrack;

public abstract class Student {

    private String name;
    private int age;
    private String contactNumber;
    private String trainingProgramme;
    private double flightHours;
    private double theoryScore;

    public Student(String name, int age, String contactNumber, String trainingProgramme,
                   double flightHours, double theoryScore) {
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
        this.trainingProgramme = trainingProgramme;
        this.flightHours = flightHours;
        this.theoryScore = theoryScore;
    }

    public abstract double getRequiredHours();

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        if (name != null && name.matches("[a-zA-Z ]+")) {
            this.name = name;
            return true;
        }
        System.out.println("Invalid name.");
        return false;
    }

    public int getAge() {
        return age;
    }

    public boolean setAge(int age) {
        if (age > 0 && age <= 120) {
            this.age = age;
            return true;
        }
        System.out.println("Invalid age.");
        return false;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public boolean setContactNumber(String contactNumber) {
        if (contactNumber != null && contactNumber.matches("[0-9]+")) {
            this.contactNumber = contactNumber;
            return true;
        }
        System.out.println("Invalid contact number.");
        return false;
    }

    public String getTrainingProgramme() {
        return trainingProgramme;
    }

    public void setTrainingProgramme(String trainingProgramme) {
        this.trainingProgramme = trainingProgramme;
    }

    public double getFlightHours() {
        return flightHours;
    }

    public boolean setFlightHours(double flightHours) {
        if (flightHours >= 0) {
            this.flightHours = flightHours;
            return true;
        }
        System.out.println("Flight hours cannot be negative.");
        return false;
    }

    public boolean logFlightHours(double extraHours) {
        if (extraHours < 0) {
            System.out.println("Cannot log negative flight hours.");
            return false;
        }
        flightHours = flightHours + extraHours;
        return true;
    }

    public double getTheoryScore() {
        return theoryScore;
    }

    public boolean setTheoryScore(double theoryScore) {
        if (theoryScore >= 0 && theoryScore <= 100) {
            this.theoryScore = theoryScore;
            return true;
        }
        System.out.println("Theory score must be between 0 and 100.");
        return false;
    }

    public void displayProgress() {
        ProgressTracker tracker = (ProgressTracker) this;
        System.out.println(name + " - CRI: " + tracker.calculateCRI() + " - Status: " + tracker.getReadinessStatus());
    }

    public void displayProgress(boolean detailed) {
        if (!detailed) {
            displayProgress();
            return;
        }

        ProgressTracker tracker = (ProgressTracker) this;

        System.out.println("---------------------------------------------");
        System.out.println("Student Name        : " + name);
        System.out.println("Age                 : " + age);
        System.out.println("Contact Number      : " + contactNumber);
        System.out.println("Training Programme  : " + trainingProgramme);
        System.out.println("Flight Hours Logged : " + flightHours);
        System.out.println("Required Hours      : " + getRequiredHours());
        System.out.println("Theory Score        : " + theoryScore + "%");
        System.out.println("CRI                 : " + tracker.calculateCRI());
        System.out.println("Readiness Status    : " + tracker.getReadinessStatus());;
    }
}