package iteration1;

import java.util.ArrayList;
import java.util.Scanner;


public class CourseRegistrationSystem {
    public void start() {
        readInputParameters();
        printMainMenu();

        //Read JSON files and create objects

    }

    private void readInputParameters() {
    }

    private void printMainMenu() {
        Scanner input = new Scanner(System.in);
        Advisor advisor = new Advisor();
        Course course = new Course();
        Student student = new Student();
        Transcript transcript = new Transcript();

        System.out.println("Welcome to the Course Registration System");
        System.out.println("Please select from the following options:");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
        int choice;
        choice = input.nextInt();
        boolean loggedIn = false;
        boolean correctChoice = false;

        while (!correctChoice) {
            switch (choice) {
                case 1:
                    System.out.println("\nPlease select user type:");
                    System.out.println("1. Student");
                    System.out.println("2. Advisor");
                    System.out.print("Enter your choice: ");
                    choice = input.nextInt();
                    String userName;
                    String password;
                    student.setUserName("a");
                    student.setPassword("a");

                    switch (choice) {
                        case 1:
                            while (!loggedIn) {
                                System.out.println("Student login page");
                                System.out.println("Please enter your username: ");
                                userName = input.next();
                                System.out.print("Enter your password: ");
                                password = input.next();
                                //TODO Girilen bilgilerin ahngi öğrenciye ait olduğu kontrol edilecek(HASH MAP kullanılabilir)

                                if (student.login(userName, password)) {
                                    studentMenu(student);
                                    loggedIn = true;
                                } else {
                                    System.out.println("Login failed");
                                }
                            }

                            break;
                        case 2:
                            while (!loggedIn) {
                                System.out.println("Advisor login page");
                                System.out.println("Please enter your username: ");
                                userName = input.next();
                                System.out.print("Enter your password: ");
                                password = input.next();
                                //TODO Girilen bilgilerin ahngi advisora ait olduğu kontrol edilecek(HASH MAP kullanılabilir)


                                if (advisor.login(userName, password)) {
                                    advisorMenu(advisor);
                                    loggedIn = true;
                                } else {
                                    System.out.println("Login failed");
                                }
                            }
                            break;
                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                case 2:
                    System.out.println("Selecet exit option");
                    System.out.println("1- Save and exit");
                    System.out.println("2- Exit without saving");
                    choice = input.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.println("Save and exit");
                            exitProgram(choice);
                            break;
                        case 2:
                            System.out.println("Exit without saving");
                            exitProgram(choice);
                            break;
                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    private void exitProgram(int choice) {
        if (choice == 1) {
            System.out.println("Save and exit"); //TODO Save JSON files
        } else {
            System.out.println("Exit without saving"); //TODO Don't save JSON files
        }
    }

    private static void studentMenu(Student student) {
        System.out.println("Student");
        System.out.println("Please select from the following options:");
        System.out.println("1. Select Courses");
        System.out.println("2. Log out");
        System.out.println("3. Return to Main Menu");
        System.out.print("Enter your choice: ");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                ArrayList<Course> courses = student.selectCourses(/**/) ;//Alınabilecek dersler.

                System.out.println("Selected Courses");
                courseSelectionMenu(student,courses);
                break;
            case 2:
                System.out.println("Log out");
                break;
            case 3:
                System.out.println("Return to Main Menu");
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    private static void courseSelectionMenu(Student student, ArrayList<Course> courses) {
        //TODO parametreden gelen course listesi ekrana yazdırılacak
        System.out.println("Course Selection");
        System.out.println("Please select from the following options:");
        System.out.println("1. Add Course");
        addCourseMenu(student,courses);
        System.out.println("2. COURSE STATUS CHECK");
        courseStatusCheckMenu(student);
        System.out.println("2. Drop Course");
        dropCourseMenu(student,courses);
        System.out.println("3. Show requests status");
        showRequestsStatusMenu(student);//Önceden oluşturulan requestlerin durumu gösterilecek.
        System.out.println("4. Return to Main Menu");
        studentMenu(student);
        System.out.print("Enter your choice: ");

    }

    private static void showRequestsStatusMenu(Student student) {
    }

    private static void dropCourseMenu(Student student, ArrayList<Course> courses) {
        System.out.println("Çıkarmak istediğiniz dersi ya da dersleri seçiniz");
        //TODO Dersleri tek tek mi yoksa bütün olarak mı seçilecek?
        //TODO İşlem bitince courseSelectionMenu'ye dönücek.
    }

    private static void addCourseMenu(Student student ,ArrayList<Course> courses) {
        System.out.println("Add Course");
        for(int i = 0; i < courses.size(); i++){
            System.out.println(i + ". " + courses.get(i).getCourseName());
        }
        System.out.println();/*TODO Alınabilecek dersler, listeler*/
        System.out.println("Please select from the following options:");
        //TODO Dersleri tek tek mi yoksa bütün olarak mı seçilecek?
        //TODO Seçilen dersler ArrayList'e eklenecek.
        //TODO ArrayList Advisor'a gönderilecek.
        //TODO Advisor.addrequest çağırılacak.
        //TODO İşlem bitince courseSelectionMenu'ye dönücek.
    }

    private static void courseStatusCheckMenu(Student student) {
        /*TODO Başarılı dersler, başarısız dersler sunulacak*/
        /*CourseSelectionMenu'ye dönücek*/
    }

    private static void advisorMenu(Advisor advisor) {
        System.out.println("Advisor");
        System.out.println("Please select from the following options:");
        System.out.println("1. List requests");
        System.out.println("2. Log out");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch (choice) {
            case 1:

                advisor.listRequests();
                System.out.println("List requests");
                break;
            case 2:
            default:
                System.out.println("Invalid choice");
                break;
        }
    }
}


/*

Course Management:
Methods for adding, removing, and updating course information.
Methods for listing available courses and course sections.

Student Management:
Methods for adding, removing, and updating student information.
Methods for student authentication and profile management.

Registration Management:
Methods for handling student registration, including checking eligibility and managing the registration process.
Methods for approving, waitlisting, and canceling registrations.

Advisor and Staff Interaction:
Methods for advisors and staff to access and manage student registrations and transcripts.
Communication with advisors and staff regarding registration requests.

Transcript Management:
Methods for generating student transcripts.

Reporting:
Generate reports on course enrollment, student progress, or other relevant metrics.

User Interface Integration:
If your system has a user interface, the CourseRegistrationSystem class can interact with it to display information and receive user input.

Initialization and Configuration:
Methods for setting up the system, loading initial data, and configuring system parameters.

Data Persistence:
Methods to interact with a database or data storage system to save and retrieve course, student, registration, and other data.

Business Logic:
Implement the core business logic of the course registration system, such as checking prerequisites, managing course capacities, and enforcing registration rules.

 */