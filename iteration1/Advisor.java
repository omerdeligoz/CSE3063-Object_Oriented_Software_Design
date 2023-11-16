package iteration1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Advisor extends Lecturer implements IDisplayMenu {

    private List<Student> studentsAdvised;
    private List<Registration> requestList;
    private int requestNumber;

    public Advisor(int ID, String name, String surname, String userName, String password) {
        super(ID, name, surname, userName, password);
        studentsAdvised = new ArrayList<>();
        requestList = new ArrayList<>();
    }

    public void printRequests() {
        System.out.println("List of Requests:");
        System.out.println("0. Back");
        if (requestList.size() >= 5) {
            for (int i = 0; i < 5; i++) {
                System.out.println((i + 1) + ". " + requestList.get(i).getStudent().getName() + " " + requestList.get(i).getStudent().getSurname());
            }
        } else {
            for (int i = 0; i < requestList.size(); i++) {
                System.out.println((i + 1) + ". " + requestList.get(i).getStudent().getName() + " " + requestList.get(i).getStudent().getSurname());
            }
        }
        //Advisor makes selection
        System.out.print("Enter the request number you want to evaluate: ");
        Scanner scanner = new Scanner(System.in);
        requestNumber = scanner.nextInt();

        if (requestNumber == 0) {
            return;

        } else if (requestNumber > requestList.size()) {
            System.out.println("Invalid choice Please select again");
            printRequests();
        } else {
            //seçilen öğrencinin requestini göster
            System.out.println(requestList.get(requestNumber - 1).getStudent().getName() + " " + requestList.get(requestNumber - 1).getStudent().getSurname() + " wants to take these courses:");
            for (Course course : requestList.get(requestNumber - 1).getStudent().getDraft()) {
                System.out.println(course.getCourseCode() + " " + course.getCourseName());
            }
            replyRequests();
        }
    }

    public void replyRequests() {
        System.out.println("1. Approve Request");
        System.out.println("2. Reject Request");
        System.out.println("3. Back");
        System.out.println("Please select an option: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                requestList.get(requestNumber - 1).approveRequest();
                requestList.remove(requestNumber - 1);
                printRequests();
                break;
            case 2:
                requestList.get(requestNumber - 1).rejectRequest();
                requestList.remove(requestNumber - 1);
                printRequests();
                break;
            case 3:
                printRequests();
                break;
            default:
                System.out.println("Invalid choice Please select again");
                replyRequests();
                break;
        }
    }

    @Override
    public void printMenu(String menuType) {
        System.out.println("\nAdvisor Menu");
        System.out.println("Welcome " + this.getName() + " " + this.getSurname() + "!");
        System.out.println("Please select from the following options:");
        System.out.println("0. Exit");
        System.out.println("1. List requests");
        System.out.println("2. Log out");
        System.out.print("Enter your choice: ");
    }

    @Override
    boolean login(String userName, String password) {
        return this.getUserName().equals(userName) && this.getPassword().equals(password);
    }

    public List<Student> getStudentsAdvised() {
        return studentsAdvised;
    }

    public List<Registration> getRequests() {
        return requestList;
    }

    public List<Registration> getRequestList() {
        return requestList;
    }
}
