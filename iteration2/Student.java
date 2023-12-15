package iteration2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//Inherits from the Person class and implements the ILogin interface
public class Student extends Person implements IDisplayMenu {

    //All attributes
    private Advisor advisor;
    private Transcript transcript;
    private byte semester;
    private boolean hasRequest;
    private Notification notification;
    private List<Course> draft;
    private List<Course> availableCoursesToAdd;
    private List<Course> availableCoursesToDrop;

    private Course[][] schedule;

    //Implement Constructor
    public Student(int studentID, String name, String surname, String userName, String password, byte semester) {
        super(studentID, name, surname, userName, password);
        this.draft = new ArrayList<>();
        this.availableCoursesToAdd = new ArrayList<>();
        this.availableCoursesToDrop = new ArrayList<>();
        this.semester = semester;
        this.schedule = new Course[5][8];
    }

    //Check if there is already a request awaiting approval
    //Check if the draft is empty
    /* Create a new Registration object and initialize it with the current
    student object (this) and draft information */
    public void sendRequest() {
        ConsoleColours.paintYellowMenu();
        if (hasRequest) {
            System.out.println("You already have a request waiting for approval.");
            ConsoleColours.resetColour();
            System.out.println();
        } else if (draft.isEmpty()) {
            System.out.println("Your draft is empty!");
            ConsoleColours.resetColour();
            System.out.println();
        } else {
            Registration registration = new Registration(this, draft);
            registration.addRequest(advisor);
        }
    }

    //Implements the printMenu method from the IDisplayMenu interface.
    //Prints a menu based on the specified menu type.
    /* After determining whether the user is a student or an advisor
    based on the String value, print the relevant menu. */
    @Override
    public void printMenu(String menuType) {
        switch (menuType) {
            case "studentMenu":
                ConsoleColours.paintBlueMenu();
                System.out.println("Welcome " + getName() + " " + getSurname() + "!");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                if (notification != null) {
                    ConsoleColours.paintYellowMenu();
                    System.out.println(notification.getMessage());
                    notification = null;
                    ConsoleColours.paintBlueMenu();
                }

                System.out.println("Please select from the following options:");
                System.out.println("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨");
                System.out.println("                         Exit System -> 0");
                System.out.println("               Course Selection Menu -> 1");
                System.out.println("                     View Transcript -> 2");
                System.out.println("                             Log out -> 3");
                System.out.println("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨");
                ConsoleColours.paintGreenMenu();
                System.out.println("Enter your choice: ");
                break;
            case "courseSelectionMenu":
                ConsoleColours.paintBlueMenu();
                System.out.println("Course Selection Menu");
                System.out.println("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨\n");
                System.out.println("Please select from the following options:");
                System.out.println("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨");
                System.out.println("                   Back to Main Menu -> 0");
                System.out.println("                 Course Status Check -> 1");
                System.out.println("                 Add Course to Draft -> 2");
                System.out.println("            Delete Course from Draft -> 3");
                System.out.println("                 Show request status -> 4");
                System.out.println("                        Send Request -> 5");
                System.out.println("                             Log out -> 6");
                System.out.println("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨");
                ConsoleColours.paintGreenMenu();
                System.out.println("Enter your choice: ");
                break;
            case "innerCourseSelectionMenu":
                ConsoleColours.paintBlueMenu();
                System.out.println("Course Selection Menu");
                System.out.println("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨\n");
                System.out.println("Please select from the following options:");
                System.out.println("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨");
                System.out.println("       Back to Course Selection Menu -> 0");
                System.out.println("                       Add Mandatory -> 1");
                System.out.println("              Add Technical Elective -> 2");
                System.out.println("          Add Non-Technical Elective -> 3");
                System.out.println("      Add Faculty Technical Elective -> 4");
                System.out.println("                         Drop Course -> 5");
                System.out.println("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨");
                ConsoleColours.paintGreenMenu();
                System.out.println("Enter your choice: ");
                break;
            case "addCourse":
                ConsoleColours.paintBlueMenu();
                System.out.println("Add Course Menu");
                System.out.println("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨\n");
                System.out.println("      Back -> 0");
                System.out.println("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨");
                System.out.println("Here is the available courses to drop:");
                System.out.println("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨");
                ConsoleColours.paintPurpleMenu();
                break;
            case "dropCourse":
                ConsoleColours.paintBlueMenu();
                System.out.println("Drop Course Menu");
                System.out.println("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨\n");
                System.out.println("       Back -> 0");
                System.out.println("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨");
                System.out.println("Here is the available courses to add:");
                System.out.println("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨");
                ConsoleColours.paintPurpleMenu();
                break;
        }
    }

    // The 'addCourse' function displays all accessible courses and adds these courses to
    // the student's draft, which is a private attribute.
    public void addCourseToDraft() {
        //If a request has been sent, terminate the function.
        if (hasRequest) {
            ConsoleColours.paintRedMenu();
            System.out.println("You can not add lecture because you have a request waiting for approval.");
            return;
        }
        //Call the viewAvailableCourses() function to see all available courses.
        ConsoleColours.paintBlueMenu();
        printMenu("innerCourseSelectionMenu");
        switch ((new CourseRegistrationSystem()).getInput()) {
            case 0:
                return;
            case 1:
                addMandatoryCourse();
                break;
            case 2:
                addTechnicalElective();
                break;
            case 3:
                addNonTechnicalElective();
                break;
            case 4:
                addFacultyTechnicalElective();
                break;
            case 5:
                addCourseToDrop();
                break;
            case -1:
                // If the user enters string or -1 give error.
                ConsoleColours.paintRedMenu();
                System.out.println("Please enter valid number!");
                break;
            default:
                ConsoleColours.paintRedMenu();
                System.out.println("Invalid input, please enter a valid number!");
        }
        addCourseToDraft();
    }

    private void addCourseToDrop() {
        computeAvailableCoursesToDrop();

        if (availableCoursesToDrop.isEmpty()) {
            ConsoleColours.paintRedMenu();
            System.out.println("You do not have any droppable course!");
        } else {
            this.printMenu("dropCourse");

            for (int i = 0; i < availableCoursesToDrop.size(); i++) {
                Course course = availableCoursesToDrop.get(i);
                System.out.println((i + 1) + ". " + course.getCourseCode() + " - " + course.getCourseName());
                System.out.println("´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´");
            }

            //Take input from the user and make assignments to the courses.
            ConsoleColours.paintGreenMenu();
            System.out.print("Choose number between 1 to " + availableCoursesToDrop.size() + " to add course: \n");
            int userNumberInput = (new CourseRegistrationSystem()).getInput();

            if (userNumberInput <= availableCoursesToDrop.size() && userNumberInput >= 1) {
                draft.add(availableCoursesToDrop.get(userNumberInput - 1));
                availableCoursesToDrop.remove(userNumberInput - 1);
                if (!availableCoursesToDrop.isEmpty())
                    addCourseToDrop();
            } else if (userNumberInput > availableCoursesToDrop.size() || userNumberInput < 0) {
                ConsoleColours.paintRedMenu();
                System.out.println("Invalid input, please enter a valid number");
                addCourseToDrop();
            }

        }
    }

    private void computeTheAvailableMandatoryCourses() {
        availableCoursesToAdd.clear();
        //Kapasite
        //If no capacity, has overlap, (student's semester is less than course's semester and student has CGPA that is
        //less than 3) or course is in draft; continue.
        for (Course course : this.getDepartment().getCourses()) {
            if (!course.getCourseType().equals("Mandatory")
                    || !course.hasCapacity()
                    || hasCourseOverlap(course)
                    || (semester < course.semester() && transcript.getCgpa() < 3)
                    || draft.contains(course)
                    || !checkThePrerequisiteAndCourseThatWasTaken(course)) {
                continue;
            } else {
                availableCoursesToAdd.add(course);
            }


        }
        //alttan ff fd dc dd durumu  -
        //Üstten ortalamanın 3.00 dan büyük olması -
        //TODO eğer labı varsa lablarından birini seçmek zorunda(mandatory)
        //öğrencinin semesterı büyük eşit olmalı
    }

    //TODO İsim bulunacak :D
    private boolean checkThePrerequisiteAndCourseThatWasTaken(Course course) {
        Map<Course, List<Grade>> mapGrade = transcript.getCourseGradeMap();
        //If student take the course for the first time, prerequisities is checked.
        if (!mapGrade.containsKey(course)) {
            boolean status = true;

            for (Course prerequisite : course.getPreRequisiteCourses()) {
                if ((mapGrade.get(prerequisite).getLast() == null
                        || mapGrade.get(prerequisite).getLast().getLetterGrade().equals("FF")
                        || mapGrade.get(prerequisite).getLast().getLetterGrade().equals("FD"))) {
                    return false;
                }
            }
            // If the student has  previously taken the course and has a grade bigger than 'CC',return false.
        } else if (!(mapGrade.get(course).getLast() != null && mapGrade.get(course).getLast().getLetterGrade().compareTo("CC") > 0)) {
            return false;
        }

        return true;
    }

    public boolean hasCourseOverlap(Course courseToAdd) {
        int i = 0, j = 0;
        switch (courseToAdd.getDay()) {
            case "Monday" -> i = 0;
            case "Tuesday" -> i = 1;
            case "Wednesday" -> i = 2;
            case "Thursday" -> i = 3;
            case "Friday" -> i = 4;
        }
        switch (courseToAdd.getHour()) {
            case 8 -> j = 0;
            case 9 -> j = 1;
            case 10 -> j = 2;
            case 11 -> j = 3;
            case 13 -> j = 4;
            case 14 -> j = 5;
            case 15 -> j = 6;
            case 16 -> j = 7;
        }


        //TODO check the overlap
        Course course1 = schedule[i][j];
        int sizeOfGradesList = transcript.getCourseGradeMap().get(course1).size();
        if (course1 == null) {
            return false;
        } else if (transcript.getCourseGradeMap().get(course1).getLast() == null) {
            if (sizeOfGradesList >= 2 &&
                    transcript.getCourseGradeMap().get(course1).get(sizeOfGradesList - 2).getLetterGrade().equals("DZ")) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    private boolean maxCoursesReached() {
        //Call the calculateNumberOfCourses() function to limit with the maximum number of courses.
        int numberOfCourses = calculateNumberOfCourses();
        if (numberOfCourses >= getDepartment().getMaxCourseNumber()) {
            ConsoleColours.paintRedMenu();
            System.out.println("Limit Reached! You can take at most "
                    + this.getDepartment().getMaxCourseNumber() + " courses.");
            return true;
        }
        return false;
    }

    private void addMandatoryCourse() {
        computeTheAvailableMandatoryCourses();
        if (maxCoursesReached()) {
            return;
        }
        //TODO Complete Add Operation

        if (availableCoursesToAdd.isEmpty()) {
            ConsoleColours.paintRedMenu();
            System.out.println("You do not have any addable course!");
        } else {
            this.printMenu("addCourse");

            for (int i = 0; i < availableCoursesToAdd.size(); i++) {
                Course course = availableCoursesToAdd.get(i);
                System.out.println((i + 1) + ". " + course.getCourseCode() + " - " + course.getCourseName());
                System.out.println("´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´");
            }

            //Take input from the user and make assignments to the courses.
            ConsoleColours.paintGreenMenu();
            System.out.print("Choose number between 1 to " + availableCoursesToAdd.size() + " to add course: \n");
            int userNumberInput1 = (new CourseRegistrationSystem()).getInput();

            if (userNumberInput1 <= availableCoursesToAdd.size() && userNumberInput1 >= 1) {
                draft.add(availableCoursesToAdd.get(userNumberInput1 - 1));
                availableCoursesToAdd.remove(userNumberInput1 - 1);
                if (!availableCoursesToAdd.isEmpty())
                    addMandatoryCourse();
            } else if (userNumberInput1 > availableCoursesToAdd.size() || userNumberInput1 < 0) {
                ConsoleColours.paintRedMenu();
                System.out.println("Invalid input, please enter a valid number");
                addMandatoryCourse();
            } else
                return;
        }
    }

    private void addFacultyTechnicalElective() {
        if (maxCoursesReached())
            return;

        this.printMenu("addCourse");
        //TODO IMPLEMENT A PRINTER LIKE A FOR LOOP
        /*
        Here is the simple example for you

            for (int i = 0; i < availableCoursesToDrop.size(); i++) {
                Course course = availableCoursesToDrop.get(i);
                System.out.println((i + 1) + ". " + course.getCourseCode() + " - " + course.getCourseName());
                System.out.println("´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´");
            }

         */
    }

    private void addNonTechnicalElective() {
        if (maxCoursesReached())
            return;

        this.printMenu("addCourse");
        //TODO IMPLEMENT A PRINTER LIKE A FOR LOOP
        /*
        Here is the simple example for you

            for (int i = 0; i < availableCoursesToDrop.size(); i++) {
                Course course = availableCoursesToDrop.get(i);
                System.out.println((i + 1) + ". " + course.getCourseCode() + " - " + course.getCourseName());
                System.out.println("´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´");
            }

         */
    }

    private void addTechnicalElective() {
        if (maxCoursesReached())
            return;

        this.printMenu("addCourse");
        //TODO IMPLEMENT A PRINTER LIKE A FOR LOOP
        /*
        Here is the simple example for you

            for (int i = 0; i < availableCoursesToDrop.size(); i++) {
                Course course = availableCoursesToDrop.get(i);
                System.out.println((i + 1) + ". " + course.getCourseCode() + " - " + course.getCourseName());
                System.out.println("´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´");
            }

         */
    }

    // This method calculates the total number of courses, considering both the drafted courses
    // and the courses in the transcript for which the student has not received a grade yet.
    public int calculateNumberOfCourses() {
        int numberOfCourses = 0;
        for (Course course : draft) {
            if (transcript.getCourseGradeMap().get(course) != null
                    && transcript.getCourseGradeMap().get(course).getLast() == null) {
                numberOfCourses++;
            }
        }

        for (Course course : transcript.getCourseGradeMap().keySet()) {
            if (transcript.getCourseGradeMap().get(course).getLast() == null) {
                numberOfCourses++;
            }
        }
        return numberOfCourses;
    }

    public void removeCourseFromDraft() {
        // Check if the student has already sent a request
        if (hasRequest) {
            ConsoleColours.paintRedMenu();
            System.out.println("You can not remove lecture because you have a request waiting for approval.");
            return;
        }
        // Check if the draft is empty.
        if (draft.isEmpty()) {
            ConsoleColours.paintYellowMenu();
            System.out.println("Your draft is empty!");
            ConsoleColours.resetColour();
            System.out.println();
        } else {
            ConsoleColours.paintBlueMenu();
            System.out.println("Remove Course from Draft");
            System.out.println("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨\n");
            System.out.println("               Back -> 0");
            System.out.println("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨");
            System.out.println("Select the course you want to remove:");
            System.out.println("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨");
            ConsoleColours.paintPurpleMenu();

            // Manipulation for displaying and removing courses in the menu.
            for (int i = 0; i < draft.size(); i++) {
                System.out.println((i + 1) + ". " + draft.get(i).getCourseCode() +
                        " - " + draft.get(i).getCourseName());
                System.out.println("´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´");
            }
            // Remove input index element -1 from the draft list if the input is valid and in the appropriate range.
            ConsoleColours.paintGreenMenu();
            System.out.print("Choose number between 1 to " + draft.size() + " to remove course: \n");

            int userNumberInput = (new CourseRegistrationSystem()).getInput();

            if (userNumberInput <= draft.size() && userNumberInput >= 1) {
                draft.remove(userNumberInput - 1);
                removeCourseFromDraft();
            } else if (userNumberInput > draft.size() || userNumberInput < 0) {
                ConsoleColours.paintRedMenu();
                System.out.println("Invalid input, please enter a number");
                removeCourseFromDraft();
            }
        }
    }

    //Checks if the student has a registration request waiting for advisor approval
    //and prints an appropriate message to the console.
    public void showRequestStatus() {
        ConsoleColours.paintYellowMenu();
        if (hasRequest) {
            System.out.println("Your request is waiting for advisor approval.");
        } else {
            System.out.println("There is no waiting request!");
            ConsoleColours.resetColour();
            System.out.println();
            return;
        }

        ConsoleColours.paintBlueMenu();
        System.out.println("Your draft: ");
        ConsoleColours.paintPurpleMenu();
        for (Course course : draft) {
            System.out.println(course.getCourseCode() + "-" + course.getCourseName());
        }
        ConsoleColours.resetColour();
        System.out.println();
    }

    //TODO Edit for loop
    private void computeAvailableCoursesToDrop() {
        availableCoursesToDrop.clear();
        Map<Course, List<Grade>> mapGrade = transcript.getCourseGradeMap();
        for (Course course : transcript.getStudentCourses()) {
            if (draft.contains(course))
                continue;
            if (mapGrade.get(course).getLast() == null) {
                availableCoursesToDrop.add(course);
            }
        }
    }

    /*
    public void computeAvailableCoursesToAdd() {

        ArrayList<Course> availableCoursestoAdd = new ArrayList<>();

        List<CourseSection> allCourseSections = getDepartment().getCourseSections();
        Map<Course, List<Grade>> mapGrade = transcript.getCourseGradeMap();
        Map<CourseSection, Course> courseSectionCourse = getDepartment().getSectionCourseMap();


        for (CourseSection courseSection : allCourseSections) {

            if (semester < courseSection.semester()
                    || draft.contains(courseSectionCourse.get(courseSection)))
                continue;


            Course course = courseSectionCourse.get(courseSection);

            if (!mapGrade.containsKey(course)) {
                boolean status = true;
                for (Course prerequisite : course.getPreRequisiteCourses()) {
                    if ((mapGrade.get(prerequisite) == null
                            || mapGrade.get(prerequisite).getLast().getLetterGrade().equals("FF")
                            || mapGrade.get(prerequisite).getLast().getLetterGrade().equals("FD"))) {
                        status = false;
                    }
                }
                if (status)
                    availableCoursestoAdd.add(course);
            } else {
                // If the student has previously taken the course and has a grade lower than 'CC', include it
                if (mapGrade.get(course) != null &&  mapGrade.get(course).getLast() !=null && mapGrade.get(course).getLast().getLetterGrade().compareTo("CC") > 0) {
                    availableCoursestoAdd.add(course);
                }
            }
        }
        availableCoursesToAdd = availableCoursestoAdd;
    }
*/

    //Checks if the provided username and password match the student's credentials.
    //Overrides the abstract login method in the Person class.
    @Override
    public boolean login(String userName, String password) {
        return getUserName().equals(userName) && getPassword().equals(password);
    }


    public String getDepartmentName() {
        return this.getDepartment().getDepartmentName();
    }

    public void setPassword(String password) {
        super.setPassword(password);
    }

    // Override the setUserName method to set the username by
    // calling the setUserName method from the inherited Person class.
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

    public byte getSemester() {
        return semester;
    }

    public void setHasRequest(boolean hasRequest) {
        this.hasRequest = hasRequest;
    }

    public List<Course> getDraft() {
        return draft;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    public void setDraft(List<Course> draft) {
        this.draft = draft;
    }

    public boolean isHasRequest() {
        return hasRequest;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public Course[][] getSchedule() {
        return schedule;
    }
}