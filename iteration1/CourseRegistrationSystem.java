package iteration1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * CourseRegistrationSystem is a class that implements the IDisplayMenu interface. It represents a course registration system
 * which allows students and advisors.json to login, view courses, select courses, drop courses, and send course registration requests.
 * The system also includes functionality for advisors.json to view and approve or reject registration requests.
 *
 * The CourseRegistrationSystem class has the following instance variables:
 * - input: A Scanner object used to read user input
 * - choice: An integer variable used to store user input choice
 *
 * The CourseRegistrationSystem class has the following methods:
 * - start(): A method that initializes the system by creating courses, students, advisors.json, transcripts, grades, course sessions,
 *   departments, and other necessary objects. It also calls the readInputParameters() method.
 * - readInputParameters(): A method that reads input parameters from JSON files and creates corresponding objects.
 * - mainMenu(Department department): A method that displays the main menu and handles user input.
 * - loginMenu(Department department): A method that handles the login process for students and advisors.json.
 * - studentMenu(Student student): A method that displays the student menu and handles student-specific operations.
 * - courseSelectionMenu(Student student): A method that displays the course selection menu and handles course-related operations for students.
 * - advisorMenu(Advisor advisor): A method that displays the advisor menu and handles advisor-specific operations.
 * - getInput(): A method that reads and returns an integer value from user input.
 * - exitProgram(): A method that saves data and exits the program.
 * - saveJSON(): A method that saves a list of students as an array to a JSON file.
 * - printMenu(String menuType): An overridden method from the IDisplayMenu interface that displays a menu based on the specified menuType.
 */
public class CourseRegistrationSystem implements IDisplayMenu {
    private Scanner input;
    private int choice;

    public void start() {
        //Create courses
        Course course1 = new Course("courseName1", "CSE100", 3, (byte) 3);
        Course course2 = new Course("courseName2", "CSE101", 3, (byte) 5);
        Course course3 = new Course("courseName3", "CSE102", 3, (byte) 7);


        //Create Students
        Person student1 = new Student(1, "name1", "surname1", "b", "b", (byte) 3);
        Person student2 = new Student(2, "name2", "surname2", "username2", "password2", (byte) 3);
        Person student3 = new Student(3, "name3", "surname3", "username3", "password3", (byte) 3);

        //Create advisors.json
        Person advisor1 = new Advisor(100, "advisorName1", "advisorSurname1", "a", "a");
        Person advisor2 = new Advisor(101, "advisorName2", "advisorSurname2", "advisorUsername2", "advisorPassword2");
        Person advisor3 = new Advisor(102, "advisorName3", "advisorSurname3", "advisorUsername3", "advisorPassword3");


        //TODO create lecturers
//        Person lecturer1= new Lecturer(103,"lecturerName1","lecturerSurname1","CSE",);

        //Create Transcripts
        Transcript transcript1 = new Transcript((Student) student1);
        Transcript transcript2 = new Transcript((Student) student2);
        Transcript transcript3 = new Transcript((Student) student3);


        //Set each student to a transcript
        transcript1.setStudent((Student) student1);
        transcript2.setStudent((Student) student2);
        transcript3.setStudent((Student) student3);


        //Set each transcript to a student
        ((Student) student1).setTranscript(transcript1);
        ((Student) student2).setTranscript(transcript2);
        ((Student) student3).setTranscript(transcript3);

        Grade grade1 = new Grade("AA");
        Grade grade2 = new Grade("BA");
        Grade grade3 = new Grade("CC");

        ((Student) student1).getTranscript().getCourseGradeMap().put(course1, grade1);
        ((Student) student1).getTranscript().getCourseGradeMap().put(course2, grade2);
        ((Student) student2).getTranscript().getCourseGradeMap().put(course1, grade3);

        //Assign advisors.json
        ((Student) student1).setAdvisor((Advisor) advisor1);
        ((Student) student2).setAdvisor((Advisor) advisor2);
        ((Student) student3).setAdvisor((Advisor) advisor3);


        //Assign prerequisite courses
        course2.getPreRequisiteCourses().add(course1);
        course3.getPreRequisiteCourses().add(course2);


        //Create courseSessions
        Course courseSession1 = new CourseSection(course1, "CSE100.1", "Monday", 3);
        Course courseSession2 = new CourseSection(course1, "CSE100.1", "Tuesday", 4);
        Course courseSession3 = new CourseSection(course2, "CSE101.1", "Wednesday", 5);


        //add sessions to courses
        course1.getCourseSessions().add(courseSession1);
        course1.getCourseSessions().add(courseSession2);
        course2.getCourseSessions().add(courseSession3);

        Department department = new Department("CSE");

        student1.setDepartment(department);
        student2.setDepartment(department);
        student3.setDepartment(department);
        advisor1.setDepartment(department);
        advisor2.setDepartment(department);
        advisor3.setDepartment(department);

        department.getCourses().add(course1);
        department.getCourses().add(course2);
        department.getCourses().add(course3);

        department.getAdvisors().add((Advisor) advisor1);
        department.getAdvisors().add((Advisor) advisor2);
        department.getAdvisors().add((Advisor) advisor3);

        department.getStudents().add((Student) student1);
        department.getStudents().add((Student) student2);
        department.getStudents().add((Student) student3);

        department.getTranscripts().add(transcript1);
        department.getTranscripts().add(transcript2);
        department.getTranscripts().add(transcript3);


        ((Student) student1).getAvailableCourses().add(course1);
        ((Student) student1).getAvailableCourses().add(course2);
        ((Student) student1).getAvailableCourses().add(course3);
        ((Student) student2).getAvailableCourses().add(course2);
        ((Student) student3).getAvailableCourses().add(course1);


        for (Student student : department.getStudents()) {
            department.getUserNamePersonMap().put(student.getUserName(), student);
        }
        for (Advisor advisor : department.getAdvisors()) {
            department.getUserNamePersonMap().put(advisor.getUserName(), advisor);
        }


        ((Advisor) advisor1).getStudentsAdvised().add((Student) student1);
        ((Advisor) advisor1).getStudentsAdvised().add((Student) student2);
        ((Advisor) advisor2).getStudentsAdvised().add((Student) student3);


        Registration request1 = new Registration((Student) student1, ((Student) student1).getDraft());
        Registration request2 = new Registration((Student) student2, ((Student) student2).getDraft());
        Registration request3 = new Registration((Student) student3, ((Student) student3).getDraft());


        ((Advisor) advisor1).getRequests().add(request1);
        ((Advisor) advisor1).getRequests().add(request2);
        ((Advisor) advisor2).getRequests().add(request3);


        readInputParameters();
        mainMenu(department);

        //Read JSON files and create objects

    }

    public void readInputParameters() {

        //TODO Read JSON files and create objects
    }


    public void mainMenu(Department department) {
        //Maybe first sentence should be displayed just once on the first running scene
        printMenu("mainMenu");

        choice = getInput();
        if (choice == -1) {
            mainMenu(department);
        } else {
            switch (choice) {
                case 0:
                    System.out.println("Exiting from system");
                    exitProgram();
                    break;
                case 1:
                    //May be more understandable naming
                    loginMenu(department);
                    break;
                default:
                    System.out.println("Invalid choice Please select again");
                    mainMenu(department);
                    break;
            }
        }
    }

    /*
        public void userTypeMenu(Department department) {
            //Maybe we should create just one scanner on top
            //Scanner input = new Scanner(System.in);

            System.out.println("\nPlease select user type:");
            //May be "0. Save & Exit"
            System.out.println("0. Exit");
            System.out.println("1. Student");
            System.out.println("2. Advisor");
            System.out.println("3. Back to main menu");
            System.out.print("Enter your choice: ");


            choice = getInput();
            if (choice == -1) {
                userTypeMenu(department);
            } else {


                switch (choice) {
                    case 0:
                        System.out.println("Exiting from system");
                        exitProgram();
                        break;
                    case 1:
                        System.out.println("\nStudent login page");
                        loginMenu(choice, department);
                        break;
                    case 2:
                        System.out.println("\nAdvisor login page");
                        loginMenu(choice, department);
                        break;
                    case 3:
                        mainMenu(department);
                        break;
                    default:
                        System.out.println("Invalid choice Please select again");
                        userTypeMenu(department);
                        break;
                }
            }
        }
    */
    public void loginMenu(Department department) {
        Person person;
        String userName, password;

        System.out.println("\nLogin Page");
        System.out.print("Please enter your username: ");
        userName = input.next();
        System.out.print("Enter your password: ");
        password = input.next();


        person = department.getUserNamePersonMap().get(userName);
        if (person != null) { //Check if there is such user
            if (person.login(userName, password)) {
                switch (person.getClass().getSimpleName()) {
                    case "Student":
                        studentMenu((Student) person);
                        break;
                    case "Advisor":
                        advisorMenu((Advisor) person);
                        break;
                }
            }
        }
    }


    public void studentMenu(Student student) {
        student.printMenu("studentMenu");
        choice = getInput();
        if (choice == -1) {
            studentMenu(student);
        } else {
            switch (choice) {
                case 0:
                    System.out.println("Exiting from system");
                    exitProgram();
                    break;
                case 1:
                    courseSelectionMenu(student);
                    break;
                case 2:
                    student.getTranscript().showTranscript();
                    studentMenu(student);
                    break;
                case 3:
                    loginMenu(student.getDepartment());
                    break;
                default:
                    System.out.println("Invalid choice Please select again");
                    studentMenu(student);
                    break;
            }
        }
    }

    public void courseSelectionMenu(Student student) {
        List<Course> courses = new ArrayList<>();  //TODO alınabilecek dersler student ta hesaplanıp return edilecek

        student.printMenu("courseSelectionMenu");

        choice = getInput();
        if (choice == -1) {
            courseSelectionMenu(student);
        } else {
            switch (choice) {
                case 0:
                    System.out.println("Exiting from system");
                    exitProgram();
                    break;
                case 1:
                    student.getTranscript().courseStatusCheck();
                    courseSelectionMenu(student);
                    break;
                case 2:
                    student.addCourse();
                    courseSelectionMenu(student);
                    break;
                case 3:
                    student.dropCourse();
                    courseSelectionMenu(student);
                    break;
                case 4:
                    student.sendRequest();
                    courseSelectionMenu(student);
                    break;
                case 5:
                    student.showRequestStatus();
                    courseSelectionMenu(student);
                    break;
                case 6:
                    loginMenu(student.getDepartment());
                    break;
                default:
                    System.out.println("Invalid choice Please select again");
                    courseSelectionMenu(student);
                    break;
            }
        }
    }

    public void advisorMenu(Advisor advisor) {
        advisor.printMenu("advisorMenu");

        choice = getInput();
        if (choice == -1) {
            advisorMenu(advisor);
        } else {
            switch (choice) {
                case 0:
                    System.out.println("Exiting from system");
                    exitProgram();
                    break;
                case 1:
                    advisor.listRequests();
                    advisorMenu(advisor);
                    break;
                case 2:
                    loginMenu(advisor.getDepartment());
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    public int getInput() {
        try {
            input = new Scanner(System.in);
            choice = input.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input, please enter a number");
            return -1;
        }
        return choice;
    }

    public void exitProgram() {
        // TODO Save all json files and exit
        input.close();
        System.exit(0);

    }

    //how to save a list of students as an array to a json flle
    public void saveJSON() {

    }


    @Override
    public void printMenu(String menuType) {
        System.out.println("\nWelcome to the Course Registration System");
        System.out.println("Please select from the following options:");
        System.out.println("0. Exit");
        System.out.println("1. Login Page");
        System.out.print("Enter your choice: ");
    }
}
