package iteration1;

import java.io.IOException;
import java.util.Scanner;


public class CourseRegistrationSimulation {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);


        Advisor advisor = new Advisor();

        Course course = new Course();

        //Read JSON files and create objects


        System.out.println("Welcome to the Course Registration System");
        System.out.println("Please select from the following options:");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
        int choice = 0;
        choice = input.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Login");
                System.out.println("Please select from the following options:");
                System.out.println("1. Advisor");
                System.out.println("2. Student");
                System.out.print("Enter your choice: ");
                choice = input.nextInt();
                switch (choice){
                    case 1:
                        System.out.println("Advisor");
                        advisorMenu();
                        break;
                    case 2:
                        System.out.println("Student");
                        Student student = new Student();
                        student.login();

                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
                break;
            case 2:
                System.out.println("Exit");
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    private static void studentMenu() {
    }

    private static void advisorMenu() {
        System.out.println("Advisor");
        System.out.println("Please select from the following options:");
        System.out.println("1. List requests");


    }
}
