
package iteration1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents a student who can log in, select courses, and send requests to advisors.json.
 * Inherits from the Person class and implements the ILogin interface.
 */
public class Student extends Person implements IDisplayMenu {

    private Advisor advisor;
    private Transcript transcript;
    private byte gradeLevel;
    private boolean hasRequest;
    private List<Course> draft;
    private List<Course> availableCourses;


    /**
     * Creates a new Student object with the given parameters.
     *
     * @param studentID  the student's ID
     * @param name       the student's name
     * @param surname    the student's surname
     * @param userName   the student's username
     * @param password   the student's password
     * @param gradeLevel the student's grade level
     */
    public Student(int studentID, String name, String surname, String userName, String password, byte gradeLevel) {
        super(studentID, name, surname, userName, password);
        this.draft = new ArrayList<>();
        this.availableCourses = new ArrayList<>();
        this.gradeLevel = gradeLevel;
    }


    public void sendRequest() {
        if (hasRequest) {
            System.out.println("\nYou already have a request waiting for approval");
        } else {
            Registration registration = new Registration(this, draft);
            registration.addRequest(advisor);
        }
    }


    @Override
    public void printMenu(String menuType) {
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
                System.out.println("0. Back");
                System.out.println("1. Course Status Check");
                System.out.println("2. Add Course");
                System.out.println("3. Drop Course");
                System.out.println("4. Send Request");
                System.out.println("5. Show request status");
                System.out.println("6. Log out");
                System.out.print("Enter your choice: ");
        }
    }


    public void addCourse() {
        System.out.println("\nAdd Course Menu");
        viewAvailableCourses();
        int numberOfCourses = calculateNumberOfCourses();

        if (numberOfCourses >= this.getDepartment().getMaxCourseNumber()) {
            System.out.println("You can not add more lectures.");
            return;
        }

        System.out.println("Here is the available courses:");
        System.out.println("0. Back");
        for (int i = 0; i < this.getAvailableCourses().size(); i++) {
            System.out.println((i + 1) + ". " + this.getAvailableCourses().get(i).getCourseCode() +
                    " - " + this.getAvailableCourses().get(i).getCourseName());
        }
        int controlGate = 1;
        while (controlGate == 1) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Choose number between 1 to " + this.getAvailableCourses().size() + " to add course: ");
            int userNumberInput = scanner.nextInt();
            //Back to Course Section Menu with return value
            if (userNumberInput == 0)
                return;
            if (1 <= userNumberInput && userNumberInput <= this.getAvailableCourses().size()) {
                controlGate = 0;
                this.draft.add(this.getAvailableCourses().get(userNumberInput - 1));
                this.getAvailableCourses().remove(userNumberInput - 1);
                addCourse();
            } else {
                System.out.println("Invalid input, please enter a valid number");
            }
        }
    }

    private int calculateNumberOfCourses() {
        int numberOfCourses = draft.size();
        for (Course course : this.getDepartment().getCourses()) {
            if (this.getTranscript().getCourseGradeMap().containsKey(course) && this.getTranscript().getCourseGradeMap().get(course) == null) {
                numberOfCourses++;
            }
        }
        return numberOfCourses;
    }

    /**
     * Displays the drop course menu and prompts the user to select the course(s) they want to drop.
     * <p>
     * The selected course(s) will be dropped from the user's course list.
     */
    public void dropCourse() {
        System.out.println("\nDrop Course Menu");
        System.out.println("Select the course you want drop");

        if (draft.isEmpty()) {
            System.out.println("Your draft is empty!");
        } else {
            System.out.println("0. Back");
            for (int i = 0; i < draft.size(); i++) {
                System.out.println((i + 1) + ". " + draft.get(i).getCourseCode() +
                        " - " + draft.get(i).getCourseName());
            }
            Scanner scanner = new Scanner(System.in);
            System.out.print("Choose number between 1 to " + this.draft.size() + " to drop course: ");
            int userNumberInput = scanner.nextInt();

            if (userNumberInput == 0)
                return;
            draft.remove(userNumberInput - 1);
            dropCourse();
        }
    }


    /**
     * This method prints the status of the request for advisor approval.
     * If there is a request waiting for approval, it displays "Your request is waiting for advisor approval".
     * Otherwise, it displays "There is no waiting request".
     */
    public void showRequestStatus() {
        if (hasRequest) {
            System.out.println("\nYour request is waiting for advisor approval");
        } else System.out.println("\nThere is no waiting request");
    }

    public List<Course> getAvailableCourses() {
        return availableCourses;
    }

    public void viewAvailableCourses() {
        ArrayList<Course> availableCourses = new ArrayList<>();
        List<CourseSection> allCourseSections = this.getDepartment().getCourseSections();
        Map<Course, Grade> mapGrade = this.getTranscript().getCourseGradeMap();
        Map<CourseSection, Course> courseSectionCourse = this.getDepartment().getSectionCourseMap();

        for (CourseSection courseSection : allCourseSections) {
            if (this.gradeLevel < courseSection.getGradeLevel() || draft.contains(courseSectionCourse.get(courseSection)))
                continue;
            Course course = courseSectionCourse.get(courseSection);
            if (!mapGrade.containsKey(course)) {
                boolean status = true;
                for (Course prerequisite : course.getPreRequisiteCourses()) {
                    if ((mapGrade.get(prerequisite).getLetterGrade().equals("FF") ||
                            mapGrade.get(prerequisite).getLetterGrade().equals("FD") ||
                            mapGrade.get(prerequisite) == null)) {
                        status = false;
                    }
                }
                if (status)
                    availableCourses.add(course);
            }
        }

        this.availableCourses = availableCourses;
    }


    @Override
    boolean login(String userName, String password) {
        return this.getUserName().equals(userName) && this.getPassword().equals(password);
    }

    public String getDepartmentName() {
        return this.getDepartment().getDepartmentName();
    }

    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }

    public byte getGradeLevel() {
        return gradeLevel;
    }

    public boolean isHasRequest() {
        return hasRequest;
    }

    public void setHasRequest(boolean hasRequest) {
        this.hasRequest = hasRequest;
    }


    public List<Course> getDraft() {
        return draft;
    }


    public Transcript getTranscript() {
        return transcript;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    public void setDraft(List<Course> draft) {
        this.draft = draft;
    }
}