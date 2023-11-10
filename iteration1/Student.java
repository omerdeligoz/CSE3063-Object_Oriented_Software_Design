package iteration1;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a student who can log in, select courses, and send requests to advisors.
 * Inherits from the Person class and implements the ILogin interface.
 */
public class Student extends Person implements ILogin {

    private Advisor advisor;
    private Transcript transcript;
    private byte semester;
    private boolean hasRequest;
    private ArrayList<Course> selectedCourses = new ArrayList<>();
    private ArrayList<Course> draft = new ArrayList<>();
    private ArrayList<Course> availableCourses = new ArrayList<>();
    private HashMap<Course, CourseSession> courseSessions = new HashMap<>();
    private HashMap<Course, String> successfulCourseGradeMap = new HashMap<>(); //


    //
    //No need
    public Student() {
        super();
    }
    //
    //

    public Student(int studentID, String name, String surname, String departmentName,
                   String userName, String password, byte semester) {
        super(studentID, name, surname, departmentName, userName, password);
        this.semester = semester;
    }

    protected ArrayList<Course> selectCourses() {
        System.out.println("Select Courses");
        //TODO
        //if send selected, create a registration object
        //        registration.sendRequest(advisor);

        return null;
    }

    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
    }


    public void sendRequest() {
        Registration registration = new Registration(this, draft);
        registration.sendRequest(advisor);
        hasRequest = true;
        System.out.println("Request sent to advisor");
        //TODO bazı kontroller yapabilir
    }


    @Override
    public void menu(String menuType) {
        switch (menuType) {
            case "studentMenu":
                System.out.println("\nStudent Menu");
                System.out.println("Please select from the following options:");
                System.out.println("0. Exit");
                System.out.println("1. Course Selection Menu");
                System.out.println("2. View Transcript");
                System.out.println("3. Log out");
                System.out.print("Enter your choice: ");
                break;
            case "courseSelectionMenu":
                System.out.println("\nCourse Selection Menu");
                System.out.println("Please select from the following options:");
                System.out.println("0. Exit");
                System.out.println("1. Course Status Check");
                System.out.println("2. Add Course");
                System.out.println("3. Drop Course");
                System.out.println("4. Send Request");
                System.out.println("5. Show request status");
                System.out.println("6. Log out");
                System.out.print("Enter your choice: ");

        }


    }

    public HashMap<Course, CourseSession> getCourseSessions() {
        return courseSessions;
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    public void showRequestStatus() {
        if (hasRequest) {
            System.out.println("Your request is waiting for advisor approval");
        } else System.out.println("There is no waiting request");
    }

    public ArrayList<Course> getAvailableCourses() {
        return availableCourses;
    }

    public void viewAvailableCourses() {
        System.out.println("Showing available courses... \n\n");
    }

    public void addCourses(ArrayList<Course> selectedCourses) {
        System.out.println("Adding courses to draft...");
        this.draft.addAll(selectedCourses);
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }

    public byte getSemester() {
        return semester;
    }

    public boolean isHasRequest() {
        return hasRequest;
    }

    public void setHasRequest(boolean hasRequest) {
        this.hasRequest = hasRequest;
    }

    public ArrayList<Course> getSelectedCourses() {
        return selectedCourses;
    }

    public ArrayList<Course> getDraft() {
        return draft;
    }

    public HashMap<Course, String> getSuccessfulCourseGradeMap() {
        return successfulCourseGradeMap;
    }
}
