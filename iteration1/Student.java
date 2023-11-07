package iteration1;

import java.util.ArrayList;
import java.util.HashMap;

public class Student extends Person implements ILogin {

    private Advisor advisor;
    private Transcript transcript;
    private byte semester;
    private boolean hasRequest;
    private ArrayList<Course> selectedCourses = new ArrayList<>();
    private ArrayList<Course> draft= new ArrayList<>();
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

    public Student(int studentID, String name, String surname, String department,
                   String userName, String password, byte semester) {
        super(studentID, name, surname, department, userName, password);
        this.semester = semester;
    }

    @Override
    public boolean login(String userName, String password) {
        return this.getUserName().equals(userName) && this.getPassword().equals(password);
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
    public void showMenu() {

    }

    public HashMap<Course, CourseSession> getCourseSessions() {
        return courseSessions;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }

    public Transcript getTranscript() {
        return transcript;
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

    public byte getSemester() {
        return semester;
    }

    public boolean isHasRequest() {
        return hasRequest;
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

    public void setHasRequest(boolean hasRequest) {
        this.hasRequest = hasRequest;
    }
}
