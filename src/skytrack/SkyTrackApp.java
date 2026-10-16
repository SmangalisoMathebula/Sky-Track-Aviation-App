package skytrack;

import java.util.ArrayList;
import java.util.Scanner;

public class SkyTrackApp {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<Student>();

    public static void main(String[] args) {

        System.out.println("=================================================");
        System.out.println(" Welcome to SkyTrack Aviation Training Academy");
        System.out.println("=================================================");

        String currentUser = promptForValidName("Please enter your name to begin: ");
        System.out.println("Welcome, " + currentUser + "!");

        int choice = 0;

        while (choice != 7) {
            printMenu();
            choice = readMenuChoice();

            if (choice == 1) {
                registerStudent();
            } else if (choice == 2) {
                viewStudentProfile();
            } else if (choice == 3) {
                logFlightHours();
            } else if (choice == 4) {
                updateTheoryScore();
            } else if (choice == 5) {
                viewProgressReport();
            } else if (choice == 6) {
                viewRemainingFlightHours();
            } else if (choice == 7) {
                System.out.println("Thank you for using SkyTrack. Fly safe!");
            } else {
                System.out.println("Invalid option. Please choose a number between 1 and 7.");
            }
        }

        scanner.close();
    }

    public static void printMenu() {
        System.out.println();
        System.out.println("================ SkyTrack Menu =================");
        System.out.println("1. Register Student");
        System.out.println("2. View Student Profile");
        System.out.println("3. Log Flight Hours");
        System.out.println("4. Update Theory Score");
        System.out.println("5. View Progress Report");
        System.out.println("6. View Remaining Flight Hours");
        System.out.println("7. Exit");
        System.out.println("=================================================");
        System.out.print("Enter your choice: ");
    }

    public static int readMenuChoice() {
        String input = scanner.nextLine().trim();
        int choice;
        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            choice = -1;
        }
        return choice;
    }

    public static void registerStudent() {
        System.out.println();
        System.out.println("----------- Register New Student -----------");

        String name = promptForValidName("Enter student name: ");

        if (findStudentByName(name) != null) {
            System.out.println("A student with this name is already registered.");
            return;
        }

        int age = promptForValidAge("Enter student age: ");
        String contactNumber = promptForValidContact("Enter contact number (digits only): ");
        String programme = promptForProgramme();
        double flightHours = promptForValidFlightHours("Enter flight hours already completed: ");
        double theoryScore = promptForValidTheoryScore("Enter theory examination score (0-100): ");

        Student newStudent;
        if (programme.equals("PPL")) {
            newStudent = new PPLStudent(name, age, contactNumber, flightHours, theoryScore);
        } else {
            newStudent = new CPLStudent(name, age, contactNumber, flightHours, theoryScore);
        }

        students.add(newStudent);
        System.out.println("Student '" + name + "' registered successfully under the " + programme + " programme.");
    }

    public static void viewStudentProfile() {
        System.out.println();
        System.out.println("----------- View Student Profile -----------");
        Student student = selectStudentByName();
        if (student == null) {
            return;
        }
        student.displayProgress(true);
    }

    public static void logFlightHours() {
        System.out.println();
        System.out.println("----------- Log Flight Hours -----------");
        Student student = selectStudentByName();
        if (student == null) {
            return;
        }

        double extraHours = promptForValidFlightHours("Enter flight hours to add: ");
        boolean success = student.logFlightHours(extraHours);
        if (success) {
            System.out.println("Flight hours updated. " + student.getName() + " now has "
                    + student.getFlightHours() + " logged hours.");
        }
    }

    public static void updateTheoryScore() {
        System.out.println();
        System.out.println("----------- Update Theory Score -----------");
        Student student = selectStudentByName();
        if (student == null) {
            return;
        }

        double newScore = promptForValidTheoryScore("Enter new theory examination score (0-100): ");
        boolean success = student.setTheoryScore(newScore);
        if (success) {
            System.out.println("Theory score updated successfully for " + student.getName() + ".");
        }
    }

    public static void viewProgressReport() {
        System.out.println();
        System.out.println("----------- Progress Report -----------");
        Student student = selectStudentByName();
        if (student == null) {
            return;
        }

        ProgressTracker tracker = (ProgressTracker) student;
        double cri = tracker.calculateCRI();
        String status = tracker.getReadinessStatus();

        System.out.println("Certification Readiness Index (CRI): " + cri);
        System.out.println("Readiness Status: " + status);

        if (status.equals("Not Ready")) {
            System.out.println("Focus on accumulating flight hours and revising theory.");
        } else if (status.equals("In Progress")) {
            System.out.println("Good progress. Continue practising and preparing for your certification.");
        } else {
            System.out.println("Congratulations! You are ready to book your certification examination.");
        }
    }

    public static void viewRemainingFlightHours() {
        System.out.println();
        System.out.println("----------- Remaining Flight Hours -----------");
        Student student = selectStudentByName();
        if (student == null) {
            return;
        }

        double required = student.getRequiredHours();
        double logged = student.getFlightHours();
        double remaining = required - logged;

        if (remaining <= 0) {
            System.out.println(student.getName() + " has already met the minimum flight hour requirement.");
        } else {
            System.out.println(student.getName() + " still requires " + remaining + " more flight hour(s).");
        }
    }

    public static Student findStudentByName(String name) {
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }

    public static Student selectStudentByName() {
        if (students.isEmpty()) {
            System.out.println("No students are currently registered.");
            return null;
        }
        System.out.print("Enter the student's name: ");
        String name = scanner.nextLine().trim();
        Student student = findStudentByName(name);
        if (student == null) {
            System.out.println("No student found with that name.");
        }
        return student;
    }

    public static String promptForProgramme() {
        while (true) {
            System.out.print("Select training programme (1 = PPL, 2 = CPL): ");
            String input = scanner.nextLine().trim();
            if (input.equals("1")) {
                return "PPL";
            } else if (input.equals("2")) {
                return "CPL";
            } else {
                System.out.println("Invalid selection. Please enter 1 for PPL or 2 for CPL.");
            }
        }
    }

    public static String promptForValidName(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.matches("[a-zA-Z ]+")) {
                return input;
            }
            System.out.println("Invalid name. Please use letters and spaces only.");
        }
    }

    public static int promptForValidAge(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int age = Integer.parseInt(input);
                if (age > 0 && age <= 120) {
                    return age;
                }
                System.out.println("Please enter a realistic age.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    public static String promptForValidContact(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.matches("[0-9]+")) {
                return input;
            }
            System.out.println("Invalid contact number. Digits only, please.");
        }
    }

    public static double promptForValidFlightHours(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double hours = Double.parseDouble(input);
                if (hours >= 0) {
                    return hours;
                }
                System.out.println("Flight hours cannot be negative.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public static double promptForValidTheoryScore(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double score = Double.parseDouble(input);
                if (score >= 0 && score <= 100) {
                    return score;
                }
                System.out.println("Theory score must be between 0 and 100.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}