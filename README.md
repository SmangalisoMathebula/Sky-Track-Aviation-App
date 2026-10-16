SkyTrack

SkyTrack is a console program written in Java for keeping track of student pilots at a flight training academy. I built it to practise object-oriented programming. It handles two programmes, the Private Pilot Licence (PPL) and the Commercial Pilot Licence (CPL).

What it does

You start by entering your name, then use a menu to register students, view their profiles, log flight hours, update theory scores, see a progress report, and check how many flight hours a student still needs. Each student gets a Certification Readiness Index (CRI) that shows how close they are to being ready for their exam.

How the CRI works

The CRI is out of 100. Half comes from flight hours and half from the theory score:

CRI = (flight hours / required hours) x 50 + (theory score / 100) x 50

A CPL student needs 200 flight hours. A PPL student needs TODO hours.

For CPL students, a CRI below 40 is "Not Ready", 40 up to 75 is "In Progress", and 75 or above is "Exam Ready". TODO: add the PPL values if they are different.

How the code is organised

Student is an abstract class that holds the details every student has: name, age, contact number, programme, flight hours and theory score. PPLStudent and CPLStudent extend it and each sets its own required hours and CRI calculation. The ProgressTracker interface defines the calculateCRI() and getReadinessStatus() methods that both student types implement. SkyTrackApp contains the menu and all the user input handling.

Student has two versions of displayProgress(), one that prints a short summary line and one that prints the full profile.

Input validation

Everything the user types is checked and they are asked again if it is not valid. Names can only contain letters and spaces, ages must be between 1 and 120, contact numbers must be digits only, flight hours cannot be negative, and theory scores must be between 0 and 100. Typing letters at the menu does not crash the program. The setters in the Student class apply the same rules.

Running it

You need Java 8 or newer. From the folder that contains the skytrack package:

javac skytrack/*.java
java skytrack.SkyTrackApp

You can also open the project in an IDE like IntelliJ or Eclipse and run SkyTrackApp.java.

Notes

Nothing is saved to a file, so all students are lost when the program closes. Two students cannot be registered with the same name.

Things I would like to add

Saving students to a file or database, a graphical interface, and the ability to edit or delete students.

Author

Smangaliso Mathebula
