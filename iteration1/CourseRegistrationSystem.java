package iteration1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CourseRegistrationSystem {
    Scanner input;
    private int choice;

    public void start() {
        //Create courses
        Course course1 = new Course("courseName1", "CSE100", 3, (byte) 3);
        Course course2 = new Course("courseName2", "CSE101", 3, (byte) 5);
        Course course3 = new Course("courseName3", "CSE102", 3, (byte) 7);


        //Create Students
        Person student1 = new Student(1, "name1", "surname1", "CSE", "b", "b", (byte) 3);
        Person student2 = new Student(2, "name2", "surname2", "CSE", "username2", "password2", (byte) 3);
        Person student3 = new Student(3, "name3", "surname3", "CSE", "username3", "password3", (byte) 3);

        //Create advisors
        Person advisor1 = new Advisor(100, "advisorName1", "advisorSurname1", "CSE", "a", "a");
        Person advisor2 = new Advisor(101, "advisorName2", "advisorSurname2", "CSE", "advisorUsername2", "advisorPassword2");
        Person advisor3 = new Advisor(102, "advisorName3", "advisorSurname3", "CSE", "advisorUsername3", "advisorPassword3");


        //TODO create lecturers
//        Person lecturer1= new Lecturer(103,"lecturerName1","lecturerSurname1","CSE",);

        //Create Transcripts
        Transcript transcript1 = new Transcript();
        Transcript transcript2 = new Transcript();
        Transcript transcript3 = new Transcript();

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

        //Assign advisors
        ((Student) student1).setAdvisor((Advisor) advisor1);
        ((Student) student2).setAdvisor((Advisor) advisor2);
        ((Student) student3).setAdvisor((Advisor) advisor3);


        //Assign prerequisite courses
        course2.getPreRequisiteCourseCodes().add(course1.getCourseCode());
        course3.getPreRequisiteCourseCodes().add(course2.getCourseCode());


        //Create courseSessions
        Course courseSession1 = new CourseSession(course1, "CSE100.1", "Monday", 3);
        Course courseSession2 = new CourseSession(course1, "CSE100.1", "Tuesday", 4);
        Course courseSession3 = new CourseSession(course2, "CSE101.1", "Wednesday", 5);


        //add sessions to courses
        course1.getCourseSessions().add(courseSession1);
        course1.getCourseSessions().add(courseSession2);
        course2.getCourseSessions().add(courseSession3);

        Department department = new Department("CSE");

        student1.setDepartmentObject(department);
        advisor1.setDepartmentObject(department);

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

    private void readInputParameters() {

        //TODO Read JSON files and create objects
    }

    public void mainMenu(Department department) {
        Scanner input = new Scanner(System.in);


        System.out.println("\nWelcome to the Course Registration System");
        System.out.println("Please select from the following options:");
        System.out.println("0. Exit");
        System.out.println("1. Login Page");
        System.out.print("Enter your choice: ");

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
                    userTypeMenu(department);
                    loginMenu(department);
                    break;
                default:
                    System.out.println("Invalid choice Please select again");
                    mainMenu(department);
                    break;
            }
        }
    }


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

    private void loginMenu(Department department) {
        Person person;
        String userName, password;

        System.out.print("Please enter your username: ");
        userName = input.next();
        System.out.print("Enter your password: ");
        password = input.next();


        person = department.getUserNamePersonMap().get(userName);
        if (person != null) { //Check if there is such user
            String userTypeString = person.getClass().getSimpleName();
            switch (userTypeString) {
                case "Student":
                    if (((Student) person).login(userName, password)) {
                        studentMenu((Student) person);
                    }
                    break;
                case "Advisor":
                    if (((Advisor) person).login(userName, password)) {
                        advisorMenu((Advisor) person);
                    }
                    break;
            }
        } else {
            System.out.println("There is no such user, please try again\n");
            loginMenu(userType, department);
        }

        if (department.getUserNamePersonMap().get(userName) != null) { //Check if there is such user
            switch (userType) {
                case 1: //Student user type
                    person = department.getUserNamePersonMap().get(userName);
                    if (((Student) person).login(userName, password)) {
                        studentMenu((Student) person);
                    }
                    break;
                case 2: //Advisor user type
                    person = department.getUserNamePersonMap().get(userName);
                    if (((Advisor) person).login(userName, password)) {
                        advisorMenu((Advisor) person);
                    }
                    break;
                default:
                    System.out.println("Login failed, please try again\n");
                    loginMenu(userType, department);
            }
        } else {
            System.out.println("There is no such user, please try again\n");
            loginMenu(userType, department);
        }
    }


    private void studentMenu(Student student) {
        student.menu();
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
                    userTypeMenu(student.getDepartmentObject());
                    break;
                default:
                    System.out.println("Invalid choice Please select again");
                    studentMenu(student);
                    break;
            }
        }
    }

    private void courseSelectionMenu(Student student) {
        List<Course> courses = new ArrayList<>();  //TODO alınabilecek dersler student ta hesaplanıp return edilecek

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
                    courseStatusCheckMenu(student);
                    courseSelectionMenu(student);
                    break;
                case 2:
                    addCourseMenu(student);
                    courseSelectionMenu(student);
                    break;
                case 3:
                    dropCourseMenu(student);
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
                    userTypeMenu(student.getDepartmentObject());
                    break;
                default:
                    System.out.println("Invalid choice Please select again");
                    courseSelectionMenu(student);
                    break;
            }
        }
    }

    private void showRequestsStatusMenu(Student student) {
    }

    private void dropCourseMenu(Student student) {
        System.out.println("Çıkarmak istediğiniz dersi ya da dersleri seçiniz");
        //TODO Dersleri tek tek mi yoksa bütün olarak mı seçilecek?
        //TODO İşlem bitince courseSelectionMenu'ye dönücek.
    }

    private void addCourseMenu(Student student) {
        System.out.println("Add Course Menu");
        System.out.println("Available courses:");
        for (int i = 0; i < student.getAvailableCourses().size(); i++) {
            System.out.println((i + 1) + ". " + student.getAvailableCourses().get(i).getCourseCode() + " - " + student.getAvailableCourses().get(i).getCourseName());
        }
        System.out.print("Please select courses you want to add:");
        String choice = input.nextLine();
        String[] choices = choice.split(" ");
        ArrayList<Course> selectedCourses = new ArrayList<>();
        for (String s : choices) {
            selectedCourses.add(student.getAvailableCourses().get(Integer.parseInt(s) - 1));
        }
        student.addCourses(selectedCourses);
    }

    private void courseStatusCheckMenu(Student student) {
        /*TODO Başarılı dersler, başarısız dersler sunulacak*/
        /*CourseSelectionMenu'ye dönücek*/
    }

    private void advisorMenu(Advisor advisor) {
        advisor.menu();

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
                    userTypeMenu(advisor.getDepartmentObject());
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    private int getInput() {
        try {
            input = new Scanner(System.in);
            choice = input.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input, please enter a number");
            return -1;
        }
        return choice;
    }

    private void exitProgram() {
        // TODO Save all json files and exit
        System.exit(1);
    }

    //how to save a list of students as an array to a json flle
    private void saveJSON() {

    }
}
